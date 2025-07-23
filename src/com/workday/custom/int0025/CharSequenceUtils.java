package com.workday.custom.int0025;

/**
 * Reproduced in whole/part under Apache license from:
 * https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/CharSequenceUtils.java
 * 
 * Recreation of the source performed to mitigate proprietary library deployment and classpath configurations
 * 
 * @author john.smail
 *
 */
public class CharSequenceUtils {
	/**
	*
	* @param cs the {@code CharSequence} to be processed
	* @param ignoreCase whether or not to be case insensitive
	* @param thisStart the index to start on the {@code cs} CharSequence
	* @param substring the {@code CharSequence} to be looked for
	* @param start the index to start on the {@code substring} CharSequence
	* @param length character length of the region
	* @return whether the region matched
	*/
	static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart, final CharSequence substring, final int start, final int length) {
		if (cs instanceof String && substring instanceof String) {
			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
		}
		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		// Extract these first so we detect NPEs the same as the java.lang.String version
		final int srcLen = cs.length() - thisStart;
		final int otherLen = substring.length() - start;

		// Check for invalid parameters
		if (thisStart < 0 || start < 0 || length < 0) {
			return false;
		}

		// Check that the regions are long enough
		if (srcLen < length || otherLen < length) {
			return false;
		}

		while (tmpLen-- > 0) {
			final char c1 = cs.charAt(index1++);
			final char c2 = substring.charAt(index2++);

			if (c1 == c2) {
				continue;
			}

			if (!ignoreCase) {
				return false;
			}

			// The real same check as in String.regionMatches():
			final char u1 = Character.toUpperCase(c1);
			final char u2 = Character.toUpperCase(c2);
			if (u1 != u2 && Character.toLowerCase(u1) != Character.toLowerCase(u2)) {
				return false;
			}
		}

		return true;
	}
}
