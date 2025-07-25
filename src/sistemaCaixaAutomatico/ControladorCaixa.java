/**
 * Autores: C.M.F. Rubira, P.A. Guerra e L.P. Tizzei
 * 
 * Introdução à Programação Orientada a Objetos usando Java
 * 
 * Estudo de Caso do Sistema de Caixa Automático
 * 
 * última modificação: março de 2014
 */

package sistemaCaixaAutomatico;

import sistemaCaixaAutomatico.ContaCor;


public class ControladorCaixa {
	//Atributos
	private CadastroContas dbContas;  // Banco de dados das contas
	private Caixa caixa;


	//Operacoes

	public ControladorCaixa(int senhaCaixa) {
		dbContas = new CadastroContas();
		caixa = new Caixa(senhaCaixa);
	}

	
	public float consultarSaldo (int num, int senha){
		ContaCor cta;
		cta = dbContas.buscarConta(num); // obtem referencia para o objeto que representa a conta 'num'
		if (cta==null)   // se numero de conta invalido ...
			return -1; // ... retorna -1 
		else             // caso contrario ... 
			return cta.obterSaldo(senha); // efetua consulta 
	} 

	/**
	 * Saques permitidos deve ser menor ou igual que o saldo disponível no caixa.
	 * @param num numero da conta corrente
	 * @param senha senha do cliente 
	 * @param val valor que sera retirado da conta
	 * @return true se o numero da conta e a senha estiverem corretos e se o valor a ser retirado esta disponível
	 * e é menor que o saldo atual. Caso contrário, retorna false.
	 */
	public boolean efetuarSaque (int num, int senha, float val){

		ContaCor cta;

		float saldoCaixa = this.caixa.obterSaldoCaixa();

		if (saldoCaixa < val ){
			System.out.println("O caixa nao possui R$"+val+" e precisa ser recarregado.");
			return false;
		}

		cta=dbContas.buscarConta(num);  // obtem a referencia para o objeto que representa a conta 'num'

		if (cta==null)  // se número de conta inválido ...
			return false;  // ... retorna false

		if (cta.debitarValor("Saque Automatico", val, senha)==false) // se saque recusado ...
			return false;  // retorna false
		else{
			this.caixa.liberarNotas((int)(val/10)); // libera pagamento
			return true;
		}


	}

	public boolean Tranferir(int numeroOrigem, int numeroDestino, int senha, float valor){
		ContaCor origem = dbContas.buscarConta(numeroOrigem);
		ContaCor destino = dbContas.buscarConta(numeroDestino);

		if(origem == null || destino == null){
			System.out.println("Conta(s) inválidas.");
			return false;
		}

		//Tenta debitar da conta de origem
		boolean debitoOK = origem.debitarValor("Transferencia para " + numeroDestino, valor, senha);
		if(!debitoOK){
			System.out.println("Falha no débito. Senha incorreta ouy slado insuficiente.");
			return false;
		}

		//Credita na conta de destino
		destino.CreditarValor(valor);

		//Cobra operação na origem
		origem.CobrarOperacao();

		//cobra operação da destino
		destino.CobrarOperacao();

		System.out.println("Transferencia realizada com sucesso.");
		return true;
	}

	public void recarregar(int senha){
		this.caixa.recarregar(senha);
	}

	public boolean validarSenha(int senha){
		return this.caixa.validarSenha(senha);
	}

	public void alternarModo(int senhaCaixa){
		this.caixa.alternarModo(senhaCaixa);
	}

	public int obterModoOperacaoAtual(){
		return this.caixa.obterModoOperacaoAtual();
	}

}