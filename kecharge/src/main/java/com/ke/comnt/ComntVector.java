/**   
* @Title: ComntVector.java 
* @Package com.keicpms.communite 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhp
* @date 2018年12月13日 下午5:06:36 
* @version V1.0   
*/
package com.ke.comnt;

/** 
* @ClassName: ComntVector 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhp
* @date 2018年12月13日 下午5:06:36 
*  
*/
public class ComntVector {
	//byte型的Vector
		public static class ByteVector {
			private int    m_size     = 0;
			private int    m_capacity = 0;
			private byte[] m_paddr    = null;
			
			public ByteVector() {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
			}
			
			public ByteVector(int capacity) {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
				
				reserve(capacity);
			}
			
			public void clear() {
				m_size 		= 0;
				m_capacity 	= 0;
				m_paddr 	= null;
			}
			
			public void resize(int size) {
				reserve(size);
				
				int i = m_size;
				for (; i < size; i++) m_paddr[i] = 0;

				m_size = size;
			}
			
			public void clone(ByteVector byte_vect) {
				if (byte_vect.size() <= 0) {		
					clear();
				}
				else {
					resize(byte_vect.size());
					for (int i = 0; i < byte_vect.size(); i++) set(i, byte_vect.at(i));
				}
			}
			
			public ByteVector clone() {
				ByteVector byte_vect = new ByteVector();
				clone(byte_vect);
				
				return byte_vect;
			}
			
			public void	push_back(byte item) {
			    checksize();
				m_paddr[m_size] = item;
			    m_size++;
			}
			
			public void	push_back(byte []item) {
				for (int i = 0; i < item.length; i++) {
					push_back(item[i]);
				}
			}
			
			public void	push_back(ByteVector cs) {
				for (int i = 0; i < cs.size(); i++) {
					push_back(cs.at(i));
				}
			}

			public void	pop_back() {
			    if (empty()) return;
				m_paddr[m_size - 1] = 0;
			    m_size--;
			}
			
