package br.com.br.ney.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://viacep.com.br/ws/01001000/json/");
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
		
		String dadosTratados = dados.replace("{", "")
				                     .replace("}", "")
		                             .replace("\"","");

		String[] split = dadosTratados.split(",");

		
		List<String> preMapa = Arrays.asList(split);

		for (String string : preMapa) {
			String[] AtributoValor = string.split(":");
			List<String> atributoValorList = Arrays.asList(AtributoValor);
			for (int i = 0, j = 1; i < atributoValorList.size() - 1; i++, j++) {
				atributoPessoaValor.put(atributoValorList.get(i).trim(), atributoValorList.get(j));
			}

		}
		

		System.out.println(atributoPessoaValor.keySet());

	}

}
