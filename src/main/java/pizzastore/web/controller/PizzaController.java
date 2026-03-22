package pizzastore.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzastore.dto.ClienteDTO;
import pizzastore.dto.PizzaDTO;
import pizzastore.dto.PizzaDTO;
import pizzastore.model.Cliente;
import pizzastore.model.Pizza;
import pizzastore.service.PizzaService;

import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ModelAndView listAllPizze() {
        ModelAndView mv = new ModelAndView();
        List<Pizza> pizze = pizzaService.listAllElements();
        mv.addObject("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        mv.setViewName("pizza/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createPizza(Model model) {
        model.addAttribute("insert_pizza_attr", new PizzaDTO());
        return "pizza/insert";
    }

    @PostMapping("/save")
    public String savePizza(@Valid @ModelAttribute("insert_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                              RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "pizza/insert";
        }
        pizzaDTO.setAttivo(true);
        pizzaService.inserisciNuovo(pizzaDTO.buildPizzaModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }
    @GetMapping("/edit/{idPizza}")
    public String editPizza(@PathVariable(required = true) Long idPizza, Model model) {
        model.addAttribute("edit_pizza_attr",
                PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloElemento(idPizza)));
        return "pizza/edit";
    }

    @PostMapping("/update")
    public String updatePizza(@Valid @ModelAttribute("edit_pizza_attr") PizzaDTO pizzaDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "pizza/edit";
        }
        pizzaService.aggiorna(pizzaDTO.buildPizzaModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/pizza";
    }

    @GetMapping("/search")
    public String searchPizza() {
        return "pizza/search";
    }

    @PostMapping("/list")
    public String listPizze(PizzaDTO pizzaExample, ModelMap model) {

        System.out.println("Ricerca Pizza - descrizione: " + pizzaExample.getDescrizione());
        System.out.println("ingredienti: " + pizzaExample.getIngredienti());
        System.out.println("prezzoBase: " + pizzaExample.getPrezzoBase());
        List<Pizza> pizze = pizzaService.findByExample(pizzaExample.buildPizzaModel());

        model.addAttribute("pizza_list_attribute", PizzaDTO.createPizzaDTOListFromModelList(pizze));
        return "pizza/list";
    }

    @GetMapping("/show/{idPizza}")
    public String showCliente(@PathVariable(required = true) Long idPizza, Model model) {
        model.addAttribute("show_pizza_attr",
                PizzaDTO.buildPizzaDTOFromModel(pizzaService.caricaSingoloElemento(idPizza)));
        return "pizza/show";
    }
}
