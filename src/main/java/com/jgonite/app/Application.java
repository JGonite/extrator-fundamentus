package com.jgonite.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.jgonite.model.AcaoIndicadoresModel;
import com.jgonite.service.ExportarCsvService;
import com.jgonite.service.ExtracaoDeExclusoesService;
import com.jgonite.service.ExtracaoFundamentusService;

public class Application {
	
	private static ExtracaoFundamentusService extracaoService = new ExtracaoFundamentusService();
	private static ExtracaoDeExclusoesService exclusaoService = new ExtracaoDeExclusoesService();
	private static ExportarCsvService csvService = new ExportarCsvService();
	
	public static void main(String[] args) throws IOException {
		
		List<AcaoIndicadoresModel> listaAcoesModel = extracaoService.extract();
		
		// Tirar as ações cujo liquidez em 2 meses, seja menor que 100 mil.
		listaAcoesModel = listaAcoesModel.stream()
				.filter( x ->  x.getLiq2meses().compareTo( new BigDecimal("100000")) >= 0).collect(Collectors.toList());
		listaAcoesModel = listaAcoesModel.stream()
				.filter( x ->  x.getEvEbit().compareTo( BigDecimal.ZERO) > 0).collect(Collectors.toList());
		
		
		
		// Remover seguradoras, bancos e ações de empresas de energia. 
		Set<String> acoesSeguros = exclusaoService.extract("31");
		Set<String> acoesEnergia = exclusaoService.extract("14");
		Set<String> acoesBancos = exclusaoService.extract("20");

		Set<String> exclusoes = acoesSeguros;
		exclusoes.addAll(acoesEnergia);
		exclusoes.addAll(acoesBancos);
		listaAcoesModel = listaAcoesModel.stream()
					.filter( x-> !exclusoes.contains(x.getPapel())).collect(Collectors.toList());
		
		// Filtrar as ações repetidas, mantendo apenas a que tem volume maior;
		Map<String, AcaoIndicadoresModel> papeisJaLidos = new HashMap<>();
		for (var acao : listaAcoesModel) {
			String papel = acao.getPapel().replaceAll("[0-9]", "");
			if (papeisJaLidos.keySet().contains(papel)) {
				if (acao.getLiq2meses().compareTo(papeisJaLidos.get(papel).getLiq2meses()) >= 0) {
					papeisJaLidos.put(papel, acao);
				}
			} else {
				papeisJaLidos.put(papel, acao);
			}
		}
		listaAcoesModel = papeisJaLidos.values().stream().collect(Collectors.toList());
		
		int tamanho = listaAcoesModel.size();
		// ranking de evEbit
		listaAcoesModel.sort(new Comparator<AcaoIndicadoresModel>() {
			@Override
			public int compare(AcaoIndicadoresModel a1, AcaoIndicadoresModel a2) {
				return a1.getEvEbit().compareTo(a2.getEvEbit());
			}
		});
		for (int i = 0; i < tamanho; i++) {
			listaAcoesModel.get(i).setPosicaoEvEbit(i+1);
		}
		
		// ranking de roic
		listaAcoesModel.sort(new Comparator<AcaoIndicadoresModel>() {
			@Override
			public int compare(AcaoIndicadoresModel a1, AcaoIndicadoresModel a2) {
				return a2.getRoic().compareTo(a1.getRoic());
			}
		});
		for (int i = 0; i < tamanho; i++) {
			listaAcoesModel.get(i).setPosicaoRoic(i+1);
			listaAcoesModel.get(i).setPosicaoFinal(listaAcoesModel.get(i).getPosicaoEvEbit() + listaAcoesModel.get(i).getPosicaoRoic());
		}
		
		// ranking por posicao final
		listaAcoesModel.sort(new Comparator<AcaoIndicadoresModel>() {
			@Override
			public int compare(AcaoIndicadoresModel a1, AcaoIndicadoresModel a2) {
				return a1.getPosicaoFinal() - a2.getPosicaoFinal();
			}
		});
		for (int i = 0; i < tamanho; i++) {
			listaAcoesModel.get(i).setPosicaoFinal(i+1);
		}
		
		csvService.exportarCsv(listaAcoesModel);
		
	}
	


}
