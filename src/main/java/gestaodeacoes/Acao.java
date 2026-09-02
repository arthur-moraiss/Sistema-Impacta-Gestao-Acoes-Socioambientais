package gestaodeacoes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acao {

    private int id;
    private int maxCapacidade;
    private LocalDateTime data;
    private String descricao;
    private String titulo;


    private List<Voluntario> inscritos = new ArrayList<>();

    public Acao(int maxCapacidade, LocalDateTime data, String descricao, String titulo) {
        this.maxCapacidade = maxCapacidade;
        this.data = data;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacidade() {
        return maxCapacidade;
    }

    public void setMaxCapacidade(int maxCapacidade) {
        this.maxCapacidade = maxCapacidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public abstract int calcularPontuacao();

    public void adicionarInscrito(Voluntario voluntario){
        if(inscritos.size() >= getMaxCapacidade()){
            throw new AcaoLotadaException("That action already max capacity ");
        }
        for(Voluntario inscrito: inscritos){
            if(inscrito.getEmail().equals(voluntario.getEmail())){
                throw new InscricaoDuplicadaException("Voluntter already is registrarion in this action");
            }
        }
        inscritos.add(voluntario);
    }
}
