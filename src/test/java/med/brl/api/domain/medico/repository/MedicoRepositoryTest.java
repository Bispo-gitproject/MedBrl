package med.brl.api.domain.medico.repository;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.entities.ConsultaEntity;
import med.brl.api.domain.consulta.enums.Motivo;
import med.brl.api.domain.endereco.dto.DTOEndereco;
import med.brl.api.domain.medico.dto.DTOCadastroMedico;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.enums.Especialidade;
import med.brl.api.domain.paciente.dto.DTOCadastraPaciente;
import med.brl.api.domain.paciente.entity.PacienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //given ou arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CirurgiaPlastica);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);


        var medicoLivre = medicoRepository.escolheMedicoAliatorioDataHora(Especialidade.CirurgiaPlastica, proximaSegundaAs10);


        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
            var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.Cardiologia);

        var medicoLivre = medicoRepository.escolheMedicoAliatorioDataHora(Especialidade.Cardiologia, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }
    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente, LocalDateTime data) {
        em.persist(new ConsultaEntity(null, medico, paciente, data, false, null));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoEntity(dtoCadastroMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private PacienteEntity cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteEntity(dtoCadastraPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DTOCadastroMedico dtoCadastroMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DTOCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dtoEndereco()
        );
    }

    private DTOCadastraPaciente dtoCadastraPaciente(String nome, String email, String cpf) {
        return new DTOCadastraPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dtoEndereco()
        );
    }

    private DTOEndereco dtoEndereco() {
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