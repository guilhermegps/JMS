package br.com.teste.modelo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PedidoFactory {

	public static Pedido geraPedidoComValores() {
		Pedido pedido = new Pedido(2459, geraData("22/12/2016"), geraData("23/12/2016"), new BigDecimal("145.98"));
		
		Item motoG = geraItem(23,"Moto G");
		Item motoX = geraItem(51,"Moto X");
		
		pedido.adicionaItem(motoX);
		pedido.adicionaItem(motoG);
		
		return pedido;

	}

	private static Item geraItem(int id, String nome) {
		return new Item(id,nome);
	}

	private static Calendar geraData(String dataComString) {
		try {
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataComString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			return cal;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
}
