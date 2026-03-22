package pizzastore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "ingrediente")
    private String ingredienti;

    @Column(name = "prezzo_base")
    private Double prezzoBase;

    @Column(name = "attivo")
    private Boolean attivo = true;

    @ManyToMany(mappedBy = "pizze")
    private List<Ordine> ordini;

    public Pizza(@NotBlank Long id, String descrizione, String ingredienti, @Min(value = 1L) Double prezzoBase, boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzoBase = prezzoBase;
        this.attivo = attivo;
    }

    public Pizza() {

    }

    public Pizza(Long id, @NotBlank(message = "Il campo descrizione è obbligatorio") String descrizione, @NotBlank(message = "Il campo ingredienti è obbligatorio") String ingredienti, @NotNull(message = "Il prezzo è obbligatorio") @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0") Double prezzoBase) {
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

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }
}
