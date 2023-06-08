package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class UnidadeFranquiaDAO {

    private List<UnidadeFranquia> listaUnidadeFranquia = new LinkedList();

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

    /* public boolean atualizaCidadeUnidadeFranquia(UnidadeFranquia uf, String novaCidadeUnidadeFranquia,
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
    }*/
 /*public boolean atualizaLoginDonoDeUnidadeDeFranquia(UnidadeFranquia uf, String novoLoginDonoDeUnidadeFranquia,
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
    }*/
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

    public UnidadeFranquia buscaUnidadeFranquiaPorId(int idUnidadeFranquia) {
        for (UnidadeFranquia unidadeFranquia : listaUnidadeFranquia) {

            if (unidadeFranquia != null && unidadeFranquia.getIdUnidadeFranquia() == idUnidadeFranquia) {
                return unidadeFranquia;
            }
        }
        return null;
    }

    public boolean insereUnidadeFranquiaNoBancoDeDados(Pessoa pessoa, Franquia franquia, UnidadeFranquia unidadeFranquia) {

        boolean adicionado = true;

        String inserePessoaDonoUnidadeDeFranquia = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,"
                + "tipousuario, telefonepessoa, datacriacao) \n"
                + "values (?,?,?,?,?,?)";

        String insereUnidadeDeFranquia = "insert into unidadefranquia (cnpjfranquia, cidade, endereco, cpfdonounidade, datacriacao) \n"
                + "values (?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereDonoDeUnidadeDeFranquia = connection.prepareStatement(inserePessoaDonoUnidadeDeFranquia);
                    PreparedStatement pstmInsereUnidadeDeFranquia = connection.prepareStatement(insereUnidadeDeFranquia)) {

                pstmInsereDonoDeUnidadeDeFranquia.setString(1, pessoa.getCpf());
                pstmInsereDonoDeUnidadeDeFranquia.setString(2, pessoa.getLoginPessoa());
                pstmInsereDonoDeUnidadeDeFranquia.setString(3, pessoa.getSenhaPessoa());
                pstmInsereDonoDeUnidadeDeFranquia.setString(4, pessoa.getTipoUsuario());
                pstmInsereDonoDeUnidadeDeFranquia.setString(5, pessoa.getTelefonePessoa());
                pstmInsereDonoDeUnidadeDeFranquia.setTimestamp(6, Timestamp.valueOf(pessoa.getDataCriacao()));

                pstmInsereDonoDeUnidadeDeFranquia.execute();

                pstmInsereUnidadeDeFranquia.setString(1, franquia.getCnpj());
                pstmInsereUnidadeDeFranquia.setString(2, unidadeFranquia.getCidadeUnidadeFranquia());
                pstmInsereUnidadeDeFranquia.setString(3, unidadeFranquia.getEnderecoUnidadeFranquia());
                pstmInsereUnidadeDeFranquia.setString(4, pessoa.getCpf());
                pstmInsereUnidadeDeFranquia.setTimestamp(5, Timestamp.valueOf(unidadeFranquia.getDataCriacao()));

                pstmInsereUnidadeDeFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;
                System.out.println("\n Nao foi possivel inserir a unidade de franquia no banco de dados!\n" + erro.getMessage());

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }

    public void BuscaUnidadeFranquiaNoBancoDeDados(PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO) {

        listaUnidadeFranquia.clear();

        String buscaUnidadeFranquia = "select idunidadefranquia, cnpjfranquia, cidade, endereco, "
                + "cpfdonounidade, datacriacao, datamodificacao"
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

                Timestamp dataCriacaoUnidadeFranquia = rs.getTimestamp("datacriacao");
                unidadeFranquia.setDataCriacao(dataCriacaoUnidadeFranquia.toLocalDateTime());

                Timestamp dataModificacaoUnidadeFranquia = rs.getTimestamp("datamodificacao");

                if (dataModificacaoUnidadeFranquia != null) {
                    unidadeFranquia.setDataModificacao(dataModificacaoUnidadeFranquia.toLocalDateTime());
                }

                listaUnidadeFranquia.add(unidadeFranquia);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados da Unidade de Franquia no banco de dados!\n" + erro.getMessage());
        }

    }

    public boolean AtualizaCidadeUnidadeFranquiaNoBancoDeDados(String novaCidadeUnidadeFranquia,
            UnidadeFranquia unidadeFranquia, CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaCidadeUnidadeFranquia = "update unidadefranquia set cidade = ? where idunidadefranquia = ?";

        String atualizaDataAlteracaoUnidadeFranquia = "update unidadefranquia set datamodificacao = ? where idunidadefranquia = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaCidadeUnidadeFranquia
                    = connection.prepareStatement(atualizaCidadeUnidadeFranquia);
                    PreparedStatement pstmAtualizaDataAlteracaoUnidadeFranquia
                    = connection.prepareStatement(atualizaDataAlteracaoUnidadeFranquia)) {

                pstmAtualizaCidadeUnidadeFranquia.setString(1, novaCidadeUnidadeFranquia);
                pstmAtualizaCidadeUnidadeFranquia.setInt(2, unidadeFranquia.getIdUnidadeFranquia());

                pstmAtualizaCidadeUnidadeFranquia.execute();

                pstmAtualizaDataAlteracaoUnidadeFranquia.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoUnidadeFranquia.setInt(2, unidadeFranquia.getIdUnidadeFranquia());

                pstmAtualizaDataAlteracaoUnidadeFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
                System.out.println("\n Nao foi possivel Atualizar A Cidade Da Unidade Franquia no banco de dados!\n" + erro.getMessage());
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaEnderecoUnidadeFranquiaNoBancoDeDados(String novoEnderecoUnidadeFranquia,
            UnidadeFranquia unidadeFranquia, CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaEnderecoUnidadeFranquia = "update unidadefranquia set endereco = ? where idunidadefranquia = ?";

        String atualizaDataAlteracaoUnidadeFranquia = "update unidadefranquia set datamodificacao = ? where idunidadefranquia = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaEnderecoUnidadeFranquia = connection.prepareStatement(atualizaEnderecoUnidadeFranquia);
                    PreparedStatement pstmAtualizaDataAlteracaoUnidadeFranquia
                    = connection.prepareStatement(atualizaDataAlteracaoUnidadeFranquia)) {

                pstmAtualizaEnderecoUnidadeFranquia.setString(1, novoEnderecoUnidadeFranquia);
                pstmAtualizaEnderecoUnidadeFranquia.setInt(2, unidadeFranquia.getIdUnidadeFranquia());

                pstmAtualizaEnderecoUnidadeFranquia.execute();

                pstmAtualizaDataAlteracaoUnidadeFranquia.setTimestamp(1, Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));
                pstmAtualizaDataAlteracaoUnidadeFranquia.setInt(2, unidadeFranquia.getIdUnidadeFranquia());

                pstmAtualizaDataAlteracaoUnidadeFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
                System.out.println("\n Nao foi possivel Atualizar O Endereco Da Unidade Franquia no banco de dados!\n" + erro.getMessage());
            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean AtualizaLoginDonoUnidadeFranquiaNoBancoDeDados(String novoLoginDonoUnidadeFranquia, UnidadeFranquia unidadeFranquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaLoginDonoUnidadeFranquia = "update tipousuario set logintipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoUnidadeFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeLoginDonoDeUnidadeFranquiaEstaEmUso(novoLoginDonoUnidadeFranquia) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaLoginDonoUnidadeFranquia
                        = connection.prepareStatement(atualizaLoginDonoUnidadeFranquia);
                        PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoUnidadeFranquia)) {

                    pstmAtualizaLoginDonoUnidadeFranquia.setString(1, novoLoginDonoUnidadeFranquia);
                    pstmAtualizaLoginDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                    pstmAtualizaLoginDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                    pstmAtualizaLoginDonoUnidadeFranquia.execute();

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.execute();

                    connection.commit();

                } catch (SQLException erro) {

                    connection.rollback();
                    atualizado = false;
                    System.out.println("\n Nao foi possivel Atualizar O Login Do Dono de Franquia no banco de dados!\n"
                            + erro.getMessage());
                }

            } catch (Exception e) {
            }

        } else {
            atualizado = false;
        }

        return atualizado != false;

    }

    public boolean AtualizaSenhaDonoUnidadeFranquiaNoBancoDeDados(String novaSenhaDonoUnidadeFranquia, UnidadeFranquia unidadeFranquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaSenhaDonoFranquia = "update tipousuario set senhatipousuario = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoUnidadeFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmAtualizaSenhaDonoUnidadeFranquia
                    = connection.prepareStatement(atualizaSenhaDonoFranquia);
                    PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia
                    = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoUnidadeFranquia)) {

                pstmAtualizaSenhaDonoUnidadeFranquia.setString(1, novaSenhaDonoUnidadeFranquia);
                pstmAtualizaSenhaDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                pstmAtualizaSenhaDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                pstmAtualizaSenhaDonoUnidadeFranquia.execute();

                pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setTimestamp(1,
                        Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                atualizado = false;
                System.out.println("\n Nao foi possivel Atualizar a Senha Do Dono de Franquia no banco de dados!\n"
                        + erro.getMessage());
            }

        } catch (Exception e) {
        }

        return atualizado != false;

    }
    
    
     public boolean AtualizaTelefoneDonoUnidadeFranquiaNoBancoDeDados(String novoTelefoneDonoUnidadeFranquia, UnidadeFranquia unidadeFranquia,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = true;

        String atualizaTelefoneDonoUnidadeFranquia = "update tipousuario set telefonepessoa = ? where cpfpessoa = ? and tipousuario = ?";

        String atualizaDataAlteracaoPessoaDonoUnidadeFranquia = "update tipousuario set datamodificacao = ? "
                + "where cpfpessoa = ? and tipousuario = ?";

        if (!verificaSeTelefoneDonoDeUnidadeFranquiaEstaEmUso(novoTelefoneDonoUnidadeFranquia) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

                connection.setAutoCommit(false);

                try (PreparedStatement pstmAtualizaTelefoneDonoUnidadeFranquia
                        = connection.prepareStatement(atualizaTelefoneDonoUnidadeFranquia);
                        PreparedStatement pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia
                        = connection.prepareStatement(atualizaDataAlteracaoPessoaDonoUnidadeFranquia)) {

                    pstmAtualizaTelefoneDonoUnidadeFranquia.setString(1, novoTelefoneDonoUnidadeFranquia);
                    pstmAtualizaTelefoneDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                    pstmAtualizaTelefoneDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                    pstmAtualizaTelefoneDonoUnidadeFranquia.execute();

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setTimestamp(1,
                            Timestamp.valueOf(calendarioSistema.getDataHoraSistema()));

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(2, unidadeFranquia.getPessoa().getCpf());
                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.setString(3, unidadeFranquia.getPessoa().getTipoUsuario());

                    pstmAtualizaDataAlteracaoPessoaDonoUnidadeFranquia.execute();

                    connection.commit();

                } catch (SQLException erro) {

                    connection.rollback();
                    atualizado = false;
                    System.out.println("\n Nao foi possivel Atualizar O Telefone Do Dono de Franquia no banco de dados!\n"
                            + erro.getMessage());
                }

            } catch (Exception e) {
            }

        } else {
            atualizado = false;
        }

        return atualizado != false;

    }

}
