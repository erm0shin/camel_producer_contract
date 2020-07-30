package ru.innotechnum.soapservice.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.springframework.stereotype.Component;

@Component
public class RouteConfiguration extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:start")
                .setHeader(CxfConstants.OPERATION_NAME, constant("GetAccountDetails"))
                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("http://www.briansjavablog.com/Accounts/"))
                .to(
                        "cxf://http://localhost:8080/soap-api/service/accounts"
                                + "?serviceClass=ru.innotechnum.soapservice.generated.com.briansjavablog.accounts.Accounts"
                )
                .log("CXF body is: ${body[0]}")
                .to("seda:end")
        ;
    }

}
