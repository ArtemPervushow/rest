package pervushov.model.db;

import pervushov.model.Account;

/**
 * Created by a.pervushov on 17.11.2017.
 */
public interface AccountDAO {
    Account insert(Account account);
    Account selectById(Integer id) throws AccountRepo.AccountRepoException;
}
