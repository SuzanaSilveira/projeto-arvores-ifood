package src;

public interface Arvore {
    void inserir(int chave, int dados); // ← CORRIGIDO: era "insertr"

    Integer buscar(int chave);

    void remover(int chave);

    int altura();

    int getContadorComparacoes();

    void resetarContadorComparacoes();
}