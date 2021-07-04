package testebanco;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import banco.Controleanuncio;

class ControleanuncioTest {

	@Test
	void testInserirdados() {//testando script do cadastro de an�ncio
		Controleanuncio controle = new Controleanuncio();
		if(controle.inserirdados("Bombril mil e uma utilidades", "Bombril",
				Date.valueOf("2021-06-25"), Date.valueOf("2021-08-01"), 2)) {
			System.out.println("Dados inseridos com sucesso.");
		}else {
			System.out.println("Falha na inser��o.");
		}
		if(controle.inserirdados("Bombril mil e uma utilidades", "Bombril",
				Date.valueOf("2021-06-25"), Date.valueOf("2021-08-01"), 2.51)) {
			System.out.println("Dados inseridos com sucesso.");
		}else {
			System.out.println("Falha na inser��o.");
		}
	}

	@Test
	void testConsultanome() {//testando script do cadastro de an�ncio
		Controleanuncio controle = new Controleanuncio();
		if(controle.consultanome("Bombril")!=null) {
			System.out.println("Cliente encontrado com sucesso.");
		}else {
			System.out.println("N�o foram encontrados an�ncios com esse crit�rio.");
		}
	}
	
	@Test
	void testConsultadata(Date datainicio, Date datafinal) {//testando script do cadastro de an�ncio
		Controleanuncio controle = new Controleanuncio();
	//	if(controle.consultadata(datainicio "2021-06-25" datafinal "2021-08-01")!=null) {
			System.out.println("Cliente encontrado com sucesso.");
		//}else {
			System.out.println("N�o foram encontrados an�ncios com esse crit�rio.");
		}
	//}
}
