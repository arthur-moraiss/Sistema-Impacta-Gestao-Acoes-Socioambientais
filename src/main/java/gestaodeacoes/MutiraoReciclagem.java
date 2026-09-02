package gestaodeacoes;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends Acao{
    private int duracaoEmHoras;

    public MutiraoReciclagem(int maxCapacidade, LocalDateTime data, String descricao, String titulo,int duracaoEmHoras) {

        super(maxCapacidade, data, descricao, titulo);

        this.duracaoEmHoras = duracaoEmHoras;



    }

    public int getDuracaoEmHoras() {
        return duracaoEmHoras;
    }

    public void setDuracaoEmHoras(int duracaoEmHoras) {
        this.duracaoEmHoras = duracaoEmHoras;
    }

    public int calcularPontuacao(){
        return 4 * duracaoEmHoras;
    }
}
