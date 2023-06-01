package Model;

import java.util.LinkedList;
import java.util.List;

public class AdmnistradorDAO {

    private List <Admnistrador> listaAdmnistrador = new LinkedList();
    
    

    public AdmnistradorDAO(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO, CalendarioSistema calendarioSistema) {
        
        Pessoa pessoaAdm = pessoaDAO.buscaPessoaCadastrada("roadm", "20");
        if (pessoaAdm != null) {
            Franquia franquiaSelecionada = franquiaDAO.buscaFranquiaPorCnpj("123456789-30");

            if (franquiaSelecionada != null) {
                Admnistrador adm = new Admnistrador(pessoaAdm, franquiaSelecionada, calendarioSistema.getDataHoraSistema());

                adicionaAdmnistrador(adm);
            }

        }

    }

    public boolean adicionaAdmnistrador(Admnistrador adm) {
       
        return listaAdmnistrador.add(adm) == true;

    }

    public Admnistrador buscaTodosAdmnistradores() {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            if (admnistrador != null) {
                System.out.println(admnistrador + "\n");
            }
        }
        return null;
    }

    public Admnistrador buscaAdmnistradorAtravesPessoaVinculada(Pessoa pessoa) {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            if (admnistrador != null && admnistrador.getPessoa().equals(pessoa)) {
                return admnistrador;
            }
        }
        return null;
    }
    
    public boolean verificaSeFranquiaPossuiAdmnistrador(Franquia franquia)
    {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            
            if(admnistrador != null && admnistrador.getFranquia().equals(franquia))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean verificaSePessoaEhAdmnistrador(Pessoa pessoa)
    {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            
            if(admnistrador != null 
               && admnistrador.getPessoa().getCpf().equals(pessoa.getCpf()))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean verificaSeLoginAdmnistradorEstaEmUso(String loginAdmnistrador)
    {
        for (Admnistrador admnistrador : listaAdmnistrador) {
            
            if(admnistrador != null && admnistrador.getPessoa().getLoginPessoa().equals(loginAdmnistrador))
            {
                return true;
            }
        }
        return false;
    }
}
