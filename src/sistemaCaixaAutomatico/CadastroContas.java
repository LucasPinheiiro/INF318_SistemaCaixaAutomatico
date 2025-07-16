/**
 * Autores: C.M.F. Rubira, P.A. Guerra e L.P. Tizzei
 * 
 * Introdução à Programação Orientada a Objetos usando Java
 * 
 * Estudo de caso: Sistema de Caixa Automático
 * 
 * última modificação: março de 2014
 */

package sistemaCaixaAutomatico;


public class CadastroContas {
	//Atributos
	  private ContaCor c[];  // vetor de contas
	  
	//Operacoes
	  
	  public CadastroContas () {  // método construtor 
	    c=new ContaCor[4];// o índice zero não é utilizado
	    
	    c[1]=new ContaCor("Juliana",500,1,1, new CobrancaPremium());
	    System.out.println("Conta de Juliana criada com id 1 senha 1 e 500,00 -- Cobranca Premium");
	    
	    c[2]=new ContaCor("Maria",500,2,2, new CobrancaPorOperacao());
	    System.out.println("Conta de Maria criada com id 2 senha 2 e 500,00 -- Cobranca por operaçao");
	    
	    c[3]=new ContaCor("Nestor",500,3,3, new CobrancaBasica());
	    System.out.println("Conta de Nestor criada com id 3 senha 3 e 500,00 -- Cobranca Basica");
	    
	  }
	  
	  public ContaCor buscarConta (int num) {
	    if (num < 1 || num > 3)  // apenas os número 1, 2 e 3 são aceitos
	      return(null);
	    else
	      return(c[num]);
	  }

}