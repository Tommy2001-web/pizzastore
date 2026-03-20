package raccoltafilmspringmvc.config;

import it.prova.raccoltafilmspringmvc.model.Film;
import it.prova.raccoltafilmspringmvc.model.Regista;
import it.prova.raccoltafilmspringmvc.model.Sesso;
import it.prova.raccoltafilmspringmvc.service.FilmService;
import it.prova.raccoltafilmspringmvc.service.RegistaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DataInit {
    @Bean
    CommandLineRunner initDatabase(FilmService filmService, RegistaService registaService) {
        return args -> {

            if (filmService.listAllElements().isEmpty()) {
                // inserisci dati

                // creo registi
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                // ===== REGISTI =====
                Regista tarantino = new Regista("Quentin", "Tarantino", "QT",
                        sdf.parse("27/03/1963"), Sesso.MASCHIO);

                Regista nolan = new Regista("Christopher", "Nolan", "CN",
                        sdf.parse("30/07/1970"), Sesso.MASCHIO);

                Regista scorsese = new Regista("Martin", "Scorsese", "MS",
                        sdf.parse("17/11/1942"), Sesso.MASCHIO);

                registaService.inserisciNuovo(tarantino);
                registaService.inserisciNuovo(nolan);
                registaService.inserisciNuovo(scorsese);

                // ===== FILM =====
                Film pulpFiction = new Film("Pulp Fiction", "Crime",
                        sdf.parse("14/10/1994"), 154, tarantino);

                Film django = new Film("Django Unchained", "Western",
                        sdf.parse("25/12/2012"), 165, tarantino);

                Film inception = new Film("Inception", "Sci-Fi",
                        sdf.parse("16/07/2010"), 148, nolan);

                Film interstellar = new Film("Interstellar", "Sci-Fi",
                        sdf.parse("07/11/2014"), 169, nolan);

                Film taxiDriver = new Film("Taxi Driver", "Drama",
                        sdf.parse("08/02/1976"), 114, scorsese);

                filmService.inserisciNuovo(pulpFiction);
                filmService.inserisciNuovo(django);
                filmService.inserisciNuovo(inception);
                filmService.inserisciNuovo(interstellar);
                filmService.inserisciNuovo(taxiDriver);

            }
        };
    }
}
