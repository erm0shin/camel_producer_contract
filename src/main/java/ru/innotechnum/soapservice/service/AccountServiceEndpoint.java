package ru.innotechnum.soapservice.service;

import org.springframework.stereotype.Service;
import ru.innotechnum.soapservice.generated.com.blog.samples.webservices.accountservice.*;
import ru.innotechnum.soapservice.generated.com.briansjavablog.accounts.Accounts;


@Service
public class AccountServiceEndpoint implements Accounts {
    @Override
    public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {
        ObjectFactory factory = new ObjectFactory();
        AccountDetailsResponse response = factory.createAccountDetailsResponse();
        Account account = factory.createAccount();
        account.setAccountNumber("12345");
        account.setAccountStatus(EnumAccountStatus.ACTIVE);
        account.setAccountName("Joe Bloggs");
        account.setAccountBalance(3400);
        response.setAccountDetails(account);
        return response;
    }
}
