private static void gerarGraficoBalanceamentoASCII(int totalElementos,
        ArvoreBinariaBusca abb,
        ArvoreAVL avl,
        ArvoreRubroNegra arn) {

    System.out.println("\n GRÁFICO DE BALANCEAMENTO");
    System.out.println("    (Quanto mais próximo de 100%, mais balanceada)");
    System.out.println("─".repeat(60));

    double alturaIdeal = Math.floor(Math.log(totalElementos) / Math.log(2));
    double balanceamentoABB = Math.min(100, (alturaIdeal / (abb.altura() - 1)) * 100);
    double balanceamentoAVL = Math.min(100, (alturaIdeal / (avl.altura() - 1)) * 100);
    double balanceamentoRN = Math.min(100, (alturaIdeal / (arn.altura() - 1)) * 100);

    System.out.printf("Altura ideal teórica: %.1f\n", alturaIdeal);
    System.out.println();

    System.out.printf("ABB:  [%-50s] %6.1f%%\n",
            "█".repeat((int) (balanceamentoABB / 2)), balanceamentoABB);

    System.out.printf("AVL:  [%-50s] %6.1f%%\n",
            "█".repeat((int) (balanceamentoAVL / 2)), balanceamentoAVL);

    System.out.printf("RN:   [%-50s] %6.1f%%\n",
            "█".repeat((int) (balanceamentoRN / 2)), balanceamentoRN);
}
