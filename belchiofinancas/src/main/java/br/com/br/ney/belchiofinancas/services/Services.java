package br.com.br.ney.belchiofinancas.services;

import java.io.IOException;
import java.util.HashMap;

public interface Services {
	
	HashMap<String, String> pesquisaCep(String Cep) throws IOException;

}
