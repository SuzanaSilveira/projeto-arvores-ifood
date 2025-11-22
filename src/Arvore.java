package src; 

public interface Arvore { //  Torna a interface pública
    void inserir(int chave, int dados);

    Integer buscar(int chave);

    int altura();

    int getContadorComparacoes();

    void resetarContadorComparacoes();

    void remover(int chave);
}
