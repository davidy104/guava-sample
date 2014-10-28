package nz.co.dav.guava.sample.file;

import java.io.File;
import java.io.IOException;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

public class HashFileExample {
	public static String TEST_FILE = "/sampleTextFileOne.txt";

	public static void main(String[] args) throws IOException {
		File file = new File(CopyFileExample.class.getResource(TEST_FILE).getFile());
		HashCode hashCode = Files.hash(file, Hashing.md5());
		System.out.println(hashCode);
	}
}
