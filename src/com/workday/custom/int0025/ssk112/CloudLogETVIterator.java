package com.workday.custom.int0025.ssk112;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.workday.mediation.impl.mediators.etv.ETVInfo;
import com.workday.mediation.impl.mediators.etv.ETVInfoCollection;
import com.workday.mediation.impl.mediators.etv.ETVInfoCollection.Target;

/**
 * An iterator over ETV messages
 * @author doug.lee
 *
 */
public class CloudLogETVIterator implements Iterator<CloudLogAdapter> {
	
	/**
	 * The minimum severity level that will be output.  By default all information will be output
	 */
	ETVInfo.MessageSeverity	minSeverity;
	ETVInfoCollection etvCollection;
	Iterator<ETVInfo> etvIterator;
	ETVInfo	next;
	List<CloudLogAdapter> messageTargetCache;

	private CloudLogETVIterator(ETVInfoCollection etvMessages, ETVInfo.MessageSeverity minimumSeverity) {
		//
		// Prepare a temporary cache for expanding single ETVInfo objects to their multiple affected targets so we have a message per target
		//
		messageTargetCache = new ArrayList<CloudLogAdapter>();

		etvCollection = etvMessages;
		minSeverity = minimumSeverity;
		//
		// Get an iterator over all messages
		//
		etvIterator = etvMessages.iterator(true);
		//
		// Find the first ETVInfo message which meets our minimum severity criteria
		//
		moveToNext();
	}
	
	public static Iterator<CloudLogAdapter> getIterator(ETVInfoCollection etvMessages, String minimumLevel) throws IllegalArgumentException {
		return new CloudLogETVIterator(etvMessages, ETVInfo.MessageSeverity.valueOf(minimumLevel.trim().toUpperCase()));
	}
	
	public static Iterator<CloudLogAdapter> getIterator(ETVInfoCollection etvMessages) throws IllegalArgumentException {
		return new CloudLogETVIterator(etvMessages, ETVInfo.MessageSeverity.INFO);
	}
	
	private void moveToNext() {
		do {
			next = etvIterator.hasNext() ? etvIterator.next() : null;

		} while ((next != null) && (next.getSeverity().compareTo(minSeverity) > 0));
	}
	
	//
	// Interface Iterator<CloudLogAdaptor>
	//

	@Override
	public boolean hasNext() {
		return !messageTargetCache.isEmpty() || next != null;
	}

	/**
	 * Obtain the a CloudLogAdapter instance wrapping the next message
	 * 
	 * @throws NoSuchElementException if no messages are available
	 */
	
	@Override
	public CloudLogAdapter next() {
		if (next == null && messageTargetCache.isEmpty()) {
			throw new NoSuchElementException("No more ETV messages are available");
		}
		
		CloudLogAdapter nextValue = null;
		
		if (!messageTargetCache.isEmpty()) {
			nextValue = messageTargetCache.remove(0);
			//do not advance "next" in the main iterator - that was already done when fetching the ETVInfo that originally populated messageTargetCache
			//on the subsequent call to next(), the ETVInfo that has been sitting awaiting consumption while messageTargetCache is drained will be returned and the iterator advanced
		} else {
			Set<Target> targets = etvCollection.getTargets(next);
			
			//if the targets collection is null or an empty hashset, or if it only has one entry, in which case the ETVInfo object has the target details and the cache mechanism is unneeded overhead...
			if (CollectionUtils.isEmpty(targets) || targets.size() == 1) {
				nextValue = new CloudLogAdapter() {
					ETVInfo info = next;
					
					public CloudLogLevel getLevel() {
						return LevelMap.get(info.getSeverity() );
					}
					
					public String getMessage() {
						return info.getSummary();
					}
					
					public String getReferenceId() {
						String refID = info.getTargetID();
						return (refID != null) ? refID : "";
					}
				};
			} else {
				for (Target t : targets) {
					messageTargetCache.add(new CloudLogAdapter() {
						ETVInfo info = next;
						Target target = t;
						
						public CloudLogLevel getLevel() {
							return LevelMap.get(info.getSeverity() );
						}
						
						public String getMessage() {
							return info.getSummary();
						}
						
						public String getReferenceId() {
							String refID = target.getID();
							return (refID != null) ? refID : "";
						}
					});
				}
				
				nextValue = messageTargetCache.remove(0);
			}
			
			moveToNext();
		}

		return nextValue;
	}
	
	//
	// Provide a map from ETV severity to cloud-log severity
	//
	static Map<ETVInfo.MessageSeverity, CloudLogLevel>	LevelMap;
	
	static {
		LevelMap = new HashMap<ETVInfo.MessageSeverity, CloudLogLevel>();
		LevelMap.put(ETVInfo.MessageSeverity.INFO, CloudLogLevel.info);
		LevelMap.put(ETVInfo.MessageSeverity.WARNING, CloudLogLevel.warn);
		LevelMap.put(ETVInfo.MessageSeverity.ERROR, CloudLogLevel.error);
		LevelMap.put(ETVInfo.MessageSeverity.CRITICAL, CloudLogLevel.critical);
	}
}
