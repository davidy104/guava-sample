package nz.co.dav.guava.sample.file.sink;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;

public class ByteSinkTest {

	ByteSink byteSink;

	public static String ORIGINAL_FILE = "/sample.pdf";
	public static String DEST_FILE = "/tmp/byteSink.pdf";

	@Test
	public void testCreateFileByteSink() throws Exception {
		File dest = new File(DEST_FILE);
		dest.deleteOnExit();
		byteSink = Files.asByteSink(dest);
		File file = new File(ByteSinkTest.class.getResource(ORIGINAL_FILE).getFile());
		byteSink.write(Files.toByteArray(file));
		assertThat(Files.toByteArray(dest), is(Files.toByteArray(file)));
	}

	@Test
	public void testWriteFromInputStream() throws Exception {
		File dest = new File(DEST_FILE);
		dest.deleteOnExit();
		byteSink = Files.asByteSink(dest);
		File sourceFile = new File(ByteSinkTest.class.getResource(ORIGINAL_FILE).getFile());
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
		byteSink.writeFrom(inputStream);
		assertThat(Files.toByteArray(dest), is(Files.toByteArray(sourceFile)));
	}

}
