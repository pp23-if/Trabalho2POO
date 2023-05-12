package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarioSistema {

    private DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private LocalDateTime dataHoraSistema = LocalDateTime.of(2023, 1, 25, 8, 0, 0);
    private LocalDateTime hoje;
    private LocalDateTime amanha;
    private LocalDate diaDoSistema = this.dataHoraSistema.toLocalDate();

    public LocalDateTime getDataHoraSistema() {
        return dataHoraSistema;
    }

    public void setDataHoraSistema(LocalDateTime dataHoraSistema) {
        this.dataHoraSistema = dataHoraSistema;
    }

    public LocalDate getDiaDoSistema() {
        return diaDoSistema;
    }

    public boolean passaDias(int dias) {

        hoje = this.dataHoraSistema;
        
        this.setDataHoraSistema(dataHoraSistema.plusDays(dias));
        amanha = this.dataHoraSistema;

        diaDoSistema = amanha.toLocalDate();

        if (verificaSeDiaPassou(hoje, amanha) == true) {
            return true;
        } else {
            return false;
        }

    }

    private boolean verificaSeDiaPassou(LocalDateTime hojeDataSistema,
            LocalDateTime amanhaDataSistema) {

        if (amanhaDataSistema.isAfter(hojeDataSistema)) {
            return true;
        } else {
            return false;
        }
    }

}
