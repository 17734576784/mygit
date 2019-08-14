package com.wo.comnt;

public class ComntFunc {
	public static byte[] string2Byte(String str) {
		byte[] tmpval = null;
		try {
			tmpval = str.getBytes("GBK");
		} catch (Exception exp) {
			System.out.println("无法使用的编码方式<GBK>.");
		}

		byte[] retval = null;
		if (tmpval != null) {
			retval = new byte[tmpval.length + 1];

			for (int i = 0; i < tmpval.length; i++) {
				retval[i] = tmpval[i];
			}

			retval[tmpval.length] = 0;
		} else {
			retval = new byte[1];
			retval[0] = 0;
		}

        return retval;
    }

	public static int string2Byte(String src_str, byte[] dest_str) {
		if (dest_str == null || dest_str.length <= 0)
			return 0;

		byte[] tmp_bytes = string2Byte(src_str);

		int copy_num = Math.min(dest_str.length, tmp_bytes.length);

		for (int i = 0; i < copy_num; i++) {
			dest_str[i] = tmp_bytes[i];
		}

		if (dest_str.length < tmp_bytes.length)
			dest_str[dest_str.length - 1] = 0;

		return copy_num;
	}
    
	public static String byte2String(byte[] str) {
		if (str == null || str.length <= 0)
			return "";

		int data_len = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 0)
				break;
			data_len++;
		}

		String retval = null;
		try {
			retval = new String(str, 0, data_len, "GBK");
		} catch (Exception exp) {
			System.out.println("无法使用的编码方式<GBK>.");
		}

		return retval;
	}
    
	public static String printProtalData(byte[] prot_data) {
		if (prot_data == null || prot_data.length <= 0)
			return "";

		StringBuffer str_buf = new StringBuffer();

		for (int i = 0; i < prot_data.length; i++) {
			if (i > 0)
				str_buf.append(" ");
			str_buf.append(String.format("%02X", prot_data[i]));
		}

		return str_buf.toString();
	}
    
    //byte-arraycopy
	public static void arrayCopy(byte[] dest_array, byte[] src_array) {
		int copy_num = Math.min(src_array.length, dest_array.length);
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(byte[] dest_array, byte[] src_array,
			int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(byte[] dest_array, int dest_idx,
			byte[] src_array, int src_idx, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[dest_idx + i] = src_array[src_idx + i];
		}
	}
    
	// short-arraycopy
	public static void arrayCopy(short[] dest_array, short[] src_array) {
		int copy_num = Math.min(src_array.length, dest_array.length);
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(short[] dest_array, short[] src_array,
			int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(short[] dest_array, int dest_idx,
			short[] src_array, int src_idx, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[dest_idx + i] = src_array[src_idx + i];
		}
	}
    
	// int-arraycopy
	public static void arrayCopy(int[] dest_array, int[] src_array) {
		int copy_num = Math.min(src_array.length, dest_array.length);
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(int[] dest_array, int[] src_array, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(int[] dest_array, int dest_idx,
			int[] src_array, int src_idx, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[dest_idx + i] = src_array[src_idx + i];
		}
	}
    
	// float-arraycopy
	public static void arrayCopy(float[] dest_array, float[] src_array) {
		int copy_num = Math.min(src_array.length, dest_array.length);
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(float[] dest_array, float[] src_array,
			int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(float[] dest_array, int dest_idx,
			float[] src_array, int src_idx, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[dest_idx + i] = src_array[src_idx + i];
		}
	}
    
	// double-arraycopy
	public static void arrayCopy(double[] dest_array, double[] src_array) {
		int copy_num = Math.min(src_array.length, dest_array.length);
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(double[] dest_array, double[] src_array,
			int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[i] = src_array[i];
		}
	}
    
	public static void arrayCopy(double[] dest_array, int dest_idx,
			double[] src_array, int src_idx, int copy_num) {
		for (int i = 0; i < copy_num; i++) {
			dest_array[dest_idx + i] = src_array[src_idx + i];
		}
	}
    
	// byte-arraySet
	public static void arraySet(byte[] array, byte val) {
		for (int i = 0; i < array.length; i++)
			array[i] = val;
	}
    
	public static void arraySet(byte[] array, int idx, int len, byte val) {
		for (int i = idx; i < len; i++)
			array[i] = val;
	}
    
	// short-arraySet
	public static void arraySet(short[] array, short val) {
		for (int i = 0; i < array.length; i++)
			array[i] = val;
	}

	public static void arraySet(short[] array, int idx, int len, short val) {
		for (int i = idx; i < len; i++)
			array[i] = val;
	}
    
	// int-arraySet
	public static void arraySet(int[] array, int val) {
		for (int i = 0; i < array.length; i++)
			array[i] = val;
	}

	public static void arraySet(int[] array, int idx, int len, int val) {
		for (int i = idx; i < len; i++)
			array[i] = val;
	}
    
	// float-arraySet
	public static void arraySet(float[] array, float val) {
		for (int i = 0; i < array.length; i++)
			array[i] = val;
	}

	public static void arraySet(float[] array, int idx, int len, float val) {
		for (int i = idx; i < len; i++)
			array[i] = val;
	}
    
	// double-arraySet
	public static void arraySet(double[] array, double val) {
		for (int i = 0; i < array.length; i++)
			array[i] = val;
	}

	public static void arraySet(double[] array, int idx, int len, double val) {
		for (int i = idx; i < len; i++)
			array[i] = val;
	}
    
	private static int glo_uuid = 1;

	public synchronized static int getNewUUID() {
		int uuid = glo_uuid++;

		if (glo_uuid >= ((int) 0x7FFFFFF0))
			glo_uuid = 1;

		return uuid;
	}
    
	public static void wait(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
}
