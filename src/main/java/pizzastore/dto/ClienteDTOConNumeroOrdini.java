package pizzastore.dto;

import jakarta.validation.constraints.NotBlank;
import pizzastore.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTOConNumeroOrdini {
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "l'indirizzo è obbligatorio")
    private String indirizzo;

    private Boolean attivo;

    private Integer numeroOrdini;

    public ClienteDTOConNumeroOrdini(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
    }

    public ClienteDTOConNumeroOrdini(Long id, String nome, String cognome,
                                     String indirizzo, Boolean attivo, Long numeroOrdini) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
        this.numeroOrdini = numeroOrdini.intValue();
    }

    public ClienteDTOConNumeroOrdini() {

    }

    public Integer getNumeroOrdini() {
        return numeroOrdini;
    }

    public void setNumeroOrdini(Integer numeroOrdini) {
        this.numeroOrdini = numeroOrdini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public static ClienteDTOConNumeroOrdini buildClienteDTOConNumeroOrdiniFromModel(Cliente clienteModel) {
        ClienteDTOConNumeroOrdini result = new ClienteDTOConNumeroOrdini(clienteModel.getId(), clienteModel.getNome(), clienteModel.getCognome(),
                clienteModel.getIndirizzo(), clienteModel.getAttivo());

        return result;
    }

}
