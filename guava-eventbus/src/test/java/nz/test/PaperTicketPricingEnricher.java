package nz.test;

public class PaperTicketPricingEnricher extends AbstractEnricher<String> {

	@Override
	void doExecute(String source, String otherResources, String additionResource) {
		System.out.println("result:{} " + source + otherResources + additionResource);
	}

}
