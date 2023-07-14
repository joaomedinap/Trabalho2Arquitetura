import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<CacheLine> linhas;
    private int numHits;
    private int numMisses;
    private int numLeituras;
    private int[] memoriaPrincipal;

    public Cache() {
        linhas = new ArrayList<>();
        numHits = 0;
        numMisses = 0;
        numLeituras = 0;
        memoriaPrincipal = new int[32];
    }
    //Verifica se deu cache hit ou miss, se deu cache miss ele atualiza o cache
    public void lerEndereco(int endereco) {
        numLeituras++;
        boolean acerto = false;

        for (CacheLine linha : linhas) {
            if (linha.getTag() == endereco) {
                acerto = true;
                linha.setUltimoAcesso(System.currentTimeMillis());
                break;
            }
        }

        if (acerto) {
            System.out.println("Cache hit para o endereço: " + endereco);
            numHits++;
        } else {
            System.out.println("Cache miss para o endereço: " + endereco);
            numMisses++;
            atualizarCache(endereco, lerDaMemoriaPrincipal(endereco));
        }
    }

    private int lerDaMemoriaPrincipal(int endereco) {
        int indice = endereco % 32;
        return memoriaPrincipal[indice];
    }
    
    //Cria uma nova cache line, se o numero for igual a 8, remove uma linha da cache usando LRU
    //Adiciona a linha criada no cache
    public void atualizarCache(int endereco, int dados) {
        CacheLine novaLinha = new CacheLine(endereco, dados, System.currentTimeMillis());

        if (linhas.size() == 8) {
            removerLinhaLRU();
        }

        linhas.add(novaLinha);
        exibirCache();
    }
    
    //Remove a cache line antiga
    private void removerLinhaLRU() {
        long menorTempoAcesso = Long.MAX_VALUE;
        CacheLine antiga = null;

        for (CacheLine linha : linhas) {
            if (linha.getUltimoAcesso() < menorTempoAcesso) {
                menorTempoAcesso = linha.getUltimoAcesso();
                antiga = linha;
            }
        }

        linhas.remove(antiga);
    }

    public void exibirCache() {
        System.out.println("Conteúdo da cache:");

        for (CacheLine linha : linhas) {
            System.out.println("Tag: " + linha.getTag() + ", Dados: " + linha.getDados());
        }

        System.out.println();
    }
    
    //Pegando conteudo da memoria principal e armazenando em memoriaPrincipal no formato decimal
    public void carregarMemoriaPrincipal(String arquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        int indice = 0;

        while ((linha = reader.readLine()) != null) {
            memoriaPrincipal[indice] = Integer.parseInt(linha, 2);
            indice++;
        }

        reader.close();
    }

    public double calcularTaxaMiss() {
        return (double) numMisses / numLeituras;
    }

    public double calcularTaxaHit() {
        return (double) numHits / numLeituras;
    }

    public int getNumHits() {
        return numHits;
    }

    public int getNumMisses() {
        return numMisses;
    }

    public int getNumLeituras() {
        return numLeituras;
    }
}
