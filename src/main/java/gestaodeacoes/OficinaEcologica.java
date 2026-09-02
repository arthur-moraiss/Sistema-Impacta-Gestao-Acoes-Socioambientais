package gestaodeacoes;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acao{

    private int duracao;
    private boolean temKitMaterial;


    public OficinaEcologica(int maxCapacidade, LocalDateTime data, String descricao, String titulo,int duracao,boolean temKitMaterial) {
        super(maxCapacidade, data, descricao, titulo);

        this.duracao = duracao;
        this.temKitMaterial = temKitMaterial;
    }

    public boolean isTemKitMaterial() {
        return temKitMaterial;
    }

    public void setTemKitMaterial(boolean temKitMaterial) {
        this.temKitMaterial = temKitMaterial;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int calcularPontuacao(){
        if(temKitMaterial){
            return (3 * duracao) + 10;
        }
        return 3 * duracao;
    }
}
