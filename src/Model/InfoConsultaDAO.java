package Model;

import java.util.LinkedList;
import java.util.List;

public class InfoConsultaDAO {

    private List <InfoConsulta> listaInfoConsulta = new LinkedList();

    public InfoConsultaDAO() {
    }

    public boolean adicionaInfoConsulta(InfoConsulta infoConsulta) {
        return listaInfoConsulta.add(infoConsulta) == true;

    }

    public InfoConsulta mostraTodasInfoConsultas() {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {

            if (infoConsulta != null) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }

    public InfoConsulta buscaInfoConsultasPorMedico(Medico medico) {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {

            if (infoConsulta != null && infoConsulta.getConsulta().getMedico().equals(medico)) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }
    
    public InfoConsulta buscaInfoConsultaPorId(int idInfoConsulta)
    {
        for (InfoConsulta infoConsulta : listaInfoConsulta) {
            
            if(infoConsulta != null && infoConsulta.getIdInfoConsulta() == idInfoConsulta)
            {
                return infoConsulta;
            }
        }
        return null;
    }

    public boolean atualizaDescricaoInfoConsulta(InfoConsulta infoConsulta, String descricao, 
            CalendarioSistema calendarioSistema)
    {
        if(infoConsulta != null)
        {
            infoConsulta.setDescricao(descricao);
            infoConsulta.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }
    
    public void recebeConsultaRealizada(Consulta consulta, CalendarioSistema calendarioSistema)
    {
        InfoConsulta infoConsulta = new InfoConsulta(consulta, "", calendarioSistema.getDataHoraSistema());
        adicionaInfoConsulta(infoConsulta);
    }
}
