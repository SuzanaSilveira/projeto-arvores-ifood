package src; // 1. Adiciona a declaração de pacote

public interface Arvore { // 2. Torna a interface pública
    void inserir(int chave, int dados);

    Integer buscar(int chave);

    int altura();

    int getContadorComparacoes();

    void resetarContadorComparacoes();

    void remover(int chave);
}