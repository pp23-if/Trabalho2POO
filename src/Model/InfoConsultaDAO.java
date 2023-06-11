package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
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
    
    /*public void recebeConsultaRealizada(Consulta consulta, CalendarioSistema calendarioSistema)
    {
        InfoConsulta infoConsulta = new InfoConsulta(consulta, "", calendarioSistema.getDataHoraSistema());
        adicionaInfoConsulta(infoConsulta);
    }*/
    
     public void BuscaInfoConsultaNoBancoDeDados(ConsultaDAO consultaDAO) {

        listaInfoConsulta.clear();

        String buscaInfoConsulta = "select * from infoconsulta;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaInfoConsulta);
                ResultSet rs = pstm.executeQuery(buscaInfoConsulta)) {

            while (rs.next()) {

                InfoConsulta infoConsulta = new InfoConsulta();
                
                infoConsulta.setIdInfoConsulta(rs.getInt("idinfoconsulta"));
                
                int idConsulta = rs.getInt("idconsulta");
                Consulta consulta = consultaDAO.buscaConsultaPorId(idConsulta);
                
                infoConsulta.setConsulta(consulta);
                
                infoConsulta.setDescricao(rs.getString("descricaoconsulta"));
                
                Timestamp dataCriacaoInfoConsulta = rs.getTimestamp("datacriacao");
                infoConsulta.setDataCriacao(dataCriacaoInfoConsulta.toLocalDateTime());
                
                Timestamp dataModificacaoInfoConsulta = rs.getTimestamp("datamodificacao");
                
                if(dataModificacaoInfoConsulta != null)
                {
                   infoConsulta.setDataModificacao(dataModificacaoInfoConsulta.toLocalDateTime());  
                }
               
                
                listaInfoConsulta.add(infoConsulta);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados das Info Consultas no banco de dados!\n" + erro.getMessage());
        }
 
     }
}
