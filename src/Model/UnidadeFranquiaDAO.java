package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UnidadeFranquiaDAO {

    private List <UnidadeFranquia> listaUnidadeFranquia = new LinkedList();

    public UnidadeFranquiaDAO(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO, CalendarioSistema calendarioSistema) {
        
//        Pessoa donoUnidadeFranquia = pessoaDAO.buscaPessoaCadastrada("rgd", "30");
//
//        if (donoUnidadeFranquia != null) {
//            Franquia franquiaCadastrada = franquiaDAO.buscaFranquiaPorCnpj("123456789-30");
//
//            if (franquiaCadastrada != null) {
//                UnidadeFranquia unidadeFranquia = new UnidadeFranquia(franquiaCadastrada, "Uberaba",
//                        "Avenida Leopoldino De Oliveira - 580", donoUnidadeFranquia, calendarioSistema.getDataHoraSistema());
//
//                adicionaUnidadeFranquia(unidadeFranquia);
//            }
        
        
//         Pessoa donoUnidadeFranquia2 = pessoaDAO.buscaPessoaCadastrada("pp23", "69");
//         
//           if (donoUnidadeFranquia2 != null) {
//            Franquia franquiaCadastrada = franquiaDAO.buscaFranquiaPorCnpj("123456789-30");
//
//            if (franquiaCadastrada != null) {
//                UnidadeFranquia unidadeFranquia2 = new UnidadeFranquia(franquiaCadastrada, "Uberlandia",
//                        "Avenida Rondom Pacheco - 1200", donoUnidadeFranquia2, calendarioSistema.getDataHoraSistema());
//
//                adicionaUnidadeFranquia(unidadeFranquia2);
//            }
//        }

    }

    

    public boolean adicionaUnidadeFranquia(UnidadeFranquia unidadeFranquia) {
        return listaUnidadeFranquia.add(unidadeFranquia) == true;

    }

    public void MostraTodasUnidadesDeFranquia() {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null) {
                System.out.println(unidadeFranquia + "\n");
            }
        }
        
    }

    public UnidadeFranquia buscaUnidadeFranquiaAtravesDaPessoaVinculada(Pessoa pessoa) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.getPessoa().equals(pessoa)) {
                return unidadeFranquia;
            }
        }
        return null;
    }

    public UnidadeFranquia buscaUnidadeFranquiaAtravesDaFranquiaVinculada(Franquia franquia) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.getFranquia().equals(franquia)) {
                System.out.println(unidadeFranquia + "\n");
            }
        }
        return null;
    }

    public boolean verificaSeDonoUnidadeFranquiaExiste(Pessoa pessoa) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getCpf().equals(pessoa.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public UnidadeFranquia buscaUnidadeDeFranquia(UnidadeFranquia uniFran) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.equals(uniFran)) {
                return unidadeFranquia;
            }
        }
        return null;
    }

    public boolean atualizaCidadeUnidadeFranquia(UnidadeFranquia uf, String novaCidadeUnidadeFranquia,
            CalendarioSistema calendarioSistema) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

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
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

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
            for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

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

        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

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
            for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

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
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getLoginPessoa().equals(novoLoginDonoUnidadeFranquia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeTelefoneDonoDeUnidadeFranquiaEstaEmUso(String novoTelefoneDonoUnidadeFranquia) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null
                    && unidadeFranquia.getPessoa().getTelefonePessoa().equals(novoTelefoneDonoUnidadeFranquia)) {
                return true;
            }
        }
        return false;
    }

    public UnidadeFranquia buscaUnidadeFranquiaPorId(int idUnidadeFranquia)
    {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {
            
            if(unidadeFranquia != null && unidadeFranquia.getIdUnidadeFranquia() == idUnidadeFranquia)
            {
                return unidadeFranquia;
            }
        }
        return null;
    }
    
   
    
    
    public void BuscaUnidadeFranquiaNoBancoDeDados(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO) {

        listaUnidadeFranquia.clear();
        
        String buscaUnidadeFranquia = "select idunidadefranquia, cnpjfranquia, cidade, endereco, cpfdonounidade"
                + " from unidadefranquia;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaUnidadeFranquia);
                ResultSet rs = pstm.executeQuery(buscaUnidadeFranquia)) {

            while (rs.next()) {

                UnidadeFranquia unidadeFranquia = new UnidadeFranquia();

                String cpfDonoUnidadeFranquia = rs.getString("cpfdonounidade");
                Pessoa pessoaDonoUnidadeFranquia = pessoaDAO.buscaPessoaDonoDeUnidadeFranquiaPorCpf(cpfDonoUnidadeFranquia);
              
                String cnpjFranquia = rs.getString("cnpjfranquia");
                Franquia franquiaVinculada = franquiaDAO.buscaFranquiaPorCnpj(cnpjFranquia);
                
                unidadeFranquia.setPessoa(pessoaDonoUnidadeFranquia);
                unidadeFranquia.setFranquia(franquiaVinculada);
                
                unidadeFranquia.setIdUnidadeFranquia(rs.getInt("idunidadefranquia"));
                unidadeFranquia.setCidadeUnidadeFranquia(rs.getString("cidade"));
                unidadeFranquia.setEnderecoUnidadeFranquia(rs.getString("endereco"));
               
                

//                String dc = rs.getString("datacriacao");
//                LocalDateTime dataCriacao = null;
//                if (dataCriacao != null) {
//                    DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//                    dataCriacao.parse(dc, fd);
//                }
//
//                String dm = rs.getString("datamodificacao");
//                LocalDateTime dataModificacao = null;
//                DateTimeFormatter fdm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//                dataModificacao.parse(dm, fdm);
//                pessoa.setDataCriacao(dataCriacao);
//                pessoa.setDataModificacao(dataModificacao);
                listaUnidadeFranquia.add(unidadeFranquia);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados da Unidade de Franquia no banco de dados!\n" + erro.getMessage());
        }

    }
   
    
}
