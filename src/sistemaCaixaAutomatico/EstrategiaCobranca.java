package sistemaCaixaAutomatico;

interface EstrategiaCobranca {
    public void CobrarOperacao(ContaCor conta, int senha);
}

class CobrancaPremium implements EstrategiaCobranca{

    private int TAXA_COBRARNCA = 0;

    @Override
    public void CobrarOperacao(ContaCor conta, int senha) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'CobrarOperacao'");
        if ( false == conta.debitarValor("desbita valor tranferencia" + TAXA_COBRARNCA, TAXA_COBRARNCA, senha)){
            System.out.println("Erro na tranferencia entre contas");
        }

    }

}

class CobrancaBasica implements EstrategiaCobranca {

    private int LIMITE_OPERACOES = 10;
    private int operacoesRestantes;
    private int TAXA_COBRARNCA = 10;

    public CobrancaBasica() {
        operacoesRestantes = LIMITE_OPERACOES;
    }

    @Override
    public void CobrarOperacao(ContaCor conta, int senha) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'CobrarOperacao'");
        if (operacoesRestantes > 0) {
            if (false == conta.debitarValor("desbita valor tranferencia" + TAXA_COBRARNCA, TAXA_COBRARNCA, senha)){
                System.out.println("Erro na cobranca da taxa de tranferencia entre contas");
            }else{
                operacoesRestantes--;
            }
        }
    }

}

class CobrancaPorOperacao implements EstrategiaCobranca {

    private int TAXA_COBRARNCA = 10;

    @Override
    public void CobrarOperacao(ContaCor conta, int senha) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'CobrarOperacao'");
            if ( false == conta.debitarValor("desbita valor tranferencia" + TAXA_COBRARNCA, TAXA_COBRARNCA, senha)){
                System.out.println("Erro na tranferencia entre contas");
        }
    }
    
}