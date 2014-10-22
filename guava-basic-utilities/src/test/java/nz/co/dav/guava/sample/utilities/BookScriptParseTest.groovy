package nz.co.dav.guava.sample.utilities;

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.common.base.Charsets
import com.google.common.base.Joiner
import com.google.common.base.Splitter
import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import com.google.common.io.Files
import com.google.common.io.LineProcessor

@Slf4j
class BookScriptParseTest {

	static String TEST_FILE_NAME="/books_create.txt"
	static File testFile

	@BeforeClass
	static void initial(){
		testFile = new File(BookScriptParseTest.class.getResource(TEST_FILE_NAME).getFile())
	}

	@Test
	void testConvert() {
		Map<Map<String,List<String>>> readLineMap = Files.readLines(testFile, Charsets.UTF_8,new ScriptLineParseProcessor())
		readLineMap.each {k,v->
			if(k == 'book'){
				println "book print..."
				List bookList = (List)v
				bookList.each { println "$it" }
				println "book print..."
			}else {
				println "author print..."
				List authorList = (List)v
				authorList.each { println "$it" }
				println "author print..."
			}
		}
	}


	private static class ScriptLineParseProcessor implements LineProcessor<Map<String,List<String>>>{
		static Splitter splitter = Splitter.on("-")
		static int TITLE_INDEX = 2
		Map convertResultMap = [:]
		JsonSlurper jsonSlurper = new JsonSlurper()


		public ScriptLineParseProcessor() {
			def bookStrList =[]
			def authorStrList=[]
			convertResultMap.put("book", bookStrList)
			convertResultMap.put("author", authorStrList)
		}


		@Override
		boolean processLine(final String line) throws IOException {
			log.info "line: {} $line"
			String authorTmpStr
			String bookInfoTmpStr = Iterables.get(splitter.trimResults().split(line),0)
			bookInfoTmpStr = bookInfoTmpStr[bookInfoTmpStr.indexOf('{')..bookInfoTmpStr.lastIndexOf('}')]
			try {
				authorTmpStr = Iterables.get(splitter.trimResults().split(line),2)
				authorTmpStr = authorTmpStr[authorTmpStr.indexOf('{')..authorTmpStr.lastIndexOf('}')]
			} catch (e) {
			}

			log.info "bookInfoTmpStr: {} $bookInfoTmpStr"
			log.info "authorTmpStr: {} $authorTmpStr"

			Map bookInfoJsonObject = (Map)jsonSlurper.parseText(bookInfoTmpStr)
			if(!bookInfoJsonObject.get("tags")){
				bookInfoJsonObject.put("tags","")
			}
			bookInfoJsonObject.put("isbn", "ISBN-${randomId()}")
			def tmpStr = processValues(bookInfoJsonObject)
			log.info "bookInfoStr: {} $tmpStr"
			convertResultMap.get("book") << tmpStr
			if(authorTmpStr){
				Map authorInforJsonObject = (Map)jsonSlurper.parseText(authorTmpStr)
				if(!authorInforJsonObject.get("surname")){
					authorInforJsonObject.put("surname","")
				}
				tmpStr = processValues(authorInforJsonObject)
				log.info "authorStr: {} $tmpStr"
				convertResultMap.get("author") << tmpStr
			}
			return true
		}


		String processValues(Map tmpValueMap){
			def valList =  []
			tmpValueMap.each {k,v->
				String s
				if(v instanceof Collection){
					List arrayJson = (List)v
					s = Joiner.on(",").join(arrayJson.toArray(new String[arrayJson.size()]))
				} else {
					s=String.valueOf(v)
				}
				valList << s
			}
			def tmpList = Lists.reverse(valList)
			return Joiner.on("|").join(tmpList.toArray(new String[tmpList.size()]))
		}

		String randomId(){
			char[] alphNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()
			Random rnd = new Random()
			StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-")
			for (int i = 0; i < 5; i++){
				sb.append(alphNum[rnd.nextInt(alphNum.length)])
			}
			return sb.toString()
		}

		@Override
		Map<String,List<String>> getResult() {
			return convertResultMap
		}
	}
}
