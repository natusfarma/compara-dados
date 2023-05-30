package com.natusfarma.pc.itecvstotvs.config;

import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.primario.PriFornecedor;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.secundario.SecFornecedor;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.primario.PriClientes;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.secundario.SecClientes;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.primario.PriProduto;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.secundario.SecProduto;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.primario.PriCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.secundario.SecCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.primario.PriContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.filial.primario.PriFilial;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.secundario.SecContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediario;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediario;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.entrada.primario.PriNotaFiscalEntrada;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.entrada.secundario.SecNotaFiscalEntrada;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.nfce.primario.PriNotaFiscalNfce;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.nfce.secundario.SecNotaFiscalNfce;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.saida.primario.PriNotaFiscalSaida;
import com.natusfarma.pc.itecvstotvs.componente.notafiscal.saida.secundario.SecNotaFiscalSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BancoConfig {

    @Bean
    public PriContasPagar priContasPagar(){
        return new PriContasPagar();
    }

    @Bean
    public SecContasPagar secContasPagarc(){
        return new SecContasPagar();
    }

    @Bean
    public PriFilial priFilial(){
        return new PriFilial();
    }

    @Bean
    public PriClientes priClientes(){
        return new PriClientes();
    }

    @Bean
    public SecClientes secClientes(){
        return new SecClientes();
    }

    @Bean
    public PriFornecedor priFornecedor(){
        return new PriFornecedor();
    }

    @Bean
    public SecFornecedor secFornecedor(){
        return new SecFornecedor();
    }

    @Bean
    public PriProduto priProduto(){
        return new PriProduto();
    }
    @Bean
    public SecProduto secProduto(){
        return new SecProduto();
    }

    @Bean
    public PriCheque priCheque(){
        return new PriCheque();
    }
    @Bean
    public SecCheque secCheque(){
        return new SecCheque();
    }

    @Bean
    public PriCrediario priCrediario(){
        return new PriCrediario();
    }
    @Bean
    public SecCrediario secCrediario(){
        return new SecCrediario();
    }
    @Bean
    public PriNotaFiscalEntrada priNotaFiscalEntrada(){
        return new PriNotaFiscalEntrada();
    }
    @Bean
    public SecNotaFiscalEntrada secNotaFiscalEntrada(){
        return new SecNotaFiscalEntrada();
    }

    @Bean
    public PriNotaFiscalSaida priNotaFiscalSaida(){
        return new PriNotaFiscalSaida();
    }
    @Bean
    public SecNotaFiscalSaida secNotaFiscalSaida(){
        return new SecNotaFiscalSaida();
    }

    @Bean
    public PriNotaFiscalNfce priNotaFiscalNfce(){
        return new PriNotaFiscalNfce();
    }
    @Bean
    public SecNotaFiscalNfce secNotaFiscalNfce(){
        return new SecNotaFiscalNfce();
    }


}
