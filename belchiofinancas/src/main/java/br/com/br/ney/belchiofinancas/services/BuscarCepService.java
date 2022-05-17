package br.com.br.ney.belchiofinancas.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BuscarCepService implements Services {

  
	
	
	public HashMap<String, String> pesquisaCep(String parametro) throws IOException {
		URL url = new URL("http://viacep.com.br/ws/"+parametro+"/json/");
		URLConnection conection = url.openConnection();
		InputStream is = conection.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		StringBuilder jsonCep = new StringBuilder();

		String cep = "";
		while ((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		HashMap<String, String> atributoPessoaValor = new HashMap<>();

		String dados = jsonCep.toString();

		String dadosTratados = dados.replace("{", "").replace("}", "").replace("\"", "");

		String[] split = dadosTratados.split(",");

		List<String> preMapa = Arrays.asList(split);

		for (String string : preMapa) {
			String[] AtributoValor = string.split(":");
			List<String> atributoValorList = Arrays.asList(AtributoValor);
			for (int i = 0, j = 1; i < atributoValorList.size() - 1; i++, j++) {
				atributoPessoaValor.put(atributoValorList.get(i).trim(), atributoValorList.get(j));
			}

		}

		return atributoPessoaValor;
	}
}