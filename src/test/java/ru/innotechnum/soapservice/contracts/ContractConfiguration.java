package ru.innotechnum.soapservice.contracts;

import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(CamelAutoConfiguration.class)
public class ContractConfiguration {

    private final CamelContext camelContext;

    @Autowired
    public ContractConfiguration(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Bean
    public MessageVerifier<Message> camelMessageVerifier() {
        return new CamelMessageVerifier(camelContext);
    }

}
