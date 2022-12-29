package br.com.cadastro.dominio.usecases;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiasUteis {

    public static int contagemDiasUteis(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDate result = dataInicial;
        int diasEntreDatas = dataInicial.until(dataFinal).getDays();
        int adicionaDias = 0;
        while (adicionaDias < diasEntreDatas) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++adicionaDias;
            }
        }
        return adicionaDias;
    }
}
