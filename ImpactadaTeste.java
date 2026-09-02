package GestãodeAções;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class ImpactTest {

//Nesta classe eu estou comentando pois não vim na aula de terça feira, ai comento
// para quando vou revisar o codigo para relembrar sobre oq fiz e sobre teste unitario
    private Impacta impacta;

    @BeforeEach
    public void setUP(){
        impacta  = new Impacta();
    }


    @Test
    void cadastrarEmailDuplicadoTeste(){
    // Arrange - prepara o cenário
    impacta.cadastrarVoluntario("Joao","Joao123@g.com","123");

    // Act + Assert - tenta cadastrar de novo com o MESMO email,espera exeção
    assertThrows(IllegalArgumentException.class, () -> {
            impacta.cadastrarVoluntario("Joao L","Joao123@g.com","1234");
        });
    }

    @Test
    void cadastrarMatriculaDuplicadaTeste(){
        // Arrange - prepara o cenário
        impacta.cadastrarVoluntario("Arthur","Arthur@x.com","321");

        // Act + Asset - tenta cadastar de novo com o MESMA matricula, espera exeção

        assertThrows(IllegalArgumentException.class, () -> {
           impacta.cadastrarVoluntario("Lucas","Lucas@x.com","321");
        });
    }


    @Test
    void inscreverVoluntarioTeste () {
        // Arrange - prepara o cenário
        impacta.cadastrarVoluntario("Arthur","Arthur@x.com","123");
        int idAcao = impacta.cadastrarPlantio("Plantio X","Descrição", LocalDateTime.now(),10,5);
        // Act - executa oq você quer testar

        assertTrue(impacta.inscreverVoluntario("Arthur@x.com",idAcao));// Assert - verifica o retorno
        //assert(resultado);
    }

    @Test
    void cadastrarPlantioSucessoTeste(){

        //Arrange - prepara o cenário
       int id=  impacta.cadastrarPlantio("Plantio1","Descricao",LocalDateTime.now(),5,4);
        assertEquals(1,id);
    }


    @Test
    void cadastrarPlantioComResultadoCorretoTeste(){
        Plantio plantio = new Plantio(5,LocalDateTime.now(),"Descricao","Titulo",7);

        assertEquals("Titulo",plantio.getTitulo());

        assertEquals(7,plantio.getQtdMudas());

        assertEquals(19,plantio.calcularPontuacao());

    }

    @Test
    void cadastrarMutiraoSucessoTest(){
        int id = impacta.cadastrarMultirao("Mutirao1","Descricao",LocalDateTime.now(),2,2);
        assertEquals(1,id);

        int id2 = impacta.cadastrarMultirao("Mutirao2","Descricao2",LocalDateTime.now(),3,2);
        assertEquals(2,id2);
    }




    @Test
    void cadastarMutiraoComResultadoCorretoTeste(){
        MutiraoReciclagem mutirao = new MutiraoReciclagem(3,LocalDateTime.now(),"Descricao","Mutirao1",2);
        assertEquals("Mutirao1",mutirao.getTitulo());

        assertEquals("Descricao",mutirao.getDescricao());

        assertEquals(8,mutirao.calcularPontuacao());
    }

    @Test
    void cadastrarOficinaComResultadoCorretoComKitTeste(){
        OficinaEcologica oficina = new OficinaEcologica(5,LocalDateTime.now(),"Descricao","Oficina1",2,true);
        assertEquals("Oficina1",oficina.getTitulo());

        assertEquals(2,oficina.getDuracao());

        assertEquals(16,oficina.calcularPontuacao());
    }


    @Test
    void cadastrarOficinaComResultadoCorretoSemKitTeste(){
        OficinaEcologica oficina = new OficinaEcologica(2,LocalDateTime.now(),"Descricao","OficinaOFC",8,false);

        assertEquals("OficinaOFC",oficina.getTitulo());

        assertEquals(8,oficina.getDuracao());

        assertEquals(24,oficina.calcularPontuacao());
    }


    @Test
    void exibirVoluntarioTeste(){
        // assert
        impacta.cadastrarVoluntario("ArthurM","art@g.com","123");
        String email = impacta.getVoluntarios().get(0).getEmail();        

       // assertEquals(, impacta.exibirVoluntario(email));
        assertEquals("Nome: ArthurM | Email: art@g.com | Ações: 0 | Pontuação: 0", impacta.exibirVoluntario(email));
    }

    @Test
    void listarVoluntariosOrdenadoPorPontuacaoTeste() {
        impacta.cadastrarVoluntario("Carlos", "carlos@x.com", "1");
        impacta.cadastrarVoluntario("Ana", "ana@x.com", "2");
        impacta.cadastrarVoluntario("Bruno", "bruno@x.com", "3");

        int idAcao1 = impacta.cadastrarPlantio("Plantio A", "desc", LocalDateTime.now(), 10, 10); // pontuação alta
        int idAcao2 = impacta.cadastrarPlantio("Plantio B", "desc", LocalDateTime.now(), 10, 1);  // pontuação baixa

        impacta.inscreverVoluntario("carlos@x.com", idAcao2); // Carlos ganha pontuação baixa
        impacta.inscreverVoluntario("ana@x.com", idAcao1);    // Ana ganha pontuação alta
        // Bruno não se inscreve em nada -> pontuação 0

        String[] resultado = impacta.listarVoluntarios();

        String[] esperado = {
                "Nome do 1 voluntário: Ana",     // maior pontuação primeiro
                "Nome do 2 voluntário: Carlos",  // pontuação intermediária
                "Nome do 3 voluntário: Bruno"    // sem pontuação, por último
        };

        assertArrayEquals(esperado, resultado);
    }

    @Test
    void inscreverVoluntarioEmAcaoLotadaDeveLancarExcecao() {
        impacta.cadastrarVoluntario("Ana", "ana@x.com", "1");
        impacta.cadastrarVoluntario("Bruno", "bruno@x.com", "2");
        int idAcao = impacta.cadastrarPlantio("Plantio Lotado", "desc", LocalDateTime.now(), 1, 5);

        impacta.inscreverVoluntario("ana@x.com", idAcao);

        assertThrows(AcaoLotadaException.class, () -> {
            impacta.inscreverVoluntario("bruno@x.com", idAcao);
        });
    }

    @Test
    void inscreverMesmoVoluntarioDuasVezesDeveLancarExcecao() {
        // Arrange
        impacta.cadastrarVoluntario("Ana", "ana@x.com", "1");
        int idAcao = impacta.cadastrarPlantio("Plantio X", "desc", LocalDateTime.now(), 10, 5);

        impacta.inscreverVoluntario("ana@x.com", idAcao);

        assertThrows(InscricaoDuplicadaException.class, () -> {
            impacta.inscreverVoluntario("ana@x.com", idAcao);
        });
    }

}





