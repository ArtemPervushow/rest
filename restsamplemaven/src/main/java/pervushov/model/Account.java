package pervushov.model;

/**
 * Created by a.pervushov on 14.11.2017.
 */
public class Account {
    private Integer id;
    private String accountDetails;

    public Account() {
    }

    public Account(Integer id, String accountDetails) {
        this.id = id;
        this.accountDetails = accountDetails;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }
}
