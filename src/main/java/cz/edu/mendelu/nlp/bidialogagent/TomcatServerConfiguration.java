package cz.edu.mendelu.nlp.bidialogagent;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatServerConfiguration {

	@Value("${server.port.alternative}")
	private int httpPort;

	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				if (factory instanceof TomcatServletWebServerFactory) {
					TomcatServletWebServerFactory tf = (TomcatServletWebServerFactory) factory;
					Connector conn = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
					conn.setPort(httpPort);
					tf.addAdditionalTomcatConnectors(conn);
				}

			}
		};

	}
}
