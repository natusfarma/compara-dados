package com.natusfarma.pc.itecvstotvs.config;

import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.primario.PriFornecedor;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.fornecedor.secundario.SecFornecedor;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.primario.PriClientes;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.cliente.secundario.SecClientes;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.primario.PriProduto;
import com.natusfarma.pc.itecvstotvs.componente.cadastro.produto.secundario.SecProduto;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.primario.PriCartoes;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cartoes.secundario.SecCartoes;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.primario.PriCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.cheque.secundario.SecCheque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.primario.PriContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.filial.primario.PriFilial;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.contaspagar.secundario.SecContasPagar;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.primario.PriCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediarioEmissao;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.crediario.secundario.SecCrediarioPagamento;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.primario.PriFechEstoque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.fechamento_estoque.secundario.SecFechEstoque;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.primario.PriMidia;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.midia.secundario.SecMidia;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.primario.PriNdf;
import com.natusfarma.pc.itecvstotvs.componente.financeiro.ndf.secundario.SecNdf;
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
    public PriCrediarioEmissao priCrediarioEmissao(){
        return new PriCrediarioEmissao();
    }
    @Bean
    public SecCrediarioEmissao secCrediarioEmissao(){
        return new SecCrediarioEmissao();
    }
    @Bean
    public PriCrediarioPagamento priCrediarioPagamento(){
        return new PriCrediarioPagamento();
    }
    @Bean
    public SecCrediarioPagamento secCrediarioPagamento(){
        return new SecCrediarioPagamento();
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
    @Bean
    public PriNdf priNdf(){
        return new PriNdf();
    }
    @Bean
    public SecNdf secNdf(){
        return new SecNdf();
    }
    @Bean
    public PriFechEstoque priFechEstoque(){
        return new PriFechEstoque();
    }
    @Bean
    public SecFechEstoque secFechEstoque(){
        return new SecFechEstoque();
    }
    @Bean
    public PriCartoes priCartoes(){
        return new PriCartoes();
    }
    @Bean
    public SecCartoes secCartoes(){
        return new SecCartoes();
    }
    @Bean
    public PriMidia priMidia(){
        return new PriMidia();
    }
    @Bean
    public SecMidia secMidia(){
        return new SecMidia();
    }
}
