package src;

import java.io.*;
import java.util.Random;

public class GeradorCSV {
    public static void main(String[] args) {
        try {
            gerarCSV(1000, "data/dados_ifood.csv");
            System.out.println("SUCESSO: Dataset gerado!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void gerarCSV(int numRegistros, String filename) throws IOException {
        File dataDir = new File("data");
        dataDir.mkdirs();

        PrintWriter writer = new PrintWriter(new FileWriter(filename));

        // ✅ CABEÇALHO SEM ID
        writer.println("restaurant_name;city;avg_rating;total_ratings;food_type;avg_delivery_time;avg_price");

        Random random = new Random();
        String[] cidades = { "Sao Paulo", "Rio de Janeiro", "Belo Horizonte", "Brasilia", "Porto Alegre" };
        String[] comidas = { "Pizza", "Hamburguer", "Japonesa", "Brasileira", "Italiana" };

        for (int i = 1; i <= numRegistros; i++) {

            String nome = "Restaurante " + i;
            String cidade = cidades[random.nextInt(cidades.length)];
            double rating = 3.0 + random.nextDouble() * 2.0;
            int totalAval = 100 + random.nextInt(1000);
            String tipo = comidas[random.nextInt(comidas.length)];
            int tempo = 20 + random.nextInt(30);
            double preco = 25 + random.nextDouble() * 50;

            writer.println(nome + ";" + cidade + ";" +
                    String.format("%.1f", rating) + ";" + totalAval + ";" +
                    tipo + ";" + tempo + ";" + String.format("%.2f", preco));
        }

        writer.close();
    }
}