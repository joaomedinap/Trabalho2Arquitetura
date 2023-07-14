public class CacheLine {
    private int tag;
    private int dados;
    private long ultimoAcesso;

    public CacheLine(int tag, int dados, long ultimoAcesso) {
        this.tag = tag;
        this.dados = dados;
        this.ultimoAcesso = ultimoAcesso;
    }

    public int getTag() {
        return tag;
    }

    public int getDados() {
        return dados;
    }

    public long getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(long ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
}
