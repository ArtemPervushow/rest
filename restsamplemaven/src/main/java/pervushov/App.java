package pervushov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Created by a.pervushov on 14.11.2017.
 */
@SpringBootApplication(scanBasePackages = "pervushov", exclude = { DataSourceAutoConfiguration.class })
public class App {
    public static void main(String[] args) {
            SpringApplication.run(App.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:account.db");
        return dataSourceBuilder.build();
    }
}
