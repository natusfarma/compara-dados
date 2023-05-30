package com.natusfarma.pc.itecvstotvs.util;

import java.time.LocalDate;

public abstract class ConverterTipo {

    /**
     * Método é utilizado para converter a sql.Date para LocalDate lembrando que o campo data não pode ser Varchar
     * convertendo a coluna varchar em String no sql CONVERT(DATE, '20230403', 103)  AS NOMECAMPO,
     * @param data
     * @return
     */
    public static LocalDate sqlDateToLocalDate(java.sql.Date data) {
        return data.toLocalDate();
    }
}
