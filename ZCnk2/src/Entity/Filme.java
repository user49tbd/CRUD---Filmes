package Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Filme {
	protected int codigo;
	protected String titulo;
	protected String genero;
	protected LocalDate data;
	protected double avaliacao;
	protected Date dataconvert;
	protected String tpObra; 
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Date getDataconvert() {
		return dataconvert = java.sql.Date.valueOf(data);
	}
	public void setDataconvert(LocalDate data) {
		this.dataconvert = java.sql.Date.valueOf(data);
	}
	public String getTpObra() {
		return tpObra;
	}
	public void setTpObra(String tpObra) {
		this.tpObra = tpObra;
	}
	
	//---------------------------------------
	
	
}
