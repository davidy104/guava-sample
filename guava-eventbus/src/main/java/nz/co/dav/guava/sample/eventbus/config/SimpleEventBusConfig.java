package nz.co.dav.guava.sample.eventbus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

@Configuration
@ComponentScan(basePackages = { "nz.co.dav.guava.sample.eventbus.publisher.simple",
		"nz.co.dav.guava.sample.eventbus.subscriber.simple" })
public class SimpleEventBusConfig {
	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
}
