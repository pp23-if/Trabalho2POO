package Controller;

import Model.Admnistrador;
import Model.AdmnistradorDAO;
import Model.CalendarioSistema;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.FinanceiroAdm;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedico;
import Model.FinanceiroMedicoDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.Procedimento;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulosAdmistrador;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdmnistradorControladora {

    MenuTitulosAdmistrador telaAdmistrador = new MenuTitulosAdmistrador();

    Scanner scanner = new Scanner(System.in);

    public AdmnistradorControladora(PessoaDAO pessoaDAO, AdmnistradorDAO admnistradorDAO,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ConsultaDAO consultaDAO, ValidacaoEntradaDados vd,
            Admnistrador admnistrador, MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO,
            CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO) {

        menuOpcoesAdmnistrador(pessoaDAO, admnistradorDAO,
                unidadeFranquiaDAO, consultaDAO, vd, admnistrador, medicoDAO, procedimentoDAO, calendarioSistema,
                financeiroAdmDAO, financeiroMedicoDAO);

    }

    private void menuOpcoesAdmnistrador(PessoaDAO pessoaDAO,
            AdmnistradorDAO admnistradorDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ConsultaDAO consultaDAO, ValidacaoEntradaDados vd, Admnistrador admnistrador,
            MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO) {

        int opcao;

        do {
            opcao = telaAdmistrador.menuAdmnistrador();

            switch (opcao) {
                case 1: {
                    System.out.println("\n" + admnistradorDAO.
                            buscaAdmnistradorAtravesPessoaVinculada(admnistrador.getPessoa()));

                    break;
                }
                case 2: {
                    menuOpcoesConsulta(consultaDAO, admnistrador,
                            unidadeFranquiaDAO, vd, pessoaDAO, medicoDAO, calendarioSistema);
                    break;
                }
                case 3: {
                    menuOpcoesProcedimento(consultaDAO, admnistrador, unidadeFranquiaDAO,
                            vd, pessoaDAO, medicoDAO, procedimentoDAO, calendarioSistema);
                    break;
                }
                case 4: {
                    menuOpcoesFinanceiro(financeiroAdmDAO, calendarioSistema,
                            consultaDAO, procedimentoDAO, admnistrador, unidadeFranquiaDAO, vd, financeiroMedicoDAO, medicoDAO);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void menuOpcoesConsulta(ConsultaDAO consultaDAO,
            Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            PessoaDAO pessoaDAO, MedicoDAO medicoDAO, CalendarioSistema calendarioSistema) {

        int opcao;

        do {
            opcao = telaAdmistrador.menuConsultas();

            switch (opcao) {
                case 1: {
                    marcarConsulta(admnistrador, unidadeFranquiaDAO, vd,
                            pessoaDAO, medicoDAO, consultaDAO, calendarioSistema);

                    break;
                }
                case 2: {
                    System.out.println("\n");
                    consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 3: {
                    cancelarConsulta(consultaDAO, admnistrador, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    remarcarConsulta(admnistrador, unidadeFranquiaDAO, vd,
                            pessoaDAO, medicoDAO, consultaDAO, calendarioSistema);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void marcarConsulta(Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            PessoaDAO pessoaDAO, MedicoDAO medicoDAO, ConsultaDAO consultaDAO, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());

        System.out.println("\nInforme o ID da Unidade da Franquia que deseja realizar a consulta: ");
        int idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
        idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);

        UnidadeFranquia unidadeEncontrada = unidadeFranquiaDAO.
                buscaUnidadeFranquiaPorId(idUnidadeFranquia);

        if (unidadeEncontrada == null) {
            System.out.println("\nUnidade de franquia nao encontrada.");
        } else {

            System.out.println("\n");
            pessoaDAO.filtraPacientes();

            System.out.println("\nInforme o ID Da pessoa que deseja marcar para consulta: ");
            int idPessoaConsulta = Integer.parseInt(scanner.nextLine());
            idPessoaConsulta = vd.validarINT(idPessoaConsulta);

            System.out.println("\n");
            Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoaConsulta);

            if (pessoaEncontrada == null) {
                System.out.println("\nPessoa nao encontrada");
            } else {
                
                System.out.println("\n");
                medicoDAO.mostraTodosMedicosHabilitados();

                System.out.println("\nInforme o ID Do Medico que deseja se consultar: ");
                int idMedico = Integer.parseInt(scanner.nextLine());
                idMedico = vd.validarINT(idMedico);

                Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

                if (medicoEncontrado == null) {
                    System.out.println("\nmedico nao encontrado");
                } else {
                    if (medicoDAO.vericaSeMedicoEPacienteSaoIguais(pessoaEncontrada,
                            medicoEncontrado) == true) {

                        System.out.println("\nMedico e Paciente sao as Mesmas Pessoas..");
                    } else {
                        double valorConsulta = medicoDAO.verificaValorConsulta(medicoEncontrado);

                        DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        System.out.println("\nInforme a Data Da Consulta No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                        String dia = scanner.nextLine();
                        LocalDate diaConsulta = LocalDate.parse(dia, fdia);

                        System.out.println("\nInforme a Hora Da Consulta No Seguinte Formato, Hora:Minutos (00:00)..: ");
                        String Hora = scanner.nextLine();
                        LocalTime horaConsulta = LocalTime.parse(Hora);

                        if (consultaDAO.verificaDataConsulta(calendarioSistema, diaConsulta) == true) {
                            System.out.println("\nData Informada Invalida.");
                        } else {
                            if (consultaDAO.verificaDisponibilidadeDiaEHoraConsultaMedico(diaConsulta,
                                    horaConsulta, medicoEncontrado) == true) {

                                System.out.println("\nDia e Hora Informados Indisponiveis.");
                            } else {
                                Consulta novaConsulta = new Consulta(diaConsulta, horaConsulta,
                                        medicoEncontrado, pessoaEncontrada, unidadeEncontrada,
                                        valorConsulta, "Agendada", calendarioSistema.getDataHoraSistema());

                                if (consultaDAO.adicionaConsulta(novaConsulta) == true) {
                                    System.out.println("\nConsulta marcada com sucesso.");
                                } else {
                                    System.out.println("\nNao foi possivel marcar Consulta");
                                }

                            }
                        }

                    }

                }
            }

        }

    }

    private void cancelarConsulta(ConsultaDAO consultaDAO,
            Admnistrador admnistrador, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());

        System.out.println("\nInforme o ID da Consulta que deseja cancelar: ");
        int idConsulta = Integer.parseInt(scanner.nextLine());
        idConsulta = vd.validarINT(idConsulta);

        Consulta consultaEncontra = consultaDAO.buscaConsultaPorId(idConsulta);

        if (consultaEncontra == null) {
            System.out.println("\nConsulta nao Encontrada");
        } else {
            if (consultaDAO.receConsultaECancela(consultaEncontra, calendarioSistema) == true) {
                System.out.println("\nConsulta cancelada com sucesso.");

            } else {
                System.out.println("\nNao foi Possivel cancelar Consulta..");
            }

        }
    }

    private void remarcarConsulta(Admnistrador admnistrador, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, PessoaDAO pessoaDAO, MedicoDAO medicoDAO, ConsultaDAO consultaDAO,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());

        System.out.println("\nInforme o ID da consulta que desaja remarcar: ");
        int idConsulta = Integer.parseInt(scanner.nextLine());
        idConsulta = vd.validarINT(idConsulta);

        Consulta consultaEncontrada = consultaDAO.buscaConsultaPorId(idConsulta);

        if (consultaEncontrada == null) {
            System.out.println("\nConsulta Nao Encontrada");
        } else {
            DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("\nInforme a Nova Data Da Consulta No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
            String dia = scanner.nextLine();
            LocalDate diaConsulta = LocalDate.parse(dia, fdia);

            System.out.println("\nInforme a Nova Hora Da Consulta No Seguinte Formato, Hora:Minutos (00:00)..: ");
            String Hora = scanner.nextLine();
            LocalTime horaConsulta = LocalTime.parse(Hora);

            if (consultaDAO.verificaDataConsulta(calendarioSistema, diaConsulta) == true) {

                System.out.println("\nData Informada Invalida.");

            } else {

                if (consultaDAO.verificaDisponibilidadeDiaEHoraConsultaMedico(diaConsulta, horaConsulta,
                        consultaEncontrada.getMedico()) == true) {
                    System.out.println("\nDia e Hora Informados Indisponiveis.");
                } else {

                    if (consultaDAO.recebeConsultaERemarca(diaConsulta, horaConsulta,
                            consultaEncontrada, calendarioSistema) == true) {

                        System.out.println("\nConsulta Remarcada Com Sucesso.");

                    } else {

                        System.out.println("\nNao Foi Possivel Remarcar a Consulta, Conulta Realizada Ou Cancelada.");
                    }
                }

            }

        }
    }

    private void menuOpcoesProcedimento(ConsultaDAO consultaDAO, Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd, PessoaDAO pessoaDAO,
            MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO, CalendarioSistema calendarioSistema) {

        int opcao;

        do {
            opcao = telaAdmistrador.menuProcedimentos();

            switch (opcao) {
                case 1: {

                    marcarProcedimento(pessoaDAO, medicoDAO, admnistrador, unidadeFranquiaDAO,
                            procedimentoDAO, vd, calendarioSistema);
                    break;
                }
                case 2: {
                    System.out.println("\n");
                    procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 3: {
                    cancelarProcedimento(procedimentoDAO, admnistrador, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    remarcarProcedimento(procedimentoDAO, admnistrador, vd, calendarioSistema);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void marcarProcedimento(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ProcedimentoDAO procedimentoDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        pessoaDAO.filtraPacientes();

        System.out.println("\nInforme o ID - paciente que Ira Passar pelo Procedimento: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        idPessoa = vd.validarINT(idPessoa);

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPaciente Nao Encontrado.");
        } else {
            System.out.println("\n");
            medicoDAO.mostraTodosMedicosHabilitados();

            System.out.println("\nInforme o ID - Medico que Ira Fazer O Procedimento: ");
            int idMedico = Integer.parseInt(scanner.nextLine());
            idMedico = vd.validarINT(idMedico);

            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nMedico Nao Encontrado.");
            } else {
                System.out.println("\n");
                unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());

                System.out.println("\nInforme o ID - UnidadeFranquia Onde Ocorrera O Procedimento: ");
                int idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
                idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);

                UnidadeFranquia unidadeFranquiaEncontrada
                        = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

                if (unidadeFranquiaEncontrada == null) {
                    System.out.println("\nUnidade De Franquia Nao Encontrada.");
                } else {

                    DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    System.out.println("\nQual procedimento Sera Feito: ");
                    String nomeProcedimento = scanner.nextLine();
                    nomeProcedimento = vd.validaString(nomeProcedimento);

                    System.out.println("\nInforme a Data Do Procedimento No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                    String dia = scanner.nextLine();
                    LocalDate diaProcediemnto = LocalDate.parse(dia, fdia);

                    System.out.println("\nInforme a Hora Da Consulta No Seguinte Formato, Hora:Minutos (00:00)..: ");
                    String Hora = scanner.nextLine();
                    LocalTime horaProcedimento = LocalTime.parse(Hora);

                    if (procedimentoDAO.verificaDataProcedimento(calendarioSistema, diaProcediemnto) == true) {
                        System.out.println("\nData Informada Invalida.");
                    } else {
                        if (procedimentoDAO.verificaDisponibilidadeDataEHoraProcedimentoMedico(diaProcediemnto, horaProcedimento,
                                medicoEncontrado) == true) {

                            System.out.println("\nDia e Hora Informados Indisponiveis.");

                        } else {

                            Consulta consulta = new Consulta(diaProcediemnto, horaProcedimento, medicoEncontrado,
                                    pessoaEncontrada, unidadeFranquiaEncontrada, 0, "Realizada",
                                    calendarioSistema.getDataHoraSistema());

                            Procedimento procedimento = new Procedimento(nomeProcedimento, consulta,
                                    diaProcediemnto, horaProcedimento, "Agendado", 1500, "",
                                    calendarioSistema.getDataHoraSistema());

                            if (procedimentoDAO.adicionaProcedimento(procedimento) == true) {

                                System.out.println("\nProcedimento Marcado Com Sucesso!");

                            } else {

                                System.out.println("\nNao Foi Possivel Marcar O Procediemnto.");
                            }

                        }
                    }

                }

            }

        }
    }

    private void cancelarProcedimento(ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {
        
        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());

        System.out.println("\nInforme o ID - Procedimento Que Deseja Cancelar: ");
        int idProcedimento = Integer.parseInt(scanner.nextLine());
        idProcedimento = vd.validarINT(idProcedimento);

        Procedimento procedimentoEncontrado = procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

        if (procedimentoEncontrado == null) {
            System.out.println("\nProcedimento Nao Encontrado.");
        } else {
            if (procedimentoDAO.recebeProcedimentoECancela(procedimentoEncontrado, calendarioSistema) == true) {
                System.out.println("\nProcedimento Cancelado Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Cancelar O Procedimento.");
            }
        }
    }

    private void remarcarProcedimento(ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());

        System.out.println("\nInforme o ID - Procedimento Que Deseja Remarcar: ");
        int idProcedimento = Integer.parseInt(scanner.nextLine());
        idProcedimento = vd.validarINT(idProcedimento);

        Procedimento procedimentoEncontrado = procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

        if (procedimentoEncontrado == null) {
            System.out.println("\nProcedimento Nao Encontrado.");
        } else {
            DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("\nInforme a Nova Data Do Procedimento No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
            String dia = scanner.nextLine();
            LocalDate diaProcedimento = LocalDate.parse(dia, fdia);

            System.out.println("\nInforme a Nova Hora Do Procedimento No Seguinte Formato, Hora:Minutos (00:00)..: ");
            String Hora = scanner.nextLine();
            LocalTime horaProcedimento = LocalTime.parse(Hora);

            if (procedimentoDAO.verificaDataProcedimento(calendarioSistema, diaProcedimento) == true) {
                System.out.println("\nData Informada Invalida.");
            } else {
                if (procedimentoDAO.verificaDisponibilidadeDataEHoraProcedimentoMedico(diaProcedimento, horaProcedimento,
                        procedimentoEncontrado.getConsulta().getMedico()) == true) {

                    System.out.println("\nDia e hora Informados, Indisponiveis.");

                } else {
                    if (procedimentoDAO.recebeProcedimentoERemarca(diaProcedimento,
                            horaProcedimento, procedimentoEncontrado, calendarioSistema) == true) {

                        System.out.println("\nProcedimento Remarcado Com Sucesso!");

                    } else {

                        System.out.println("\nNao Foi Possivel Remarcar O Procedimento.");
                    }
                }
            }

        }

    }

    private void menuOpcoesFinanceiro(FinanceiroAdmDAO financeiroAdmDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, FinanceiroMedicoDAO financeiroMedicoDAO, MedicoDAO medicoDAO) {

        int opcao;

        do {
            System.out.println("\nData e Hora do Sistema: " + calendarioSistema.getDataHoraSistema().format(DateTimeFormatter.
                    ofPattern("dd/MM/yyyy HH:mm:ss")));
            opcao = telaAdmistrador.menuFinanceiroAdm();

            int dias = 0;
            switch (opcao) {
                case 1: {

                    dias++;

                    if (calendarioSistema.passaDias(dias) == true) {
                        System.out.println("\nDia Encerrado com sucesso.");

                        cancelaConsultasNaoAtendidasNoDia(consultaDAO, calendarioSistema);
                        cancelaProcedimentosNaoAtendidosNoDia(procedimentoDAO, calendarioSistema);

                    } else {
                        System.out.println("\nNao foi possivel Encerrar o dia");
                    }

                    break;
                }
                case 2: {
                     pagamentosAdm(financeiroAdmDAO, calendarioSistema, consultaDAO, 
                             procedimentoDAO, admnistrador, unidadeFranquiaDAO, vd, financeiroMedicoDAO, medicoDAO);
                    break;
                }
                case 3: {
                    System.out.println("\n");
                    financeiroAdmDAO.buscaMovimentacoesFinanceirasPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    financeiroMedicoDAO.buscaPagamentosMedicosPorFranquia(admnistrador.getFranquia());
                    break;
                }

            }

        } while (opcao != 0);
    }

    private void cancelaConsultasNaoAtendidasNoDia(ConsultaDAO consultaDAO,
            CalendarioSistema calendarioSistema) {

        if (consultaDAO.cancelaConsultasNaoRealizadasNoDia(calendarioSistema) == true) {
            System.out.println("\nTodas Consultas Nao realizadas No Dia Anterior Foram Canceladas.");
        }
    }

    private void cancelaProcedimentosNaoAtendidosNoDia(ProcedimentoDAO procedimentoDAO,
            CalendarioSistema calendarioSistema) {
        if (procedimentoDAO.cancelaProcedimentosNaoRealizadosNoDia(calendarioSistema) == true) {
            System.out.println("\nTodos Procedimentos Nao Realizados No Dia Anterior Foram Cancelados.");
        }
    }

    private boolean verificaSeEhPrimeiroDiaDoMes(CalendarioSistema calendarioSistema) {

        int diaSistema = calendarioSistema.getDiaDoSistema().getDayOfMonth();

        if (diaSistema == 01) {
            return true;
        }

        return false;
    }

    private void pagamentosAdm(FinanceiroAdmDAO financeiroAdmDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, FinanceiroMedicoDAO financeiroMedicoDAO, MedicoDAO medicoDAO) {
        
        int opcao;

        do {
            opcao = telaAdmistrador.menuPagamentosAdm();

            switch (opcao) {
                case 1: {

                    if (verificaSeEhPrimeiroDiaDoMes(calendarioSistema) == true) 
                    {
                           if (pagaAdmnistradora(calendarioSistema, financeiroAdmDAO, unidadeFranquiaDAO,
                                    admnistrador, vd) == true) {

                               if (CalculaValoresMedicos(calendarioSistema, consultaDAO, procedimentoDAO,
                                        financeiroMedicoDAO, medicoDAO, vd, admnistrador) == true) {

                                    pagarMedicos(admnistrador, financeiroMedicoDAO, calendarioSistema, vd);
                               }
                            }
                        
                    } else {
                        System.out.println("\nEsse Tipo De Pagamento So Libera No Dia 01 De Cada Mes!");
                    }

                    break;
                }

                case 2: {
                     pagaDespesasAvulsas(calendarioSistema, unidadeFranquiaDAO, admnistrador, vd, financeiroAdmDAO);
                    break;

                }

            }

        } while (opcao != 0);
    }

    private boolean pagaAdmnistradora(CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO,
            UnidadeFranquiaDAO unidadeFranquiaDAO, Admnistrador admnistrador, ValidacaoEntradaDados vd) {

        double rendaBruta;
        double parteAdministradora;
        double ganhoLiquido;

        boolean pago;
        int opcao;

        boolean saiu = false;

        System.out.println("\n============ Dia de Pagamento!!! =============");

        do {

            System.out.println("\n");
            unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());

            System.out.println("\nInforme o ID - UnidadeFranquia da Qual deseja fazer o Pagamento: ");
            int idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
            idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);

            UnidadeFranquia unidadeSelecionada = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

            if (unidadeSelecionada == null) {
                System.out.println("\nUnidade de Franquia nao Encontrada!");
            } else {

                pago = financeiroAdmDAO.verificaPagamentoUnidade(calendarioSistema, unidadeSelecionada);

                if (pago == true) {
                    System.out.println("\n------ A Unidade Informada Ja Fez O Pagamento Esse Mes. -----");
                } else {
                    rendaBruta = financeiroAdmDAO.calculaRendaBruta(calendarioSistema, unidadeSelecionada);
                    parteAdministradora = financeiroAdmDAO.calculaParteValorAdmnistradora(rendaBruta,
                            unidadeSelecionada, calendarioSistema);
                    ganhoLiquido = financeiroAdmDAO.calculaRendaLiquida(rendaBruta, parteAdministradora);

                    System.out.println("\nUnidade: " + unidadeSelecionada);
                    System.out.println("\n*******Ganho Bruto: ");
                    System.out.println("R$: " + rendaBruta);

                    System.out.println("\n*******Parte Da Admnistradora: ");
                    System.out.println("R$: " + parteAdministradora);

                    System.out.println("\n******Ganho Liquido: ");
                    System.out.println("R$: " + ganhoLiquido);

                }

            }

            System.out.println("\n0 - Para Sair Do Modulo De Pagamentos Franquia: ");
            System.out.println("\n1 - Para Continuar Realizando Pagamentos Franquia: ");
            System.out.println("\nInforme Opcao : ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) {
                saiu = true;
            }

        } while (saiu == false);

        return saiu == true;
    }

    private void pagaDespesasAvulsas(CalendarioSistema calendarioSistema, UnidadeFranquiaDAO unidadeFranquiaDAO,
            Admnistrador admnistrador, ValidacaoEntradaDados vd, FinanceiroAdmDAO financeiroAdmDAO) {

        System.out.println("\n");
        unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());

        System.out.println("\nInforme o ID - UnidadeFranquia da Qual deseja fazer o Pagamento: ");
        int idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
        idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);

        UnidadeFranquia unidadeSelecionada = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

        if (unidadeSelecionada == null) {
            System.out.println("\nUnidade de Franquia nao Encontrada!");
        } else {
            System.out.println("\nInforme O Descritivo Do Movimento: ");
            String descritivoMovimento = scanner.nextLine();
            descritivoMovimento = vd.validaString(descritivoMovimento);

            System.out.println("\nInforme O Valor Do Pagamento: ");
            double valorPagamento = Double.parseDouble(scanner.nextLine());
            valorPagamento = vd.validarDoble(valorPagamento);

            FinanceiroAdm financeiroAdm = new FinanceiroAdm("Saida", valorPagamento, unidadeSelecionada,
                    descritivoMovimento, calendarioSistema.getDataHoraSistema());

            if (financeiroAdmDAO.adicionaFinanceiroAdm(financeiroAdm) == true) {
                System.out.println("\nPagamento Realizado Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Realizar O Pagamento.");
            }
        }
    }

    private boolean CalculaValoresMedicos(CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            MedicoDAO medicoDAO, ValidacaoEntradaDados vd, Admnistrador admnistrador) {

        double valorConsultas;
        double valorProcedimentos;
        double parteDescontadaConsulta;
        double parteDescontadaProcedimento;
        double valorLiquidomedico;

        boolean saiu = false;
        int opcao;

        System.out.println("\n=========== Gerar Valores Dos Medicos! =============");

        do {

            System.out.println("\n");
            medicoDAO.mostraTodosMedicos();

            System.out.println("\nInforme o ID - Medico Que deseja Gerar O Calculo: ");
            int idMedico = Integer.parseInt(scanner.nextLine());
            idMedico = vd.validarINT(idMedico);

            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nO Medico Informado Nao Foi Encontrado!!!");
            } else {
                if (financeiroMedicoDAO.verificaCalculosValoresMedico(medicoEncontrado, calendarioSistema,
                        admnistrador.getFranquia()) == true) {
                    System.out.println("\n--------- Os Calculos Do Medico Informado Ja Foram Feitos esse mes. -----");
                } else {
                    System.out.println("\n" + medicoEncontrado);

                    valorConsultas = consultaDAO.calculaValorConsultasDoMes(medicoEncontrado, calendarioSistema,
                            admnistrador.getFranquia());
                    System.out.println("\nValor Bruto Das Consultas: " + valorConsultas);

                    valorProcedimentos = procedimentoDAO.calculaValorProcedimentosDoMes(medicoEncontrado,
                            calendarioSistema, admnistrador.getFranquia());
                    System.out.println("\nValor Bruto Dos Procedimentos: " + valorProcedimentos);

                    parteDescontadaConsulta = consultaDAO.calculaParteDescontoConsultas(valorConsultas);
                    System.out.println("\nA Parte Descontada Sobre As Consultas E: " + parteDescontadaConsulta);

                    parteDescontadaProcedimento = procedimentoDAO.calculaParteDescontoProcedimentos(valorProcedimentos);
                    System.out.println("\nA Parte Descontada Sobre Os Procedimentos E: " + parteDescontadaProcedimento);

                    valorLiquidomedico = financeiroMedicoDAO.calculaValorLiquidoAReceberMedico(valorConsultas, valorProcedimentos,
                            parteDescontadaConsulta, parteDescontadaProcedimento);
                    System.out.println("\nO Valor Liquido A Ser Pago Ao Medico E: " + valorLiquidomedico);

                    FinanceiroMedico financeiroMedico = new FinanceiroMedico(valorLiquidomedico, medicoEncontrado,
                            "Agendado", admnistrador.getFranquia(), calendarioSistema.getDataHoraSistema());

                    if (financeiroMedicoDAO.adicionaFinanceiroMedico(financeiroMedico) == true) {
                        System.out.println("\nCalculos Gerados Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Gerar Os Calculos.");
                    }

                }
            }

            System.out.println("\n0 - Para Sair Do Modulo De Geracao De Calculos Financeiros De Medicos: ");
            System.out.println("\n1 - Para Continuar Realizando Geracao De Calculos Financeiros De Medicos: ");
            System.out.println("\nInforme Opcao : ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) {
                saiu = true;
            }

        } while (saiu == false);

        return saiu == true;
    }

    private void pagarMedicos(Admnistrador admnistrador, FinanceiroMedicoDAO financeiroMedicoDAO,
            CalendarioSistema calendarioSistema, ValidacaoEntradaDados vd) {

        int opcao;

        System.out.println("\n=========== Pagar Os Medicos! =============");

        do {

            System.out.println("\n");
            if (financeiroMedicoDAO.buscaPagamentosMedicosPorFranquiaEhMes(admnistrador.getFranquia(),
                    calendarioSistema) == false) {
                System.out.println("\n------------ Todos Os Medicos Ja Foram pagos Esse Mes! --------");

            } else {

                System.out.println("\nInforme o ID - Financeiro Medico Que Deseja Pagar: ");
                int idFinanceiroMedico = Integer.parseInt(scanner.nextLine());
                idFinanceiroMedico = vd.validarINT(idFinanceiroMedico);

                FinanceiroMedico financeiroMedicoEncontrado
                        = financeiroMedicoDAO.buscaPagamentosMedicosPorID(idFinanceiroMedico);

                if (financeiroMedicoEncontrado == null) {
                    System.out.println("\nFinanceiro Medico Nao Encontrado!");
                } else {
                    if (financeiroMedicoDAO.pagarMedico(financeiroMedicoEncontrado, calendarioSistema) == true) {
                        System.out.println("\nPagamento Realizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Realizar O Pagamento.");
                    }
                }
            }

            System.out.println("\n0 - Para Sair Do Modulo De Pagamento De Medicos: ");
            System.out.println("\n1 - Para Continuar Realizando Pagamento De Medicos: ");
            System.out.println("\nInforme Opcao : ");
            opcao = Integer.parseInt(scanner.nextLine());

        } while (opcao != 0);

    }

}
