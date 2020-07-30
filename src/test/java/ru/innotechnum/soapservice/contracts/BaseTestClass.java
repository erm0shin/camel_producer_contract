package ru.innotechnum.soapservice.contracts;

import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {ContractConfiguration.class}
)
@CamelSpringBootTest
@AutoConfigureMessageVerifier
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BaseTestClass {
}
