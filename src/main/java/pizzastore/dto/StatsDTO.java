package pizzastore.dto;

import java.util.List;

public class StatsDTO {

    private Double ricaviTotali;
    private Double costiTotali;
    private Long numeroOrdini;
    private Long numeroPizze;
    private List<ClienteDTO> clientiVirtuosi;

    public Double getRicaviTotali() {
        return ricaviTotali;
    }

    public void setRicaviTotali(Double ricaviTotali) {
        this.ricaviTotali = ricaviTotali;
    }

    public Double getCostiTotali() {
        return costiTotali;
    }

    public void setCostiTotali(Double costiTotali) {
        this.costiTotali = costiTotali;
    }

    public Long getNumeroOrdini() {
        return numeroOrdini;
    }

    public void setNumeroOrdini(Long numeroOrdini) {
        this.numeroOrdini = numeroOrdini;
    }

    public Long getNumeroPizze() {
        return numeroPizze;
    }

    public void setNumeroPizze(Long numeroPizze) {
        this.numeroPizze = numeroPizze;
    }

    public List<ClienteDTO> getClientiVirtuosi() {
        return clientiVirtuosi;
    }

    public void setClientiVirtuosi(List<ClienteDTO> clientiVirtuosi) {
        this.clientiVirtuosi = clientiVirtuosi;
    }
}
