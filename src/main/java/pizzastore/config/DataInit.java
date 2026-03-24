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

                Cliente maria = new Cliente();
                maria.setNome("Maria");
                maria.setCognome("Neri");
                maria.setIndirizzo("Via Firenze 30");

                clienteService.inserisciNuovo(mario);
                clienteService.inserisciNuovo(luigi);
                clienteService.inserisciNuovo(anna);
                clienteService.inserisciNuovo(maria);

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

                Pizza napoli = new Pizza();
                napoli.setDescrizione("Napoli");
                napoli.setIngredienti("Pomodoro, alici, origano");
                napoli.setPrezzoBase(8.0);

                Pizza quattroFormaggi = new Pizza();
                quattroFormaggi.setDescrizione("Quattro formaggi");
                quattroFormaggi.setIngredienti("Gorgonzola, fior di latte, stracchino, pecorino");
                quattroFormaggi.setPrezzoBase(8.0);

                Pizza marinara = new Pizza();
                marinara.setDescrizione("Marinara");
                marinara.setIngredienti("Pomodoro, origano, olio");
                marinara.setPrezzoBase(5.0);

                // Salvo tutte le pizze prima di creare gli ordini
                pizzaService.inserisciNuovo(margherita);
                pizzaService.inserisciNuovo(diavola);
                pizzaService.inserisciNuovo(boscaiola);
                pizzaService.inserisciNuovo(napoli);
                pizzaService.inserisciNuovo(quattroFormaggi);
                pizzaService.inserisciNuovo(marinara);

                // ===== ORDINI =====
                Ordine ordine1 = new Ordine();
                ordine1.setCliente(mario);
                ordine1.setDataOrdine(LocalDateTime.now());
                ordine1.setCodice("ORD001");
                ordine1.setClosed(true);
                ordine1.setPizze(List.of(margherita));
                ordine1.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine1));

                Ordine ordine2 = new Ordine();
                ordine2.setCliente(luigi);
                ordine2.setDataOrdine(LocalDateTime.now().minusDays(1));
                ordine2.setCodice("ORD002");
                ordine2.setClosed(false);
                ordine2.setPizze(List.of(diavola));
                ordine2.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine2));

                Ordine ordine3 = new Ordine();
                ordine3.setCliente(anna);
                ordine3.setDataOrdine(LocalDateTime.now().minusDays(2));
                ordine3.setCodice("ORD003");
                ordine3.setClosed(true);
                ordine3.setPizze(List.of(boscaiola));
                ordine3.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine3));

                Ordine ordine4 = new Ordine();
                ordine4.setCliente(mario);
                ordine4.setDataOrdine(LocalDateTime.now());
                ordine4.setCodice("ORD004");
                ordine4.setClosed(true);
                ordine4.setPizze(List.of(napoli));
                ordine4.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine4));

                Ordine ordine5 = new Ordine();
                ordine5.setCliente(mario);
                ordine5.setDataOrdine(LocalDateTime.now());
                ordine5.setCodice("ORD005");
                ordine5.setClosed(true);
                ordine5.setPizze(List.of(boscaiola));
                ordine5.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine5));

                Ordine ordine6 = new Ordine();
                ordine6.setCliente(mario);
                ordine6.setDataOrdine(LocalDateTime.now());
                ordine6.setCodice("ORD006");
                ordine6.setClosed(true);
                ordine6.setPizze(List.of(quattroFormaggi, marinara, boscaiola, margherita));
                ordine6.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine6));

                Ordine ordine7 = new Ordine();
                ordine7.setCliente(mario);
                ordine7.setDataOrdine(LocalDateTime.now());
                ordine7.setCodice("ORD007");
                ordine7.setClosed(true);
                ordine7.setPizze(List.of(margherita, marinara, napoli));
                ordine7.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine7));

                Ordine ordine8 = new Ordine();
                ordine8.setCliente(mario);
                ordine8.setDataOrdine(LocalDateTime.now());
                ordine8.setCodice("ORD008");
                ordine8.setClosed(true);
                ordine8.setPizze(List.of(quattroFormaggi, marinara));
                ordine8.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine8));

                Ordine ordine9 = new Ordine();
                ordine9.setCliente(mario);
                ordine9.setDataOrdine(LocalDateTime.now());
                ordine9.setCodice("ORD009");
                ordine9.setClosed(true);
                ordine9.setPizze(List.of(boscaiola, margherita, marinara));
                ordine9.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine9));

                Ordine ordine10 = new Ordine();
                ordine10.setCliente(mario);
                ordine10.setDataOrdine(LocalDateTime.now());
                ordine10.setCodice("ORD010");
                ordine10.setClosed(true);
                ordine10.setPizze(List.of(boscaiola, marinara));
                ordine10.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine10));

                Ordine ordine11 = new Ordine();
                ordine11.setCliente(mario);
                ordine11.setDataOrdine(LocalDateTime.now());
                ordine11.setCodice("ORD011");
                ordine11.setClosed(true);
                ordine11.setPizze(List.of(marinara, boscaiola));
                ordine11.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine11));

                // Salva tutti gli ordini
                ordineService.inserisciNuovo(ordine1);
                ordineService.inserisciNuovo(ordine2);
                ordineService.inserisciNuovo(ordine3);
                ordineService.inserisciNuovo(ordine4);
                ordineService.inserisciNuovo(ordine5);
                ordineService.inserisciNuovo(ordine6);
                ordineService.inserisciNuovo(ordine7);
                ordineService.inserisciNuovo(ordine8);
                ordineService.inserisciNuovo(ordine9);
                ordineService.inserisciNuovo(ordine10);
                ordineService.inserisciNuovo(ordine11);
            }
        };
    }
}