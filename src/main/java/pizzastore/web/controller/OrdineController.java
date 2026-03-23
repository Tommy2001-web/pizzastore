package pizzastore.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzastore.dto.ClienteDTO;
import pizzastore.dto.OrdineDTO;
import pizzastore.dto.PizzaDTO;
import pizzastore.dto.StatsDTO;
import pizzastore.model.Cliente;
import pizzastore.model.Ordine;
import pizzastore.model.Pizza;
import pizzastore.service.ClienteService;
import pizzastore.service.OrdineService;
import pizzastore.service.PizzaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String listOrdini(Model model) {
        List<Ordine> ordini = ordineService.listAllElements();
        model.addAttribute("ordine_list_attribute", OrdineDTO.createOrdineDTOListFromModelList(ordini));
        return "ordine/list";
    }

    @GetMapping("/insert")
    public String createOrdine(Model model) {
        model.addAttribute("insert_ordine_attr", new OrdineDTO());
        model.addAttribute("cliente_list", clienteService.listAllElements());
        model.addAttribute("pizza_list", pizzaService.listAllElements());
        return "ordine/insert";
    }

    @GetMapping("/search")
    public String searchOrdine(Model model) {

        // Popola il form vuoto
        OrdineDTO ordineDTO = new OrdineDTO();
        model.addAttribute("edit_ordine_attr", ordineDTO);

        // Lista clienti attivi come DTO
        List<ClienteDTO> clientiAttivi = clienteService.listAllElements()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getAttivo()))
                .map(ClienteDTO::buildClienteDTOFromModel)
                .toList();
        model.addAttribute("cliente_list", clientiAttivi);

        // Lista pizze attive come DTO
        List<PizzaDTO> pizzeAttive = pizzaService.listAllElements()
                .stream()
                .filter(p -> Boolean.TRUE.equals(p.getAttivo()))
                .map(PizzaDTO::buildPizzaDTOFromModel)
                .toList();
        model.addAttribute("pizza_list", pizzeAttive);

        return "ordine/search"; // la tua JSP
    }

    @PostMapping("/list")
    public String listOrdiniByFilter(
            @RequestParam(required = false) String codice,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) List<Long> pizzaIds,
            Model model) {

        // Costruisco l'Ordine esempio con i filtri
        Ordine example = new Ordine();
        example.setCodice(codice);

        if (clienteId != null) {
            Cliente c = new Cliente();
            c.setId(clienteId);
            example.setCliente(c);
        }

        if (pizzaIds != null && !pizzaIds.isEmpty()) {
            List<Pizza> pizze = pizzaIds.stream().map(id -> {
                Pizza p = new Pizza();
                p.setId(id);
                return p;
            }).collect(Collectors.toList());
            example.setPizze(pizze);
        }

        // Chiamo il repository per filtrare
        List<Ordine> ordiniFiltrati = ordineService.findByExample(example);

        // Trasformo in DTO per la JSP
        List<OrdineDTO> ordiniDTO = OrdineDTO.createOrdineDTOListFromModelList(ordiniFiltrati);
        model.addAttribute("ordine_list_attribute", ordiniDTO);

        return "ordine/list"; // JSP da mostrare
    }

    @GetMapping("/show/{idOrdine}")
    public String showOrdine(@PathVariable Long idOrdine, Model model) {

        // Carica ordine con pizze inizializzate
        Ordine ordine = ordineService.caricaSingoloElementoEager(idOrdine);

        if (ordine == null) {
            // gestione ordine non trovato
            model.addAttribute("errorMessage", "Ordine non trovato");
            return "redirect:/ordine";
        }

        // Converte in DTO per la JSP
        OrdineDTO ordineDTO = OrdineDTO.buildOrdineDTOFromModel(ordine);
        model.addAttribute("show_ordine_attr", ordineDTO);

        return "ordine/show";
    }

    @PostMapping("/save")
    public String saveOrdine(@Valid @ModelAttribute("insert_ordine_attr") OrdineDTO ordineDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttrs,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cliente_list", clienteService.listAllElements());
            model.addAttribute("pizza_list", pizzaService.listAllElements());
            return "ordine/insert";
        }

        Cliente cliente = clienteService.caricaSingoloElemento(ordineDTO.getClienteId());

        List<Pizza> pizze = new ArrayList<>();
        if (ordineDTO.getPizzaIds() != null) {
            for (Long idPizza : ordineDTO.getPizzaIds()) {
                Pizza pizza = pizzaService.caricaSingoloElemento(idPizza);
                if (pizza != null) pizze.add(pizza);
            }
        }

        Ordine ordine = ordineDTO.buildOrdineModel(cliente, pizze);

        ordine.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine));

        ordineService.inserisciNuovo(ordine);

        redirectAttrs.addFlashAttribute("successMessage", "Ordine salvato correttamente");
        return "redirect:/ordine";
    }

    @GetMapping("/edit/{id}")
    public String editOrdine(@PathVariable Long id, Model model) {
        Ordine ordine = ordineService.caricaSingoloElemento(id);
        if (ordine == null) return "redirect:/ordine";

        model.addAttribute("edit_ordine_attr", OrdineDTO.buildOrdineDTOFromModel(ordine));
        model.addAttribute("cliente_list", clienteService.listAllElements());
        model.addAttribute("pizza_list", pizzaService.listAllElements());
        return "ordine/edit";
    }

    @PostMapping("/update")
    public String updateOrdine(@Valid @ModelAttribute("edit_ordine_attr") OrdineDTO ordineDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttrs,
                               Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cliente_list", clienteService.listAllElements());
            model.addAttribute("pizza_list", pizzaService.listAllElements());
            return "ordine/edit";
        }

        Cliente cliente = clienteService.caricaSingoloElemento(ordineDTO.getClienteId());

        List<Pizza> pizze = new ArrayList<>();
        if (ordineDTO.getPizzaIds() != null) {
            for (Long idPizza : ordineDTO.getPizzaIds()) {
                Pizza pizza = pizzaService.caricaSingoloElemento(idPizza);
                if (pizza != null) pizze.add(pizza);
            }
        }

        Ordine ordine = ordineDTO.buildOrdineModel(cliente, pizze);

        ordine.setCostoTotale(ordineService.calcolaPrezzoOrdine(ordine));

        ordineService.aggiorna(ordine);

        redirectAttrs.addFlashAttribute("successMessage", "Ordine aggiornato correttamente");
        return "redirect:/ordine";
    }

    @GetMapping("/delete/{idOrdine}")
    public String toggleOrdine(@PathVariable Long idOrdine,
                                RedirectAttributes redirectAttrs) {

        Ordine ordine = ordineService.caricaSingoloElemento(idOrdine);

        boolean eraChiuso = ordine.getClosed();

        ordineService.toggleAttivo(idOrdine);

        String messaggio = eraChiuso
                ? "Ordine aperto correttamente"
                : "Ordine chiuso correttamente";

        redirectAttrs.addFlashAttribute("successMessage", messaggio);

        return "redirect:/ordine";
    }

    @GetMapping("/stats")
    public String showStatsForm() {
        return "ordine/stats-form";
    }

    @PostMapping("/stats")
    public String calcolaStatistiche(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInizio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFine,
            Model model) {

        StatsDTO stats = ordineService.calcolaStatistiche(dataInizio, dataFine);

        model.addAttribute("stats", stats);
        return "ordine/stats-result";
    }
}