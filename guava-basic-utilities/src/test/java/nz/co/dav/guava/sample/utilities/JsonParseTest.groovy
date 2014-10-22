package nz.co.dav.guava.sample.utilities;

import static org.junit.Assert.*
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import org.junit.Test
@Slf4j
class JsonParseTest {
	JsonSlurper jsonSlurper = new JsonSlurper()
	@Test
	public void test() {
		String text ='{"title":"Things Fall Apart","tags":["novel"]}'
		Map bookInfoJsonObject = (Map)jsonSlurper.parseText(text)
		log.info "title: {} "+bookInfoJsonObject.get('title')
	}
}
