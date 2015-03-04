package nz.test;

public abstract class AbstractEnricher<M> {

	public void execute(String source, M otherResources, Object... additionalResources) {
		String additionResource = null;
		if (additionalResources != null) {
			additionResource = (String) additionalResources[0];
		}
		doExecute(source, otherResources, additionResource);
	}

	abstract void doExecute(String source, M otherResources, String additionResource);
}
