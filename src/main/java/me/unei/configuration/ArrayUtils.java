package me.unei.configuration;

public final class ArrayUtils
{
	public static final long[] EMPTY_LONG = new long[0];
	public static final int[] EMPTY_INT = new int[0];
	public static final byte[] EMPTY_BYTE = new byte[0];
	
	public static long[] toPrimitive(final Long[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_LONG;
		}
		final long[] result = new long[array.length];
		for (int i = 0; i < array.length; ++i) {
			result[i] = array[i].longValue();
		}
		return result;
	}
	
	public static int[] toPrimitive(final Integer[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_INT;
		}
		final int[] result = new int[array.length];
		for (int i = 0; i < array.length; ++i) {
			result[i] = array[i].intValue();
		}
		return result;
	}
	
	public static byte[] toPrimitive(final Byte[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_BYTE;
		}
		final byte[] result = new byte[array.length];
		for (int i = 0; i < array.length; ++i) {
			result[i] = array[i].byteValue();
		}
		return result;
	}
	
	private ArrayUtils() { }
}