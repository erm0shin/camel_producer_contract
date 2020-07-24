package ru.innotechnum.soapservice;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ServiceStatus;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.AccountDetailsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CamelSpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InvokeSoapServiceRouteBuilderTest {

    @Autowired
    protected CamelContext camelContext;

    @Autowired
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:output")
    MockEndpoint mockOutput;

    @Test
    public void testInvokeSoapService() throws Exception {
        assertEquals(ServiceStatus.Started, camelContext.getStatus());

        mockOutput.expectedMessageCount(1);

        AccountDetailsRequest request = new AccountDetailsRequest();
        request.setAccountNumber("123");
        template.sendBody("direct:start", request);

        mockOutput.assertIsSatisfied();

        AccountDetailsResponse response = mockOutput.getExchanges().get(0).getMessage().getBody(AccountDetailsResponse.class);
        assertEquals("Joe Bloggs", response.getAccountDetails().getAccountName());
    }

}