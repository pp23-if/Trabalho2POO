package Controller;

import Model.Admnistrador;
import Model.AdmnistradorDAO;
import Model.CalendarioSistema;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedicoDAO;
import Model.Franquia;
import Model.FranquiaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulosFranquia;
import java.util.Scanner;

public class FranquiaControladora {

    Scanner scanner = new Scanner(System.in);
    MenuTitulosFranquia telaFranquia = new MenuTitulosFranquia();

    public FranquiaControladora(Franquia franquia, FranquiaDAO franquiaDAO, PessoaDAO pessoaDAO,
            MedicoDAO medicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, AdmnistradorDAO admnistradorDAO) {

        menuOpcoesFranquia(franquia, franquiaDAO, pessoaDAO, medicoDAO, unidadeFranquiaDAO, vd,
                calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO, admnistradorDAO);
    }

    private void menuOpcoesFranquia(Franquia franquia, FranquiaDAO franquiaDAO, PessoaDAO pessoaDAO,
            MedicoDAO medicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, AdmnistradorDAO admnistradorDAO) {

        int opcao;

        do {
            opcao = telaFranquia.menuFranquia();

            switch (opcao) {
                case 1: {
                    System.out.println("\n" + franquiaDAO.buscaFranquia(franquia));
                    break;
                }
                case 2: {
                    System.out.println("\n" + franquia.getPessoa());
                    break;
                }
                case 3: {
                    cadastraNovaFranquia(franquiaDAO, pessoaDAO, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    designaAdmnistradorFranquia(admnistradorDAO, franquia, pessoaDAO, vd, calendarioSistema);
                    break;
                }
                case 5: {
                    menuOpcoesAtualizarDadosFranquia(franquia, franquiaDAO, vd, calendarioSistema);
                    break;
                }
                case 6: {
                    menuOpcoesExclusao(pessoaDAO,vd, calendarioSistema, medicoDAO);
                    break;
                }
                case 7: {
                    System.out.println("\n");
                    franquiaDAO.mostraTodasFranquias();
                    break;
                }
                case 8: {
                    cadastraMedico(pessoaDAO, medicoDAO, vd, calendarioSistema);
                    break;
                }
                case 9: {
                    System.out.println("\n");
                    medicoDAO.mostraTodosMedicosHabilitados();
                    break;
                }
                case 10: {
                    cadastraUnidadeFranquia(pessoaDAO, unidadeFranquiaDAO, franquia, vd, calendarioSistema);
                    break;
                }
                case 11: {
                    System.out.println("\n");
                    unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(franquia);
                    break;
                }
                case 12: {
                    geraRelatoriosFranquia(financeiroAdmDAO, financeiroMedicoDAO, franquia, vd);
                    break;
                }
                case 13: {
                    System.out.println("\n");
                    pessoaDAO.filtraPacientes();
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void cadastraNovaFranquia(FranquiaDAO franquiaDAO, PessoaDAO pessoaDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        pessoaDAO.filtraPessoasCandidatasADonoDeFranquia();

        System.out.println("\nInforme o Id da pessoa que Sera a Dona da Franquia: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        idPessoa = vd.validarINT(idPessoa);

        Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoa == null) {
            System.out.println("\nPessoa Nao Encontrada");
        } else if (franquiaDAO.verificaDonosDeFranquia(pessoa) == true) {
            System.out.println("\nPessoa ja Cadastrada Como Dono de Franquia\n");
        } else {
            System.out.println("\nInforme o Login de Dono de Franquia: ");
            String loginDonoFranquia = scanner.nextLine();
            loginDonoFranquia = vd.validaString(loginDonoFranquia);

            if (franquiaDAO.verificaSeLoginDonoFranquiaEstaSendoUsado(loginDonoFranquia) == true) {
                System.out.println("\nLogin Dono De Franquia Ja Esta Sendo Usado!");
            } else {

                System.out.println("\nInforme a Senha de Dono de Franquia: ");
                String senhaDonoFranquia = scanner.nextLine();
                senhaDonoFranquia = vd.validaString(senhaDonoFranquia);

                Pessoa pessoaDonoFranquia = new Pessoa(pessoa.getNomePessoa(), pessoa.getCpf(), pessoa.getEnderecoPessoa(),
                        pessoa.getTelefonePessoa(), loginDonoFranquia, senhaDonoFranquia, "DonodeFranquia",
                        calendarioSistema.getDataHoraSistema());

                if (pessoaDAO.adicionaPessoa(pessoaDonoFranquia) == true) {
                    System.out.println("\nInforme o Nome da Franquia: ");
                    String nomeFranquia = scanner.nextLine();
                    nomeFranquia = vd.validaString(nomeFranquia);

                    System.out.println("\nInforme o Cnpj da Franquia: ");
                    String cnpjFranquia = scanner.nextLine();
                    cnpjFranquia = vd.validaString(cnpjFranquia);

                    System.out.println("\nInforme a Cidade da Franquia: ");
                    String cidadeFranquia = scanner.nextLine();
                    cidadeFranquia = vd.validaString(cidadeFranquia);

                    System.out.println("\nInforme o Endereco da Franquia: ");
                    String enderecoFranquia = scanner.nextLine();
                    enderecoFranquia = vd.validaString(enderecoFranquia);

                    if (franquiaDAO.verificaSeFranquiaExiste(nomeFranquia, cnpjFranquia) == true) {
                        System.out.println("\nA Franquia Ja Existe");
                    } else {
                        Franquia franquia = new Franquia(nomeFranquia.toUpperCase(), cnpjFranquia,
                                cidadeFranquia, enderecoFranquia, pessoaDonoFranquia, calendarioSistema.getDataHoraSistema());

                        boolean franquiaAdicionada = franquiaDAO.adicionaFranquia(franquia);

                        if (franquiaAdicionada == true) {
                            System.out.println("\nFranquia Cadastrada Com Sucesso!");
                        } else {
                            System.out.println("\nNao Foi Possivel Cadastrar a Franquia.");
                        }
                    }

                } else {
                    System.out.println("\nErro Ao Cadastrar Franquia.");
                }
            }

        }

    }

    private void menuOpcoesAtualizarDadosFranquia(Franquia franquia, FranquiaDAO franquiaDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {
        int opcao;

        do {
            opcao = telaFranquia.menuAtualizacaoDeDadosFranquia();

            switch (opcao) {
                case 1: {
                    System.out.println("\nInforme o Novo Nome da Franquia: ");
                    String novoNomeFranquia = scanner.nextLine();
                    novoNomeFranquia = vd.validaString(novoNomeFranquia);

                    if (franquiaDAO.atualizarNomeFranquia(franquia, novoNomeFranquia, calendarioSistema) == true) {
                        System.out.println("\nNome Da Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Nome Da Franquia.");
                    }

                    break;
                }

                case 2: {
                    System.out.println("\nInforme a Nova Cidade da Franquia: ");
                    String novaCidadeFranquia = scanner.nextLine();
                    novaCidadeFranquia = vd.validaString(novaCidadeFranquia);

                    if (franquiaDAO.atualizarCidadeFranquia(franquia, novaCidadeFranquia, calendarioSistema) == true) {
                        System.out.println("\nCidade Da Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar A Cidade Da Franquia.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nInforme o Novo Endereco Cidade da Franquia: ");
                    String novoEnderecoFranquia = scanner.nextLine();
                    novoEnderecoFranquia = vd.validaString(novoEnderecoFranquia);

                    if (franquiaDAO.atualizarEnderecoFranquia(franquia, novoEnderecoFranquia, calendarioSistema) == true) {
                        System.out.println("\nEndereco Da Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Endereco Da Franquia.");
                    }
                    break;
                }

                case 4: {
                    System.out.println("\nInforme o Novo Login De Dono De Franquia: ");
                    String novoLoginDonoFranquia = scanner.nextLine();
                    novoLoginDonoFranquia = vd.validaString(novoLoginDonoFranquia);

                    if (franquiaDAO.atualizaLoginDonoDeFranquia(franquia, novoLoginDonoFranquia, calendarioSistema) == true) {
                        System.out.println("\nLogin De Dono De Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar o Login De Dono De Franquia.");
                    }

                    break;
                }

                case 5: {
                    System.out.println("\nInforme a Nova Senha De Dono De Franquia: ");
                    String novaSenhaDonoFranquia = scanner.nextLine();
                    novaSenhaDonoFranquia = vd.validaString(novaSenhaDonoFranquia);

                    if (franquiaDAO.atualizaSenhaDonoDeFranquia(franquia, novaSenhaDonoFranquia, calendarioSistema) == true) {
                        System.out.println("\nSenha De Dono De Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar a Senha De Dono De Franquia.");
                    }
                    break;
                }

                case 6: {
                    System.out.println("\nInforme o Novo Telefone De Dono De Franquia: ");
                    String novoTelefoneDonoFranquia = scanner.nextLine();
                    novoTelefoneDonoFranquia = vd.validaString(novoTelefoneDonoFranquia);

                    if (franquiaDAO.atualizaTelefoneDonoDeFranquia(franquia, novoTelefoneDonoFranquia, calendarioSistema) == true) {
                        System.out.println("\nTelefone De Dono De Franquia Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar o Telefone De Dono De Franquia.");
                    }
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void cadastraMedico(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        pessoaDAO.filtraPessoasCandidatasAMedico();

        System.out.println("\nInforme o Id da pessoa que Sera Medico: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        idPessoa = vd.validarINT(idPessoa);

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPessoa Nao Encontrada");
        } else {

            if (medicoDAO.verificaSeMedicoExiste(pessoaEncontrada) == true) {
                System.out.println("\nPessoa ja Cadastrada Como Medico.");
            } else {

                System.out.println("\nInforme O Crm do Medico: ");
                String crm = scanner.nextLine();
                crm = vd.validaString(crm);

                if (medicoDAO.verificaCrm(crm) == true) {
                    System.out.println("\nO Crm Informado Ja Esta Sendo Usado.");
                } else {
                    System.out.println("\nInforme A Especialidade do Medico: ");
                    String medicoEspecialidade = scanner.nextLine();
                    medicoEspecialidade = vd.validaString(medicoEspecialidade);

                    System.out.println("\nInforme O Login De Medico: ");
                    String LoginMedico = scanner.nextLine();
                    LoginMedico = vd.validaString(LoginMedico);

                    if (medicoDAO.verificaSeloginEstaSendoUsado(LoginMedico) == true) {
                        System.out.println("\nLogin De Medico Ja Esta Sendo Usado!");
                    } else {
                        System.out.println("\nInforme A Senha De Medico: ");
                        String senhaMedico = scanner.nextLine();
                        senhaMedico = vd.validaString(senhaMedico);

                        Pessoa pessoaMedico = new Pessoa(pessoaEncontrada.getNomePessoa(), pessoaEncontrada.getCpf(),
                                pessoaEncontrada.getEnderecoPessoa(), pessoaEncontrada.getTelefonePessoa(),
                                LoginMedico, senhaMedico, "Medico", calendarioSistema.getDataHoraSistema());

                        if (pessoaDAO.adicionaPessoa(pessoaMedico) == true) {

                            Medico medico = new Medico(crm, pessoaMedico, medicoEspecialidade,
                                    calendarioSistema.getDataHoraSistema());

                            if (medicoDAO.adicionaMedico(medico) == true) {
                                System.out.println("\nMedico Cadastrado Com Sucesso!");
                            } else {
                                System.out.println("\nNao Foi Possivel Cadastrar o Medico.");
                            }
                        } else {
                            System.out.println("\n Erro ao Cadastrar medico.");
                        }
                    }

                }

            }

        }
    }

    private void cadastraUnidadeFranquia(PessoaDAO pessoaDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            Franquia franquia, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        pessoaDAO.filtraPessoaCandidatasADonoUnidadeFranquia();

        System.out.println("\nInforme o Id da Pessoa Que Sera Dono De Unidade De Franquia: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        idPessoa = vd.validarINT(idPessoa);

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPessoa Nao Encontrada");
        } else {
            if (unidadeFranquiaDAO.verificaSeDonoUnidadeFranquiaExiste(pessoaEncontrada) == true) {
                System.out.println("\nPessoa ja Cadastrada Como Dono De Unidade de Franquia.");
            } else {
                System.out.println("\nInforme a Cidade da Unidade De Franquia: ");
                String cidadeUnidadeFranquia = scanner.nextLine();
                cidadeUnidadeFranquia = vd.validaString(cidadeUnidadeFranquia);

                System.out.println("\nInforme o Endereco da Unidade De Franquia: ");
                String enderecoUnidadeFranquia = scanner.nextLine();
                enderecoUnidadeFranquia = vd.validaString(enderecoUnidadeFranquia);

                System.out.println("\nInforme O Login Dono De Unidade De Franquia: ");
                String LoginDonoUnidadeFranquia = scanner.nextLine();
                LoginDonoUnidadeFranquia = vd.validaString(LoginDonoUnidadeFranquia);

                if (unidadeFranquiaDAO.verificaSeLoginDonoDeUnidadeFranquiaEstaEmUso(LoginDonoUnidadeFranquia) == true) {
                    System.out.println("\nLogin De Dono Unidade Franquia Ja Esta Sendo Usado!");
                } else {
                    System.out.println("\nInforme A Senha De Dono De Unidade De Franquia: ");
                    String senhaDonoUnidadeFranquia = scanner.nextLine();
                    senhaDonoUnidadeFranquia = vd.validaString(senhaDonoUnidadeFranquia);

                    Pessoa donoUnidadeFranquia = new Pessoa(pessoaEncontrada.getNomePessoa(),
                            pessoaEncontrada.getCpf(), pessoaEncontrada.getEnderecoPessoa(),
                            pessoaEncontrada.getTelefonePessoa(),
                            LoginDonoUnidadeFranquia, senhaDonoUnidadeFranquia,
                            "DonoDeUnidadeDeFranquia", calendarioSistema.getDataHoraSistema());

                    if (pessoaDAO.adicionaPessoa(donoUnidadeFranquia) == true) {
                        UnidadeFranquia unidadeFranquia = new UnidadeFranquia(franquia, cidadeUnidadeFranquia,
                                enderecoUnidadeFranquia, donoUnidadeFranquia, calendarioSistema.getDataHoraSistema());

                        if (unidadeFranquiaDAO.adicionaUnidadeFranquia(unidadeFranquia) == true) {
                            System.out.println("\nUnidade De Franquia Cadastrada Com Sucesso!");
                        } else {
                            System.out.println("\nNao Foi Possivel Cadastrar A Unidade De Franquia.");
                        }
                    } else {
                        System.out.println("\n Erro ao Cadastrar Dono De Unidade Franquia.");
                    }

                }

            }

        }

    }

    private void geraRelatoriosFranquia(FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            Franquia franquia, ValidacaoEntradaDados vd) {

        int opcao;

        do {
            opcao = telaFranquia.menuRelatoriosFinanceirosFranquia();

            switch (opcao) {
                case 1: {
                    relatorioGeralFranquia(financeiroAdmDAO, financeiroMedicoDAO, franquia);
                    break;
                }

                case 2: {
                    relatorioMensalFranquia(financeiroAdmDAO, financeiroMedicoDAO, franquia, vd);
                    break;

                }

            }

        } while (opcao != 0);
    }

    private void relatorioGeralFranquia(FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            Franquia franquia) {

        System.out.println("\nMovimentacoes Financeiras  - (Entrada/saida): ");
        System.out.println("\n");
        financeiroAdmDAO.geraRelatorioEntradaSaidaFranquia(franquia);

        System.out.println("\nMovimentacoes Financeiras - (Pagamentos Dos Medicos): ");
        System.out.println("\n");
        financeiroMedicoDAO.geraRelatorioPagamentoMedicosPorFranquia(franquia);

    }

    private void relatorioMensalFranquia(FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            Franquia franquia, ValidacaoEntradaDados vd) {

        System.out.println("\nInforme O Numero Do Mes Que Deseja Ver Relatorio: ");
        int numeroMes = Integer.parseInt(scanner.nextLine());
        numeroMes = vd.validarINT(numeroMes);

        System.out.println("\nMovimentacoes Financeiras  - (Entrada/saida): ");
        System.out.println("\n");
        financeiroAdmDAO.geraRelatorioEntradaSaidaFranquiaMes(franquia, numeroMes);

        System.out.println("\nMovimentacoes Financeiras - (Pagamentos Dos Medicos): ");
        System.out.println("\n");
        financeiroMedicoDAO.geraRelatorioPagamentoMedicosPorFranquiaMes(franquia, numeroMes);

    }

    private void designaAdmnistradorFranquia(AdmnistradorDAO admnistradorDAO, Franquia franquia,
            PessoaDAO pessoaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {
        System.out.println("\n");

        if (admnistradorDAO.verificaSeFranquiaPossuiAdmnistrador(franquia) == true) {
            System.out.println("\nEssa Franquia Ja Possui Um Admnistrador.");
        } else {
            System.out.println("\n");
            pessoaDAO.filtraPacientes();

            System.out.println("\nInforme o ID - Pessoa Que Deseja Como Admnistrador Da Franquia: ");
            int idPessoa = Integer.parseInt(scanner.nextLine());
            idPessoa = vd.validarINT(idPessoa);

            Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

            if (pessoaEncontrada == null) {
                System.out.println("\nPessoa Nao Encontrada.");
            } else {
                if (admnistradorDAO.verificaSePessoaEhAdmnistrador(pessoaEncontrada) == true) {
                    System.out.println("\nA Pessoa Informada Ja e Admnistrador.");
                } else {
                    System.out.println("\nInforme o Login De Admnistrador: ");
                    String loginAdmnistrador = scanner.nextLine();
                    loginAdmnistrador = vd.validaString(loginAdmnistrador);

                    if (admnistradorDAO.verificaSeLoginAdmnistradorEstaEmUso(loginAdmnistrador) == true) {
                        System.out.println("\nLogin Informado Ja se Encontra Em Uso.");
                    } else {
                        System.out.println("\nInforme a Senha De Admnistrador: ");
                        String senhaAdmnistrador = scanner.nextLine();
                        senhaAdmnistrador = vd.validaString(senhaAdmnistrador);

                        Pessoa pessoaAdm = new Pessoa(pessoaEncontrada.getNomePessoa(), pessoaEncontrada.getCpf(),
                                pessoaEncontrada.getCpf(), pessoaEncontrada.getTelefonePessoa(),
                                loginAdmnistrador, senhaAdmnistrador, "Admnistrador", calendarioSistema.getDataHoraSistema());

                        if (pessoaDAO.adicionaPessoa(pessoaAdm) == true) {

                            Admnistrador admnistrador = new Admnistrador(pessoaAdm, franquia,
                                    calendarioSistema.getDataHoraSistema());

                            if (admnistradorDAO.adicionaAdmnistrador(admnistrador) == true) {

                                System.out.println("\nAdmnistrador Designado Com Sucesso!");

                            } else {

                                System.out.println("\nNao Foi Possivel Designar O Admnistrador.");
                            }
                        } else {
                            System.out.println("\nErro Ao Designar Admnistrador.");
                        }

                    }

                }
            }

        }
    }

    private void menuOpcoesExclusao(PessoaDAO pessoaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema,
            MedicoDAO medicoDAO) {
        int opcao;

        do {
            opcao = telaFranquia.menuExclusoesDonoFranquia();

            switch (opcao) {
                case 1: {
                    excluirPaciente(pessoaDAO, vd, calendarioSistema);
                    break;
                }

                case 2: {
                    reverterExclusaoPaciente(pessoaDAO, vd, calendarioSistema);
                    break;
                }
                
                case 3: {
                    excluirMedico(medicoDAO, vd, calendarioSistema);
                    break;
                }
                
                case 4: {
                    reverterExclusaoMedico(medicoDAO, vd, calendarioSistema);
                    break;
                }

            }

        } while (opcao != 0);
    }

    private void excluirPaciente(PessoaDAO pessoaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {
        
        System.out.println("\n");
        pessoaDAO.filtraPacientes();

        System.out.println("\nInforme o ID - Pessoa Que Deseja Excluir: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        idPessoa = vd.validarINT(idPessoa);

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            
            System.out.println("\nPessoa Nao Encontrada.");
            
        } else {
            if (pessoaDAO.excluirPaciente(pessoaEncontrada, calendarioSistema) == true) {
                
                System.out.println("\nPaciente Excluido Com Sucesso!");
                
            } else {
                
                System.out.println("\nNao Foi Possivel Excluir O Paciente.");
            }
        }

    }
    
    
     private void excluirMedico(MedicoDAO medicoDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {
         
        System.out.println("\n");
        medicoDAO.mostraTodosMedicosHabilitados();

        System.out.println("\nInforme o ID - Medico Que Deseja Excluir: ");
        int idMedico = Integer.parseInt(scanner.nextLine());
        idMedico = vd.validarINT(idMedico);

        Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

        if (medicoEncontrado == null) {
            
            System.out.println("\nMedico Nao Encontrado.");
            
        } else {
            if (medicoDAO.excluirMedico(medicoEncontrado, calendarioSistema) == true) {
                
                System.out.println("\nMedico Excluido Com Sucesso!");
                
            } else {
                
                System.out.println("\nNao Foi Possivel Excluir O Medico.");
            }
        }

    }
     
    private void reverterExclusaoPaciente(PessoaDAO pessoaDAO, ValidacaoEntradaDados vd, 
            CalendarioSistema calendarioSistema)
    {
        
         System.out.println("\n");
         pessoaDAO.filtraPacientesExcluidos();
         
        System.out.println("\nInforme o ID - Pessoa Que Deseja Reverter Exclusao: ");
        int idPessoaExcluida = Integer.parseInt(scanner.nextLine());
        idPessoaExcluida = vd.validarINT(idPessoaExcluida);
        
        Pessoa pessoaExcluida = pessoaDAO.buscaPessoaExcluidaPorId(idPessoaExcluida);
         
        if(pessoaExcluida == null)
        {
          System.out.println("\nPaciente Excluido Nao Encontrado.");  
        }
        else
        {
            if(pessoaDAO.ReverterExclusaoPaciente(pessoaExcluida, calendarioSistema) == true)
            {
                System.out.println("\nReversao De Exclusao Do Paciente Realizada Com Sucesso!");
            }
            else
            {
               System.out.println("\nNao Foi Possivel Reverter A Exclusao Do Paciente.");  
            }
        }
    }
    
    private void reverterExclusaoMedico(MedicoDAO medicoDAO, ValidacaoEntradaDados vd, 
            CalendarioSistema calendarioSistema)
    {
        
         System.out.println("\n");
         medicoDAO.filtraMedicosExcluidos();
         
        System.out.println("\nInforme o ID - Medico Que Deseja Reverter Exclusao: ");
        int idMedicoExcluido = Integer.parseInt(scanner.nextLine());
        idMedicoExcluido = vd.validarINT(idMedicoExcluido);
        
        Medico medicoExcluido = medicoDAO.buscaMedicoExcluidoPorId(idMedicoExcluido);
         
        if(medicoExcluido == null)
        {
          System.out.println("\nMedico Excluido Nao Encontrado.");  
        }
        else
        {
            if(medicoDAO.reverterExclusaoMedico(medicoExcluido, calendarioSistema) == true)
            {
                System.out.println("\nReversao De Exclusao Do Medico Realizado Com Sucesso!");
            }
            else
            {
               System.out.println("\nNao Foi Possivel Reverter A Exclusao Do Medico.");  
            }
        }
    }


}
