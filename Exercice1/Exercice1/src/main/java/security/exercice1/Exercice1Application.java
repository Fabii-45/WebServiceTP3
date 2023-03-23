package security.exercice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import security.exercice1.modele.FacadeUtilisateurs;

@SpringBootApplication
public class Exercice1Application {
    @Autowired
    FacadeUtilisateurs facadeUtilisateurs;

    public static void main(String[] args) {
        SpringApplication.run(Exercice1Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            facadeUtilisateurs.inscrireUtilisateur("yohan.boichut@univ-orleans.fr",
                    "monMotDePasse");
            facadeUtilisateurs.inscrireUtilisateur("gerard.menvussaa@etu.univ-orleans.fr",
                    "sonMotDePasse");
        };
    }

}
