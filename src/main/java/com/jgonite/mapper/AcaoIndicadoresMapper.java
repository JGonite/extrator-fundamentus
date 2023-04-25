package com.jgonite.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.jgonite.dto.AcaoIndicadoresDTO;
import com.jgonite.model.AcaoIndicadoresModel;

public class AcaoIndicadoresMapper {
	
	public AcaoIndicadoresModel toModel(AcaoIndicadoresDTO dto) {
		AcaoIndicadoresModel model = new AcaoIndicadoresModel();
		model.setPapel(dto.getPapel());
		model.setCotacao(converterStringParaBigDecimal(dto.getCotacao()));
		model.setPl(converterStringParaBigDecimal(dto.getPl()));
		model.setEvEbit(converterStringParaBigDecimal(dto.getEvEbit()));
		model.setRoic(converterStringParaBigDecimalParaPorcentagens(dto.getRoic()));
		model.setLiq2meses(converterStringParaBigDecimal(dto.getLiq2meses()));
		return model;
	}
	
	private BigDecimal converterStringParaBigDecimal(String value) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(value).setScale(2);
		} catch (Exception e) {
			bd = null;
		}
		return bd;
	}
	
	private BigDecimal converterStringParaBigDecimalParaPorcentagens(String value) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(value.replace("%", "")).setScale(4).divide(new BigDecimal("100"), RoundingMode.HALF_UP);
		} catch (Exception e) {
			bd = null;
		}
		return bd;
	}

}
