package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class FinanceiroMedicoDAO {

    private List<FinanceiroMedico> listaFinanceiroMedico = new LinkedList();

    public FinanceiroMedicoDAO() {
    }

    public boolean adicionaFinanceiroMedico(FinanceiroMedico financeiroMedico) {
        return listaFinanceiroMedico.add(financeiroMedico) == true;

    }

    public FinanceiroMedico mostraTodosFinanceiroMedico() {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null) {
                System.out.println(financeiroMedico + "\n");
            }
        }
        return null;
    }

    public boolean verificaCalculosValoresMedico(Medico medico, CalendarioSistema calendarioSistema, Franquia franquia) {

        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null
                    && financeiroMedico.getMedico().equals(medico)
                    && financeiroMedico.getEstado().equals("Agendado")
                    && financeiroMedico.getFranquia().equals(franquia)
                    && financeiroMedico.getDataCriacao().isEqual(calendarioSistema.getDataHoraSistema())) {
                return true;
            }
        }
        return false;
    }

    public FinanceiroMedico buscaPagamentosMedicosPorFranquia(Franquia franquia) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getFranquia().equals(franquia)) {
                System.out.println(financeiroMedico + "\n");
            }
        }
        return null;
    }

    public boolean buscaPagamentosMedicosPorFranquiaEhMes(Franquia franquia, CalendarioSistema calendarioSistema) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getFranquia().equals(franquia)
                    && financeiroMedico.getEstado().equals("Agendado")
                    && financeiroMedico.getDataCriacao().isEqual(calendarioSistema.getDataHoraSistema())) {
                System.out.println(financeiroMedico + "\n");
                return true;
            }
        }
        return false;

    }

    public FinanceiroMedico buscaPagamentosMedicosPorID(int idFinanceiroMedico) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getIdFinanceiroMedico() == idFinanceiroMedico) {
                return financeiroMedico;
            }
        }
        return null;
    }

    public FinanceiroMedico buscaPagamentosMedicosPorMedico(Medico medico) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getMedico().equals(medico)) {
                System.out.println(financeiroMedico + "\n");
            }
        }
        return null;
    }

    public FinanceiroMedico buscaPagamentosMedicosPorMedicoMes(Medico medico, int numeroMes) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getMedico().equals(medico)
                    && financeiroMedico.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroMedico + "\n");
            }
        }
        return null;
    }

    public boolean pagarMedico(FinanceiroMedico financeiroMedico, CalendarioSistema calendarioSistema) {
        if (financeiroMedico != null) {
            financeiroMedico.setEstado("Pago");
            financeiroMedico.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public double calculaValorLiquidoAReceberMedico(double valorTotalConsultas, double valorTotalProcedimentos,
            double parteUnidadeFranquiaConsulta, double parteUnidadeFranquiaProcedimento) {

        double valorLiquidoMedico = valorTotalConsultas + valorTotalProcedimentos;

        valorLiquidoMedico = valorLiquidoMedico - (parteUnidadeFranquiaConsulta + parteUnidadeFranquiaProcedimento);

        return valorLiquidoMedico;
    }

    public void geraRelatorioPagamentoMedicosPorFranquia(Franquia franquia) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null && financeiroMedico.getFranquia().equals(franquia)) {
                System.out.println(financeiroMedico + "\n");
            }
        }
    }

    public void geraRelatorioPagamentoMedicosPorFranquiaMes(Franquia franquia, int numeroMes) {
        for (FinanceiroMedico financeiroMedico : listaFinanceiroMedico) {

            if (financeiroMedico != null
                    && financeiroMedico.getFranquia().equals(franquia)
                    && financeiroMedico.getDataCriacao().getMonthValue() == numeroMes) {
                System.out.println(financeiroMedico + "\n");
            }
        }
    }

    public void buscaFinanceiroMedicoNoBancoDeDados(MedicoDAO medicoDAO, FranquiaDAO franquiaDAO) {
        
        listaFinanceiroMedico.clear();
        String buscaFinanceiroMedico = "select * from financeiromedico";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados(); 
                PreparedStatement pstm = connection.prepareStatement(buscaFinanceiroMedico);
                ResultSet rs = pstm.executeQuery(buscaFinanceiroMedico)) {

            while (rs.next()) {

                FinanceiroMedico financeiroMedico = new FinanceiroMedico();

                financeiroMedico.setIdFinanceiroMedico(rs.getInt("idfinanceiromedico"));
                financeiroMedico.setValor(rs.getDouble("valorfinanceiromedico"));

                String crm = rs.getString("crm");
                Medico medico = medicoDAO.buscaMedicoPorCrm(crm);
                financeiroMedico.setMedico(medico);

                financeiroMedico.setEstado(rs.getString("estado"));

                String cnpjFranquia = rs.getString("cnpjfranquia");
                Franquia franquia = franquiaDAO.buscaFranquiaPorCnpj(cnpjFranquia);
                financeiroMedico.setFranquia(franquia);

                Timestamp dataCriacaoFinanceiroMedico = rs.getTimestamp("datacriacao");
                financeiroMedico.setDataCriacao(dataCriacaoFinanceiroMedico.toLocalDateTime());

                Timestamp dataModificacaoFinanceiroMedico = rs.getTimestamp("datamodificacao");

                if (dataModificacaoFinanceiroMedico != null) {
                    financeiroMedico.setDataModificacao(dataModificacaoFinanceiroMedico.toLocalDateTime());
                }
                listaFinanceiroMedico.add(financeiroMedico);
            }

        } catch (SQLException erro) {
            System.out.println("\nNao foi possivel buscar finamceiro medico no banco de dados." + erro.getMessage());

        }

    }
}
