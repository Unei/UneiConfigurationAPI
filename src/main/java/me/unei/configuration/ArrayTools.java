package me.unei.configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class ArrayTools {
	
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
	private static final short[] EMPTY_SHORT_ARRAY = new short[0];
	private static final int[] EMPTY_INT_ARRAY = new int[0];
	private static final long[] EMPTY_LONG_ARRAY = new long[0];

	public static Class<?> getIterableParam(Iterable<?> iterable) {
		Class<?> result = null;
		if (iterable == null) {
			return null;
		}
		Iterator<?> it = iterable.iterator();
		if (it.hasNext()) {
			Object o = it.next();
			result = (o != null) ? o.getClass() : null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Iterable<T> value, Class<T> clz) {
		if (value == null) {
			return null;
		}
		Collection<T> listed;
		if (value instanceof Collection) {
			listed = (Collection<T>) value;
		} else {
			listed = new ArrayList<>();
			Iterator<T> it = value.iterator();
			while (it.hasNext()) {
				listed.add(it.next());
			}
		}
		T[] result = (T[]) Array.newInstance(clz, listed.size());
		return listed.toArray(result);
	}

	public static byte[] toPrimitive(Byte[] bytes) {
		if (bytes == null || bytes.length <= 0) {
			return EMPTY_BYTE_ARRAY;
		}
		byte[] result = new byte[bytes.length];
		for (int i = 0; i < bytes.length; ++i) {
			result[i] = bytes[i].byteValue();
		}
		return result;
	}

	public static long[] toPrimitive(Long[] longs) {
		if (longs == null || longs.length <= 0) {
			return EMPTY_LONG_ARRAY;
		}
		long[] result = new long[longs.length];
		for (int i = 0; i < longs.length; ++i) {
			result[i] = longs[i].longValue();
		}
		return result;
	}

	public static int[] toPrimitive(Integer[] ints) {
		if (ints == null || ints.length <= 0) {
			return EMPTY_INT_ARRAY;
		}
		int[] result = new int[ints.length];
		for (int i = 0; i < ints.length; ++i) {
			result[i] = ints[i].intValue();
		}
		return result;
	}

	public static short[] toPrimitive(Short[] shorts) {
		if (shorts == null || shorts.length <= 0) {
			return EMPTY_SHORT_ARRAY;
		}
		short[] result = new short[shorts.length];
		for (int i = 0; i < shorts.length; ++i) {
			result[i] = shorts[i].shortValue();
		}
		return result;
	}

	public static byte[] toBytes(Object iterable)
	{
		if (iterable == null || !(iterable instanceof Iterable)) {
			return EMPTY_BYTE_ARRAY;
		}
		int length;
		if (iterable instanceof Collection) {
			length = ((Collection<?>) iterable).size();
		} else {
			int cnt = 0;
			Iterator<?> it = ((Iterable<?>) iterable).iterator();
			while (it.hasNext()) {
				cnt += 1;
			}
			length = cnt;
		}
		byte[] result = new byte[length];
		int i = 0;
		for (Iterator<?> it = ((Iterable<?>) iterable).iterator(); it.hasNext(); ++i) {
			result[i] = ((Number) it.next()).byteValue();
		}
		return result;
	}

	public static long[] toLongs(Object iterable)
	{
		if (iterable == null || !(iterable instanceof Iterable)) {
			return EMPTY_LONG_ARRAY;
		}
		int length;
		if (iterable instanceof Collection) {
			length = ((Collection<?>) iterable).size();
		} else {
			int cnt = 0;
			Iterator<?> it = ((Iterable<?>) iterable).iterator();
			while (it.hasNext()) {
				cnt += 1;
			}
			length = cnt;
		}
		long[] result = new long[length];
		int i = 0;
		for (Iterator<?> it = ((Iterable<?>) iterable).iterator(); it.hasNext(); ++i) {
			result[i] = ((Number) it.next()).longValue();
		}
		return result;
	}

	public static int[] toInts(Object iterable)
	{
		if (iterable == null || !(iterable instanceof Iterable)) {
			return EMPTY_INT_ARRAY;
		}
		int length;
		if (iterable instanceof Collection) {
			length = ((Collection<?>) iterable).size();
		} else {
			int cnt = 0;
			Iterator<?> it = ((Iterable<?>) iterable).iterator();
			while (it.hasNext()) {
				cnt += 1;
			}
			length = cnt;
		}
		int[] result = new int[length];
		int i = 0;
		for (Iterator<?> it = ((Iterable<?>) iterable).iterator(); it.hasNext(); ++i) {
			result[i] = ((Number) it.next()).intValue();
		}
		return result;
	}

	private ArrayTools() throws IllegalAccessException {
		throw new IllegalAccessException("Cannot instanciate a static class");
	}
}