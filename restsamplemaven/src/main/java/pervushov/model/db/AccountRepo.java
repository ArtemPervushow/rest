package pervushov.model.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import pervushov.model.Account;

import java.sql.PreparedStatement;

/**
 * Created by a.pervushov on 17.11.2017.
 */
@Component
public class AccountRepo implements AccountDAO{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account insert(Account account) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO account (id ,details) VALUES (?,?)");
            statement.setInt(1, account.getId());
            statement.setString(2, account.getAccountDetails());
            return statement;
        }, keyHolder);
        account.setId((Integer)keyHolder.getKey());
        return account;
    }

    @Override
    public Account selectById(Integer id) throws AccountRepoException {
        try {
            return jdbcTemplate.queryForObject("SELECT id, details FROM account WHERE id = ?", new BeanPropertyRowMapper<Account>(Account.class), id);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new AccountRepoException();
        }
    }

    public class AccountRepoException extends Exception{

    }
}
