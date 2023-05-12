package Model;

public class InfoConsultaDAO {

    private InfoConsulta vetorInfoConsulta[] = new InfoConsulta[50];

    public InfoConsultaDAO() {
    }

    public boolean adicionaInfoConsulta(InfoConsulta infoConsulta) {
        int proxima = proximaPosilivreInfoConsulta();
        if (proxima != -1) {
            vetorInfoConsulta[proxima] = infoConsulta;
            return true;
        } else {
            return false;
        }

    }

    private int proximaPosilivreInfoConsulta() {
        for (int i = 0; i < vetorInfoConsulta.length; i++) {
            if (vetorInfoConsulta[i] == null) {
                return i;
            }

        }
        return -1;
    }

    public InfoConsulta mostraTodasInfoConsultas() {
        for (InfoConsulta infoConsulta : vetorInfoConsulta) {

            if (infoConsulta != null) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }

    public InfoConsulta buscaInfoConsultasPorMedico(Medico medico) {
        for (InfoConsulta infoConsulta : vetorInfoConsulta) {

            if (infoConsulta != null && infoConsulta.getConsulta().getMedico().equals(medico)) {
                System.out.println(infoConsulta + "\n");
            }
        }
        return null;
    }
    
    public InfoConsulta buscaInfoConsultaPorId(int idInfoConsulta)
    {
        for (InfoConsulta infoConsulta : vetorInfoConsulta) {
            
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
