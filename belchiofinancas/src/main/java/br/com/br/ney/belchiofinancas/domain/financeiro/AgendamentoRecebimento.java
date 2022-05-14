package br.com.br.ney.belchiofinancas.domain.financeiro;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.br.ney.belchiofinancas.domain.pessoas.Pessoa;

@Entity
@DiscriminatorValue("R")
public class AgendamentoRecebimento extends AgendamentoFinanceiro{

	
	public AgendamentoRecebimento() {}
	
	public AgendamentoRecebimento(Pessoa pessoa, int id, Double valor, Date dataAgendamento, Date dataVencimento,
			String descricao) {
		super(pessoa, id, valor, dataAgendamento, dataVencimento, descricao);
		
	}
   
	



}
