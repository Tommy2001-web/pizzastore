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
import pizzastore.model.Cliente;
import pizzastore.service.ClienteService;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @GetMapping
    public ModelAndView listAllClienti() {
        ModelAndView mv = new ModelAndView();
        List<Cliente> clienti = clienteService.listAllElements();
        mv.addObject("cliente_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienti));
        mv.setViewName("cliente/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createCliente(Model model) {
        model.addAttribute("insert_cliente_attr", new ClienteDTO());
        return "cliente/insert";
    }

    @PostMapping("/save")
    public String saveCliente(@Valid @ModelAttribute("insert_cliente_attr") ClienteDTO clienteDTO, BindingResult result,
                           RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "cliente/insert";
        }
        clienteService.inserisciNuovo(clienteDTO.buildClienteModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cliente";
    }

    @GetMapping("/search")
    public String searchCliente() {
        return "cliente/search";
    }

    @PostMapping("/list")
    public String listClienti(ClienteDTO clienteExample, ModelMap model) {

        List<Cliente> clienti = clienteService.findByExample(clienteExample.buildClienteModel());

        model.addAttribute("cliente_list_attribute", ClienteDTO.createClienteDTOListFromModelList(clienti));
        return "cliente/list";
    }

    @GetMapping("/show/{idCliente}")
    public String showCliente(@PathVariable(required = true) Long idCliente, Model model) {
        model.addAttribute("show_cliente_attr",
                ClienteDTO.buildClienteDTOFromModel(clienteService.caricaSingoloElemento(idCliente)));
        return "cliente/show";
    }

    @GetMapping("/edit/{idCliente}")
    public String editCliente(@PathVariable(required = true) Long idCliente, Model model) {
        model.addAttribute("edit_cliente_attr",
                ClienteDTO.buildClienteDTOFromModel(clienteService.caricaSingoloElemento(idCliente)));
        return "cliente/edit";
    }

    @PostMapping("/update")
    public String updateCliente(@Valid @ModelAttribute("edit_cliente_attr") ClienteDTO clienteDTO, BindingResult result,
                                RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "cliente/edit";
        }
        clienteService.aggiorna(clienteDTO.buildClienteModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cliente";
    }

}
