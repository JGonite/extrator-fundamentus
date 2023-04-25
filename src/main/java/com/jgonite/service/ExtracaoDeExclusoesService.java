package com.jgonite.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jgonite.dto.AcaoIndicadoresDTO;

public class ExtracaoDeExclusoesService {
	
	public Set<String> extract(String setor) throws IOException {
		Document doc = Jsoup.connect("https://www.fundamentus.com.br/resultado.php?setor=".concat(setor)).get();
		Element table = doc.select("table#resultado").first();
		boolean header = true;
		List<AcaoIndicadoresDTO> listaDeAcoes = new ArrayList<>();
		for (Element row : table.select("tr")) {
			if (header) {
				header = false;
				continue;
			}
			Elements column = row.select("td");
			AcaoIndicadoresDTO dto = new AcaoIndicadoresDTO();
			dto.setPapel(limparTextoExtraido( column.get(0).select("a").text()));
			listaDeAcoes.add(dto);
		}
		
		return listaDeAcoes.stream().map(x->x.getPapel()).collect(Collectors.toSet());
	}
	
	private static String limparTextoExtraido(String texto) {
		return texto.replace(".", "").replace(",", ".");
	}

}
