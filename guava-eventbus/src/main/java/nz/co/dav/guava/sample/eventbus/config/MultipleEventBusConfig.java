package nz.co.dav.guava.sample.eventbus.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

@Configuration
@ComponentScan(basePackages = { "nz.co.dav.guava.sample.eventbus..publisher.complex",
		"nz.co.dav.guava.sample.eventbus..subscriber.complex" })
public class MultipleEventBusConfig {

	@Bean(autowire = Autowire.BY_NAME, name = "salesEventBus")
	public EventBus salesEventBus() {
		return new EventBus();
	}

	@Bean(autowire = Autowire.BY_NAME, name = "buysEventBus")
	public EventBus buysEventBus() {
		return new EventBus();
	}

}
