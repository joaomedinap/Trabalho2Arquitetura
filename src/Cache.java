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

    // Realiza a leitura de um endereço na memória cache.
    // Verifica se o endereço está presente na cache (acerto) ou não (falha)
    // Atualiza o contador de acertos (numHits) ou falhas (numMisses)
    // chama o método atualizarCache se houver uma falha.
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
            atualizarCache(endereco, lerMemoriaPrincipal(endereco));
        }
    }

    private int lerMemoriaPrincipal(int endereco) {
        int indice = endereco % 32;
        return memoriaPrincipal[indice];
    }

    // Atualiza a memória cache com o dado lido da memória principal. Se a cache
    // estiver cheia, utiliza o algoritmo de substituição LRU
    // para remover a linha recentemente usada e inserir a nova linha.

    public void atualizarCache(int endereco, int dados) {
        CacheLine novaLinha = new CacheLine(endereco, dados, System.currentTimeMillis());

        if (linhas.size() == 8) {
            removerLinhaLRU();
        }

        linhas.add(novaLinha);
        exibirCache();
    }
    
    //Remove a linha do cache recentemente usada
    private void removerLinhaLRU() {
        long menorTempoAcesso = Long.MAX_VALUE;
        CacheLine menosRecente = null;

        for (CacheLine linha : linhas) {
            if (linha.getUltimoAcesso() < menorTempoAcesso) {
                menorTempoAcesso = linha.getUltimoAcesso();
                menosRecente = linha;
            }
        }

        linhas.remove(menosRecente);
    }
    // Exibe o conteúdo atual da memória cache, mostrando a tag e os dados de cada linha.
    public void exibirCache() {
        System.out.println("Conteúdo da cache:");

        for (CacheLine linha : linhas) {
            System.out.println("Tag: " + linha.getTag() + ", Dados: " + linha.getDados());
        }

        System.out.println();
    }
    
    // Exibe o conteúdo atual da memória cache, mostrando a tag e os dados de cada linha.
    public double calcularTaxaMiss() {
        return (double) numMisses / numLeituras;
    }
    
    // Calcula a taxa de acertos (cache hits) em relação ao número total de leituras.
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
    
    // Define a memória principal do sistema, que será consultada em caso de falha na cache.
    public void setMemoriaPrincipal(int[] memoriaPrincipal) {
        this.memoriaPrincipal = memoriaPrincipal;
    }
}
