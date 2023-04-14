package com.natusfarma.pc.itecvstotvs;

import com.natusfarma.pc.itecvstotvs.componente.CompararDados;
import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimario;
import com.natusfarma.pc.itecvstotvs.componente.primario.DatabasePrimarioFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class ItecvstotvsApplication implements CommandLineRunner {

	@Autowired
	private CompararDados compararDados;

	@Autowired
	private DatabasePrimarioFilial filiais;

	@Autowired
	private DatabasePrimario databasePrimario;

	public static void main(String[] args) {
		SpringApplication.run(ItecvstotvsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//periodo de vencimento.
		LocalDate dataInicial = LocalDate.of(2023, 04, 14);
		LocalDate dataFinal = LocalDate.of(2023, 04, 17);
//		compararDados.processar(dataInicial,dataFinal);
//		compararDados.imprimirListas();
//		File file = new File("C:\\Unidade\\foto\\comparativo"+dataInicial+".csv");
//		compararDados.gerarArquivoCsv(file);

//		filiais.imprimirListas(filiais.processar());

		databasePrimario.processarPorFilial(5,dataInicial,dataFinal);
	}
}
