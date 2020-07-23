package ru.innotechnum.soapservice.generated.com.briansjavablog.accounts;

import  ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.7
 * 2020-07-23T12:16:34.530+03:00
 * Generated source version: 3.3.7
 *
 */
@WebService(targetNamespace = "http://www.briansjavablog.com/Accounts/", name = "Accounts")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Accounts {

    @WebMethod(operationName = "GetAccountDetails", action = "http://www.briansjavablog.com/Accounts/GetAccountDetails")
    @WebResult(name = "AccountDetailsResponse", targetNamespace = "http://com/blog/samples/webservices/accountservice", partName = "parameters")
    public AccountDetailsResponse getAccountDetails(

        @WebParam(partName = "parameters", name = "AccountDetailsRequest", targetNamespace = "http://com/blog/samples/webservices/accountservice")
                AccountDetailsRequest parameters
    );
}