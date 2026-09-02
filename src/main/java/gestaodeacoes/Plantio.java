package GestãodeAções;

import java.time.LocalDateTime;

public class Plantio extends Acao{

    private int qtdMudas;


    public Plantio(int maxCapacidade, LocalDateTime data, String descricao, String titulo, int qtdMudas) {
        super(maxCapacidade, data, descricao, titulo);

        this.qtdMudas = qtdMudas;
    }



    public int getQtdMudas() {
        return qtdMudas;
    }

    public void setQtdMudas(int qtdMudas) {
        this.qtdMudas = qtdMudas;
    }

    public int calcularPontuacao() {
        return 5 + (qtdMudas * 2);
    }
}
