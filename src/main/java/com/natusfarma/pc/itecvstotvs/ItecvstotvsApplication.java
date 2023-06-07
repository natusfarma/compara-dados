package com.natusfarma.pc.itecvstotvs;



import com.natusfarma.pc.itecvstotvs.componente.MapClass;
import com.natusfarma.pc.itecvstotvs.model.ModeloNdf;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class ItecvstotvsApplication {// implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ItecvstotvsApplication.class, args);
	}

//
//
//	@Override
//	public void run(String... args) throws Exception {
//		//periodo de vencimento.
//		LocalDate dataInicial = LocalDate.of(2023, 05, 18);
//		LocalDate dataFinal = LocalDate.of(2023, 05, 18);
//		java.io.File file = new java.io.File("C:\\Unidade\\foto\\comparativo"+dataInicial+".csv");
//
////		processar para impressão no console
////		compararDados.processar(dataInicial,dataFinal);
////		System.out.println(compararDados.cabecalhoTipo());
////		compararDados.imprimirListas();
//
////		String[] ids = {"2","3","4","5","6"};
////		compararDadosClientesService.processarPorIds(ids);
////		compararDadosClientesService.imprimirListas();
//
////		compararDadosProdutoService.processarPorIds(ids);
////		compararDadosProdutoService.imprimirListas();
//
//		System.out.println("------------------------------");
//
//		System.out.println("total de atributos "+MapClass.totalDeAtributos(ModeloNdf.class));
//
//		System.out.println("------------------------------");
//
//	}

}
