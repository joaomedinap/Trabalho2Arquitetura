import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache();
        int[] memoriaPrincipal = new int[32];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemoriaPrincipal.txt"));
            String linha;
            int indice = 0;

            while ((linha = reader.readLine()) != null) {
                int dados = Integer.parseInt(linha, 2);
                memoriaPrincipal[indice] = dados;
                indice++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cache.setMemoriaPrincipal(memoriaPrincipal);

        // Simulação de leitura dos endereços da memória cache
        cache.lerEndereco(0b00000);
        cache.lerEndereco(0b00001);
        cache.lerEndereco(0b00010);
        cache.lerEndereco(0b00011);
        cache.lerEndereco(0b00100);
        cache.lerEndereco(0b00101);
        cache.lerEndereco(0b00110);
        cache.lerEndereco(0b00111);
        cache.lerEndereco(0b00000);
        cache.lerEndereco(0b00001);
        cache.lerEndereco(0b11000);
        cache.lerEndereco(0b10010);
        cache.lerEndereco(0b11000);
        cache.lerEndereco(0b01011);
        cache.lerEndereco(0b01000);
        cache.lerEndereco(0b10100);
        cache.lerEndereco(0b01011);
        cache.lerEndereco(0b10000);
        cache.lerEndereco(0b11000);
        cache.lerEndereco(0b01000);
        cache.lerEndereco(0b11100);
        cache.lerEndereco(0b00100);
        cache.lerEndereco(0b00010);
        cache.lerEndereco(0b01001);
        cache.lerEndereco(0b00011);
        cache.lerEndereco(0b11100);
        cache.lerEndereco(0b10000);
        cache.lerEndereco(0b11000);
        cache.lerEndereco(0b00011);
        cache.lerEndereco(0b01001);
        cache.lerEndereco(0b11010);
        cache.lerEndereco(0b01000);
        cache.lerEndereco(0b00000);
        cache.lerEndereco(0b00111);
        cache.lerEndereco(0b10010);
        cache.lerEndereco(0b01001);
        cache.lerEndereco(0b01111);
        cache.lerEndereco(0b10000);
        cache.lerEndereco(0b11001);
        cache.lerEndereco(0b00111);

        // Exibição dos resultados
        System.out.println("Número de leituras: " + cache.getNumLeituras());
        System.out.println("Número de hits: " + cache.getNumHits());
        System.out.println("Número de misses: " + cache.getNumMisses());
        System.out.println("Taxa de hits: " + (cache.calcularTaxaHit() * 100) + "%");
        System.out.println("Taxa de misses: " + (cache.calcularTaxaMiss() * 100) + "%");
    }
}
