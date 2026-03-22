package pizzastore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pizzastore.model.Pizza;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaDTO {


    private Long id;

    @NotBlank(message = "Il campo descrizione è obbligatorio")
    private String descrizione;

    @NotBlank(message = "Il campo ingredienti è obbligatorio")
    private String ingredienti;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private Double prezzoBase;

    private Boolean attivo = true;

    public PizzaDTO(Long id, String descrizione, String ingredienti, Double prezzoBase, Boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzoBase = prezzoBase;
        this.attivo = attivo;
    }

    public PizzaDTO() {

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

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Pizza buildPizzaModel() {
        return new Pizza(this.id, this.descrizione, this.ingredienti, this.prezzoBase);
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
