package pizzastore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizzastore.model.Cliente;
import pizzastore.model.Pizza;
import pizzastore.model.Ordine;
import pizzastore.service.ClienteService;
import pizzastore.service.PizzaService;
import pizzastore.service.OrdineService;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(ClienteService clienteService,
                                   PizzaService pizzaService,
                                   OrdineService ordineService) {

        return args -> {

            if (clienteService.listAllElements().isEmpty()) {

                // ===== CLIENTI =====
                Cliente mario = new Cliente();
                mario.setNome("Mario");
                mario.setCognome("Rossi");
                mario.setIndirizzo("Via Roma 1");

                Cliente luigi = new Cliente();
                luigi.setNome("Luigi");
                luigi.setCognome("Verdi");
                luigi.setIndirizzo("Via Milano 10");

                Cliente anna = new Cliente();
                anna.setNome("Anna");
                anna.setCognome("Bianchi");
                anna.setIndirizzo("Via Napoli 5");

                clienteService.inserisciNuovo(mario);
                clienteService.inserisciNuovo(luigi);
                clienteService.inserisciNuovo(anna);

                // ===== PIZZE =====
                Pizza margherita = new Pizza();
                margherita.setDescrizione("Margherita");
                margherita.setIngredienti("Pomodoro, mozzarella, basilico");
                margherita.setPrezzoBase(5.0);

                Pizza diavola = new Pizza();
                diavola.setDescrizione("Diavola");
                diavola.setIngredienti("Pomodoro, mozzarella, salame piccante");
                diavola.setPrezzoBase(7.5);

                Pizza boscaiola = new Pizza();
                boscaiola.setDescrizione("Boscaiola");
                boscaiola.setIngredienti("Mozzarella, funghi, salsiccia");
                boscaiola.setPrezzoBase(8.0);

                pizzaService.inserisciNuovo(margherita);
                pizzaService.inserisciNuovo(diavola);
                pizzaService.inserisciNuovo(boscaiola);

                // ===== ORDINI =====
                Ordine ordine1 = new Ordine();
                ordine1.setCliente(mario);
                ordine1.setDataOrdine(LocalDateTime.now());
                ordine1.setCodice("ORD001");
                ordine1.setClosed(true);
                ordine1.setPizze(List.of(margherita));

                ordine1.setCostoTotale(
                        ordineService.calcolaPrezzoOrdine(ordine1)
                );

                Ordine ordine2 = new Ordine();
                ordine2.setCliente(luigi);
                ordine2.setDataOrdine(LocalDateTime.now().minusDays(1));
                ordine2.setCodice("ORD002");
                ordine2.setClosed(false);
                ordine2.setPizze(List.of(diavola));

                ordine2.setCostoTotale(
                        ordineService.calcolaPrezzoOrdine(ordine2)
                );

                Ordine ordine3 = new Ordine();
                ordine3.setCliente(anna);
                ordine3.setDataOrdine(LocalDateTime.now().minusDays(2));
                ordine3.setCodice("ORD003");
                ordine3.setClosed(true);
                ordine3.setPizze(List.of(boscaiola));

                ordine3.setCostoTotale(
                        ordineService.calcolaPrezzoOrdine(ordine3)
                );

                ordineService.inserisciNuovo(ordine1);
                ordineService.inserisciNuovo(ordine2);
                ordineService.inserisciNuovo(ordine3);
            }
        };
    }
}