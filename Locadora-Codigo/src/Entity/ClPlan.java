package Entity;

import java.time.LocalDate;

public class ClPlan {
	private int codigo;
	private String clnome;
	private String planoNome;
	private LocalDate dataI;
	private LocalDate dataF;
	private double ppreco;
	private String Status;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getPlanoNome() {
		return planoNome;
	}
	public void setPlanoNome(String planoNome) {
		this.planoNome = planoNome;
	}
	public LocalDate getDataI() {
		return dataI;
	}
	public void setDataI(LocalDate dataI) {
		this.dataI = dataI;
	}
	public LocalDate getDataF() {
		return dataF;
	}
	public void setDataF(LocalDate dataF) {
		this.dataF = dataF;
	}
	public String getClnome() {
		return clnome;
	}
	public void setClnome(String clnome) {
		this.clnome = clnome;
	}
	public double getPpreco() {
		return ppreco;
	}
	public void setPpreco(double ppreco) {
		this.ppreco = ppreco;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
