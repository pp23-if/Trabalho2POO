package Model;

public class UnidadeFranquiaDAO {

    private UnidadeFranquia[] vetorUnidadeFranquia = new UnidadeFranquia[50];

    public UnidadeFranquiaDAO(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO, CalendarioSistema calendarioSistema) {
        
        Pessoa donoUnidadeFranquia = pessoaDAO.buscaPessoaCadastrada("rgd", "30");

        if (donoUnidadeFranquia != null) {
            Franquia franquiaCadastrada = franquiaDAO.buscaFranquiaPorCnpj("123456789-30");

            if (franquiaCadastrada != null) {
                UnidadeFranquia unidadeFranquia = new UnidadeFranquia(franquiaCadastrada, "Uberaba",
                        "Avenida Leopoldino De Oliveira - 580", donoUnidadeFranquia, calendarioSistema.getDataHoraSistema());

                adicionaUnidadeFranquia(unidadeFranquia);
            }
        }
        
         Pessoa donoUnidadeFranquia2 = pessoaDAO.buscaPessoaCadastrada("pp23", "69");
         
           if (donoUnidadeFranquia2 != null) {
            Franquia franquiaCadastrada = franquiaDAO.buscaFranquiaPorCnpj("123456789-30");

            if (franquiaCadastrada != null) {
                UnidadeFranquia unidadeFranquia2 = new UnidadeFranquia(franquiaCadastrada, "Uberlandia",
                        "Avenida Rondom Pacheco - 1200", donoUnidadeFranquia2, calendarioSistema.getDataHoraSistema());

                adicionaUnidadeFranquia(unidadeFranquia2);
            }
        }

    }

    private int proximaPosilivreUnidadeFranquia() {
        for (int i = 0; i < vetorUnidadeFranquia.length; i++) {
            if (vetorUnidadeFranquia[i] == null) {
                return i;
            }

        }
        return -1;
    }

    public boolean adicionaUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        int proxima = proximaPosilivreUnidadeFranquia();
        if (proxima != -1) {
            vetorUnidadeFranquia[proxima] = unidadeFranquia;
            return true;
        } else {
            return false;
        }

    }

    public UnidadeFranquia MostraTodasUnidadesDeFranquia() {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null) {
                System.out.println(unidadeFranquia + "\n");
            }
        }
        return null;
    }

    public UnidadeFranquia buscaUnidadeFranquiaAtravesDaPessoaVinculada(Pessoa pessoa) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.getPessoa().equals(pessoa)) {
                return unidadeFranquia;
            }
        }
        return null;
    }

    public UnidadeFranquia buscaUnidadeFranquiaAtravesDaFranquiaVinculada(Franquia franquia) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.getFranquia().equals(franquia)) {
                System.out.println(unidadeFranquia + "\n");
            }
        }
        return null;
    }

    public boolean verificaSeDonoUnidadeFranquiaExiste(Pessoa pessoa) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getCpf().equals(pessoa.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public UnidadeFranquia buscaUnidadeDeFranquia(UnidadeFranquia uniFran) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.equals(uniFran)) {
                return unidadeFranquia;
            }
        }
        return null;
    }

    public boolean atualizaCidadeUnidadeFranquia(UnidadeFranquia uf, String novaCidadeUnidadeFranquia,
            CalendarioSistema calendarioSistema) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.equals(uf)) {
                unidadeFranquia.setCidadeUnidadeFranquia(novaCidadeUnidadeFranquia);
                unidadeFranquia.setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }
        return false;
    }

    public boolean atualizaEnderecoUnidadeDeFranquia(UnidadeFranquia uf, String novoEnderecoUnidadeFranquia,
            CalendarioSistema calendarioSistema) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.equals(uf)) {
                unidadeFranquia.setEnderecoUnidadeFranquia(novoEnderecoUnidadeFranquia);
                unidadeFranquia.setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }
        return false;
    }

    public boolean atualizaLoginDonoDeUnidadeDeFranquia(UnidadeFranquia uf, String novoLoginDonoDeUnidadeFranquia,
            CalendarioSistema calendarioSistema) {

        if (!verificaSeLoginDonoDeUnidadeFranquiaEstaEmUso(novoLoginDonoDeUnidadeFranquia) == true) {
            for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

                if (unidadeFranquia != null && unidadeFranquia.equals(uf)) {
                    unidadeFranquia.getPessoa().setLoginPessoa(novoLoginDonoDeUnidadeFranquia);
                    unidadeFranquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean atualizaSenhaDonoDeUnidadeDeFranquia(UnidadeFranquia uf, String novaSenhaDonoDeUnidadeFranquia,
            CalendarioSistema calendarioSistema) {

        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.equals(uf)) {
                unidadeFranquia.getPessoa().setSenhaPessoa(novaSenhaDonoDeUnidadeFranquia);
                unidadeFranquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }

        return false;
    }

    public boolean atualizaTelefoneDonoDeUnidadeDeFranquia(UnidadeFranquia uf, String novoTelefoneDonoDeUnidadeFranquia,
            CalendarioSistema calendarioSistema) {

        if (!verificaSeTelefoneDonoDeUnidadeFranquiaEstaEmUso(novoTelefoneDonoDeUnidadeFranquia) == true) {
            for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

                if (unidadeFranquia != null && unidadeFranquia.equals(uf)) {
                    unidadeFranquia.getPessoa().setTelefonePessoa(novoTelefoneDonoDeUnidadeFranquia);
                    unidadeFranquia.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }

        }

        return false;
    }

    public boolean verificaSeLoginDonoDeUnidadeFranquiaEstaEmUso(String novoLoginDonoUnidadeFranquia) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getLoginPessoa().equals(novoLoginDonoUnidadeFranquia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeTelefoneDonoDeUnidadeFranquiaEstaEmUso(String novoTelefoneDonoUnidadeFranquia) {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getTelefonePessoa().equals(novoTelefoneDonoUnidadeFranquia)) {
                return true;
            }
        }
        return false;
    }

    public UnidadeFranquia buscaUnidadeFranquiaPorId(int idUnidadeFranquia)
    {
        for (UnidadeFranquia unidadeFranquia : vetorUnidadeFranquia) {
            
            if(unidadeFranquia != null && unidadeFranquia.getIdUnidadeFranquia() == idUnidadeFranquia)
            {
                return unidadeFranquia;
            }
        }
        return null;
    }
    
   
}
