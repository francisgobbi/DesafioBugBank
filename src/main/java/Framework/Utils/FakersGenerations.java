package Framework.Utils;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static Framework.Report.ReportFactory.driver;

public class FakersGenerations {
    private Faker faker;
    private String name;
    private String email;
    private String senha;

    public FakersGenerations() {
        faker = new Faker(new Locale("pt-BR"));
    }

    public String getName() {
        name = faker.name().name();
        return name;
    }

    public String getEmail() {
        email = faker.internet().emailAddress();
        return email;
    }

    public String getSenha() {
        senha = faker.internet().password();
        return senha;
    }

    public String getSegundoName() {
        name = faker.name().name();
        return name;
    }

    public String getSegundoEmail() {
        email = faker.internet().emailAddress();
        return email;
    }

    public String getSegundaSenha() {
        senha = faker.internet().password();
        return senha;
    }

}
