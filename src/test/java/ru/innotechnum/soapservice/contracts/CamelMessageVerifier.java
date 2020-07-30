package ru.innotechnum.soapservice.contracts;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.camel.CamelStubMessages;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CamelMessageVerifier implements MessageVerifier<Message> {

    private static final Logger log = LoggerFactory.getLogger(CamelStubMessages.class);

    private final CamelContext context;

    private final ContractVerifierCamelMessageBuilder builder;

    @Autowired
    public CamelMessageVerifier(CamelContext context) {
        this.context = context;
        this.builder = new ContractVerifierCamelMessageBuilder(context);
    }

    @Override
    public void send(Message message, String destination) {
        try {
            ProducerTemplate producerTemplate = this.context.createProducerTemplate();
            Exchange exchange = new DefaultExchange(this.context);
            exchange.setIn(message);
            producerTemplate.send(destination, exchange);
        }
        catch (Exception e) {
            log.error("Exception occurred while trying to send a message [" + message
                    + "] " + "to a channel with name [" + destination + "]", e);
            throw e;
        }
    }

    @Override
    public <T> void send(T payload, Map<String, Object> headers, String destination) {
        send(this.builder.create(payload, headers), destination);
    }

    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit) {
        try {
            ConsumerTemplate consumerTemplate = this.context.createConsumerTemplate();
            Exchange exchange = consumerTemplate.receive(destination,
                    timeUnit.toMillis(timeout));
            return exchange != null ? exchange.getIn() : null;
        }
        catch (Exception e) {
            log.error("Exception occurred while trying to read a message from "
                    + " a channel with name [" + destination + "]", e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Message receive(String destination) {
        return receive(destination, 5, TimeUnit.SECONDS);
    }

}
