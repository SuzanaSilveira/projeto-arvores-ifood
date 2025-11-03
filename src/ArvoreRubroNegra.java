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

    // MÉTODOS AUXILIARES: ROTINA DE ROTAÇÃO E RECOLORAÇÃO

    // Troca referências do nó X com o nó Y na árvore
    private void transplantar(No u, No v) {
        if (u.pai == null) {
            this.raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        if (v != null) {
            v.pai = u.pai;
        }
    }

    private void rotacionarEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != null) {
            y.esquerda.pai = x;
        }
        y.pai = x.pai;
        transplantar(x, y); // Garante que as referências da raiz sejam atualizadas
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacionarDireita(No x) {
        No y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != null) {
            y.direita.pai = x;
        }
        y.pai = x.pai;
        transplantar(x, y); // Garante que as referências da raiz sejam atualizadas
        y.pai = x.pai;
        y.direita = x;
        x.pai = y;
    }

    private void balancearAposInsercao(No z) {
        z.cor = vermelho;

        while (z != this.raiz && z.pai != null && z.pai.cor == vermelho) {
            No p = z.pai; // Pai
            No g = p.pai; // Avô

            // Garante que o avô existe (se o pai for vermelho, o avô deve ser preto e
            // existir)
            if (g == null) {
                break;
            }

            if (p == g.esquerda) { // PAI é o filho ESQUERDO
                No u = g.direita; // Tio à direita

                if (u != null && u.cor == vermelho) {
                    // CASO 1: Tio é vermelho -> Recolore e move para o Avô
                    p.cor = preto;
                    u.cor = preto;
                    g.cor = vermelho;
                    z = g;
                } else { // Tio é preto
                    if (z == p.direita) {
                        // CASO 2: Rotação dupla (Filho direito)
                        z = p;
                        rotacionarEsquerda(z);
                    }
                    // CASO 3: Rotação simples (Filho esquerdo ou após Caso 2)
                    z.pai.cor = preto;
                    z.pai.pai.cor = vermelho;
                    rotacionarDireita(z.pai.pai);
                }
            } else { // PAI é o filho DIREITO (Simetria)
                No u = g.esquerda; // Tio à esquerda

                if (u != null && u.cor == vermelho) {
                    // CASO 1 (Simetria): Tio é vermelho
                    p.cor = preto;
                    u.cor = preto;
                    g.cor = vermelho;
                    z = g;
                } else { // Tio é preto
                    if (z == p.esquerda) {
                        // CASO 2 (Simetria): Rotação dupla (Filho esquerdo)
                        z = p;
                        rotacionarDireita(z);
                    }
                    // CASO 3 (Simetria): Rotação simples (Filho direito ou após Caso 2)
                    z.pai.cor = preto;
                    z.pai.pai.cor = vermelho;
                    rotacionarEsquerda(z.pai.pai);
                }
            }
        }
        this.raiz.cor = preto;
    }

    // MÉTODOS DA INTERFACE

    @Override
    public void inserir(int chave, int dados) {
        No novoNo = new No(chave, dados);
        novoNo.cor = vermelho; // Novo nó sempre começa vermelho

        No y = null; // Pai do novoNo
        No x = this.raiz;

        // Inserção iterativa (melhor para RN)
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

        // Chamada para o balanceamento
        balancearAposInsercao(novoNo);
    }

    // [Os métodos buscar, altura, getContadorComparacoes, etc., são os mesmos da
    // sua versão.]
    @Override
    public Integer buscar(int chave) {
        // ... (Seu código de busca)
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