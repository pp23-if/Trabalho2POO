package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import javax.swing.text.DateFormatter;

public class PessoaDAO {

    private List<Pessoa> listaPessoa = new LinkedList();

    public PessoaDAO(CalendarioSistema calendarioSistema) {

//      Pessoa pessoa = new Pessoa("Pedro Augusto Rodrigues", "123",  "Rua 13", "225544",
//               "1", "1", "Paciente", calendarioSistema.getDataHoraSistema());
//       adicionaPessoa(pessoa);
//
//        Pessoa pessoa2 = new Pessoa("Lucas Rocha Pereira", "7272", "rua 32", "9999",
//                "lu123", "2", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa2);
//
//        Pessoa pessoa3 = new Pessoa("Lucas Rocha Pereira", "7272", "rua 32", "9999",
//                "lm23", "456", "Medico", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa3);
//
//        Pessoa pessoa4 = new Pessoa("Juliana Carla De Souza", "789101010", "rua da batata", "3333",
//                "ju", "10", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa4);
//
//        Pessoa pessoa5 = new Pessoa("Juliana Carla De Souza", "789101010", "rua da batata", "3333",
//                "ju25", "123", "Medico", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa5);
//
//        Pessoa pessoa6 = new Pessoa("Eduardo Silvestre", "456666", "Rua dos Limoes", "33112020",
//                "dudu10", "12", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa6);
//
//        Pessoa pessoa7 = new Pessoa("Eduardo Silvestre", "456666", "Rua dos Limoes", "33112020",
//                "Edu28", "24", "DonodeFranquia", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa7);
//        
//        Pessoa pessoa8 = new Pessoa("Roberto Gomes", "332288", "Rua das uvas - 17", "32985427",
//                "gb35", "50", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa8);
//        
//        Pessoa pessoa9 = new Pessoa("Roberto Gomes", "332288", "Rua das uvas - 17", "32985427",
//                "rgd", "30", "DonoDeUnidadeDeFranquia", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa9);
//        
//         Pessoa pessoa10 = new Pessoa("Marcos Pereira Faria", "3315152626", "Rua das Bananas - 600", "999612854",
//                "mp22", "36", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa10);
//        
//        Pessoa pessoa11 = new Pessoa("Marcos Pereira Faria", "3315152626", "Rua das Bananas - 600", "999612854",
//                "mpm", "100", "Medico", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa11);
//        
//         Pessoa pessoa12 = new Pessoa("Roberta Miranda De Sousa", "77882424", "Rua dos Abacates - 557", "99330044",
//                "ro25", "99", "Paciente", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa12);
//        
//        Pessoa pessoa13 = new Pessoa("Roberta Miranda De Sousa", "77882424", "Rua dos Abacates - 557", "99330044",
//                "roadm", "20", "Admnistrador", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa13);
//        
//        
//        Pessoa pessoa14 = new Pessoa("Pedro Augusto Rodrigues", "123", "Rua 13", "225544",
//                "pp23", "69", "DonoDeUnidadeDeFranquia", calendarioSistema.getDataHoraSistema());
//        adicionaPessoa(pessoa14);
    }

    public boolean adicionaPessoa(Pessoa pessoa) {

        if (listaPessoa.add(pessoa) == true) {
            return true;
        }

        return false;

    }

