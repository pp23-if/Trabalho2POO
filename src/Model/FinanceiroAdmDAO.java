package Model;

public class FinanceiroAdmDAO {

    private FinanceiroAdm[] vetotFinanceiroAdm = new FinanceiroAdm[50];

    public FinanceiroAdmDAO() {
    }

    private int proximaPosilivreFinanceiroAdm() {
        for (int i = 0; i < vetotFinanceiroAdm.length; i++) {
            if (vetotFinanceiroAdm[i] == null) {
                return i;
            }

        }
        return -1;
    }

    public boolean adicionaFinanceiroAdm(FinanceiroAdm financeiroAdm) {
        int proxima = proximaPosilivreFinanceiroAdm();
        if (proxima != -1) {
            vetotFinanceiroAdm[proxima] = financeiroAdm;
            return true;
        } else {
            return false;
        }

    }

    public FinanceiroAdm mostraTodosMovimentosFinanceiros() {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {

            if (financeiroAdm != null) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

    public FinanceiroAdm buscaMovimentacoesFinanceirasPorFranquia(Franquia franquia) {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

    public void geraMovimentacaoFinanceiraConsulta(Consulta consulta, CalendarioSistema calendarioSistema) {
        FinanceiroAdm entradaConsultas = new FinanceiroAdm("Entrada", consulta.getValor(),
                consulta.getUnidadeFranquia(), "Consulta", calendarioSistema.getDataHoraSistema());
        adicionaFinanceiroAdm(entradaConsultas);
    }

    public void geraMovimentacaoFinanceiraProcedimento(Procedimento procedimento, CalendarioSistema calendarioSistema) {
        FinanceiroAdm entradaProcedimentos = new FinanceiroAdm("Entrada", procedimento.getValorProcedimento(),
                procedimento.getConsulta().getUnidadeFranquia(), "Procedimento", calendarioSistema.getDataHoraSistema());
        adicionaFinanceiroAdm(entradaProcedimentos);
    }
    
    public void geraMovimentacaoFinanceiraPagamentosFranquia(UnidadeFranquia unidadeFranquia, 
            double valorPagamento, CalendarioSistema calendarioSistema)
    {
        FinanceiroAdm saidaPagamentoFranquia = new FinanceiroAdm("Saida", valorPagamento, unidadeFranquia, 
                "PagamentoFranquia", calendarioSistema.getDataHoraSistema());
        adicionaFinanceiroAdm(saidaPagamentoFranquia);
    }
    
    public boolean verificaPagamentoUnidade(CalendarioSistema calendarioSistema, UnidadeFranquia unidadeFranquia)
    {
       
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
            
            if(financeiroAdm != null
              && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
              && financeiroAdm.getDescritivoMovimento().equals("PagamentoFranquia")
              && financeiroAdm.getDataCriacao().getMonthValue() == calendarioSistema.getDataHoraSistema().getMonthValue())
            {
                return true;
            }
        }
        return false;
    }
    
  

    public double calculaRendaBruta(CalendarioSistema calendarioSistema, UnidadeFranquia unidadeFranquia) {
        
        double valorTotalConsultas = 0;
        double valorTotalprocedimentos = 0;
        double valorTotalEntradas;
      
        int mesSitemaComparavel = calendarioSistema.getDiaDoSistema().minusDays(1).getMonthValue();
        
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
             
             
            if (financeiroAdm != null
                    && financeiroAdm.getDescritivoMovimento().equals("Consulta")
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == mesSitemaComparavel) {
                valorTotalConsultas += financeiroAdm.getValor();
            } 
            else if (financeiroAdm != null
                    && financeiroAdm.getDescritivoMovimento().equals("Procedimento")
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == mesSitemaComparavel) {
                valorTotalprocedimentos += financeiroAdm.getValor();
            }

        }
        valorTotalEntradas = valorTotalConsultas + valorTotalprocedimentos;
      
        return valorTotalEntradas;

    }
    
    public double calculaParteValorAdmnistradora(double rendaBruta, UnidadeFranquia unidadeFranquia,
            CalendarioSistema calendarioSistema)
    {
        double valorAdministradora;
        
        valorAdministradora = (rendaBruta * 0.05) + 1000;
        
        geraMovimentacaoFinanceiraPagamentosFranquia(unidadeFranquia, valorAdministradora, calendarioSistema);
        
        return valorAdministradora;
    }
    
    public double calculaRendaLiquida(double rendaBruta, double parteAdministradora)
    {
        double rendaLiquida = rendaBruta - parteAdministradora;
        
        return rendaLiquida;
    }
    
    public void geraRelatorioEntradaSaidaFranquia(Franquia franquia)
    {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
            
            if(financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia))
            {
                System.out.println(financeiroAdm  + "\n");
            }
        }
    }
    
    public void geraRelatorioEntradaSaidaFranquiaMes(Franquia franquia, int numeroMes)
    {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
            
            if(financeiroAdm != null 
               && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)
               && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes)
            {
                System.out.println(financeiroAdm  + "\n");
            }
        }
    }
    
     public void geraRelatorioEntradaSaidaUnidadeFranquia(UnidadeFranquia unidadeFranquia)
    {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
            
            if(financeiroAdm != null && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia))
            {
                System.out.println(financeiroAdm  + "\n");
            }
        }
    }
     
      public void geraRelatorioEntradaSaidaUnidadeFranquiaMes(UnidadeFranquia unidadeFranquia, int numeroMes)
    {
        for (FinanceiroAdm financeiroAdm : vetotFinanceiroAdm) {
            
            if(financeiroAdm != null 
               && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
               && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes)
            {
                System.out.println(financeiroAdm  + "\n");
            }
        }
    }
    
}
