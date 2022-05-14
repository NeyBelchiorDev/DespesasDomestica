package br.com.br.ney.belchiofinancas.beans;

import java.util.Objects;

import javax.faces.bean.ManagedBean;

import br.com.br.ney.belchiofinancas.dao.DAO;
import br.com.br.ney.belchiofinancas.domain.pessoas.Endereco;
import br.com.br.ney.belchiofinancas.domain.pessoas.Pessoa;
import br.com.br.ney.belchiofinancas.domain.pessoas.TipoPessoa;

@ManagedBean
public class CadastroDePessoaBean {

	private Pessoa pessoa = new Pessoa();

	private Endereco endereco = new Endereco();
	

	 int tipoPessoa;

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}



	public void gravar() throws Exception {
		if (!Objects.isNull(endereco)&&!Objects.isNull(pessoa)) {
            this.endereco.setPessoa(this.pessoa);
			this.pessoa.setTipo(TipoPessoa.toEnum(this.tipoPessoa));
			this.pessoa.getEndereco().add(endereco);
			new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
		} else {
			throw new Exception("O Endereço e a pessoa devem está preenchidos");
		}
	}

}