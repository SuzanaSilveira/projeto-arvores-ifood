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

                int alturaABB = abb.altura();
                int alturaAVL = avl.altura();
                int alturaRN = arn.altura();

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

                double alturaIdeal = Math.floor(Math.log(totalElementos + 1) / Math.log(2));
                double balanceamentoABB = (alturaIdeal / abb.altura()) * 100;
                double balanceamentoAVL = (alturaIdeal / avl.altura()) * 100;
                double balanceamentoRN = (alturaIdeal / arn.altura()) * 100;

                System.out.printf("Altura ideal teórica: %.1f\n", alturaIdeal);
                System.out.println();

                System.out.printf("ABB:  [%-50s] %6.1f%%\n",
                                "█".repeat((int) (balanceamentoABB / 2)), balanceamentoABB);

                System.out.printf("AVL:  [%-50s] %6.1f%%\n",
                                "█".repeat((int) (balanceamentoAVL / 2)), balanceamentoAVL);

                System.out.printf("RN:   [%-50s] %6.1f%%\n",
                                "█".repeat((int) (balanceamentoRN / 2)), balanceamentoRN);
        }

        private static void gerarGraficoComparacaoASCII(ArvoreBinariaBusca abb,
                        ArvoreAVL avl,
                        ArvoreRubroNegra arn) {

                System.out.println("\n COMPARAÇÃO DE DESEMPENHO RELATIVO");
                System.out.println("    (Baseado na altura - menor altura = melhor)");
                System.out.println("─".repeat(60));

                int[] alturas = { abb.altura(), avl.altura(), arn.altura() };
                int menorAltura = Arrays.stream(alturas).min().getAsInt();

                String[] nomes = { "ABB", "AVL", "Rubro-Negra" };
                String[] simbolos = { "x", "y", "z" };

                for (int i = 0; i < 3; i++) {
                        double desempenho = (double) menorAltura / alturas[i] * 100;
                        int barras = (int) (desempenho / 4);

                        System.out.printf("%s %-15s: %-25s %5.1f%%\n",
                                        simbolos[i], nomes[i],
                                        "q".repeat(barras),
                                        desempenho);
                }
        }

        public static void gerarTabelaComparativa(List<Restaurante> restaurantes,
                        ArvoreBinariaBusca abb,
                        ArvoreAVL avl,
                        ArvoreRubroNegra arn,
                        double tempoInsercaoABB, double tempoInsercaoAVL, double tempoInsercaoRN,
                        double tempoBuscaABB, double tempoBuscaAVL, double tempoBuscaRN,
                        double comparacoesBuscaABB, double comparacoesBuscaAVL, double comparacoesBuscaRN) {

                System.out.println("\n" + "=".repeat(80));
                System.out.println("                   TABELA COMPARATIVA DETALHADA");
                System.out.println("=".repeat(80));

                // Calcular valores reais para a tabela
                double alturaIdeal = Math.floor(Math.log(restaurantes.size() + 1) / Math.log(2));
                double balanceamentoABB = (alturaIdeal / abb.altura()) * 100;
                double balanceamentoAVL = (alturaIdeal / avl.altura()) * 100;
                double balanceamentoRN = (alturaIdeal / arn.altura()) * 100;

                String[][] dados = {
                                { "Operação", "ABB", "AVL", "Rubro-Negra", "Melhor" },
                                { "Altura", String.valueOf(abb.altura()), String.valueOf(avl.altura()),
                                                String.valueOf(arn.altura()), getMelhorAltura(abb, avl, arn) },
                                { "Tempo Inserção",
                                                String.format("%.1f ms", tempoInsercaoABB),
                                                String.format("%.1f ms", tempoInsercaoAVL),
                                                String.format("%.1f ms", tempoInsercaoRN),
                                                getMelhorTempoInsercao(tempoInsercaoABB, tempoInsercaoAVL,
                                                                tempoInsercaoRN) },
                                { "Tempo Busca",
                                                String.format("%.2f μs", tempoBuscaABB),
                                                String.format("%.2f μs", tempoBuscaAVL),
                                                String.format("%.2f μs", tempoBuscaRN),
                                                getMelhorTempoBusca(tempoBuscaABB, tempoBuscaAVL, tempoBuscaRN) },
                                { "Comparações",
                                                String.format("%.1f", comparacoesBuscaABB),
                                                String.format("%.1f", comparacoesBuscaAVL),
                                                String.format("%.1f", comparacoesBuscaRN),
                                                getMelhorComparacoes(comparacoesBuscaABB, comparacoesBuscaAVL,
                                                                comparacoesBuscaRN) },
                                { "Balanceamento",
                                                String.format("%.1f%%", balanceamentoABB),
                                                String.format("%.1f%%", balanceamentoAVL),
                                                String.format("%.1f%%", balanceamentoRN),
                                                getMelhorBalanceamento(balanceamentoABB, balanceamentoAVL,
                                                                balanceamentoRN) }
                };

                for (String[] linha : dados) {
                        System.out.printf("│ %-15s │ %-15s │ %-15s │ %-15s │ %-10s │\n",
                                        (Object[]) linha);
                }

                System.out.println("─".repeat(80));
        }

        private static String getMelhorAltura(ArvoreBinariaBusca abb, ArvoreAVL avl, ArvoreRubroNegra arn) {
                int altABB = abb.altura();
                int altAVL = avl.altura();
                int altRN = arn.altura();

                if (altAVL <= altABB && altAVL <= altRN)
                        return "AVL";
                if (altRN <= altABB && altRN <= altAVL)
                        return "RN";
                return "ABB";
        }

        private static String getMelhorTempoInsercao(double abb, double avl, double rn) {
                if (abb <= avl && abb <= rn)
                        return "ABB";
                if (rn <= abb && rn <= avl)
                        return "RN";
                return "AVL";
        }

        private static String getMelhorTempoBusca(double abb, double avl, double rn) {
                if (abb <= avl && abb <= rn)
                        return "ABB";
                if (avl <= abb && avl <= rn)
                        return "AVL";
                return "RN";
        }

        private static String getMelhorComparacoes(double abb, double avl, double rn) {
                if (abb <= avl && abb <= rn)
                        return "ABB";
                if (avl <= abb && avl <= rn)
                        return "AVL";
                return "RN";
        }

        private static String getMelhorBalanceamento(double abb, double avl, double rn) {
                if (avl >= abb && avl >= rn)
                        return "AVL";
                if (rn >= abb && rn >= avl)
                        return "RN";
                return "ABB";
        }
}