package com.prisila.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtGeral {
	
	private static Logger LOG = LoggerFactory.getLogger(UtGeral.class);
	
	public boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	public List<String> converteParaLista(String textoSeparadoPorVirgula) {
		List<String> retornoList = new ArrayList<String>();
		if (textoSeparadoPorVirgula != null && textoSeparadoPorVirgula.length() > 0) {
			String[] arrayString = textoSeparadoPorVirgula.split(",");
			for (int c = 0; c < arrayString.length; c++) {
				retornoList.add(arrayString[c]);
			}
		}
		
		return retornoList;
	}
	
	private static String getDataGeral(String data, int opcao_formato) throws ParseException {
		String formato = null;
		switch (opcao_formato) {
			case 1:
				formato = "dd/MM/yyyy";
				break;
			case 2:
				formato = "dd/MM/yyyy '&agrave;s' HH:mm";
				break;
		}
		SimpleDateFormat dataSimples = new SimpleDateFormat(formato);
		dataSimples.setLenient(false);
		return dataSimples.parse(data).toString();
		
	}
	
	public static String getData(String data) throws ParseException {
		return getDataGeral(data, 1);
	}
	
	public static String getDataComHora(String data) throws ParseException {
		return getDataGeral(data, 2);
	}
	
	public static Date[] getPrimeiroUltimoDiaDaSemana() {
		Calendar dataAtual = Calendar.getInstance();
		Calendar primeiroDia = Calendar.getInstance();
		Calendar ultimoDia = Calendar.getInstance();
		int diaDaSemana = dataAtual.get(Calendar.DAY_OF_WEEK);
		int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		int primeiroDiaInt = diaAtual - (diaDaSemana - 1);
		primeiroDia.set(Calendar.DAY_OF_MONTH, primeiroDiaInt);
		ultimoDia.set(Calendar.DAY_OF_MONTH, (primeiroDiaInt + 6));
		Date[] retorno = new Date[2];
		retorno[0] = primeiroDia.getTime();
		retorno[1] = ultimoDia.getTime();
		return retorno;
	}
	
	public static String hashMD5(String s) {
		if (s == null) {
			LOG.error("Erro ao calular o hash MD5 ");
			return null;
		}
		
		byte[] bytes;
		try {
			bytes = s.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger md5 = new BigInteger(1, md.digest(bytes));
			return md5.toString(16);
		} catch (Exception e) {
			LOG.error("Erro ao calular o hash MD5 ");
		}
		return null;
	}
}
