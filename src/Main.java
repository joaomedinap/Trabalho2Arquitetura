import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache();

        try {
            cache.carregarMemoriaPrincipal("MemoriaPrincipal.txt");
            BufferedReader reader = new BufferedReader(new FileReader("Leituras.txt"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                int endereco = Integer.parseInt(linha, 2);
                cache.lerEndereco(endereco);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Número de hits: " + cache.getNumHits());
        System.out.println("Número de misses: " + cache.getNumMisses());
        System.out.println("Número de leituras: " + cache.getNumLeituras());
        System.out.println("Taxa de misses: " + (cache.calcularTaxaMiss() * 100) + "%");
        System.out.println("Taxa de hits: " + (cache.calcularTaxaHit() * 100) + "%");
    }
}
    