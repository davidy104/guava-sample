package nz.test.test;

import nz.test.EnricherFactory;
import nz.test.PaperTicketPricingEnricher;

import org.junit.Test;

public class EnricherTest {

	@Test
	public void test() {
		PaperTicketPricingEnricher enricher = (PaperTicketPricingEnricher) EnricherFactory.getPaperTicketPricingEnricher();
		enricher.execute("Hello ", "World ", "Addition. ");
	}

}
