package Framework.Utils;
import com.github.javafaker.Faker;

import java.util.Locale;

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

}
