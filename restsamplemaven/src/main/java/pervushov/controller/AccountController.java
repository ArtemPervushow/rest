package pervushov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pervushov.model.Account;
import pervushov.service.AccountService;

/**
 * Created by a.pervushov on 14.11.2017.
 */
@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@RequestBody Account account) {
        return null;
    }

    @RequestMapping(value = "/accounts/{id}")
    public Account selectAccount(@PathVariable Integer id) {

        return accountService.getAccountById(id);
    }
}
