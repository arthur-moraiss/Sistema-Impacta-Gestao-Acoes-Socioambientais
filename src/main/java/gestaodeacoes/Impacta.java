package GestãodeAções;

import java.time.LocalDateTime;
import java.util.*;

public class Impacta {

    private int proximoId = 0;

    public int getProximoId() {
        return proximoId;
    }

    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }

    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(ArrayList<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public ArrayList<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(ArrayList<Acao> acoes) {
        this.acoes = acoes;
    }

    private ArrayList<Voluntario> voluntarios = new ArrayList<>();
    private ArrayList<Acao> acoes = new ArrayList<>();

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {

        if (nome.trim().isEmpty() || email.trim().isEmpty() || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Name, email e matricula is required.");
        }

        for (Voluntario voluntario : voluntarios) {
            if (email.equals(voluntario.getEmail())) {
                throw new IllegalArgumentException("This email address already exists");
            }
        }

        for(Voluntario voluntario : voluntarios){
            if(matricula.equals(voluntario.getMatricula())){
                throw new IllegalArgumentException("This Enrollment already exist");
            }
        }

        Voluntario voluntario = new Voluntario(nome,email,matricula);

        voluntarios.add(voluntario);

        return true;
    }

    public String exibirVoluntario(String email) {

        for (Voluntario voluntario : voluntarios) {

            if (email.equals(voluntario.getEmail())) {

                return "Nome: " + voluntario.getNome()
                        + " | Email: " + voluntario.getEmail()
                        + " | Ações: " + voluntario.getQuantidadeDeAcoes()
                        + " | Pontuação: " + voluntario.getPontuacaoAcumulada();
            }
        }

        throw new IllegalArgumentException("Do not display the email address");
    }

    public String[] listarVoluntarios() {

        List<Voluntario> ordenados = new ArrayList<>(voluntarios);

        Comparator<Voluntario> comparador = (v1, v2) -> v2.getPontuacaoAcumulada() - v1.getPontuacaoAcumulada();
        comparador = comparador.thenComparing(Voluntario::getNome);

        Collections.sort(ordenados, comparador);

        String[] resultado = new String[ordenados.size()];
        int quantidade = 0;

        for (Voluntario voluntario : ordenados) {
            resultado[quantidade] = "Nome do " + (quantidade + 1)
                    + " voluntário: " + voluntario.getNome();
            quantidade++;
        }

        return resultado;
    }


    int cadastrarPlantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas){
        if (titulo.trim().isEmpty() ||  descricao.trim().isEmpty() || data == null || maxParticipantes <=0 || qtdMudas <=0 ){
            throw new IllegalArgumentException("title, describe,data , maxpeople and qtdMudas is required");
        }
        for (Acao acao : acoes){
            if(titulo.equals(acao.getTitulo())){
                throw new IllegalArgumentException("It already exists this title");
            }
        }
        Plantio plantio = new Plantio(maxParticipantes,data, descricao, titulo, qtdMudas);


        plantio.setId(++proximoId);

        acoes.add(plantio);


        return plantio.getId();
    }

    int cadastrarMultirao(String titulo,String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras){
        if(titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0){
            throw new IllegalArgumentException("title, describe, data, maxpeploe and duracaohoras is required ");
        }
        for(Acao acao : acoes ){
            if (titulo.equals(acao.getTitulo())){
                throw new IllegalArgumentException("it alreday exist the title");
            }

        }
        MutiraoReciclagem mutirao = new MutiraoReciclagem(maxParticipantes,data,descricao,titulo,duracaoHoras);

        mutirao.setId(++proximoId);

        acoes.add(mutirao);

        return mutirao.getId();
    }

    int cadastrarOficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial){
        if(titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <=0 || duracaoHoras <= 0){
            throw new IllegalArgumentException("title, describe, data, maxpeploe and duracaohoras is required ");

        }
        for (Acao acao : acoes){
            if(titulo.equals(acao.getTitulo())){
                throw new IllegalArgumentException("it alreday exist the title");
            }
        }

        OficinaEcologica oficial = new OficinaEcologica(maxParticipantes,data,descricao,titulo,duracaoHoras,kitMaterial);
        oficial.setId(++proximoId);

        acoes.add(oficial);

        return oficial.getId();
    }

    boolean inscreverVoluntario(String emailVoluntario, int idAcao){
        Voluntario voluntario = null;
        for(Voluntario v : voluntarios){
            if(emailVoluntario.equals(v.getEmail())){
                voluntario = v;
            }
        }

        if(voluntario == null){
            throw new IllegalArgumentException("Voluntario not found");
        }

        Acao acao = null;
        for(Acao a : acoes){
            if(a.getId() == idAcao){
                acao = a;
            }
        }

        if(acao == null){
            throw new IllegalArgumentException("Acao not found");
        }

        acao.adicionarInscrito(voluntario);

        voluntario.setPontuacaoAcumulada(voluntario.getPontuacaoAcumulada() + acao.calcularPontuacao());
        voluntario.setQuantidadeDeAcoes(voluntario.getQuantidadeDeAcoes() + 1);

        return true;
    }
}
