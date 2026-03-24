package pizzastore.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pizzastore.model.Ordine;
import pizzastore.model.Pizza;
import pizzastore.model.Cliente;

public class OrdineDTO {

    private Long id;
    private LocalDateTime dataOrdine;
    private Boolean closed = false;

    @NotBlank(message = "Il codice è obbligatorio")
    private String codice;
    
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private Double costoTotale;

    private Long clienteId;
    private List<Long> pizzaIds;

    private String nomeCliente;
    private List<String> pizzeDescrizione;

    public OrdineDTO() {}

    public OrdineDTO(Long id, LocalDateTime dataOrdine, Boolean closed, String codice,
                     Double costoTotale, Long clienteId, List<Long> pizzaIds) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.codice = codice;
        this.costoTotale = costoTotale;
        this.clienteId = clienteId;
        this.pizzaIds = pizzaIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public Date getDataOrdineDate() {
        if (this.dataOrdine == null) return null;
        return Date.from(this.dataOrdine.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(Double costoTotale) {
        this.costoTotale = costoTotale;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<String> getPizzeDescrizione() {
        return pizzeDescrizione;
    }

    public void setPizzeDescrizione(List<String> pizzeDescrizione) {
        this.pizzeDescrizione = pizzeDescrizione;
    }

    public Ordine buildOrdineModel(Cliente cliente, List<Pizza> pizze) {
        Ordine ordine = new Ordine();

        ordine.setId(this.id);
        ordine.setDataOrdine(this.dataOrdine);
        ordine.setClosed(this.closed);
        ordine.setCodice(this.codice);
        ordine.setCostoTotale(this.costoTotale);

        ordine.setCliente(cliente);
        ordine.setPizze(pizze);

        return ordine;
    }


    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel) {

        OrdineDTO dto = new OrdineDTO();

        dto.setId(ordineModel.getId());
        dto.setDataOrdine(ordineModel.getDataOrdine());
        dto.setClosed(ordineModel.getClosed());
        dto.setCodice(ordineModel.getCodice());
        dto.setCostoTotale(ordineModel.getCostoTotale());


        if (ordineModel.getCliente() != null) {
            dto.setClienteId(ordineModel.getCliente().getId());
            dto.setNomeCliente(ordineModel.getCliente().getNome());
        }


        if (ordineModel.getPizze() != null) {
            dto.setPizzaIds(
                    ordineModel.getPizze().stream()
                            .map(Pizza::getId)
                            .collect(Collectors.toList())
            );

            dto.setPizzeDescrizione(
                    ordineModel.getPizze().stream()
                            .map(Pizza::getDescrizione)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }


    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput) {
        return modelListInput.stream()
                .map(OrdineDTO::buildOrdineDTOFromModel)
                .collect(Collectors.toList());
    }


}