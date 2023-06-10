package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ConsultaDAO {

    private List <Consulta> listaConsulta = new LinkedList();

    public ConsultaDAO() {
    }


    public boolean adicionaConsulta(Consulta consulta) {
        return listaConsulta.add(consulta) == true;

    }

    public Consulta mostraTodasConsultas() {

        for (Consulta consulta : listaConsulta) {

            if (consulta != null) {
                System.out.println(consulta + "\n");
            }
        }
        return null;
    }

    public Consulta buscaConsultaAtravesDaPessoaVinculada(Pessoa p) {
        for (Consulta consulta : listaConsulta) {

            if (consulta != null && consulta.getPessoa().equals(p)) {
                System.out.println(consulta + "\n");
            }
        }
        return null;
    }

    public Consulta buscaConsultaPorId(int idConsulta) {
        for (Consulta consulta : listaConsulta) {

            if (consulta != null && consulta.getIdConsulta() == idConsulta) {
                return consulta;
            }
        }
        return null;
    }

    public Consulta buscaConsultaRealizadaPorId(int idConsulta) {
        for (Consulta consulta : listaConsulta) {

            if (consulta != null && consulta.getIdConsulta() == idConsulta
                    && consulta.getEstadoConsulta().equals("Realizada")) {
                return consulta;
            }
        }
        return null;
    }

    /*public boolean receConsultaECancela(Consulta consulta, CalendarioSistema calendarioSistema) {
        if (consulta != null && consulta.getEstadoConsulta().equals("Agendada")) {
            consulta.setEstadoConsulta("Cancelada");
            consulta.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }*/

    public boolean verificaDisponibilidadeDiaEHoraConsultaMedico(LocalDate novoDiaConsulta,
            LocalTime novaHoraConsulta, Medico medico) {

        for (Consulta consulta : listaConsulta) {

            if (consulta != null && consulta.getDiaConsulta().equals(novoDiaConsulta)
                    && consulta.getHoraConsulta().equals(novaHoraConsulta)
                    && consulta.getMedico().equals(medico)) {
                return true;
            }

        }
        return false;
    }
    
    public boolean verificaDataConsulta(CalendarioSistema calendarioSistema, LocalDate novoDiaConsulta)
    {
        if(novoDiaConsulta.isBefore(calendarioSistema.getDiaDoSistema()))
        {
          return true;  
        }
        return false;
    }

    public boolean recebeConsultaERemarca(LocalDate novoDiaConsulta,
            LocalTime novaHoraConsulta, Consulta consulta, CalendarioSistema calendarioSistema) {
        if (consulta != null && consulta.getEstadoConsulta().equals("Agendada")) {
            consulta.setDiaConsulta(novoDiaConsulta);
            consulta.setHoraConsulta(novaHoraConsulta);
            consulta.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public Consulta buscaConsultaPorFranquia(Franquia franquia) {
        for (Consulta consulta : listaConsulta) {
            if (consulta != null
                    && consulta.getUnidadeFranquia().getFranquia().equals(franquia)) {

                System.out.println(consulta + "\n");
            }
        }
        return null;

    }

    public Consulta buscaConsultaPorMedico(Medico medico) {
        for (Consulta consulta : listaConsulta) {
            if (consulta != null && consulta.getMedico().equals(medico)) {
                System.out.println(consulta + "\n");

            }
        }
        return null;
    }

    public boolean atenderConsulta(Medico medico, InfoConsultaDAO infoConsultaDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO) {
        for (Consulta consulta : listaConsulta) {
            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getEstadoConsulta().equals("Agendada")
                    && consulta.getDiaConsulta().isEqual(calendarioSistema.getDiaDoSistema())) {

                consulta.setEstadoConsulta("Realizada");
                consulta.setDataModificacao(calendarioSistema.getDataHoraSistema());

                infoConsultaDAO.recebeConsultaRealizada(consulta, calendarioSistema);
                financeiroAdmDAO.geraMovimentacaoFinanceiraConsulta(consulta, calendarioSistema);

                return true;
            }
        }
        return false;
    }

    public Consulta buscaConsultasDoDia(CalendarioSistema calendarioSistema, Medico medico) {

        for (Consulta consulta : listaConsulta) {

            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getEstadoConsulta().equals("Agendada")
                    && consulta.getDiaConsulta().isEqual(calendarioSistema.getDiaDoSistema())) {
                System.out.println(consulta + "\n");
            }
        }
        return null;
    }

    public Consulta buscaConsultasQueTemMedicoSolicitanteEPacienteEscolhido(Pessoa pessoa, Medico medico) {
        for (Consulta consulta : listaConsulta) {

            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getPessoa().equals(pessoa)) {
                System.out.println(consulta + "\n");
            }
        }
        return null;

    }

    public boolean cancelaConsultasNaoRealizadasNoDia(CalendarioSistema calendarioSistema) {

        boolean canceladas = false;
        for (Consulta consulta : listaConsulta) {
            if (consulta != null && consulta.getEstadoConsulta().equals("Agendada")
                    && calendarioSistema.getDiaDoSistema().isAfter(consulta.getDiaConsulta())) {

                consulta.setEstadoConsulta("Cancelada");
                consulta.setDataModificacao(calendarioSistema.getDataHoraSistema());
                canceladas = true;
            }
            if (canceladas == true) {
                return true;
            }
        }
        return false;

    }

    public double calculaValorConsultasDoMes(Medico medico, CalendarioSistema calendarioSistema, Franquia franquia) {
        
        double totalConsulta = 0;

        int mesSitemaComparavel = calendarioSistema.getDiaDoSistema().minusDays(1).getMonthValue();

        for (Consulta consulta : listaConsulta) {

            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getEstadoConsulta().equals("Realizada")
                    && consulta.getUnidadeFranquia().getFranquia().equals(franquia)
                    && consulta.getDiaConsulta().getMonthValue() == mesSitemaComparavel) {
                
                totalConsulta += consulta.getValor();
            }
        }

        return totalConsulta;
    }
    
    public double calculaValorConsultasPorUnidadeFranquia(Medico medico, UnidadeFranquia unidadeFranquia) {
        
        double totalConsulta = 0;

        for (Consulta consulta : listaConsulta) {

            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getEstadoConsulta().equals("Realizada")
                    && consulta.getUnidadeFranquia().equals(unidadeFranquia)) {
                
                totalConsulta += consulta.getValor();
            }
        }

        return totalConsulta;
    }
    
     public double calculaValorConsultasPorUnidadeFranquiaMes(Medico medico, UnidadeFranquia unidadeFranquia, int numeroMes) {
        
        double totalConsulta = 0;

        for (Consulta consulta : listaConsulta) {

            if (consulta != null
                    && consulta.getMedico().equals(medico)
                    && consulta.getEstadoConsulta().equals("Realizada")
                    && consulta.getUnidadeFranquia().equals(unidadeFranquia)
                    && consulta.getDiaConsulta().getMonthValue() == numeroMes) {
                
                totalConsulta += consulta.getValor();
            }
        }

        return totalConsulta;
    }
    
    public double calculaParteDescontoConsultas(double valorConsultas)
    {
        double valorParteConsulta;
        
        valorParteConsulta = valorConsultas * 0.30;
        
        return valorParteConsulta;
    }
    
    
     public boolean insereConsultaNoBancoDeDados(Consulta consulta) {

        boolean adicionado = true;

        String insereConsulta = "insert into consulta (diaconsulta, horaconsulta, estadoconsulta, crm,"
                + "cpfpessoa, valorconsulta, idunidadefranquia, datacriacao) values (?,?,?,?,?,?,?,?)";


        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereConsulta = connection.prepareStatement(insereConsulta)) {

               pstmInsereConsulta.setDate(1, Date.valueOf(consulta.getDiaConsulta()));
               pstmInsereConsulta.setTime(2, Time.valueOf(consulta.getHoraConsulta()));
               pstmInsereConsulta.setString(3, consulta.getEstadoConsulta());
               pstmInsereConsulta.setString(4, consulta.getMedico().getCrm());
               pstmInsereConsulta.setString(5, consulta.getPessoa().getCpf());
               pstmInsereConsulta.setDouble(6, consulta.getValor());
               pstmInsereConsulta.setInt(7, consulta.getUnidadeFranquia().getIdUnidadeFranquia());
               pstmInsereConsulta.setTimestamp(8, Timestamp.valueOf(consulta.getDataCriacao()));
               
               pstmInsereConsulta.execute();
               
               connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;
                System.out.println("\n Nao foi possivel inserir a Consulta no banco de dados!\n" + erro.getMessage());

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }
     
     
     
     public void BuscaConsultaNoBancoDeDados(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO) {

        listaConsulta.clear();

        String buscaConsulta = "select * from consulta;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaConsulta);
                ResultSet rs = pstm.executeQuery(buscaConsulta)) {

            while (rs.next()) {

                Consulta consulta = new Consulta();
                
                consulta.setIdConsulta(rs.getInt("idconsulta"));
                
                Date diaConsulta = rs.getDate("diaconsulta");
                consulta.setDiaConsulta(diaConsulta.toLocalDate());
                
                Time horaConsulta = rs.getTime("horaconsulta");
                consulta.setHoraConsulta(horaConsulta.toLocalTime());
                
                consulta.setEstadoConsulta(rs.getString("estadoconsulta"));
                
                String crm = rs.getString("crm");
                Medico medicoConsulta = medicoDAO.buscaMedicoPorCrm(crm);
                
                String cpfConsulta = rs.getString("cpfpessoa");
                Pessoa pessoaConsulta = pessoaDAO.buscaPessoaPacientePorCpf(cpfConsulta);
                
                consulta.setMedico(medicoConsulta);
                consulta.setPessoa(pessoaConsulta);
                consulta.setValor(rs.getDouble("valorconsulta"));
                
                int unidadeFranquiaConsulta = rs.getInt("idunidadefranquia");
                UnidadeFranquia unidadeFranquia = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(unidadeFranquiaConsulta);
                
                consulta.setUnidadeFranquia(unidadeFranquia);
                
                Timestamp dataCriacaoConsulta = rs.getTimestamp("datacriacao");
                consulta.setDataCriacao(dataCriacaoConsulta.toLocalDateTime());
                
                Timestamp dataModficacaoConsulta = rs.getTimestamp("datamodificacao");
                
                if(dataModficacaoConsulta != null)
                {
                   consulta.setDataModificacao(dataModficacaoConsulta.toLocalDateTime()); 
                }
                
                
                listaConsulta.add(consulta);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados das Consultas no banco de dados!\n" + erro.getMessage());
        }

    }
     

     public boolean cancelaConsultaNoBancoDeDados(Consulta consulta, CalendarioSistema calendarioSistema) {

        boolean cancelado = true;

        String cancelaConsulta = "update consulta set estadoconsulta = ? where idconsulta = ?";
        
        String atualizaDataAlteracaoConsulta = "update consulta set datamodificacao = ? where idconsulta = ?";


        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmCancelaConsulta = connection.prepareStatement(cancelaConsulta);
                  PreparedStatement pstmAtualizaDataAlteracaoConsulta = connection.prepareStatement(atualizaDataAlteracaoConsulta)) {

                pstmCancelaConsulta.setString(1, "Cancelada");
                pstmCancelaConsulta.setInt(2, consulta.getIdConsulta());
                
                pstmCancelaConsulta.execute();
                
                pstmAtualizaDataAlteracaoConsulta.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoConsulta.setInt(2, consulta.getIdConsulta());
                
                pstmAtualizaDataAlteracaoConsulta.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                cancelado = false;
                System.out.println("\n Nao foi possivel Cancelar a Consulta no banco de dados!\n" + erro.getMessage());
            }

        } catch (Exception e) {
        }

        return cancelado != false;
    }

     
}
