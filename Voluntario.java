package GestãodeAções;

public class Voluntario {

    private String nome;
    private String email;
    private String matricula;
    private int quantidadeDeAcoes;
    private int pontuacaoAcumulada;


    //CRIAR CONSTRUTO


    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeDeAcoes = 0;
        this.pontuacaoAcumulada = 0;
    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getQuantidadeDeAcoes() {
        return quantidadeDeAcoes;
    }

    public void setQuantidadeDeAcoes(int quantidadeDeAcoes) {
        this.quantidadeDeAcoes = quantidadeDeAcoes;
    }

    public int getPontuacaoAcumulada() {
        return pontuacaoAcumulada;
    }

    public void setPontuacaoAcumulada(int pontuacaoAcumulada) {
        this.pontuacaoAcumulada = pontuacaoAcumulada;
    }
}
