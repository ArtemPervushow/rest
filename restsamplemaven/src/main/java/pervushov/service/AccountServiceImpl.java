package pervushov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pervushov.model.Account;
import pervushov.model.db.AccountDAO;
import pervushov.model.db.AccountRepo;
import pervushov.service.thirdparty.AccountDetailService;

/**
 * Created by a.pervushov on 17.11.2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    private AccountDetailService accountDetailService;

    @Autowired
    public void setAccountDetailService(AccountDetailService accountDetailService) {
        this.accountDetailService = accountDetailService;
    }

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account getAccountById(Integer id) {
        Account account = new Account();
        try {
            account = accountDAO.selectById(id);
        } catch (AccountRepo.AccountRepoException e) {
            account = new Account(id, "");
            account = accountDAO.insert(account);
        }
        account.setAccountDetails(accountDetailService.getAccountDetailsById(id));

        return account;
    }
}
