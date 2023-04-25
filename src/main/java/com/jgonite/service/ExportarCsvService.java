package com.jgonite.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.jgonite.model.AcaoIndicadoresModel;

public class ExportarCsvService {
	
	private String CABECALHO = "Data de extração; Papel; EvEbit;Posicao EvEbit; Roic; Posicao Roic; PosicaoFinal"; 
	private String NOME_DO_ARQUIVO = "ranking_magic_formula.csv";
	
	
	public void exportarCsv(List<AcaoIndicadoresModel> acoes) throws IOException {
		
		LocalDate date = LocalDate.now();
		File arquivo = new File("src/main/resources/".concat(NOME_DO_ARQUIVO));
		
		FileWriter writer = new FileWriter(arquivo);
		BufferedWriter buff = new BufferedWriter(writer);
		
		buff.append(CABECALHO);
		for (var acao : acoes) {
			buff.newLine();
			StringBuilder sb = new StringBuilder();
			sb.append(date).append(";");
			sb.append(acao.getPapel()).append(";");
			sb.append(acao.getEvEbit()).append(";");
			sb.append(acao.getPosicaoEvEbit()).append(";");
			sb.append(acao.getRoic()).append(";");
			sb.append(acao.getPosicaoRoic()).append(";");
			sb.append(acao.getPosicaoFinal()).append(";");
			buff.append(sb.toString());
		}
		
		buff.close();
		writer.close();
				
		
	}

}
