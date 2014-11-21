package nz.co.dav.guava.sample.utilities;

import static org.junit.Assert.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.common.base.Charsets
import com.google.common.io.Files

@Slf4j
class ScriptParseTest {
	static String TEST_FILE_NAME="/books_create.txt"
	static File testFile

	@BeforeClass
	static void initial(){
		testFile = new File(BookScriptParseTest.class.getResource(TEST_FILE_NAME).getFile())
	}

	@Test
	void testConvert() {
		Map<String,Set<String>> resultMap = Files.readLines(testFile, Charsets.UTF_8,new MyScriptLineParseProcessor())
		println "--book--"
		resultMap.get("book").each { println "$it" }
		println "--author--"
		resultMap.get("author").each { println "$it" }

		this.doWriteToFile("/tmp/book.txt", resultMap.get("book"))
		this.doWriteToFile("/tmp/authro.txt", resultMap.get("author"))
	}

	void doWriteToFile(final String fileName, final Set<String> lines){
		File file = new File(fileName)
		BufferedWriter writer
		try{
			writer = Files.newWriter(file, Charsets.UTF_8)
			lines.each{ writer.writeLine(it) }
		}finally{
			if(writer){
				writer.close()
			}
		}
	}
}
