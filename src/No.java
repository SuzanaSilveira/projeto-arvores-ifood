package src;

public class No { // Adicione 'public' aqui!
    int chave;
    int dados;
    No esquerda;
    No direita;
    int altura;
    public No pai;
    public int cor;

    public No(int chave, int dados) {
        this.chave = chave;
        this.dados = dados;
        this.altura = 1;
        this.esquerda = null;
        this.direita = null;
    }
}