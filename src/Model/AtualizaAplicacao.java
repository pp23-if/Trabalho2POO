
package Model;


public class AtualizaAplicacao {
    
    public void atualizaAplicacaoComBancoDeDados (PessoaDAO pessoaDAO, MedicoDAO medicoDAO, FranquiaDAO franquiaDAO,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO,
            InfoConsultaDAO infoConsultaDAO, FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            AdmnistradorDAO admnistradorDAO)
    {
        pessoaDAO.BuscaPessoaNoBancoDeDados();
        medicoDAO.BuscaMedicoNoBancoDeDados(pessoaDAO);
    }
    
}
