package med.brl.api.domain.endereco.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.brl.api.domain.endereco.dto.DTOEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public EnderecoEntity(DTOEndereco endereco) {

        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();

    }

    public void atualizarInformacoes(DTOEndereco endereco) {
    }
    public void atualizarInformacoes(EnderecoEntity dados) {
        if (dados.logradouro != null) {
            this.logradouro = dados.logradouro;
        }
        if (dados.bairro != null) {
            this.bairro = dados.bairro;
        }
        if (dados.cep != null) {
            this.cep = dados.cep;
        }
        if (dados.uf != null) {
            this.uf = dados.uf;
        }
        if (dados.cidade != null) {
            this.cidade = dados.cidade;
        }
        if (dados.numero != null) {
            this.numero = dados.numero;
        }
        if (dados.complemento != null) {
            this.complemento = dados.complemento;
        }
    }
}
