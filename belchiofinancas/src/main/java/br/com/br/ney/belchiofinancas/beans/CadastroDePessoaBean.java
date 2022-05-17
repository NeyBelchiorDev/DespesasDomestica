package br.com.br.ney.belchiofinancas.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.br.ney.belchiofinancas.dao.DAO;
import br.com.br.ney.belchiofinancas.domain.pessoas.Endereco;
import br.com.br.ney.belchiofinancas.domain.pessoas.Pessoa;
import br.com.br.ney.belchiofinancas.domain.pessoas.TipoPessoa;
import br.com.br.ney.belchiofinancas.services.BuscarCepService;
import br.com.br.ney.belchiofinancas.services.Services;

@ViewScoped
@ManagedBean
public class CadastroDePessoaBean {

	private Services buscarCepService = new BuscarCepService();
	private String cep;
	private Pessoa pessoa = new Pessoa();
	private int tipoPessoa;
	private Endereco endereco = new Endereco();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	


	public void setEnderecos(List<Endereco> enderecos) {
   		this.enderecos = enderecos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void adicionaEndereco() {
		
		System.out.println("adionaEndereco foi chamado");
		this.enderecos.add(endereco);
        this.endereco=new Endereco();
	}

	public void buscaEnderecoPeloCep() {
		System.out.println("metodo foi chamado");
		try {
			if (this.cep.isEmpty()) {

				FacesContext.getCurrentInstance().addMessage("teste", new FacesMessage("Informe o Cep"));
				return;
			} else {
				HashMap<String, String> pesquisaCep = buscarCepService.pesquisaCep(cep);
				this.endereco.setLogradouro(pesquisaCep.get("logradouro"));
				this.endereco.setComplemento(pesquisaCep.get("complemento"));
				this.endereco.setBairro(pesquisaCep.get("bairro"));
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private void validaSeEnderecoEstaVinculadoAPessoa(List<Endereco> enderecos) {
		    for(Endereco endereco :enderecos) {
		    	if(Objects.isNull(endereco.getPessoa())) {
		    		FacesContext.getCurrentInstance().addMessage("teste", new FacesMessage("É necessário vincular uma pessoa ao endereço"));
		    	}
		       
		    }
	}

	public void gravar() throws Exception {
		if (!Objects.isNull(endereco) && !Objects.isNull(pessoa)) {
			enderecos.forEach(endereco->endereco.setPessoa(this.pessoa));
			this.pessoa.setTipo(TipoPessoa.toEnum(this.tipoPessoa));
			this.pessoa.getEndereco().addAll(enderecos);
			this.validaSeEnderecoEstaVinculadoAPessoa(enderecos);
			new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
			 this.pessoa = new Pessoa();
			 this.endereco=new Endereco();
		} else {
			throw new Exception("O Endereço e a pessoa devem está preenchidos");
		}
	}

}
