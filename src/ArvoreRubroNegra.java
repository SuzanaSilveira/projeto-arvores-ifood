package src;

class ArvoreRubroNegra implements Arvore {
    private No raiz;
    private int contadorComparacoes;
    private final int vermelho = 1;
    private final int preto = 0;

    public ArvoreRubroNegra() {
        this.raiz = null;
        this.contadorComparacoes = 0;
    }

    @Override
    public void inserir(int chave, int dados) {
        No novoNo = new No(chave, dados);
        raiz = inserirRecursivo(raiz, novoNo);
        // Ensure root is always black
        if (raiz != null)
            raiz.cor = preto;
        // Correção de violações seria implementada aqui
    }

    private No inserirRecursivo(No raiz, No novoNo) {
        contadorComparacoes++;
        if (raiz == null) {
            novoNo.cor = vermelho; // Set new node color to red
            return novoNo;
        }

        contadorComparacoes++;
        if (novoNo.chave < raiz.chave) {
            raiz.esquerda = inserirRecursivo(raiz.esquerda, novoNo);
            raiz.esquerda.pai = raiz;
        } else if (novoNo.chave > raiz.chave) {
            raiz.direita = inserirRecursivo(raiz.direita, novoNo);
            raiz.direita.pai = raiz;
        }

        return raiz;
    }

    @Override
    public Integer buscar(int chave) {
        return buscarRecursivo(raiz, chave);
    }

    private Integer buscarRecursivo(No no, int chave) {
        contadorComparacoes++;
        if (no == null)
            return null;

        contadorComparacoes++;
        if (chave == no.chave)
            return no.dados;
        else if (chave < no.chave)
            return buscarRecursivo(no.esquerda, chave);
        else
            return buscarRecursivo(no.direita, chave);
    }

    @Override
    public int altura() {
        return alturaRecursivo(raiz);
    }

    private int alturaRecursivo(No no) {
        if (no == null)
            return 0;
        return 1 + Math.max(alturaRecursivo(no.esquerda), alturaRecursivo(no.direita));
    }

    @Override
    public int getContadorComparacoes() {
        return contadorComparacoes;
    }

    @Override
    public void resetarContadorComparacoes() {
        contadorComparacoes = 0;
    }

    @Override
    public void remover(int chave) {
        // Implementação simplificada
        System.out.println("Remoção na Rubro-Negra não implementada neste exemplo");
    }
}