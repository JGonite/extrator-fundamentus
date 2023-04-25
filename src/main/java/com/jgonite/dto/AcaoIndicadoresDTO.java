package com.jgonite.dto;

public class AcaoIndicadoresDTO {
	
	private String papel;
	private String cotacao;
	private String pl;
	private String evEbit;
	private String roic;
	private String liq2meses;

	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public String getCotacao() {
		return cotacao;
	}
	public void setCotacao(String cotacao) {
		this.cotacao = cotacao;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getEvEbit() {
		return evEbit;
	}
	public void setEvEbit(String evEbit) {
		this.evEbit = evEbit;
	}
	public String getRoic() {
		return roic;
	}
	public void setRoic(String roic) {
		this.roic = roic;
	}
	public String getLiq2meses() {
		return liq2meses;
	}
	public void setLiq2meses(String liq2meses) {
		this.liq2meses = liq2meses;
	}
	@Override
	public String toString() {
		return "AcaoIndicadoresDTO [papel=" + papel + ", cotacao=" + cotacao + ", pl=" + pl + ", evEbit=" + evEbit
				+ ", roic=" + roic + ", liq2meses=" + liq2meses + "]";
	}
	
	

}
