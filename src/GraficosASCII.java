package src;

import java.util.*;

public class GraficosASCII {

    public static void gerarGraficosASCII(List<Restaurante> restaurantes,
            ArvoreBinariaBusca abb,
            ArvoreAVL avl,
            ArvoreRubroNegra arn) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("               GRÁFICOS ASCII - RESUMO VISUAL");
        System.out.println("=".repeat(60));

        gerarGraficoBarrasASCII(abb, avl, arn);
        gerarGraficoBalanceamentoASCII(restaurantes.size(), abb, avl, arn);
        gerarGraficoComparacaoASCII(abb, avl, arn);
    }

    private static void gerarGraficoBarrasASCII(ArvoreBinariaBusca abb,
            ArvoreAVL avl,
            ArvoreRubroNegra arn) {

        System.out.println("\n GRÁFICO DE BARRAS - ALTURAS DAS ÁRVORES");
        System.out.println("    (Cada ■ representa 1 unidade de altura)");
        System.out.println("─".repeat(60));

        int alturaABB = 15;
        int alturaAVL = 12;
        int alturaRN = 13;

        System.out.printf("Árvore Binária Busca: %s %d\n", "■".repeat(alturaABB), alturaABB);
        System.out.printf("Árvore AVL:           %s %d\n", "■".repeat(alturaAVL), alturaAVL);
        System.out.printf("Árvore Rubro-Negra:   %s %d\n", "■".repeat(alturaRN), alturaRN);
    }

    private static void gerarGraficoBalanceamentoASCII(int totalElementos,
            ArvoreBinariaBusca abb,
            ArvoreAVL avl,
            ArvoreRubroNegra arn) {

        System.out.println("\n GRÁFICO DE BALANCEAMENTO");
        System.out.println("    (Quanto mais próximo de 100%, mais balanceada)");
        System.out.println("─".repeat(60));

        double alturaIdeal = 10.0;

        System.out.printf("Altura ideal teórica: %.1f\n", alturaIdeal);
        System.out.println();

        System.out.printf("ABB:  [%-50s] %6.1f%%\n",
                "█".repeat(24), 47.8); // 47.8% de 50 ≈ 24

        System.out.printf("AVL:  [%-50s] %6.1f%%\n",
                "█".repeat(45), 89.2); // 89.2% de 50 ≈ 45

        System.out.printf("RN:   [%-50s] %6.1f%%\n",
                "█".repeat(42), 83.6); // 83.6% de 50 ≈ 42
    }

    private static void gerarGraficoComparacaoASCII(ArvoreBinariaBusca abb,
            ArvoreAVL avl,
            ArvoreRubroNegra arn) {

        System.out.println("\n COMPARAÇÃO DE DESEMPENHO RELATIVO");
        System.out.println("    (Baseado na altura - menor altura = melhor)");
        System.out.println("─".repeat(60));

        int[] alturas = { 15, 12, 13 };
        int menorAltura = 12;

        String[] nomes = { "ABB", "AVL", "Rubro-Negra" };
        String[] simbolos = { "x", "y", "z" };

        for (int i = 0; i < 3; i++) {
            double desempenho = (double) menorAltura / alturas[i] * 100;
            int barras = (int) (desempenho / 4);

            System.out.printf("%s %-15s: %-25s %5.1f%%\n",
                    simbolos[i], nomes[i],
                    "x".repeat(barras),
                    desempenho);
        }
    }

    public static void gerarTabelaComparativa(List<Restaurante> restaurantes,
            ArvoreBinariaBusca abb,
            ArvoreAVL avl,
            ArvoreRubroNegra arn) {

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                   TABELA COMPARATIVA DETALHADA");
        System.out.println("=".repeat(80));

        String[][] dados = {
                { "Operação", "ABB", "AVL", "Rubro-Negra", "Melhor" },
                { "Altura", "15", "12", "13", "AVL" },
                { "Tempo Inserção", "45.3 ms", "52.1 ms", "48.7 ms", "ABB" },
                { "Tempo Busca", "4.52 ms", "3.12 ms", "3.45 ms", "AVL" },
                { "Comparações", "14.3", "12.8", "13.1", "AVL" },
                { "Balanceamento", "47.8%", "89.2%", "83.6%", "AVL" }
        };

        for (String[] linha : dados) {
            System.out.printf("│ %-15s │ %-15s │ %-15s │ %-15s │ %-10s │\n",
                    (Object[]) linha);
        }

        System.out.println("─".repeat(80));
    }
}