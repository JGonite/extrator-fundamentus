package com.jgonite.model;

import java.math.BigDecimal;

public class AcaoIndicadoresModel {
	
	private String papel;
	private BigDecimal cotacao;
	private BigDecimal pl;
	private BigDecimal evEbit;
	private BigDecimal roic;
	private BigDecimal liq2meses;
	
	private int posicaoEvEbit;
	private int posicaoRoic;
	private int posicaoFinal;
	
	
	
	public int getPosicaoEvEbit() {
		return posicaoEvEbit;
	}
	public void setPosicaoEvEbit(int posicaoEvEbit) {
		this.posicaoEvEbit = posicaoEvEbit;
	}
	public int getPosicaoRoic() {
		return posicaoRoic;
	}
	public void setPosicaoRoic(int posicaoRoic) {
		this.posicaoRoic = posicaoRoic;
	}
	public int getPosicaoFinal() {
		return posicaoFinal;
	}
	public void setPosicaoFinal(int posicaoFinal) {
		this.posicaoFinal = posicaoFinal;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public BigDecimal getCotacao() {
		return cotacao;
	}
	public void setCotacao(BigDecimal cotacao) {
		this.cotacao = cotacao;
	}
	public BigDecimal getPl() {
		return pl;
	}
	public void setPl(BigDecimal pl) {
		this.pl = pl;
	}
	public BigDecimal getEvEbit() {
		return evEbit;
	}
	public void setEvEbit(BigDecimal evEbit) {
		this.evEbit = evEbit;
	}
	public BigDecimal getRoic() {
		return roic;
	}
	public void setRoic(BigDecimal roic) {
		this.roic = roic;
	}
	public BigDecimal getLiq2meses() {
		return liq2meses;
	}
	public void setLiq2meses(BigDecimal liq2meses) {
		this.liq2meses = liq2meses;
	}
	@Override
	public String toString() {
		return "AcaoIndicadoresModel [papel=" + papel + ", cotacao=" + cotacao + ", pl=" + pl + ", evEbit=" + evEbit
				+ ", roic=" + roic + ", liq2meses=" + liq2meses + ", posicaoEvEbit=" + posicaoEvEbit + ", posicaoRoic="
				+ posicaoRoic + ", posicaoFinal=" + posicaoFinal + "]";
	}
	
	
	
	

}
