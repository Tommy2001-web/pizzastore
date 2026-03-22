package pizzastore.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzastore.dto.ClienteDTO;
import pizzastore.dto.PizzaDTO;
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
}
