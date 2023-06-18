package Model;

public class AtualizaAplicacao {

    public void atualizaAplicacaoComBancoDeDados(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, FranquiaDAO franquiaDAO,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO,
            InfoConsultaDAO infoConsultaDAO, FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            AdmnistradorDAO admnistradorDAO) {
        
        
        pessoaDAO.BuscaPessoaNoBancoDeDados();
        medicoDAO.BuscaMedicoNoBancoDeDados(pessoaDAO);
        franquiaDAO.BuscaFranquiaNoBancoDeDados(pessoaDAO);
        unidadeFranquiaDAO.BuscaUnidadeFranquiaNoBancoDeDados(pessoaDAO, franquiaDAO);
        admnistradorDAO.BuscaAdmnistradorNoBancoDeDados(pessoaDAO, franquiaDAO);
        consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);
        infoConsultaDAO.BuscaInfoConsultaNoBancoDeDados(consultaDAO);
        procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);
        financeiroAdmDAO.buscaFinanceiroADMNoBancoDeDados(unidadeFranquiaDAO);
        financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);
    }

}
