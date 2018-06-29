package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yaojie.hou
 * @create 2018/6/5
 */
public class FileChannelTest {

	public static void main(String[] args) throws IOException {

		RandomAccessFile file = new RandomAccessFile("/data/nio-data.txt", "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = channel.read(buf);

		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();

			while (buf.hasRemaining()) {
				System.out.println((char) buf.get());
			}

			buf.clear();
			bytesRead = channel.read(buf);
		}
		file.close();
	}
}
