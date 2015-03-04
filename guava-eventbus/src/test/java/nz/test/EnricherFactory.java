package nz.test;

public class EnricherFactory {
	private static PaperTicketPricingEnricher paperTicketPricingEnricher = new PaperTicketPricingEnricher();

	public static AbstractEnricher<?> getPaperTicketPricingEnricher() {
		return paperTicketPricingEnricher;
	}
}
