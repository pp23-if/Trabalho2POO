package Controller;

import Model.Admnistrador;
import Model.AdmnistradorDAO;
import Model.CalendarioSistema;
import Model.ConsultaDAO;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedicoDAO;
import Model.Franquia;
import Model.FranquiaDAO;
import Model.InfoConsultaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulos;
import java.util.Scanner;

public class GerenciaControladoras {

    Scanner scanner = new Scanner(System.in);

    MenuTitulos Tela = new MenuTitulos();

    /*Instanciando a Classe de Validacao de dados.*/
    ValidacaoEntradaDados vd = new ValidacaoEntradaDados();
    
    /*Instanciando o Calendario Do Sistema*/
    CalendarioSistema calendarioSistema = new CalendarioSistema();

    /*Instanciando os DAO.*/
    PessoaDAO pessoaDAO = new PessoaDAO(calendarioSistema);
    MedicoDAO medicoDAO = new MedicoDAO(pessoaDAO, calendarioSistema);
    FranquiaDAO franquiaDAO = new FranquiaDAO(pessoaDAO, calendarioSistema);
    UnidadeFranquiaDAO unidadeFranquiaDAO = new UnidadeFranquiaDAO(pessoaDAO, franquiaDAO, calendarioSistema);
    ConsultaDAO consultaDAO = new ConsultaDAO();
    AdmnistradorDAO admnistradorDAO = new AdmnistradorDAO(pessoaDAO, franquiaDAO, calendarioSistema);
    InfoConsultaDAO infoConsultaDAO = new InfoConsultaDAO();
    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(pessoaDAO, medicoDAO, unidadeFranquiaDAO, 
            calendarioSistema, consultaDAO);
    FinanceiroAdmDAO financeiroAdmDAO = new FinanceiroAdmDAO();
    FinanceiroMedicoDAO financeiroMedicoDAO = new FinanceiroMedicoDAO();

