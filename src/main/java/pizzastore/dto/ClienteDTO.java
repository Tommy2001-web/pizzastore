package pizzastore.dto;

import jakarta.validation.constraints.NotBlank;
import pizzastore.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "l'indirizzo è obbligatorio")
    private String indirizzo;

    private Boolean attivo;

    public ClienteDTO(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
    }

    public ClienteDTO() {

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

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Cliente buildClienteModel() {
        return new Cliente(this.id, this.nome, this.cognome, this.indirizzo, this.attivo);
    }

    public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel) {
        ClienteDTO result = new ClienteDTO(clienteModel.getId(), clienteModel.getNome(), clienteModel.getCognome(),
                clienteModel.getIndirizzo(), clienteModel.getAttivo());

        return result;
    }

    public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput) {
        return modelListInput.stream().map(ClienteDTO::buildClienteDTOFromModel).collect(Collectors.toList());
    }
}
