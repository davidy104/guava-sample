package nz.co.dav.guava.sample.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.common.io.Closer;

public class CloserExample {
	public static String ORIGINAL_FILE = "/sampleTextFileOne.txt";
	public static String COPY_FILE = "/tmp/copy.txt";

	public static void main(String[] args) throws IOException {
		Closer closer = Closer.create();
		try {
			File destination = new File(COPY_FILE);
			destination.deleteOnExit();
			BufferedReader reader = new BufferedReader(new FileReader(CopyFileExample.class.getResource(ORIGINAL_FILE).getFile()));
			BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
			closer.register(reader);
			closer.register(writer);
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
			}

		} catch (Throwable t) {
			throw closer.rethrow(t);
		} finally {
			closer.close();
		}
	}

}
