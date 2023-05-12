package Model;

public class MedicoDAO {

    private Medico[] vetorMedico = new Medico[50];

    public MedicoDAO(PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema) {

        Pessoa pessoaMedico1 = pessoaDAO.buscaPessoaCadastrada("lm23", "456");

        if (pessoaMedico1 != null) {
            Medico medico1 = new Medico("ABC-123", pessoaMedico1, "Ortopedista", calendarioSistema.getDataHoraSistema());
            adicionaMedico(medico1);
        }

        Pessoa pessoaMedico2 = pessoaDAO.buscaPessoaCadastrada("ju25", "123");

        if (pessoaMedico2 != null) {
            Medico medico2 = new Medico("DEF-456", pessoaMedico2, "Nutricionista", calendarioSistema.getDataHoraSistema());
            adicionaMedico(medico2);
        }

        Pessoa pessoaMedico3 = pessoaDAO.buscaPessoaCadastrada("mpm", "100");

        if (pessoaMedico3 != null) {
            Medico medico3 = new Medico("GHI-789", pessoaMedico3, "Cardiologista", calendarioSistema.getDataHoraSistema());
            adicionaMedico(medico3);
        }

    }

    private int proximaPosilivreMedico() {
        for (int i = 0; i < vetorMedico.length; i++) {
            if (vetorMedico[i] == null) {
                return i;
            }

        }
        return -1;
    }

    public boolean adicionaMedico(Medico medico) {
        int proxima = proximaPosilivreMedico();
        if (proxima != -1) {
            vetorMedico[proxima] = medico;
            return true;
        } else {
            return false;
        }

    }

    public void mostraTodosMedicosHabilitados() {
        for (Medico medico : vetorMedico) {
            if (medico != null && medico.getPessoa().isHabilitado() == true) {
                System.out.println(medico + "\n");
            }
        }
    }
    
    
     public void mostraTodosMedicos() {
        for (Medico medico : vetorMedico) {
            if (medico != null) {
                System.out.println(medico + "\n");
            }
        }
    }

    public Medico buscaMedico(Medico m) {

        for (Medico medico : vetorMedico) {

            if (medico != null && medico.equals(m)) {
                return medico;
            }
        }
        return null;
    }

    public Medico buscaMedicoAtravesdaPessoaVinculada(Pessoa pessoaLogada) {

        for (Medico medico : vetorMedico) {
            if (medico != null && medico.getPessoa().equals(pessoaLogada)) {
                return medico;
            }
        }

        return null;
    }

    public boolean atualizaLoginMedico(Medico m, String novoLogin, CalendarioSistema calendarioSistema) {

        if (!verificaSeloginEstaSendoUsado(novoLogin) == true) {
            for (Medico medico : vetorMedico) {

                if (medico != null && medico.equals(m)) {
                    medico.getPessoa().setLoginPessoa(novoLogin);
                    medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }

            }
        }

        return false;
    }

    public boolean atualizaSenhaMedico(Medico m, String novaSenha, CalendarioSistema calendarioSistema) {

        for (Medico medico : vetorMedico) {

            if (medico != null && medico.equals(m)) {
                medico.getPessoa().setSenhaPessoa(novaSenha);
                medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                return true;
            }
        }

        return false;
    }

    public boolean atualizaTelefoneMedico(Medico m, String novoTelefone, CalendarioSistema calendarioSistema) {

        if (!verificaSeTelefoneEstaSendoUsado(novoTelefone) == true) {
            for (Medico medico : vetorMedico) {

                if (medico != null && medico.equals(m)) {
                    medico.getPessoa().setTelefonePessoa(novoTelefone);
                    medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean verificaSeloginEstaSendoUsado(String login) {
        for (Medico medico : vetorMedico) {
            if (medico != null && medico.getPessoa().getLoginPessoa().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaSeTelefoneEstaSendoUsado(String telefone) {
        for (Medico medico : vetorMedico) {
            if (medico != null && medico.getPessoa().getTelefonePessoa().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaSeMedicoExiste(Pessoa p) {
        for (Medico medico : vetorMedico) {

            if (medico != null && medico.getPessoa().getCpf().equals(p.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaCrm(String Crm) {
        for (Medico medico : vetorMedico) {

            if (medico != null && medico.getCrm().equals(Crm.toUpperCase())
                    || medico != null && medico.getCrm().equals(Crm.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Medico buscaMedicoPorId(int idMedico) {
        for (Medico medico : vetorMedico) {

            if (medico != null && medico.getIdMedico() == idMedico) {
                return medico;
            }
        }
        return null;
    }

    public double verificaValorConsulta(Medico medico) {
        if (medico.getEspecialidade().equals("Ortopedista")) {
            return 500;
        } else if (medico.getEspecialidade().equals("Nutricionista")) {
            return 1000;
        } else if (medico.getEspecialidade().equals("Cardiologista")) {
            return 1200;
        } else {
            return 1500;
        }

    }

    public boolean vericaSeMedicoEPacienteSaoIguais(Pessoa pessoa, Medico medico) {
        if (pessoa != null && medico != null && medico.
                getPessoa().getCpf().equals(pessoa.getCpf())) {

            return true;
        }
        return false;
    }
    
   public boolean excluirMedico(Medico medico, CalendarioSistema calendarioSistema)
   {
       if(medico != null
          && medico.getPessoa().isHabilitado() == true)
       {
          medico.getPessoa().setHabilitado(false);
          medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
          medico.setDataModificacao(calendarioSistema.getDataHoraSistema());
          return true;
       }
        return false;
   }
    
    public void filtraMedicosExcluidos()
    {
        for (Medico medico : vetorMedico) {
            
            if(medico != null && medico.getPessoa().isHabilitado() == false)
            {
                System.out.println(medico + "\n");
            }
        }
    }
    
   public boolean reverterExclusaoMedico(Medico medico, CalendarioSistema calendarioSistema)
   {
       if(medico != null
          && medico.getPessoa().isHabilitado() == false)
       {
          medico.getPessoa().setHabilitado(true);
          medico.getPessoa().setDataModificacao(calendarioSistema.getDataHoraSistema());
          medico.setDataModificacao(calendarioSistema.getDataHoraSistema());
          return true;
       }
        return false;
   }
   
    public Medico buscaMedicoExcluidoPorId(int idMedicoExcluido) {
        for (Medico medico : vetorMedico) {

            if (medico != null && medico.getIdMedico() == idMedicoExcluido
                    && medico.getPessoa().isHabilitado() == false) {
                return medico;
            }
        }
        return null;
    }
}