    public GerenciaControladoras() {

        int opcao;

        do {

            opcao = Tela.menuInicial();

            switch (opcao) {
                case 1: {
                    fazLogin();
                    break;
                }

                case 2: {
                    cadastrarPessoa();
                    break;
                }

                case 3: {
                    System.out.println("\nMostrando Todas as PESSOAS Cadastradas: " + "\n");
                    pessoaDAO.mostraTodasPessoas();
                    System.out.println("\nMostrando TodOS os MEDICOS Cadastrados: " + "\n");
                    medicoDAO.mostraTodosMedicosHabilitados();
                    System.out.println("\nMostrando Todas as FRANQUIAS Cadastradas: " + "\n");
                    franquiaDAO.mostraTodasFranquias();
                    System.out.println("\nMostrando Todas as UNIDADES DE FRANQUIAS Cadastradas: " + "\n");
                    unidadeFranquiaDAO.MostraTodasUnidadesDeFranquia();
                    System.out.println("\nMostrando Todos os ADMNISTRADORES Cadastradas: " + "\n");
                    admnistradorDAO.buscaTodosAdmnistradores();
                  
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarPessoa() {
        
        System.out.println("\nInforme o Nome da Pessoa: ");
        String nomePessoa = scanner.nextLine();
        nomePessoa = vd.validaString(nomePessoa);

        System.out.println("\nInforme o Cpf da Pessoa: ");
        String cpf = scanner.nextLine();
        cpf = vd.validaString(cpf);

        System.out.println("\nInforme o Endereco da Pessoa: ");
        String enderecoPessoa = scanner.nextLine();
        enderecoPessoa = vd.validaString(enderecoPessoa);

        System.out.println("\nInforme o login da Pessoa: ");
        String loginPessoa = scanner.nextLine();
        loginPessoa = vd.validaString(loginPessoa);

        System.out.println("\nInforme a Senha da Pessoa: ");
        String senhaPessoa = scanner.nextLine();
        senhaPessoa = vd.validaString(senhaPessoa);

        System.out.println("\nInforme o Telefone da Pessoa: ");
        String telefonePessoa = scanner.nextLine();
        telefonePessoa = vd.validaString(telefonePessoa);

        String tipoUsuario = "Paciente";

        boolean existePessoa;
        boolean adicionado;

        existePessoa = pessoaDAO.verificaSePessoaExiste(loginPessoa, cpf);

        if (existePessoa == true) {
            System.out.println("\nPessoa ja Cadastrada");

        } else {
            Pessoa pessoa = new Pessoa(nomePessoa, cpf, enderecoPessoa, telefonePessoa,
                    loginPessoa, senhaPessoa, tipoUsuario, calendarioSistema.getDataHoraSistema());

            adicionado = pessoaDAO.adicionaPessoa(pessoa);

            if (adicionado == true) {
                System.out.println("\nPessoa Cadastrada Com Sucesso!!!");
            } else {
                System.out.println("\nNao Foi Possivel Cadastrar a Pessoa.");
            }
        }
    }

    private void fazLogin() {
        System.out.println("\nLogin: ");
        String login = scanner.nextLine();
        login = vd.validaString(login);

        System.out.println("\nSenha: ");
        String senha = scanner.nextLine();
        senha = vd.validaString(senha);

        Pessoa pessoaLogada = pessoaDAO.buscaPessoaQuerendoLogar(login, senha);

        gerenciaControladoras(pessoaLogada);
    }

    private void gerenciaControladoras(Pessoa pessoaLogada) {

        if (pessoaLogada != null && pessoaLogada.isHabilitado() == true) {
            
            System.out.println("\nLogin efetuado Com Sucesso!");
            System.out.println("Logado Como: " + pessoaLogada.getTipoUsuario());

            if (pessoaLogada.getTipoUsuario().equals("Paciente")) {

                PacienteControladora pacienteControladora = 
                new PacienteControladora(pessoaLogada, pessoaDAO, vd, consultaDAO, procedimentoDAO, 
                        calendarioSistema, medicoDAO);

            } else if (pessoaLogada.getTipoUsuario().equals("Medico")) {

                Medico medico = medicoDAO.buscaMedicoAtravesdaPessoaVinculada(pessoaLogada);

                MedicoControladora medicoControladora = new MedicoControladora(medico,
                        medicoDAO, vd, consultaDAO, infoConsultaDAO, procedimentoDAO, pessoaDAO, 
                        calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO);

            } else if (pessoaLogada.getTipoUsuario().equals("DonodeFranquia")) {

                Franquia franquia = franquiaDAO.buscaFranquiaAtravesDaPessoaVinculada(pessoaLogada);

                FranquiaControladora franquiaControladora
                        = new FranquiaControladora(franquia, franquiaDAO, pessoaDAO, medicoDAO,
                                unidadeFranquiaDAO, vd, calendarioSistema, financeiroAdmDAO, 
                                financeiroMedicoDAO, admnistradorDAO);
                
            } else if (pessoaLogada.getTipoUsuario().equals("DonoDeUnidadeDeFranquia")) {
                
                UnidadeFranquia unidadeFranquia
                        = unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaPessoaVinculada(pessoaLogada);

                UnidadeFranquiaControladora unidadeFranquiaControladora
                        = new UnidadeFranquiaControladora(unidadeFranquia, unidadeFranquiaDAO, medicoDAO,
                                pessoaDAO, vd, calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO,
                        consultaDAO, procedimentoDAO);
                
            } else if (pessoaLogada.getTipoUsuario().equals("Admnistrador")) {
                
                Admnistrador admnistradorEncontrado
                        = admnistradorDAO.buscaAdmnistradorAtravesPessoaVinculada(pessoaLogada);

                AdmnistradorControladora admnistradorControladora
                        = new AdmnistradorControladora(pessoaDAO, admnistradorDAO, 
                                unidadeFranquiaDAO, consultaDAO, vd, 
                                admnistradorEncontrado, medicoDAO, 
                                procedimentoDAO, calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO);
            }

        } else {
            System.out.println("\nNao Foi Possivel Fazer Login: Usuario Nao Encontrado OU "
                    + "Nao Cadastrado.");
        }
    }

}
