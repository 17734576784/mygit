package com.wo.comnt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ComntSocket {
	private SocketChannel socket_chan = null;
	 private Selector      selector    = null;
	 private boolean	   err_flag	   = false;	 
	 
	 private static final int FORBITERR_NUM = 102400;
	 
	 public boolean connect(String ip, int port) {		 
		 boolean conn_val = false;
		 
		  try {
			   //生成一个socketchannel
			   socket_chan = SocketChannel.open();
			   socket_chan.configureBlocking(false);

			   //连接到server
			   InetSocketAddress addr = new InetSocketAddress(ip, port);			   
			   socket_chan.connect(addr);	   
			   while(!socket_chan.finishConnect()) {
				   try {
					   Thread.sleep(1);
				   }
				   catch (InterruptedException e){
					   e.printStackTrace(System.err);
				   }
			   }

			   selector = Selector.open();			   
			   socket_chan.register(selector, SelectionKey.OP_READ);

			   conn_val = true;
			   //System.out.println("connection has been established!...");			   
		  }catch(IOException ioe) {
			   //ioe.printStackTrace();			   
		  }

		  if (!conn_val) close();

		  return conn_val;
	 }

	 public boolean isSocketValid() {
		 if (socket_chan == null || selector == null) return false;
		 else return true;
	 }

	 public boolean getErrFlag() {
		 return err_flag;
	 }
	 
	 public void setErrFlag(boolean errf) {
		 err_flag = errf;
	 }
	 
	 public void close() {
		 try {
			 if (socket_chan != null) {
				 socket_chan.close();
			 }
		  }catch(IOException ioe) {
			   ioe.printStackTrace();
		  }
		  
		  socket_chan = null;
		  
		 try {		 
			 if (selector != null) {
				 selector.close();
			 }
		  }catch(IOException ioe) {
			   ioe.printStackTrace();
		  }
		  
		  selector = null;
	 }
	 
	 public int select(int time_out) {
		 if (socket_chan == null || selector == null) return -1;
		 
		 int sel_val = 0;
		 try{
			 sel_val = selector.select(time_out);
		 }
		 catch (IOException ioe) {
			 sel_val = -1;
			 ioe.printStackTrace();
		 }
		 
		 if (sel_val > 0) {
			 Set<SelectionKey> keys = selector.selectedKeys();
			 keys.clear();
		 }
		 
		 return sel_val;
	 }
	 
	 /*
	 Set<SelectionKey> keys = selector.selectedKeys();		 
	 for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext();) {
		 SelectionKey key = it.next();
		 if (key.isReadable()) {
			 System.out.println("ok");
			 SocketChannel sc = (SocketChannel) key.channel();
			 int r = readsocket(sc, read_buf, offset, buf_len);
			 if (r <= 0) break;
		 }
		 it.remove();
	 }
	 */
	 
	 private int read(ByteBuffer read_buf)	 {		 
		 int read_num = 0, read_count = 0;
		 int forbiterr_num = 0;

		 try{
			 do {
				 read_num = socket_chan.read(read_buf);				 
				 if (read_num < 0) return -1;
				 
				 read_count += read_num;
				 
				 forbiterr_num++;
				 if (forbiterr_num >= FORBITERR_NUM) break;
			 } while (read_num > 0);
		 } catch(IOException ioe) {
			 ioe.printStackTrace();		   
			 return -1;
		 }		 
		 
		 return read_count;
	 }
	 
	 public int read(ByteBuffer read_buf, int time_out)	 {
		 if (socket_chan == null || selector == null) return -1;		 
		 read_buf.clear();
		 
		 int result = 0;
		 int ret_code = 0;
		 
		 int len = read_buf.capacity();
		 int len_left = 0;
		 int size_packet = len;
		 
		 int rxntime  = 0;
		
		 boolean f_flag = true;

		 while (len_left < len) {
			 if (rxntime > 20) {
				 ret_code = -1;
				 break;
			 }

			 if (!f_flag) {				 
				 result = select(time_out);				 
				 if (result == 0) {
					 rxntime++;
					 continue;
				 }
				 
				 if (result < 0) {
					 ret_code = -1;
					 break;
				 }
			 }
			 else {
				 f_flag = false;
			 }

			 result = read(read_buf);

			 if (result < 0) {
				 ret_code = -1;
				 break;
			 }

			 if (result == 0) {
				 rxntime++;
				 continue;
			 }

			 rxntime = 0;
			 len_left += result;

			 if (len - len_left < size_packet) size_packet = len - len_left;
		 }

		 if (ret_code < 0) return -1;
		 
		 read_buf.flip();  
		 
		 return read_buf.limit();
	 }
	 
	 public int write(ByteBuffer write_buf)	 {
		 if (socket_chan == null || selector == null) return 0;

		 write_buf.flip();		 
		 if (write_buf.limit() <= 0) return 0;

		 int write_num = 0, write_count = 0;
		 int forbiterr_num = 0;

		 try{
			 while(write_buf.hasRemaining()) {
				 write_num = socket_chan.write(write_buf);
				 if (write_num < 0) break;
				 write_count += write_num;

				 forbiterr_num++;
				 if (forbiterr_num >= FORBITERR_NUM) break;				 
			 }			 
		 } catch(IOException ioe) {
			 ioe.printStackTrace();
			 return 0;
		 }

		 return write_count;
	 }
	 
	 public int read(byte[] read_buf, int offset, int buf_len, int time_out) {
		 if (socket_chan == null || selector == null) return 0;
		 if (read_buf == null || offset < 0) return 0;
		 if ((offset + buf_len) > read_buf.length) return 0;
		 
		 ByteBuffer byte_buf = ByteBuffer.allocate(buf_len);
		 
		 int read_num = read(byte_buf, time_out);
		 
		 int copy_num = Math.min(read_num, buf_len);
		 
		 for (int i = 0; i < copy_num; i++) read_buf[offset + i] = byte_buf.get();

		 return copy_num;
	 }
	 
	 public int read(byte[] read_buf, int time_out) {
		 return read(read_buf, 0, read_buf.length, time_out);
	 }
	 
	 public int write(byte[] write_buf, int offset, int buf_len) {
		 if (socket_chan == null || selector == null) return 0;
		 if (write_buf == null || offset < 0) return 0;
		 if ((offset + buf_len) > write_buf.length) return 0;
		 
		 ByteBuffer byte_buf = ByteBuffer.allocate(buf_len);
		 for (int i = 0; i < buf_len; i++) byte_buf.put(write_buf[offset + i]);
		 
		 return write(byte_buf);		 
	 }
	 
	 public int write(byte[] write_buf)	 {
		 return write(write_buf, 0, write_buf.length);
	 }
}
