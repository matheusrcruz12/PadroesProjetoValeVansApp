package valevansapp.com.br.valevansapp.modelo;

public class Van {
    private static Van vanUniqueInstance = null;
    private String placa;
    private String nome;
    private int lugares;

    private Van() {
    }

    public static Van getInstance(){

        if (vanUniqueInstance == null){

            vanUniqueInstance = new Van();
        }
        return vanUniqueInstance;
    }
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    @Override
    public String toString() {
        return nome;
    }
}
