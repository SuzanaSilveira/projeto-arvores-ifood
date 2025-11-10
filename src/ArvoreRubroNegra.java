package src;

class ArvoreRubroNegra implements Arvore {
    private No raiz;
    private int contadorComparacoes;

    public ArvoreRubroNegra() {
        this.raiz = null;
        this.contadorComparacoes = 0;
    }

    @Override
    public void inserir(int chave, int dados) {
        No novoNo = new No(chave, dados);

        No y = null;
        No x = this.raiz;

        while (x != null) {
            y = x;
            contadorComparacoes++;
            if (novoNo.chave < x.chave) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        novoNo.pai = y;

        if (y == null) {
            this.raiz = novoNo;
        } else if (novoNo.chave < y.chave) {
            y.esquerda = novoNo;
        } else {
            y.direita = novoNo;
        }
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
        System.out.println("Remoção na Rubro-Negra não implementada neste exemplo");
    }
}