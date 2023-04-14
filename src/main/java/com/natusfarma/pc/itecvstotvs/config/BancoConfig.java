package com.natusfarma.pc.itecvstotvs.config;

import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimario;
import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimarioFilial;
import com.natusfarma.pc.itecvstotvs.componente.secundario.DatabaseSecundario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BancoConfig {

    @Bean
    public DatabasePrimario bancoPrimarioService(){
        return new DatabasePrimario();
    }

    @Bean
    public DatabaseSecundario bancoSecundarioService(){
        return new DatabaseSecundario();
    }

    @Bean
    public DatabasePrimarioFilial databasePrimarioFilial(){
        return new DatabasePrimarioFilial();
    }
}
