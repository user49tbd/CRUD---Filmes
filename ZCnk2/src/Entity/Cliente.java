package Entity;

public class Cliente extends Pessoa{
		private String cpf;
		private String rg;
		private String plano;
		private String email;
		private String telefone;
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getRg() {
			return rg;
		}
		public void setRg(String rg) {
			this.rg = rg;
		}
		public String getPlano() {
			return plano;
		}
		public void setPlano(String plano) {
			this.plano = plano;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		
		
}
