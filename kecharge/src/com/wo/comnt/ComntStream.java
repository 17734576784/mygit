package com.wo.comnt;

public class ComntStream {
	public static int writeStream(ComntVector.ByteVector byte_vect, boolean data) {
		int item_size = 1;

		byte b_data = (byte) ((data == true) ? 1 : 0);

		byte_vect.push_back(b_data);
		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			boolean[] data, int vect_offset, int data_len) {
		int item_size = 1;

		byte b_data = 0;

		for (int i = 0; i < data_len; i++) {
			b_data = (byte) ((data[vect_offset + i] == true) ? 1 : 0);
			byte_vect.push_back(b_data);
		}

		return data_len * item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, byte data) {
		int item_size = 1;

		byte_vect.push_back(data);
		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			byte[] data, int vect_offset, int data_len) {
		int item_size = 1;

		for (int i = 0; i < data_len; i++) {
			byte_vect.push_back(data[vect_offset + i]);
		}

		return data_len * item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, short data) {
		int item_size = 2;

		byte[] b = new byte[item_size];
		b[0] = (byte) (data & 0xff);
		b[1] = (byte) ((data >> 8) & 0xff);

		for (int i = 0; i < b.length; i++)
			byte_vect.push_back(b[i]);

		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			short[] data, int vect_offset, int data_len) {
		int item_size = 2;

		for (int i = 0; i < data_len; i++) {
			writeStream(byte_vect, data[vect_offset + i]);
		}

		return item_size * data_len;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, int data) {
		int item_size = 4;

		byte[] b = new byte[item_size];
		b[0] = (byte) (data & 0xff);
		b[1] = (byte) ((data >> 8) & 0xff);
		b[2] = (byte) ((data >> 16) & 0xff);
		b[3] = (byte) ((data >> 24) & 0xff);

		for (int i = 0; i < b.length; i++)
			byte_vect.push_back(b[i]);

		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, int[] data,
			int vect_offset, int data_len) {
		int item_size = 4;

		for (int i = 0; i < data_len; i++) {
			writeStream(byte_vect, data[vect_offset + i]);
		}

		return item_size * data_len;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, long data) {
		int item_size = 8;

		byte[] b = new byte[item_size];
		b[0] = (byte) (data & 0xff);
		b[1] = (byte) ((data >> 8) & 0xff);
		b[2] = (byte) ((data >> 16) & 0xff);
		b[3] = (byte) ((data >> 24) & 0xff);
		b[4] = (byte) ((data >> 32) & 0xff);
		b[5] = (byte) ((data >> 40) & 0xff);
		b[6] = (byte) ((data >> 48) & 0xff);
		b[7] = (byte) ((data >> 56) & 0xff);

		for (int i = 0; i < b.length; i++)
			byte_vect.push_back(b[i]);

		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			long[] data, int vect_offset, int data_len) {
		int item_size = 8;

		for (int i = 0; i < data_len; i++) {
			writeStream(byte_vect, data[vect_offset + i]);
		}

		return item_size * data_len;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, float data) {
		int item_size = 4;

		int i_data = Float.floatToIntBits(data);

		byte[] b = new byte[item_size];
		b[0] = (byte) (i_data & 0xff);
		b[1] = (byte) ((i_data >> 8) & 0xff);
		b[2] = (byte) ((i_data >> 16) & 0xff);
		b[3] = (byte) ((i_data >> 24) & 0xff);

		for (int i = 0; i < b.length; i++)
			byte_vect.push_back(b[i]);

		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			float[] data, int vect_offset, int data_len) {
		int item_size = 4;

		for (int i = 0; i < data_len; i++) {
			writeStream(byte_vect, data[vect_offset + i]);
		}

		return item_size * data_len;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect, double data) {
		int item_size = 8;

		long l_data = Double.doubleToLongBits(data);

		byte[] b = new byte[item_size];
		b[0] = (byte) (l_data & 0xff);
		b[1] = (byte) ((l_data >> 8) & 0xff);
		b[2] = (byte) ((l_data >> 16) & 0xff);
		b[3] = (byte) ((l_data >> 24) & 0xff);
		b[4] = (byte) ((l_data >> 32) & 0xff);
		b[5] = (byte) ((l_data >> 40) & 0xff);
		b[6] = (byte) ((l_data >> 48) & 0xff);
		b[7] = (byte) ((l_data >> 56) & 0xff);

		for (int i = 0; i < b.length; i++)
			byte_vect.push_back(b[i]);

		return item_size;
	}

	public static int writeStream(ComntVector.ByteVector byte_vect,
			double[] data, int vect_offset, int data_len) {
		int item_size = 8;

		for (int i = 0; i < data_len; i++) {
			writeStream(byte_vect, data[vect_offset + i]);
		}

		return item_size * data_len;
	}

	public static boolean readSteam(ComntVector.ByteVector byte_vect,
			int vect_offset, boolean data) {
		byte b_val = byte_vect.at(vect_offset);

		return ((b_val == 0) ? false : true);
	}

