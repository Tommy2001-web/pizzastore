package pizzastore.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzastore.dto.OrdineDTO;
import pizzastore.model.Cliente;
import pizzastore.model.Ordine;
import pizzastore.model.Pizza;
import pizzastore.service.ClienteService;
import pizzastore.service.OrdineService;
import pizzastore.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/delete/{id}")
    public String deleteOrdine(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        ordineService.disattiva(id);
        redirectAttrs.addFlashAttribute("successMessage", "Ordine eliminato correttamente");
        return "redirect:/ordine";
    }
}