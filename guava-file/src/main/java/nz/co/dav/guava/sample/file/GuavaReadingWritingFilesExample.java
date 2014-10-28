package nz.co.dav.guava.sample.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class GuavaReadingWritingFilesExample {

	public static String TEST_FILE = "/sentences.txt";

	public static void main(String[] args) throws Exception {
		List<String> sentences = Lists.newArrayList();
		sentences.add("This is line one\n");
		sentences.add("This is line two\n");
		sentences.add("This is line three\n");
		sentences.add("This is line four\n");

		File file = new File(GuavaReadingWritingFilesExample.class.getResource(TEST_FILE).getFile());

		try (BufferedWriter writer = Files.newWriter(file, Charsets.UTF_8)) {
			for (String sentence : sentences) {
				writer.write(sentence);
			}
		}

		try (BufferedReader reader = Files.newReader(file, Charsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}

	}
}