    public void mostraTodasPessoas() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public Pessoa buscaPessoaCadastrada(String login, String senha) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    && pessoa.getSenhaPessoa().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean verificaSePessoaExiste(String login, String cpf) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    || pessoa != null && pessoa.getCpf().equals(cpf)) {
                return true;
            }

        }
        return false;
    }

    public Pessoa buscaPessoaQuerendoLogar(String login, String senha) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)
                    && pessoa.getSenhaPessoa().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }

    /*public boolean atualizaNomePessoa(String nomePessoa, String novoNomePessoa,
            String cpf, CalendarioSistema calendarioSistema) {

        boolean atualizado = false;

        if (!verificaSeNomeEstaSendoUsado(novoNomePessoa) == true) {

            for (Pessoa pessoa : listaPessoa) {

                if (pessoa != null && pessoa.getNomePessoa().equals(nomePessoa)
                        && pessoa.getCpf().equals(cpf)) {
                    pessoa.setNomePessoa(novoNomePessoa);
                    pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                    atualizado = true;

                }

            }
        }
        return atualizado;

    }*/
    public boolean atualizaCpfPessoa(String cpf, String novoCpf,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = false;

        if (!verificaSeCpfEstaSendoUsado(novoCpf) == true) {
            for (Pessoa pessoa : listaPessoa) {
                if (pessoa != null && pessoa.getCpf().equals(cpf)) {
                    pessoa.setCpf(novoCpf);
                    pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                    atualizado = true;
                }

            }
        }

        return atualizado;
    }

    public boolean atualizaEnderecoPessoa(String endereco, String novoEndereco,
            CalendarioSistema calendarioSistema) {

        boolean atualizado = false;

        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getEnderecoPessoa().equals(endereco)) {
                pessoa.setEnderecoPessoa(novoEndereco);
                pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                atualizado = true;
            }

        }
        return atualizado;
    }

    public boolean atualizaTelefonePessoa(String telefone, String novoTelefone, String tipoUsuario,
            CalendarioSistema calendarioSistema) {

        if (!verificaSeTelefoneEstaSendoUsado(novoTelefone) == true) {
            for (Pessoa pessoa : listaPessoa) {

                if (pessoa != null && pessoa.getTelefonePessoa().equals(telefone)
                        && pessoa.getTipoUsuario().equals(tipoUsuario)) {
                    pessoa.setTelefonePessoa(novoTelefone);
                    pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean atualizaLoginPessoa(String login, String novoLogin, String tipoUsuario,
            CalendarioSistema calendarioSistema) {
        if (!verificaSeloginEstaSendoUsado(novoLogin) == true) {
            for (Pessoa pessoa : listaPessoa) {

                if (pessoa != null && pessoa.getLoginPessoa().equals(login) && pessoa.getTipoUsuario().equals(tipoUsuario)) {
                    pessoa.setLoginPessoa(novoLogin);
                    pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }

            }
        }

        return false;
    }

    public boolean atualizaSenhaPessoa(String senha, String login, String novaSenha, String tipoUsuario,
            CalendarioSistema calendarioSistema) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getLoginPessoa().equals(login) && pessoa.getSenhaPessoa().equals(senha)
                    && pessoa.getTipoUsuario().equals(tipoUsuario)) {
                pessoa.setSenhaPessoa(novaSenha);
                pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }

        }
        return false;
    }

    /*private boolean verificaSeNomeEstaSendoUsado(String nome) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getNomePessoa().equals(nome)) {
                return true;
            }
        }
        return false;
    }*/
    private boolean verificaSeCpfEstaSendoUsado(String cpf) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneEstaSendoUsado(String telefone) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTelefonePessoa().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeloginEstaSendoUsado(String login) {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getLoginPessoa().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public Pessoa filtraPessoasCandidatasADonoDeFranquia() {

        String cpfDono = null;

        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("DonodeFranquia")) {
                cpfDono = pessoa.getCpf();
            }
            if (pessoa != null && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getCpf().equals(cpfDono)
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {

                System.out.println(pessoa + "\n");
            }

        }

        return null;
    }

    public Pessoa buscaPessoaPorId(int idPessoa) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getId() == idPessoa) {
                return pessoa;
            }
        }
        return null;

    }

    public Pessoa filtraPessoasCandidatasAMedico() {

        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getTipoUsuario().equals("DonodeFranquia")
                    && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }

        return null;
    }

    public Pessoa filtraPessoaCandidatasADonoUnidadeFranquia() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && !pessoa.getTipoUsuario().equals("Medico")
                    && !pessoa.getTipoUsuario().equals("DonodeFranquia")
                    && !pessoa.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")
                    && !pessoa.getTipoUsuario().equals("Admnistrador")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public Pessoa filtraPacientes() {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("Paciente")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public Pessoa filtraMedicos() {
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null && pessoa.getTipoUsuario().equals("Medico")
                    && pessoa.isHabilitado() == true) {
                System.out.println(pessoa + "\n");
            }
        }
        return null;
    }

    public boolean excluirPaciente(Pessoa pessoa, CalendarioSistema calendarioSistema) {
        if (pessoa != null
                && pessoa.getTipoUsuario().equals("Paciente")
                && pessoa.isHabilitado() == true) {
            pessoa.setHabilitado(false);
            pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public void filtraPacientesExcluidos() {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null
                    && pessoa.getTipoUsuario().equals("Paciente")
                    && pessoa.isHabilitado() == false) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public Pessoa buscaPessoaExcluidaPorId(int idPessoaExcluida) {
        for (Pessoa pessoa : listaPessoa) {

            if (pessoa != null && pessoa.getId() == idPessoaExcluida
                    && pessoa.isHabilitado() == false) {
                return pessoa;
            }
        }
        return null;

    }

    public boolean ReverterExclusaoPaciente(Pessoa pessoa, CalendarioSistema calendarioSistema) {
        if (pessoa != null
                && pessoa.getTipoUsuario().equals("Paciente")
                && pessoa.isHabilitado() == false) {
            pessoa.setHabilitado(true);
            pessoa.setDataModificacao(calendarioSistema.getDataHoraSistema());
            return true;
        }
        return false;
    }

    public boolean inserePessoaNoBancoDeDados(Pessoa pessoa) {

        boolean adicionado = true;

        String inserePessoa = "insert into pessoa (nome,enderecopessoa,cpf,telefonepessoa) \n"
                + "values (?,?,?,?)";

        String insereTipoUsuario = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,tipousuario,datacriacao) \n"
                + "values (?,?,?,?,?)";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInserePessoa = connection.prepareStatement(inserePessoa);
                    PreparedStatement pstmInsereTipoUsuario = connection.prepareStatement(insereTipoUsuario)) {

                pstmInserePessoa.setString(1, pessoa.getNomePessoa());
                pstmInserePessoa.setString(2, pessoa.getEnderecoPessoa());
                pstmInserePessoa.setString(3, pessoa.getCpf());
                pstmInserePessoa.setString(4, pessoa.getTelefonePessoa());

                pstmInserePessoa.execute();

                pstmInsereTipoUsuario.setString(1, pessoa.getCpf());
                pstmInsereTipoUsuario.setString(2, pessoa.getLoginPessoa());
                pstmInsereTipoUsuario.setString(3, pessoa.getSenhaPessoa());
                pstmInsereTipoUsuario.setString(4, pessoa.getTipoUsuario());

                LocalDateTime dc = pessoa.getDataCriacao();
                DateTimeFormatter fd = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
                fd.format(dc);
                String dataModificacao = dc.format(fd);
                pstmInsereTipoUsuario.setString(5, dataModificacao);

                pstmInsereTipoUsuario.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;
                System.out.println("\n Nao foi possivel inserir a pessoa no banco de dados!\n" + erro.getMessage());

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }
//+ "tp.datacriacao, tp.datamodificacao\n"

    public void BuscaPessoaNoBancoDeDados() {

        listaPessoa.clear();

        String buscaPessoa = "select p.idpessoa,p.nome, p.enderecopessoa,\n"
                + "p.cpf, p.telefonepessoa, \n"
                + "tp.tipousuario, tp.logintipousuario, tp.senhatipousuario, tp.tipousuario\n"
                + "from pessoa p inner join tipousuario tp\n"
                + "on p.cpf = tp.cpfpessoa;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaPessoa);
                ResultSet rs = pstm.executeQuery(buscaPessoa)) {

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setIdPessoa(rs.getInt("idpessoa"));
                pessoa.setNomePessoa(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEnderecoPessoa(rs.getString("enderecopessoa"));
                pessoa.setTelefonePessoa(rs.getString("telefonepessoa"));
                pessoa.setLoginPessoa(rs.getString("logintipousuario"));
                pessoa.setSenhaPessoa(rs.getString("senhatipousuario"));
                pessoa.setTipoUsuario(rs.getString("tipousuario"));
                pessoa.setHabilitado(true);

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
                listaPessoa.add(pessoa);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados das pessoas no banco de dados!\n" + erro.getMessage());
        }

    }

    public boolean AtualizaNomePessoaNoBancoDeDados(String novoNome, Pessoa pessoa) {

        boolean atualizado = true;

        String atualizaNomePessoa = "update pessoa set nome = ? where cpf = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(atualizaNomePessoa)) {

            pstm.setString(1, novoNome);
            pstm.setString(2, pessoa.getCpf());

            pstm.execute();

        } catch (SQLException erro) {

            atualizado = false;
            System.out.println("\n Nao foi possivel Atualizar o Nome da pessoa no banco de dados!\n" + erro.getMessage());
        }

        return atualizado != false;
    }

    public boolean AtualizaCpfPessoaNoBancoDeDados(String novoCpf, Pessoa pessoa) {

        boolean atualizado = true;

        String atualizaCpfPessoa = "update pessoa set cpf = ? where cpf = ?";

        if (!verificaSeCpfEstaSendoUsado(novoCpf) == true) {

            try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                    PreparedStatement pstm = connection.prepareStatement(atualizaCpfPessoa)) {

                pstm.setString(1, novoCpf);
                pstm.setString(2, pessoa.getCpf());

                pstm.execute();

            } catch (SQLException erro) {

                atualizado = false;
                System.out.println("\n Nao foi possivel Atualizar o Cpf da pessoa no banco de dados!\n" + erro.getMessage());
            }

        } else {
            atualizado = false;
        }

        return atualizado != false;

    }

}
