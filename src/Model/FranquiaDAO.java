package Model;

import java.util.LinkedList;
import java.util.List;

public class FranquiaDAO {

    private List <Franquia> listaFranquia = new LinkedList();

    public FranquiaDAO(PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema) {
        
        Pessoa donoDeFranquia = pessoaDAO.buscaPessoaCadastrada("Edu28", "24");

        if (donoDeFranquia != null) {
            Franquia franquia = new Franquia("Unimed".toUpperCase(), 
                    "123456789-30", "Sao Paulo",
                    "Avenida Paulista, 2000 - Centro", donoDeFranquia, 
                    calendarioSistema.getDataHoraSistema());

            adicionaFranquia(franquia);
        }

    }

    public Franquia buscaFranquia(Franquia f) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.equals(f)) {
                return franquia;
            }
        }
        return null;
    }

    public Franquia buscaFranquiaAtravesDaPessoaVinculada(Pessoa pessoaLogada) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().equals(pessoaLogada)) {
                return franquia;
            }
        }
        return null;
    }

    public boolean adicionaFranquia(Franquia franquia) {
        return listaFranquia.add(franquia) == true;

    }


    public void mostraTodasFranquias() {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null) {
                System.out.println(franquia + "\n");
            }
        }
    }

    public boolean verificaSeFranquiaExiste(String nomeFranquia, String cnpj) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getNomeFranquia().equals(nomeFranquia.toUpperCase())
                    || franquia != null && franquia.getNomeFranquia().equals(nomeFranquia.toLowerCase())
                    || franquia != null && franquia.getCnpj().equals(cnpj)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaDonosDeFranquia(Pessoa p) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getCpf().equals(p.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public boolean atualizarNomeFranquia(Franquia f, String novoNomeFranquia, 
            CalendarioSistema calendarioSistema) {

        if (!verificaSeNomeFranquiaEstaSendoUsado(novoNomeFranquia) == true) {
            for (Franquia franquia : listaFranquia) {

                if (franquia != null && franquia.equals(f)) {
                    franquia.setNomeFranquia(novoNomeFranquia.toUpperCase());
                    franquia.setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    

    public boolean atualizarCidadeFranquia(Franquia f, String novaCidadeFranquia, 
            CalendarioSistema calendarioSistema) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.equals(f)) {
                franquia.setCidade(novaCidadeFranquia);
                franquia.setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }
        return false;
    }

    public boolean atualizarEnderecoFranquia(Franquia f, String novoEnderecoFranquia, 
            CalendarioSistema calendarioSistema) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.equals(f)) {
                franquia.setEnderecoFranquia(novoEnderecoFranquia);
                franquia.setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }
        return false;
    }

    public boolean atualizaLoginDonoDeFranquia(Franquia f, String novoLoginDonoFranquia, 
            CalendarioSistema calendarioSistema) {

        if (!verificaSeLoginDonoFranquiaEstaSendoUsado(novoLoginDonoFranquia) == true) {
            for (Franquia franquia : listaFranquia) {

                if (franquia != null && franquia.equals(f)) {
                    franquia.getPessoa().setLoginPessoa(novoLoginDonoFranquia);
                    franquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean atualizaSenhaDonoDeFranquia(Franquia f, String novaSenhaDonoFranquia, 
            CalendarioSistema calendarioSistema) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.equals(f)) {
                franquia.getPessoa().setSenhaPessoa(novaSenhaDonoFranquia);
                franquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }
        return false;
    }

    public boolean atualizaTelefoneDonoDeFranquia(Franquia f, 
            String novoTelefoneDonoFranquia, CalendarioSistema calendarioSistema) {

        if (!verificaSeTelefoneDonoFranquiaEstaSendoUsado(novoTelefoneDonoFranquia) == true) {

            for (Franquia franquia : listaFranquia) {

                if (franquia != null && franquia.equals(f)) {
                    franquia.getPessoa().setTelefonePessoa(novoTelefoneDonoFranquia);
                    franquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verificaSeNomeFranquiaEstaSendoUsado(String novoNomeFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getNomeFranquia().equals(novoNomeFranquia.toUpperCase())
                    || franquia != null && franquia.getNomeFranquia().equals(novoNomeFranquia.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

   

    public boolean verificaSeLoginDonoFranquiaEstaSendoUsado(String novoLoginDonoFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getLoginPessoa().equals(novoLoginDonoFranquia)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneDonoFranquiaEstaSendoUsado(String novoTelefoneDonoFranquia) {
        for (Franquia franquia : listaFranquia) {

            if (franquia != null && franquia.getPessoa().getTelefonePessoa().equals(novoTelefoneDonoFranquia)) {
                return true;
            }
        }
        return false;
    }
    
   public Franquia buscaFranquiaPorCnpj(String cnpjFranquia)
   {
       for (Franquia franquia : listaFranquia) {
           
           if(franquia != null && franquia.getCnpj().equals(cnpjFranquia))
           {
               return franquia;
           }
       }
        return null;
   }
   
   public Franquia buscaFranquiaPorId(int idFranquia)
   {
       for (Franquia franquia : listaFranquia) {
           
           if(franquia != null && franquia.getIdFranquia() == idFranquia)
           {
               return franquia;
           }
       }
        return null;
   }

}
