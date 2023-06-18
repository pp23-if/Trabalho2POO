package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FinanceiroAdmDAO {

    private List<FinanceiroAdm> listaFinanceiroAdm = new LinkedList();

    public FinanceiroAdmDAO() {
    }

    public boolean adicionaFinanceiroAdm(FinanceiroAdm financeiroAdm) {
        return listaFinanceiroAdm.add(financeiroAdm) == true;

    }

    public FinanceiroAdm mostraTodosMovimentosFinanceiros() {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

    public FinanceiroAdm buscaMovimentacoesFinanceirasPorFranquia(Franquia franquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
        return null;
    }

//    public void geraMovimentacaoFinanceiraConsulta(Consulta consulta, CalendarioSistema calendarioSistema) {
//        FinanceiroAdm entradaConsultas = new FinanceiroAdm("Entrada", consulta.getValor(),
//                consulta.getUnidadeFranquia(), "Consulta", calendarioSistema.getDataHoraSistema());
//        adicionaFinanceiroAdm(entradaConsultas);
//    }
//
//    public void geraMovimentacaoFinanceiraProcedimento(Procedimento procedimento, CalendarioSistema calendarioSistema) {
//        FinanceiroAdm entradaProcedimentos = new FinanceiroAdm("Entrada", procedimento.getValorProcedimento(),
//                procedimento.getConsulta().getUnidadeFranquia(), "Procedimento", calendarioSistema.getDataHoraSistema());
//        adicionaFinanceiroAdm(entradaProcedimentos);
//    }
    public void geraMovimentacaoFinanceiraPagamentosFranquia(UnidadeFranquia unidadeFranquia,
            double valorPagamento, CalendarioSistema calendarioSistema) {
        FinanceiroAdm saidaPagamentoFranquia = new FinanceiroAdm("Saida", valorPagamento, unidadeFranquia,
                "PagamentoFranquia", calendarioSistema.getDataHoraSistema());
        //adicionaFinanceiroAdm(saidaPagamentoFranquia);

        inserePagamentoAvulsoEPagamentoFranquiaNoBancoDeDados(saidaPagamentoFranquia);
    }

    public boolean verificaPagamentoUnidade(CalendarioSistema calendarioSistema, UnidadeFranquia unidadeFranquia) {

        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDescritivoMovimento().equals("PagamentoFranquia")
                    && financeiroAdm.getDataCriacao().getMonthValue() == calendarioSistema.getDataHoraSistema().getMonthValue()) {
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

        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getDescritivoMovimento().equals("Consulta")
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == mesSitemaComparavel) {
                valorTotalConsultas += financeiroAdm.getValor();
            } else if (financeiroAdm != null
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
            CalendarioSistema calendarioSistema) {
        double valorAdministradora;

        valorAdministradora = (rendaBruta * 0.05) + 1000;

        geraMovimentacaoFinanceiraPagamentosFranquia(unidadeFranquia, valorAdministradora, calendarioSistema);

        return valorAdministradora;
    }

    public double calculaRendaLiquida(double rendaBruta, double parteAdministradora) {
        double rendaLiquida = rendaBruta - parteAdministradora;

        return rendaLiquida;
    }

    public void geraRelatorioEntradaSaidaFranquia(Franquia franquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaFranquiaMes(Franquia franquia, int numeroMes) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().getFranquia().equals(franquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void geraRelatorioEntradaSaidaUnidadeFranquiaMes(UnidadeFranquia unidadeFranquia, int numeroMes) {
        for (FinanceiroAdm financeiroAdm : listaFinanceiroAdm) {

            if (financeiroAdm != null
                    && financeiroAdm.getUnidadeFranquia().equals(unidadeFranquia)
                    && financeiroAdm.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroAdm + "\n");
            }
        }
    }

    public void buscaFinanceiroADMNoBancoDeDados(UnidadeFranquiaDAO unidadeFranquiaDAO) {

        listaFinanceiroAdm.clear();

        String buscaFinanceiroADM = "select * from financeiroadm";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(buscaFinanceiroADM); 
                ResultSet rs = pstm.executeQuery(buscaFinanceiroADM)) {

            while (rs.next()) {

                FinanceiroAdm financeiroAdm = new FinanceiroAdm();

                financeiroAdm.setIdFinanceiroAdm(rs.getInt("idfinanceiroadm"));

                int idUnidadeFranquia = rs.getInt("idunidadefranquia");

                UnidadeFranquia unidadeFranquia = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

                financeiroAdm.setUnidadeFranquia(unidadeFranquia);

                financeiroAdm.setTipoMovimento(rs.getString("tipomovimento"));

                financeiroAdm.setValor(rs.getDouble("valorfinanceiroadm"));

                financeiroAdm.setDescritivoMovimento(rs.getString("descritivomovimento"));

                Timestamp dataCriacaoFinanceiroADM = rs.getTimestamp("datacriacao");
                financeiroAdm.setDataCriacao(dataCriacaoFinanceiroADM.toLocalDateTime());

                Timestamp dataModificaoFinamceiroADM = rs.getTimestamp("datamodificacao");

                if (dataModificaoFinamceiroADM != null) {

                    financeiroAdm.setDataModificacao(dataModificaoFinamceiroADM.toLocalDateTime());

                }

                listaFinanceiroAdm.add(financeiroAdm);
            }

        } catch (SQLException erro) {

        }
    }

    public boolean inserePagamentoAvulsoEPagamentoFranquiaNoBancoDeDados(FinanceiroAdm financeiroAdm) {

        boolean pago = true;

        String pagamentoAvulso = "insert into financeiroadm (tipomovimento, valorfinanceiroadm, "
                + "idunidadefranquia, descritivomovimento, datacriacao) values (?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmPagamentoAvulso = connection.prepareStatement(pagamentoAvulso)) {

                pstmPagamentoAvulso.setString(1, financeiroAdm.getTipoMovimento());
                pstmPagamentoAvulso.setDouble(2, financeiroAdm.getValor());
                pstmPagamentoAvulso.setInt(3, financeiroAdm.getUnidadeFranquia().getIdUnidadeFranquia());
                pstmPagamentoAvulso.setString(4, financeiroAdm.getDescritivoMovimento());
                pstmPagamentoAvulso.setTimestamp(5, Timestamp.valueOf(financeiroAdm.getDataCriacao()));

                pstmPagamentoAvulso.execute();

                connection.commit();

            } catch (SQLException e) {
                System.out.println("\nNao foi Possivel Inserir O Pagamento no Bamco de Dados\n" + e.getMessage());
                pago = false;
                connection.rollback();
            }

        } catch (SQLException erro) {

        }
        return pago != false;
    }

}
