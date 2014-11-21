package nz.co.dav.guava.sample.utilities;

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import com.google.common.base.Joiner
import com.google.common.base.Splitter
import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import com.google.common.io.LineProcessor
@Slf4j
public class MyScriptLineParseProcessor implements LineProcessor<Map<String,Set<String>>>{

	static Splitter splitter = Splitter.on("-")
	static int TITLE_INDEX = 2
	Map<String,Set<String>> resultMap = [:]
	JsonSlurper jsonSlurper = new JsonSlurper()


	@Override
	public boolean processLine(final String line) throws IOException {
		log.info "line: {} $line"
		String authorNo
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
		
		if(authorTmpStr){
			authorNo = "AUTHOR-${randomId()}"
			this.doProcessAuthorString(authorTmpStr, authorNo)
		}
		this.doProcessBookString(bookInfoTmpStr, authorNo)
		return true
	}

	void doProcessBookString(final String bookInfoStr,final String authorNo){
		double price = new Random().nextInt(300 + 1 -30) + 30
		String isbn = "ISBN-${randomId()}"
		Map bookInfoJsonObject = (Map)jsonSlurper.parseText(bookInfoStr)
		if(!bookInfoJsonObject.get("tags")){
			bookInfoJsonObject.put("tags","")
		}
		bookInfoJsonObject.put("price", price)
		bookInfoJsonObject.put("isbn", isbn)
		if(authorNo){
			bookInfoJsonObject.put("author", authorNo)
		}
		String convertStr = processValues(bookInfoJsonObject)

		if(resultMap.containsKey("book")){
			Set<String> convertStrSet = resultMap.get("book")
			convertStrSet << convertStr
		} else {
			resultMap.put("book", [convertStr] as Set)
		}
	}

	void doProcessAuthorString(final String authorInfoStr,final String authorNo){
		Map authorInforJsonObject = (Map)jsonSlurper.parseText(authorInfoStr)
		if(!authorInforJsonObject.get("surname")){
			authorInforJsonObject.put("surname","")
		}
		authorInforJsonObject.put("authorNo", authorNo)
		String tmpStr = processValues(authorInforJsonObject)
		if(resultMap.containsKey("author")){
			Set<String> convertStrSet = resultMap.get("author")
			convertStrSet << tmpStr
		} else {
			resultMap.put("author", [tmpStr] as Set)
		}
	}

	@Override
	public Map<String,Set<String>> getResult() {
		return resultMap
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
}
