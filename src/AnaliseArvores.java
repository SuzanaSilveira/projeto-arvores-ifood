package src;

import java.util.*;

public class AnaliseArvores {
    private static List<Restaurante> restaurantes;
    private static ArvoreBinariaBusca abb;
    private static ArvoreAVL avl;
    private static ArvoreRubroNegra arn;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("    ANÁLISE DE ÁRVORES - DATASET iFOOD");
        System.out.println("    Estrutura de Dados - Trabalho 1");
        System.out.println("==================================================\n");

        inicializarEstruturas();
        carregarDataset();
        executarTestesPerformance();
        realizarAnalisesEspecificas();
        gerarVisualizacoes();
        demonstrarOperacoes();

        System.out.println("\n==================================================");
        System.out.println("         TRABALHO CONCLUÍDO COM SUCESSO!");
        System.out.println("==================================================");
    }

    private static void inicializarEstruturas() {
        System.out.println("INICIALIZANDO ESTRUTURAS DE DADOS...");
        abb = new ArvoreBinariaBusca();
        avl = new ArvoreAVL();
        arn = new ArvoreRubroNegra();
        restaurantes = new ArrayList<>();
        System.out.println(" Árvores inicializadas: ABB, AVL e Rubro-Negra");
    }

    private static void carregarDataset() {
        System.out.println("\n CARREGANDO DATASET iFOOD...");
        restaurantes = CarregadorDataset.carregarRestaurantes();

        if (restaurantes.isEmpty()) {
            System.out.println(" ERRO: Não foi possível carregar o dataset!");
            System.exit(1);
        }

        System.out.println("Dataset carregado com sucesso!");
        System.out.println("   Total de restaurantes: " + restaurantes.size());

        mostrarEstatisticasDataset();
    }

    private static void mostrarEstatisticasDataset() {
        System.out.println("\nESTATÍSTICAS DO DATASET:");
        System.out.println("──────────────────────────────────────────────────");

        double mediaAvaliacao = restaurantes.stream()
                .mapToDouble(r -> r.avaliacaoMedia)
                .average()
                .orElse(0.0);

        double mediaTempoEntrega = restaurantes.stream()
                .mapToInt(r -> r.tempoEntregaMedio)
                .average()
                .orElse(0.0);

        System.out.printf(" Avaliação média:  %.2f/5.0\n", mediaAvaliacao);
        System.out.printf(" Tempo de entrega médio:  %.1f min\n", mediaTempoEntrega);

        System.out.println("\n AMOSTRA DE DADOS (primeiros 3 registros):");
        System.out.println("──────────────────────────────────────────────────");
        for (int i = 0; i < Math.min(3, restaurantes.size()); i++) {
            Restaurante r = restaurantes.get(i);
            System.out.printf("  %s\n", r);
        }
    }

    private static void executarTestesPerformance() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         TESTES DE PERFORMANCE DAS ÁRVORES");
        System.out.println("=".repeat(60));

        System.out.println("\n TESTE DE INSERÇÃO EM LOTE:");
        System.out.println("──────────────────────────────────────────────────");
        testarInsercaoEmLote();

        System.out.println("\n TESTE DE BUSCA ALEATÓRIA:");
        System.out.println("──────────────────────────────────────────────────");
        testarBuscaAleatoria();

        System.out.println("\n MÉTRICAS DE ESTRUTURA:");
        System.out.println("──────────────────────────────────────────────────");
        mostrarMetricasEstrutura();
    }

    private static void testarInsercaoEmLote() {
        Metricas metricas = new Metricas();

        // Testar ABB
        metricas.iniciarTemporizador();
        abb.resetarContadorComparacoes();
        for (Restaurante r : restaurantes) {
            abb.inserir(r.idRestaurante, r.tempoEntregaMedio);
        }
        metricas.pararTemporizador();
        System.out.printf("ABB  - Tempo: %8.2f ms | Comparações: %8d | Altura: %2d\n",
                metricas.getTempoDecorridoMillis(), abb.getContadorComparacoes(), abb.altura());

        // Testar AVL
        metricas.iniciarTemporizador();
        avl.resetarContadorComparacoes();
        for (Restaurante r : restaurantes) {
            avl.inserir(r.idRestaurante, r.tempoEntregaMedio);
        }
        metricas.pararTemporizador();
        System.out.printf("AVL  - Tempo: %8.2f ms | Comparações: %8d | Altura: %2d\n",
                metricas.getTempoDecorridoMillis(), avl.getContadorComparacoes(), avl.altura());

        // Testar Rubro-Negra
        metricas.iniciarTemporizador();
        arn.resetarContadorComparacoes();
        for (Restaurante r : restaurantes) {
            arn.inserir(r.idRestaurante, r.tempoEntregaMedio);
        }
        metricas.pararTemporizador();
        System.out.printf("RN   - Tempo: %8.2f ms | Comparações: %8d | Altura: %2d\n",
                metricas.getTempoDecorridoMillis(), arn.getContadorComparacoes(), arn.altura());
    }

    private static void testarBuscaAleatoria() {
        Random random = new Random();
        int numBuscas = Math.min(1000, restaurantes.size());
        Metricas metricas = new Metricas();

        int[] chavesBusca = new int[numBuscas];
        for (int i = 0; i < numBuscas; i++) {
            chavesBusca[i] = restaurantes.get(random.nextInt(restaurantes.size())).idRestaurante;
        }

        // Testar ABB
        metricas.iniciarTemporizador();
        abb.resetarContadorComparacoes();
        for (int chave : chavesBusca) {
            abb.buscar(chave);
        }
        metricas.pararTemporizador();
        System.out.printf("ABB  - Tempo: %9.6f ms/busca | Comparações: %6.2f/busca\n",
                metricas.getTempoDecorridoMillis() / numBuscas,
                ((Number) abb.getContadorComparacoes()).doubleValue() / numBuscas);

        // Testar AVL
        metricas.iniciarTemporizador();
        avl.resetarContadorComparacoes();
        for (int chave : chavesBusca) {
            avl.buscar(chave);
        }
        metricas.pararTemporizador();
        System.out.printf("AVL  - Tempo: %9.6f ms/busca | Comparações: %6.2f/busca\n",
                metricas.getTempoDecorridoMillis() / numBuscas,
                ((Number) avl.getContadorComparacoes()).doubleValue() / numBuscas);

        // Testar Rubro-Negra
        metricas.iniciarTemporizador();
        arn.resetarContadorComparacoes();
        for (int chave : chavesBusca) {
            arn.buscar(chave);
        }
        metricas.pararTemporizador();
        System.out.printf("RN   - Tempo: %9.6f ms/busca | Comparações: %6.2f/busca\n",
                metricas.getTempoDecorridoMillis() / numBuscas,
                ((Number) arn.getContadorComparacoes()).doubleValue() / numBuscas);
    }

    private static void mostrarMetricasEstrutura() {
        int totalElementos = restaurantes.size();
        double alturaIdeal = Math.ceil(Math.log(totalElementos + 1) / Math.log(2));

        System.out.printf("Altura ideal teórica: %.1f\n", alturaIdeal);
        System.out.println();

        System.out.printf("ABB  - Altura: %2d | Balanceamento: %5.1f%%\n",
                abb.altura(), (alturaIdeal / abb.altura()) * 100);
        System.out.printf("AVL  - Altura: %2d | Balanceamento: %5.1f%%\n",
                avl.altura(), (alturaIdeal / avl.altura()) * 100);
        System.out.printf("RN   - Altura: %2d | Balanceamento: %5.1f%%\n",
                arn.altura(), (alturaIdeal / arn.altura()) * 100);
    }

    private static void realizarAnalisesEspecificas() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         ANÁLISES ESPECÍFICAS DO DATASET");
        System.out.println("=".repeat(60));

        analisarDistribuicaoTiposComida();
        analisarAvaliacoes();
    }

    private static void analisarDistribuicaoTiposComida() {
        System.out.println("\n DISTRIBUIÇÃO POR TIPO DE COMIDA:");
        System.out.println("──────────────────────────────────────────────────");

        Map<String, Integer> contagem = new HashMap<>();

        for (Restaurante r : restaurantes) {
            contagem.merge(r.tipoComida, 1, Integer::sum);
        }

        contagem.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(8)
                .forEach(entry -> {
                    String tipo = entry.getKey();
                    int quantidade = entry.getValue();
                    double percentual = (quantidade * 100.0) / restaurantes.size();

                    System.out.printf("• %-15s: %3d (%5.1f%%)\n", tipo, quantidade, percentual);
                });
    }

    private static void analisarAvaliacoes() {
        System.out.println("\n DISTRIBUIÇÃO DE AVALIAÇÕES:");
        System.out.println("──────────────────────────────────────────────────");

        int[] faixas = new int[5];
        String[] labels = { "0-2.9 ", "3.0-3.4 ", "3.5-3.9 ", "4.0-4.4 ", "4.5-5.0 " };

        for (Restaurante r : restaurantes) {
            if (r.avaliacaoMedia < 3.0)
                faixas[0]++;
            else if (r.avaliacaoMedia < 3.5)
                faixas[1]++;
            else if (r.avaliacaoMedia < 4.0)
                faixas[2]++;
            else if (r.avaliacaoMedia < 4.5)
                faixas[3]++;
            else
                faixas[4]++;
        }

        for (int i = 0; i < faixas.length; i++) {
            double percentual = (faixas[i] * 100.0) / restaurantes.size();
            System.out.printf("• %-15s: %4d (%5.1f%%)\n", labels[i], faixas[i], percentual);
        }
    }

    private static void gerarVisualizacoes() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         VISUALIZAÇÕES E GRÁFICOS");
        System.out.println("=".repeat(60));

        GraficosASCII.gerarGraficosASCII(restaurantes, abb, avl, arn);
        GraficosASCII.gerarTabelaComparativa(restaurantes, abb, avl, arn);
    }

    private static void demonstrarOperacoes() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         DEMONSTRAÇÃO DE OPERAÇÕES");
        System.out.println("=".repeat(60));

        if (restaurantes.size() < 5)
            return;

        System.out.println("\n DEMONSTRAÇÃO DE BUSCA:");
        System.out.println("──────────────────────────────────────────────────");

        Restaurante exemplo = restaurantes.get(0);
        System.out.printf("Buscando: %s\n", exemplo.nomeRestaurante);

        Integer tempoABB = abb.buscar(exemplo.idRestaurante);
        Integer tempoAVL = avl.buscar(exemplo.idRestaurante);
        Integer tempoRN = arn.buscar(exemplo.idRestaurante);

        System.out.printf("ABB:  %d min | AVL: %d min | RN: %d min\n", tempoABB, tempoAVL, tempoRN);

        System.out.println("\n CONCLUSÕES FINAIS:");
        System.out.println("──────────────────────────────────────────────────");
        System.out.println(" ABB:  Mais rápida na inserção");
        System.out.println(" AVL:  Melhor balanceamento geral");
        System.out.println(" RN:   Equilíbrio entre operações");
        System.out.println(" Dataset iFood: Análise real de desempenho");
    }
}