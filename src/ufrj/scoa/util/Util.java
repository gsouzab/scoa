package ufrj.scoa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
	
	public static String generateNewPassword(String cpf) {
		
		MessageDigest md = null;
		cpf = unmaskCPF(cpf);
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        md.update(cpf.getBytes());
 
        byte byteData[] = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}

	
	public static String unmaskCPF(String cpf) {
		cpf = cpf.replaceAll("\\.", "");
		cpf = cpf.replaceAll("-", "");
		cpf = cpf.replaceAll(" ", "");
		
		return cpf;
	}
	
	public static String formatDateToSql (String date) {
		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String reformattedStr = "";

		try {
			reformattedStr = myFormat.format(fromUser.parse(date));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return reformattedStr;
	}
	
	public static String formatDateFromSql (String date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		String reformattedStr = "";

		try {
			reformattedStr = myFormat.format(fromUser.parse(date));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return reformattedStr;
	}
	
	public static String unmaskDate(String date) {
		date = date.replaceAll("\\/", "");
		date = date.replaceAll(" ", "");
		
		return date;
	}

}
