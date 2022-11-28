package Entity;

public class Plano {
	private String nome;
	private String periodo;
	private int	obraMedia;
	private int quant;
	private String quali;
	private Double preco;
	private String tpobra;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getObraMedia() {
		return obraMedia;
	}
	public void setObraMedia(int obraMedia) {
		this.obraMedia = obraMedia;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public String getQuali() {
		return quali;
	}
	public void setQuali(String quali) {
		this.quali = quali;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getTpobra() {
		return tpobra;
	}
	public void setTpobra(String tpobra) {
		this.tpobra = tpobra;
	}
	
	
}
