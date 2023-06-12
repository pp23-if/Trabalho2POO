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
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ProcedimentoDAO {

    private List <Procedimento> listaProcedimento = new LinkedList();

    public ProcedimentoDAO(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO) {
        
        
//        UnidadeFranquia unidadeEncontrada = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(2);
//        
//        if(unidadeEncontrada != null)
//        {
//            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(1);
//            
//            if(medicoEncontrado != null)
//            {
//                Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(1);
//                
//                if(pessoaEncontrada != null)
//                {
//                    
//                    DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                
//                    
//                    LocalDate diaConsulta = LocalDate.parse("27/01/2023", fdia);
//                    LocalTime horaConsulta = LocalTime.parse("15:23");
//                    
//                    Consulta consultaProcedimento = new Consulta(diaConsulta, horaConsulta, medicoEncontrado, 
//                            pessoaEncontrada, unidadeEncontrada, 500,"Agendada", calendarioSistema.getDataHoraSistema());
//                    
//                    if(consultaDAO.adicionaConsulta(consultaProcedimento) == true)
//                    {
//                        
//                        LocalDate diaProcedimento = LocalDate.parse("28/01/2023", fdia);
//                        LocalTime horaProcedimento = LocalTime.parse("16:14");
//                        
//                          Procedimento procedimentoMarcado = new Procedimento("Exame De Sangue", consultaProcedimento, 
//                            diaProcedimento, horaProcedimento, "Agendado", 1500, "", calendarioSistema.getDataHoraSistema());
//                    
//                        adicionaProcedimento(procedimentoMarcado);
//                    }
//                    
//                }
//            }
//        }
        
    }
    
    
    
    public void mostraTodosProcedimentos ()
    {
        for (Procedimento procedimento : listaProcedimento) {
            
            if(procedimento != null)
            {
                System.out.println(procedimento + "\n");
            }
            
        }
    }
    

    public boolean adicionaProcedimento(Procedimento procedimento) {
        return listaProcedimento.add(procedimento) == true;

    }


    public Procedimento buscaProcedimentoPorPaciente(Pessoa pessoa) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta().getPessoa().equals(pessoa)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorMedico(Medico medico) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta().getMedico().equals(medico)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorFranquia(Franquia franquia) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getConsulta()
                    .getUnidadeFranquia().getFranquia().equals(franquia)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoPorId(int idProcediemnto) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getIdProcedimento() == idProcediemnto) {
                return procedimento;
            }
        }
        return null;
    }

    public boolean recebeProcedimentoECancela(Procedimento procedimento, CalendarioSistema calendarioSistema) {
        if (procedimento != null && procedimento.getEstadoProcedimento().equals("Agendado")) {
            procedimento.setEstadoProcedimento("Cancelado");
            procedimento.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public boolean verificaDisponibilidadeDataEHoraProcedimentoMedico(LocalDate diaProcedimento, LocalTime horaProcedimento,
            Medico medico) {
        
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getDiaProcedimento().equals(diaProcedimento)
                    && procedimento.getHoraProcedimento().equals(horaProcedimento)
                    && procedimento.getConsulta().getMedico().equals(medico)) {

                return true;
            }
        }
        return false;
    }

    public boolean verificaDataProcedimento(CalendarioSistema calendarioSistema, LocalDate novoDiaProcedimento)
    {
        if(novoDiaProcedimento.isBefore(calendarioSistema.getDiaDoSistema()))
        {
          return true;  
        }
        return false;
    }
    
    
    public boolean recebeProcedimentoERemarca(LocalDate novoDiaProcedimento,
            LocalTime novaHoraProcedimento, Procedimento procedimento, CalendarioSistema calendarioSistema) {
        if (procedimento != null && procedimento.getEstadoProcedimento().equals("Agendado")) {
            procedimento.setDiaProcedimento(novoDiaProcedimento);
            procedimento.setHoraProcedimento(novaHoraProcedimento);
            procedimento.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;

    }

    public Procedimento buscaProcedimentosQueTemMedicoSolicitanteEPacienteEscolhido(Pessoa pessoa, Medico medico) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getConsulta().getPessoa().equals(pessoa)) {
                System.out.println(procedimento + "\n");
            }
        }
        return null;
    }

    public Procedimento buscaProcedimentoNaoRealizado(Medico medico, CalendarioSistema calendarioSistema) {
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Agendado")
                    && procedimento.getDiaProcedimento().isEqual(calendarioSistema.getDiaDoSistema())) {
                return procedimento;
            }

        }
        return null;
    }

    public boolean realizarProcedimento(Procedimento procedimento, String laudo,
            CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO) {
        if (procedimento != null) {
            procedimento.setEstadoProcedimento("Realizado");
            procedimento.setLaudo(laudo);
            procedimento.setDataModificacao(calendarioSistema.getDataHoraSistema());

            financeiroAdmDAO.geraMovimentacaoFinanceiraProcedimento(procedimento, calendarioSistema);
            return true;
        }
        return false;
    }

    public boolean cancelaProcedimentosNaoRealizadosNoDia(CalendarioSistema calendarioSistema) {

        boolean canceladas = false;
        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null && procedimento.getEstadoProcedimento().equals("Agendado")
                    && calendarioSistema.getDiaDoSistema().isAfter(procedimento.getDiaProcedimento())) {
                procedimento.setEstadoProcedimento("Cancelado");
                procedimento.setDataModificacao(calendarioSistema.getDataHoraSistema());
                canceladas = true;
            }
            if (canceladas == true) {
                return true;
            }
        }
        return false;

    }

    public double calculaValorProcedimentosDoMes(Medico medico, CalendarioSistema calendarioSistema, Franquia franquia) {
        
        double totalProcedimentos = 0;

        int mesSitemaComparavel = calendarioSistema.getDiaDoSistema().minusDays(1).getMonthValue();

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().getFranquia().equals(franquia)
                    && procedimento.getDiaProcedimento().getMonthValue() == mesSitemaComparavel) {
                
                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }
        
        return totalProcedimentos;
    }
    
    
     public double calculaValorProcedimentosPorUnidadeFranquia(Medico medico, UnidadeFranquia unidadeFranquia) {
        
        double totalProcedimentos = 0;

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().equals(unidadeFranquia)) {
                
                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }
        
        return totalProcedimentos;
    }
    
     public double calculaValorProcedimentosPorUnidadeFranquiaMes(Medico medico, UnidadeFranquia unidadeFranquia, int numeroMes) {
        
        double totalProcedimentos = 0;

        for (Procedimento procedimento : listaProcedimento) {

            if (procedimento != null
                    && procedimento.getConsulta().getMedico().equals(medico)
                    && procedimento.getEstadoProcedimento().equals("Realizado")
                    && procedimento.getConsulta().getUnidadeFranquia().equals(unidadeFranquia)
                    && procedimento.getDiaProcedimento().getMonthValue() == numeroMes) {
                
                totalProcedimentos += procedimento.getValorProcedimento();
            }

        }
        
        return totalProcedimentos;
    }
    
    public double calculaParteDescontoProcedimentos(double valorProcedimentos)
    {
        double valorParteProcedimento;
        
        valorParteProcedimento = valorProcedimentos * 0.50;
        
        return valorParteProcedimento;
    }
    
    
    public boolean insereProcedimentoNoBancoDeDados(Consulta consulta, Procedimento procedimento) {

        boolean adicionado = true;

        String insereProcedimento = "insert into procedimento (nomeprocedimento, idconsulta, diaprocedimento, "
                + "horaprocedimento, estadoprocedimento, valorprocedimento, laudo, datacriacao) values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereProcedimento = connection.prepareStatement(insereProcedimento)) {

               
               pstmInsereProcedimento.setString(1, procedimento.getNomeProcedimento());
               pstmInsereProcedimento.setInt(2, consulta.getIdConsulta());
               pstmInsereProcedimento.setDate(3, Date.valueOf(procedimento.getDiaProcedimento()));
               pstmInsereProcedimento.setTime(4, Time.valueOf(procedimento.getHoraProcedimento()));
               pstmInsereProcedimento.setString(5, procedimento.getEstadoProcedimento());
               pstmInsereProcedimento.setDouble(6, procedimento.getValorProcedimento());
               pstmInsereProcedimento.setString(7, procedimento.getLaudo());
               pstmInsereProcedimento.setTimestamp(8, Timestamp.valueOf(procedimento.getDataCriacao()));
               
               pstmInsereProcedimento.execute();
               
               
               connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;
                System.out.println("\n Nao foi possivel inserir o Procedimento no banco de dados!\n" + erro.getMessage());

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }
    
    
     public void BuscaProcedimentosNoBancoDeDados(ConsultaDAO consultaDAO) {

        listaProcedimento.clear();
    
        String buscaProcedimento = "select * from procedimento;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaProcedimento);
                ResultSet rs = pstm.executeQuery(buscaProcedimento)) {

            while (rs.next()) {

               Procedimento procedimento = new Procedimento();
                
               procedimento.setIdProcedimento(rs.getInt("idprocedimento"));
               procedimento.setNomeProcedimento(rs.getString("nomeprocedimento"));
               
               int idConsulta = rs.getInt("idconsulta");
               Consulta consulta = consultaDAO.buscaConsultaPorId(idConsulta);
               
               procedimento.setConsulta(consulta);
               
               Date diaProcedimento = rs.getDate("diaprocedimento");
               procedimento.setDiaProcedimento(diaProcedimento.toLocalDate());
               
               Time horaProcedimento = rs.getTime("horaprocedimento");
               procedimento.setHoraProcedimento(horaProcedimento.toLocalTime());
               
               procedimento.setEstadoProcedimento(rs.getString("estadoprocedimento"));
               procedimento.setValorProcedimento(rs.getDouble("valorprocedimento"));
               procedimento.setLaudo(rs.getString("laudo"));
               
               Timestamp dataCriacaoProcedimento = rs.getTimestamp("datacriacao");
               procedimento.setDataCriacao(dataCriacaoProcedimento.toLocalDateTime());
                       
               Timestamp dataModificacaoProcedimento = rs.getTimestamp("datamodificacao");
               
               if(dataModificacaoProcedimento != null)
               {
                 procedimento.setDataModificacao(dataModificacaoProcedimento.toLocalDateTime());
               }
               
                
                listaProcedimento.add(procedimento);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados dos Procedimentos no banco de dados!\n" + erro.getMessage());
        }

    }
    
}
