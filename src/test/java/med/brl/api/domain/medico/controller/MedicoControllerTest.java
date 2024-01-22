package med.brl.api.domain.medico.controller;

import med.brl.api.domain.endereco.dto.DTOEndereco;
import med.brl.api.domain.endereco.entities.EnderecoEntity;
import med.brl.api.domain.medico.dto.DTOCadastroMedico;
import med.brl.api.domain.medico.dto.DTODetalhaMedico;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.enums.Especialidade;
import med.brl.api.domain.medico.repository.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DTOCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DTODetalhaMedico> dadosDetalhaMedicoJson;

    @Autowired
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria devolver HTTP 400 quando informações estão invalidas")
    @WithMockUser
    void cadastraMedicoCenario1() throws Exception{

        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DTOCadastroMedico(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Especialidade.Cardiologia,
                dadosEndereco());

        MedicoEntity medicoEntity = new MedicoEntity(dadosCadastro);
        when(repository.save(any(MedicoEntity.class))).thenReturn(medicoEntity);

        var response = mvc
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroMedicoJson.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DTODetalhaMedico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new EnderecoEntity(dadosCadastro.endereco())
        );
        var jsonEsperado = dadosDetalhaMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DTOEndereco dadosEndereco() {
        return new DTOEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}