package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaDAO {

    private Consulta vetorConsulta[] = new Consulta[50];

    public ConsultaDAO() {
    }

    private int proximaPosilivreConsulta() {
        for (int i = 0; i < vetorConsulta.length; i++) {
            if (vetorConsulta[i] == null) {
                return i;
            }

        }
        return -1;
    }

    public boolean adicionaConsulta(Consulta consulta) {
        int proxima = proximaPosilivreConsulta();
        if (proxima != -1) {
            vetorConsulta[proxima] = consulta;
            return true;
        } else {
            return false;
        }

    }

    public Consulta mostraTodasConsultas() {

        for (Consulta consulta : vetorConsulta) {

            if (consulta != null) {
                System.out.println(consulta + "\n");
            }
        }
        return null;
    }

    public Consulta buscaConsultaAtravesDaPessoaVinculada(Pessoa p) {
        for (Consulta consulta : vetorConsulta) {

            if (consulta != null && consulta.getPessoa().equals(p)) {
                System.out.println(consulta + "\n");
            }
        }
        return null;
    }

    public Consulta buscaConsultaPorId(int idConsulta) {
        for (Consulta consulta : vetorConsulta) {

            if (consulta != null && consulta.getIdConsulta() == idConsulta) {
                return consulta;
            }
        }
        return null;
    }

    public Consulta buscaConsultaRealizadaPorId(int idConsulta) {
        for (Consulta consulta : vetorConsulta) {

            if (consulta != null && consulta.getIdConsulta() == idConsulta
                    && consulta.getEstadoConsulta().equals("Realizada")) {
                return consulta;
            }
        }
        return null;
    }

    public boolean receConsultaECancela(Consulta consulta, CalendarioSistema calendarioSistema) {
        if (consulta != null && consulta.getEstadoConsulta().equals("Agendada")) {
            consulta.setEstadoConsulta("Cancelada");
            consulta.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public boolean verificaDisponibilidadeDiaEHoraConsultaMedico(LocalDate novoDiaConsulta,
            LocalTime novaHoraConsulta, Medico medico) {

        for (Consulta consulta : vetorConsulta) {

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
        for (Consulta consulta : vetorConsulta) {
            if (consulta != null
                    && consulta.getUnidadeFranquia().getFranquia().equals(franquia)) {

                System.out.println(consulta + "\n");
            }
        }
        return null;

    }

    public Consulta buscaConsultaPorMedico(Medico medico) {
        for (Consulta consulta : vetorConsulta) {
            if (consulta != null && consulta.getMedico().equals(medico)) {
                System.out.println(consulta + "\n");

            }
        }
        return null;
    }

    public boolean atenderConsulta(Medico medico, InfoConsultaDAO infoConsultaDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO) {
        for (Consulta consulta : vetorConsulta) {
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

        for (Consulta consulta : vetorConsulta) {

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
        for (Consulta consulta : vetorConsulta) {

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
        for (Consulta consulta : vetorConsulta) {
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

        for (Consulta consulta : vetorConsulta) {

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

        for (Consulta consulta : vetorConsulta) {

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

        for (Consulta consulta : vetorConsulta) {

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

}