	public static boolean[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, boolean[] data, int data_offset, int data_len) {
		// int item_size = 1;
		byte b_val = 0;

		for (int i = 0; i < data_len; i++) {
			b_val = byte_vect.at(vect_offset + i);
			data[data_offset + i] = ((b_val == 0) ? false : true);
		}

		return data;
	}

	public static int getDataSize(boolean data) {
		return 1;
	}

	public static byte readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, byte data) {
		// int item_size = 1;

		return byte_vect.at(vect_offset);
	}

	public static byte[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, byte[] data, int data_offset, int data_len) {
		// int item_size = 1;

		for (int i = 0; i < data_len; i++) {
			data[data_offset + i] = byte_vect.at(vect_offset + i);
		}

		return data;
	}

	public static int getDataSize(byte data) {
		return 1;
	}

	public static short readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, short data) {
		int item_size = 2;
		short[] b = new short[item_size];

		for (int i = 0; i < item_size; i++)
			b[i] = byte_vect.at(vect_offset + i);

		return (short) ((b[0] & 0xff) | ((b[1] & 0xff) << 8));
	}

	public static short[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, short[] data, int data_offset, int data_len) {
		int item_size = 2;

		for (int i = 0; i < data_len; i++) {
			data[i + data_offset] = readStream(byte_vect, vect_offset + i
					* item_size, data[i + data_offset]);
		}

		return data;
	}

	public static int getDataSize(short data) {
		return 2;
	}

	public static int readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, int data) {
		int item_size = 4;
		int[] b = new int[item_size];

		for (int i = 0; i < item_size; i++)
			b[i] = byte_vect.at(vect_offset + i);

		return (int) ((b[0] & 0xff) | ((b[1] & 0xff) << 8)
				| ((b[2] & 0xff) << 16) | ((b[3] & 0xff) << 24));
	}

	public static int[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, int[] data, int data_offset, int data_len) {
		int item_size = 4;

		for (int i = 0; i < data_len; i++) {
			data[i + data_offset] = readStream(byte_vect, vect_offset + i
					* item_size, data[i + data_offset]);
		}

		return data;
	}

	public static int getDataSize(int data) {
		return 4;
	}

	public static long readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, long data) {
		int item_size = 8;
		long[] b = new long[item_size];

		for (int i = 0; i < item_size; i++)
			b[i] = byte_vect.at(vect_offset + i);

		return (long) ((b[0] & 0xff) | ((b[1] & 0xff) << 8)
				| ((b[2] & 0xff) << 16) | ((b[3] & 0xff) << 24)
				| ((b[4] & 0xff) << 32) | ((b[5] & 0xff) << 40)
				| ((b[6] & 0xff) << 48) | ((b[7] & 0xff) << 56));
	}

	public static long[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, long[] data, int data_offset, int data_len) {
		int item_size = 8;

		for (int i = 0; i < data_len; i++) {
			data[i + data_offset] = readStream(byte_vect, vect_offset + i
					* item_size, data[i + data_offset]);
		}

		return data;
	}

	public static int getDataSize(long data) {
		return 8;
	}

	public static float readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, float data) {
		int item_size = 4;
		int[] b = new int[item_size];

		for (int i = 0; i < item_size; i++)
			b[i] = byte_vect.at(vect_offset + i);

		int fi_val = (int) ((b[0] & 0xff) | ((b[1] & 0xff) << 8)
				| ((b[2] & 0xff) << 16) | ((b[3] & 0xff) << 24));

		return Float.intBitsToFloat(fi_val);
	}

	public static float[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, float[] data, int data_offset, int data_len) {
		int item_size = 4;

		for (int i = 0; i < data_len; i++) {
			data[i + data_offset] = readStream(byte_vect, vect_offset + i
					* item_size, data[i + data_offset]);
		}

		return data;
	}

	public static int getDataSize(float data) {
		return 4;
	}

	public static double readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, double data) {
		int item_size = 8;
		long[] b = new long[item_size];

		for (int i = 0; i < item_size; i++)
			b[i] = byte_vect.at(vect_offset + i);

		long dl_val = (long) ((b[0] & 0xff) | ((b[1] & 0xff) << 8)
				| ((b[2] & 0xff) << 16) | ((b[3] & 0xff) << 24)
				| ((b[4] & 0xff) << 32) | ((b[5] & 0xff) << 40)
				| ((b[6] & 0xff) << 48) | ((b[7] & 0xff) << 56));

		return Double.longBitsToDouble(dl_val);
	}

	public static double[] readStream(ComntVector.ByteVector byte_vect,
			int vect_offset, double[] data, int data_offset, int data_len) {
		int item_size = 8;

		for (int i = 0; i < data_len; i++) {
			data[i + data_offset] = readStream(byte_vect, vect_offset + i
					* item_size, data[i + data_offset]);
		}

		return data;
	}

	public static int getDataSize(double data) {
		return 8;
	}
}
