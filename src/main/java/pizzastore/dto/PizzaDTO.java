package pizzastore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import pizzastore.model.Pizza;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaDTO {

    @NotBlank
    private Long id;

    private String descrizione;
    private String ingredienti;

    @Min(value = 1L)
    private Double prezzoBase;
    private Boolean attivo;

    public PizzaDTO(Long id, String descrizione, String ingredienti, Double prezzoBase, Boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzoBase = prezzoBase;
        this.attivo = attivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public Double getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(Double prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    public Boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Pizza buildPizzaModel() {
        return new Pizza(this.id, this.descrizione, this.ingredienti, this.prezzoBase, this.attivo);
    }

    public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
        return new PizzaDTO (
                pizzaModel.getId(),
                pizzaModel.getDescrizione(),
                pizzaModel.getIngredienti(),
                pizzaModel.getPrezzoBase(),
                pizzaModel.isAttivo()
        );
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput) {
        return modelListInput.stream()
                .map(PizzaDTO::buildPizzaDTOFromModel)
                .collect(Collectors.toList());
    }

}