			public void	pop_head() {
			    if (empty()) return;

			    if (0 != (m_size - 1)) {
			    	for (int i = 0; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			public void	remove_at(int idx) {
			    if (empty()) return;
				if (idx >= m_size) return;

				if (idx != (m_size - 1)) {
			    	for (int i = idx; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	insert_at(int idx, byte item) {
				checksize();

				if (idx < 0) idx = 0;

				if (idx >= m_size) {
					push_back(item);
					return;
				}
				else {
					for (int i = idx; i < m_size; i++) m_paddr[i + 1] = m_paddr[i];
					m_paddr[idx] = item;

					m_size++;
				}
			}
			
			public byte	front() {
				if (empty()) return 0;
				else return m_paddr[0];
			}
			
			public byte	back() {
				if (empty()) return 0;
				else return m_paddr[m_size - 1];
			}

			public int  size() {
				return m_size;
			}
			
			public boolean  empty() {
				return m_size <= 0;
			}
			
			public int	capacity() {
				return m_capacity;
			}

			public byte at(int idx) {
				if (idx >= 0 && idx < m_size) {
					return m_paddr[idx];
				}
				else return 0;
			}

			public void set(int idx, byte val) {
				if (idx >= 0 && idx < m_size) {
					m_paddr[idx] = val;
				}
				else return;
			}
			
			public byte[] getaddr() {
				return m_paddr;
			}

			public int getItemSize() {
				return 1;
			}
			
			protected void reserve(int n) {
			    if (n <= m_capacity) return;

				byte[] p = new byte[n];

				if (m_size > 0) {
					for (int i = 0; i < m_size; i++) p[i] = m_paddr[i];
				}
				
			    m_paddr    = p;
			    m_capacity = n;
			}	 

			protected void checksize() {
			    if (m_size == m_capacity) {
			        if (m_capacity == 0) {        
			            reserve(1);
			        }
			        else {
			            reserve(2 * m_capacity);
					}
			    }
			}
		}
		
		//short型的Vector--没办法，java不支持基本类型的模板，重复劳动
		public static class ShortVector {
			private int     m_size     = 0;
			private int     m_capacity = 0;
			private short[] m_paddr    = null;
			
			public ShortVector() {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
			}
			
			public ShortVector(int capacity) {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
				
				reserve(capacity);
			}
			
			public void clear() {
				m_size 		= 0;
				m_capacity 	= 0;
				m_paddr 	= null;
			}
			
			public void resize(int size) {
				reserve(size);
				
				int i = m_size;
				for (; i < size; i++) m_paddr[i] = 0;

				m_size = size;
			}
			
			public void clone(ShortVector vect) {
				if (vect.size() <= 0) {		
					clear();
				}
				else {
					resize(vect.size());
					for (int i = 0; i < vect.size(); i++) set(i, vect.at(i));
				}
			}
			
			public ShortVector clone() {
				ShortVector vect = new ShortVector();
				clone(vect);
				
				return vect;
			}
			
			public void	push_back(short item) {
			    checksize();
				m_paddr[m_size] = item;
			    m_size++;
			}
			
			public void	push_back(short []item) {
				for (int i = 0; i < item.length; i++) {
					push_back(item[i]);
				}
			}
			
			public void	push_back(ShortVector cs) {
				for (int i = 0; i < cs.size(); i++) {
					push_back(cs.at(i));
				}
			}

			public void	pop_back() {
			    if (empty()) return;
				m_paddr[m_size - 1] = 0;
			    m_size--;
			}
			
			public void	pop_head() {
			    if (empty()) return;

			    if (0 != (m_size - 1)) {
			    	for (int i = 0; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	remove_at(int idx) {
			    if (empty()) return;
				if (idx >= m_size) return;

				if (idx != (m_size - 1)) {
			    	for (int i = idx; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	insert_at(int idx, short item) {
				checksize();

				if (idx < 0) idx = 0;

				if (idx >= m_size) {
					push_back(item);
					return;
				}
				else {
					for (int i = idx; i < m_size; i++) m_paddr[i + 1] = m_paddr[i];
					m_paddr[idx] = item;

					m_size++;
				}
			}
			
			public short	front() {
				if (empty()) return 0;
				else return m_paddr[0];
			}
			
			public short	back() {
				if (empty()) return 0;
				else return m_paddr[m_size - 1];
			}

			public int  size() {
				return m_size;
			}
			
			public boolean  empty() {
				return m_size <= 0;
			}
			
			public int	capacity() {
				return m_capacity;
			}

			public short at(int idx) {
				if (idx >= 0 && idx < m_size) {
					return m_paddr[idx];
				}
				else return 0;
			}

			public void set(int idx, short val) {
				if (idx >= 0 && idx < m_size) {
					m_paddr[idx] = val;
				}
				else return ;
			}
			
			public short[] getaddr() {
				return m_paddr;
			}

			public int getItemSize() {
				return 2;
			}
			
			protected void reserve(int n) {
			    if (n <= m_capacity) return;

			    short[] p = new short[n];

				if (m_size > 0) {
					for (int i = 0; i < m_size; i++) p[i] = m_paddr[i];
				}
				
			    m_paddr    = p;
			    m_capacity = n;
			}	 

			protected void checksize() {
			    if (m_size == m_capacity) {
			        if (m_capacity == 0) {        
			            reserve(1);
			        }
			        else {
			            reserve(2 * m_capacity);
					}
			    }
			}
		}
		
		//int型的Vector--没办法，java不支持基本类型的模板，重复劳动
		public static class IntVector {
			private int     m_size     = 0;
			private int     m_capacity = 0;
			private int[] 	m_paddr    = null;
			
			public IntVector() {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
			}
			
			public IntVector(int capacity) {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
				
				reserve(capacity);
			}
			
			public void clear() {
				m_size 		= 0;
				m_capacity 	= 0;
				m_paddr 	= null;
			}
			
			public void resize(int size) {
				reserve(size);
				
				int i = m_size;
				for (; i < size; i++) m_paddr[i] = 0;

				m_size = size;
			}
			
			public void clone(IntVector vect) {
				if (vect.size() <= 0) {		
					clear();
				}
				else {
					resize(vect.size());
					for (int i = 0; i < vect.size(); i++) set(i, vect.at(i));
				}
			}
			
			public IntVector clone() {
				IntVector vect = new IntVector();
				clone(vect);
				
				return vect;
			}
			
			public void	push_back(int item) {
			    checksize();
				m_paddr[m_size] = item;
			    m_size++;
			}
			
			public void	push_back(int []item) {
				for (int i = 0; i < item.length; i++) {
					push_back(item[i]);
				}
			}
			
			public void	push_back(IntVector cs) {
				for (int i = 0; i < cs.size(); i++) {
					push_back(cs.at(i));
				}
			}

			public void	pop_back() {
			    if (empty()) return;
				m_paddr[m_size - 1] = 0;
			    m_size--;
			}
			
			public void	pop_head() {
			    if (empty()) return;

			    if (0 != (m_size - 1)) {
			    	for (int i = 0; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			public void	remove_at(int idx) {
			    if (empty()) return;
				if (idx >= m_size) return;

				if (idx != (m_size - 1)) {
			    	for (int i = idx; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	insert_at(int idx, int item) {
				checksize();

				if (idx < 0) idx = 0;

				if (idx >= m_size) {
					push_back(item);
					return;
				}
				else {
					for (int i = idx; i < m_size; i++) m_paddr[i + 1] = m_paddr[i];
					m_paddr[idx] = item;

					m_size++;
				}
			}
			
			public int	front() {
				if (empty()) return 0;
				else return m_paddr[0];
			}
			
			public int	back() {
				if (empty()) return 0;
				else return m_paddr[m_size - 1];
			}

			public int  size() {
				return m_size;
			}
			
			public boolean  empty() {
				return m_size <= 0;
			}
			
			public int	capacity() {
				return m_capacity;
			}

			public int at(int idx) {
				if (idx >= 0 && idx < m_size) {
					return m_paddr[idx];
				}
				else return 0;
			}

			public void set(int idx, int val) {
				if (idx >= 0 && idx < m_size) {
					m_paddr[idx] = val;
				}
				else return ;
			}
			
			public int[] getaddr() {
				return m_paddr;
			}

			public int getItemSize() {
				return 4;
			}
			
			protected void reserve(int n) {
			    if (n <= m_capacity) return;

			    int[] p = new int[n];

				if (m_size > 0) {
					for (int i = 0; i < m_size; i++) p[i] = m_paddr[i];
				}
				
			    m_paddr    = p;
			    m_capacity = n;
			}	 

			protected void checksize() {
			    if (m_size == m_capacity) {
			        if (m_capacity == 0) {        
			            reserve(1);
			        }
			        else {
			            reserve(2 * m_capacity);
					}
			    }
			}
		}
		
		//float型的Vector--没办法，java不支持基本类型的模板，重复劳动
		public static class FloatVector {
			private int     m_size     = 0;
			private int     m_capacity = 0;
			private float[] m_paddr    = null;
			
			public FloatVector() {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
			}
			
			public FloatVector(int capacity) {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
				
				reserve(capacity);
			}
			
			public void clear() {
				m_size 		= 0;
				m_capacity 	= 0;
				m_paddr 	= null;
			}
			
			public void resize(int size) {
				reserve(size);
				
				int i = m_size;
				for (; i < size; i++) m_paddr[i] = 0;

				m_size = size;
			}
			
			public void clone(FloatVector vect) {
				if (vect.size() <= 0) {		
					clear();
				}
				else {
					resize(vect.size());
					for (int i = 0; i < vect.size(); i++) set(i, vect.at(i));
				}
			}
			
			public FloatVector clone() {
				FloatVector vect = new FloatVector();
				clone(vect);
				
				return vect;
			}
			
			public void	push_back(float item) {
			    checksize();
				m_paddr[m_size] = item;
			    m_size++;
			}
			
			public void	push_back(float []item) {
				for (int i = 0; i < item.length; i++) {
					push_back(item[i]);
				}
			}
			
			public void	push_back(FloatVector cs) {
				for (int i = 0; i < cs.size(); i++) {
					push_back(cs.at(i));
				}
			}

			public void	pop_back() {
			    if (empty()) return;
				m_paddr[m_size - 1] = 0;
			    m_size--;
			}
			
			public void	pop_head() {
			    if (empty()) return;

			    if (0 != (m_size - 1)) {
			    	for (int i = 0; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			public void	remove_at(int idx) {
			    if (empty()) return;
				if (idx >= m_size) return;

				if (idx != (m_size - 1)) {
			    	for (int i = idx; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	insert_at(int idx, float item) {
				checksize();

				if (idx < 0) idx = 0;

				if (idx >= m_size) {
					push_back(item);
					return;
				}
				else {
					for (int i = idx; i < m_size; i++) m_paddr[i + 1] = m_paddr[i];
					m_paddr[idx] = item;

					m_size++;
				}
			}
			
			public float	front() {
				if (empty()) return 0;
				else return m_paddr[0];
			}
			
			public float	back() {
				if (empty()) return 0;
				else return m_paddr[m_size - 1];
			}

			public int  size() {
				return m_size;
			}
			
			public boolean  empty() {
				return m_size <= 0;
			}
			
			public int	capacity() {
				return m_capacity;
			}

			public float at(int idx) {
				if (idx >= 0 && idx < m_size) {
					return m_paddr[idx];
				}
				else return 0;
			}

			public void set(int idx, float val) {
				if (idx >= 0 && idx < m_size) {
					m_paddr[idx] = val;
				}
				else return ;
			}
			
			public float[] getaddr() {
				return m_paddr;
			}

			public int getItemSize() {
				return 4;
			}
			
			protected void reserve(int n) {
			    if (n <= m_capacity) return;

			    float[] p = new float[n];

				if (m_size > 0) {
					for (int i = 0; i < m_size; i++) p[i] = m_paddr[i];
				}
				
			    m_paddr    = p;
			    m_capacity = n;
			}	 

			protected void checksize() {
			    if (m_size == m_capacity) {
			        if (m_capacity == 0) {        
			            reserve(1);
			        }
			        else {
			            reserve(2 * m_capacity);
					}
			    }
			}
		}
		
		//double型的Vector--没办法，java不支持基本类型的模板，重复劳动
		public static class DoubleVector {
			private int      m_size     = 0;
			private int      m_capacity = 0;
			private double[] m_paddr    = null;
			
			public DoubleVector() {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
			}
			
			public DoubleVector(int capacity) {
				m_size     = 0;
				m_capacity = 0;
				m_paddr    = null;
				
				reserve(capacity);
			}
			
			public void clear() {
				m_size 		= 0;
				m_capacity 	= 0;
				m_paddr 	= null;
			}
			
			public void resize(int size) {
				reserve(size);
				
				int i = m_size;
				for (; i < size; i++) m_paddr[i] = 0;

				m_size = size;
			}
			
			public void clone(DoubleVector vect) {
				if (vect.size() <= 0) {		
					clear();
				}
				else {
					resize(vect.size());
					for (int i = 0; i < vect.size(); i++) set(i, vect.at(i));
				}
			}
			
			public DoubleVector clone() {
				DoubleVector vect = new DoubleVector();
				clone(vect);
				
				return vect;
			}
			
			public void	push_back(double item) {
			    checksize();
				m_paddr[m_size] = item;
			    m_size++;
			}
			
			public void	push_back(double []item) {
				for (int i = 0; i < item.length; i++) {
					push_back(item[i]);
				}
			}
			
			public void	push_back(DoubleVector cs) {
				for (int i = 0; i < cs.size(); i++) {
					push_back(cs.at(i));
				}
			}

			public void	pop_back() {
			    if (empty()) return;
				m_paddr[m_size - 1] = 0;
			    m_size--;
			}
			
			public void	pop_head() {
			    if (empty()) return;

			    if (0 != (m_size - 1)) {
			    	for (int i = 0; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	remove_at(int idx) {
			    if (empty()) return;
				if (idx >= m_size) return;

				if (idx != (m_size - 1)) {
			    	for (int i = idx; i < m_size - 1; i++) {
			    		m_paddr[i] = m_paddr[i+1]; 
			    	}
				}

				m_size--;
			}
			
			public void	insert_at(int idx, double item) {
				checksize();

				if (idx < 0) idx = 0;

				if (idx >= m_size) {
					push_back(item);
					return;
				}
				else {
					for (int i = idx; i < m_size; i++) m_paddr[i + 1] = m_paddr[i];
					m_paddr[idx] = item;

					m_size++;
				}
			}
			
			public double	front() {
				if (empty()) return 0;
				else return m_paddr[0];
			}
			
			public double	back() {
				if (empty()) return 0;
				else return m_paddr[m_size - 1];
			}

			public int  size() {
				return m_size;
			}
			
			public boolean  empty() {
				return m_size <= 0;
			}
			
			public int	capacity() {
				return m_capacity;
			}

			public double at(int idx) {
				if (idx >= 0 && idx < m_size) {
					return m_paddr[idx];
				}
				else return 0;
			}

			public void set(int idx, double val) {
				if (idx >= 0 && idx < m_size) {
					m_paddr[idx] = val;
				}
				else return ;
			}
			
			public double[] getaddr() {
				return m_paddr;
			}

			public int getItemSize() {
				return 8;
			}
			
			protected void reserve(int n) {
			    if (n <= m_capacity) return;

			    double[] p = new double[n];

				if (m_size > 0) {
					for (int i = 0; i < m_size; i++) p[i] = m_paddr[i];
				}
				
			    m_paddr    = p;
			    m_capacity = n;
			}

			protected void checksize() {
			    if (m_size == m_capacity) {
			        if (m_capacity == 0) {        
			            reserve(1);
			        }
			        else {
			            reserve(2 * m_capacity);
					}
			    }
			}
		}
}
