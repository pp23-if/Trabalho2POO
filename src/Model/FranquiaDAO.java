package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class FranquiaDAO {

    private List <Franquia> listaFranquia = new LinkedList();

    public FranquiaDAO(PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema) {
        
        /*Pessoa donoDeFranquia = pessoaDAO.buscaPessoaCadastrada("Edu28", "24");

        if (donoDeFranquia != null) {
            Franquia franquia = new Franquia("Unimed".toUpperCase(), 
                    "123456789-30", "Sao Paulo",
                    "Avenida Paulista, 2000 - Centro", donoDeFranquia, 
                    calendarioSistema.getDataHoraSistema());

            adicionaFranquia(franquia);
        }*/

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

   
    public boolean insereFranquiaNoBancoDeDados(Pessoa pessoa, Franquia franquia) {

        boolean adicionado = true;
        
        
       String inserePessoaDonoDeFranquia = "insert into tipousuario (cpfpessoa,logintipousuario,senhatipousuario,"
                + "tipousuario, telefonepessoa, datacriacao) \n"
                + "values (?,?,?,?,?,?)";

        String insereFranquia = "insert into franquia (nomefranquia, cnpj, cidade, endereco, cpfdono, datacriacao) \n"
                + "values (?,?,?,?,?,?)";

       
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            connection.setAutoCommit(false);

            try (PreparedStatement pstmInsereDonoDeUnidadeDeFranquia = connection.prepareStatement(inserePessoaDonoDeFranquia);
                 PreparedStatement pstmInsereFranquia = connection.prepareStatement(insereFranquia)) {

                pstmInsereDonoDeUnidadeDeFranquia.setString(1, pessoa.getCpf());
                pstmInsereDonoDeUnidadeDeFranquia.setString(2, pessoa.getLoginPessoa());
                pstmInsereDonoDeUnidadeDeFranquia.setString(3, pessoa.getSenhaPessoa());
                pstmInsereDonoDeUnidadeDeFranquia.setString(4, pessoa.getTipoUsuario());
                pstmInsereDonoDeUnidadeDeFranquia.setString(5, pessoa.getTelefonePessoa());

                LocalDateTime dc = pessoa.getDataCriacao();
                DateTimeFormatter fd = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
                fd.format(dc);
                String dataCriacao = dc.format(fd);
                
                pstmInsereDonoDeUnidadeDeFranquia.setString(6,dataCriacao);

                pstmInsereDonoDeUnidadeDeFranquia.execute();
                
                
                pstmInsereFranquia.setString(1, franquia.getNomeFranquia());
                pstmInsereFranquia.setString(2, franquia.getCnpj());
                pstmInsereFranquia.setString(3, franquia.getCidade());
                pstmInsereFranquia.setString(4, franquia.getEnderecoFranquia());
                pstmInsereFranquia.setString(5, pessoa.getCpf());
               
                LocalDateTime dcf = franquia.getDataCriacao();
                DateTimeFormatter fdm = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
                fd.format(dcf);
                String dataCriacaoMedico = dcf.format(fdm);
                
                pstmInsereFranquia.setString(6, dataCriacaoMedico);
                  
                pstmInsereFranquia.execute();

                connection.commit();

            } catch (SQLException erro) {

                connection.rollback();
                adicionado = false;
                System.out.println("\n Nao foi possivel inserir o Medico no banco de dados!\n" + erro.getMessage());

            }

        } catch (SQLException erro) {

        }

        return adicionado != false;

    }
   
   
   public void BuscaFranquiaNoBancoDeDados(PessoaDAO pessoaDAO) {

        listaFranquia.clear();
        
        String buscaFranquia = "select idfranquia, nomefranquia, cnpj, cidade, endereco, cpfdono from franquia;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstm = connection.prepareStatement(buscaFranquia);
                ResultSet rs = pstm.executeQuery(buscaFranquia)) {

            while (rs.next()) {

                Franquia franquia = new Franquia();

                String cpfDonoFranquia = rs.getString("cpfdono");
                Pessoa pessoaDonoFranquia = pessoaDAO.buscaPessoaDonoDeFranquiaPorCpf(cpfDonoFranquia);
                franquia.setPessoa(pessoaDonoFranquia);
                
                franquia.setIdFranquia(rs.getInt("idfranquia"));
                franquia.setNomeFranquia(rs.getString("nomefranquia"));
                franquia.setCnpj(rs.getString("cnpj"));
                franquia.setCidade(rs.getString("cidade"));
                franquia.setEnderecoFranquia(rs.getString("endereco"));
               
                

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
                listaFranquia.add(franquia);

            }

        } catch (SQLException erro) {
            System.out.println("\n Nao foi possivel Buscar os dados dos Medicos no banco de dados!\n" + erro.getMessage());
        }

    }
   
}
