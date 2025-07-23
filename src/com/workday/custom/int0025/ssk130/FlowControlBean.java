package com.workday.custom.int0025.ssk130;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.capeclear.mediation.MediationContext;
import com.workday.custom.int0025.MediationConstants;
import com.workday.custom.int0025.ssk109.LocalOutHelper;

public class FlowControlBean {
	private static Map<String, Map<String, Object>> invocationProperties;
	private static Stack<String> invocationUniqueIds;
	private static final BigDecimal INCREMENT_BY_1 = new BigDecimal(1);
	
	static {
		invocationProperties = new HashMap<String, Map<String, Object>>();
		invocationUniqueIds = new Stack<String>();
	}
	
	private FlowControlBean() {}
	
	public static void initializeLoop(MediationContext ctx) {
		String uniqueId = (String)ctx.getProperty(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_UNIQUE_ID);
		
		invocationUniqueIds.push(uniqueId);
		
		Map<String, Object> localInvocationMap = new HashMap<String, Object>();
		localInvocationMap.put(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START, new BigDecimal(String.valueOf(ctx.getProperty(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START))).setScale(1, RoundingMode.HALF_UP));
		localInvocationMap.put(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END, new BigDecimal(String.valueOf(ctx.getProperty(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END))).setScale(1, RoundingMode.HALF_UP));
		localInvocationMap.put(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD, new BigDecimal(String.valueOf(ctx.getProperty(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD))).setScale(1, RoundingMode.HALF_UP));
		
		invocationProperties.put(uniqueId, localInvocationMap);
	}
	
	public static void deregisterLoop(MediationContext context) {
		String uniqueId = invocationUniqueIds.pop();		
		invocationProperties.remove(uniqueId);
	}
	
	public static boolean isUniqueKeyRegistered(String key) {
		return invocationUniqueIds.isEmpty() ? false : getMap().containsKey(key);
	}
	
	private static Map<String, Object> getMap() {
		return invocationProperties.get(invocationUniqueIds.peek());
	}
	
	public static void configureLoop(MediationContext ctx, String totalRecords) {
		Map<String, Object> localMap = getMap();
		
		BigDecimal total = new BigDecimal(totalRecords).setScale(1, RoundingMode.HALF_UP);
		
		if (total.compareTo(new BigDecimal(0)) > 0) {
			BigDecimal start = (BigDecimal)localMap.get(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START);
			BigDecimal end = (BigDecimal)localMap.get(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END);

			localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL, total);
			localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_COUNTER, new BigDecimal(0));
			localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_UPDATE_COUNTER, new BigDecimal(1));

			localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR, start);
			localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM, end.subtract(start).divide(total, 1, RoundingMode.HALF_UP));
		}
	}
	
	public static void recalculateProgressUpdateThreshold(String uniqueKey) {
		recalculateProgressUpdateThreshold(invocationProperties.get(uniqueKey));
	}
	
	public static void recalculateProgressUpdateThreshold() {
		recalculateProgressUpdateThreshold(getMap());
	}
	
	private static void recalculateProgressUpdateThreshold(Map<String, Object> localMap) {
		BigDecimal start = (BigDecimal)localMap.get(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START);
		BigDecimal updateCounter = (BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_UPDATE_COUNTER);
		BigDecimal incrementThreshold = (BigDecimal)localMap.get(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD);
		
		BigDecimal nextThreshold = incrementThreshold.multiply(updateCounter).add(start).setScale(1, RoundingMode.HALF_UP);
		
		localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD, nextThreshold);
	}
	
	public static void startLoopDetectBugWorkaround(MediationContext ctx) {
		Map<String, Object> localMap = getMap();
		localMap.put(MediationConstants.PROP_LOCAL_LOOPDETECT_WORKAROUND, LocalOutHelper.SnapshotLoopDetection(ctx));
	}
	
	@SuppressWarnings("unchecked")
	public static void stopLoopDetectBugWorkaround(MediationContext ctx) {
		Map<String, Object> localMap = getMap();
		
		LocalOutHelper.ClearLoopDetectionFlags(ctx, (Set<String>)localMap.get(MediationConstants.PROP_LOCAL_LOOPDETECT_WORKAROUND));
		localMap.remove(MediationConstants.PROP_LOCAL_LOOPDETECT_WORKAROUND);
	}
	
	public static void incrementItemCounter() {
		Map<String, Object> localMap = getMap();

		localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_COUNTER, ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_COUNTER)).add(INCREMENT_BY_1));
	}
	
	public static void incrementPercentAccumulator() {
		Map<String, Object> localMap = getMap();
		BigDecimal percentPerItem = (BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM);

		localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR, ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR)).add(percentPerItem));
	}
	
	public static void incrementUpdateCounter() {
		Map<String, Object> localMap = getMap();

		localMap.put(MediationConstants.PROP_LOCAL_FLOWCONTROL_UPDATE_COUNTER, ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_UPDATE_COUNTER)).add(INCREMENT_BY_1));
	}
	
	public static String getCurrentItemNumber() {
		Map<String, Object> localMap = getMap();

		return ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_COUNTER)).toPlainString();
	}
	
	public static String getCurrentProgressPercent() {
		Map<String, Object> localMap = getMap();
		return ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR)).toPlainString();
	}
	
	public static String getNextProgressPercent() {
		Map<String, Object> localMap = getMap();
		return ((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR)).add((BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD)).toPlainString();
	}
	
	public static boolean isUpdateProgress() {
		Map<String, Object> localMap = getMap();

		BigDecimal nextThreshold = (BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD);
		BigDecimal accumulator = (BigDecimal)localMap.get(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_ACCUMULATOR);

		return (accumulator.compareTo(nextThreshold) >= 0);
	}
	
	public static String getValue(String uniqueKey, String propertyKey) {
		return getValue(invocationProperties.get(uniqueKey), propertyKey);
	}
	
	public static String getValue(String propertyKey) {
		return getValue(getMap(), propertyKey);
	}

	private static String getValue(Map<String, Object> localMap, String propertyKey) {
		Object value = localMap.get(propertyKey);
		if (value instanceof BigDecimal) {
			return ((BigDecimal)value).toPlainString();
		} else if (value == null) {
			return null;
		} else {
			return String.valueOf(value);
		}
	}
}
