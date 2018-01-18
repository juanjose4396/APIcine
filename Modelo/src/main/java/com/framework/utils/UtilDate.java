package com.framework.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilDate {

	/**
	 * Método para obtener la fecha y la hora en formato yyyyMMddHHmmssSSS
	 * @return String Cadena de caracteres con la fecha y la hora actual en formato yyyyMMddHHmmssSSS
	 */
	public static String FechaHora() {
		Date fechaActual_w = new Date();
		SimpleDateFormat formato_w = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return formato_w.format(fechaActual_w);
	}

	/**
	 * 
	 * @param dInit
	 * @param dEnd
	 * @return
	 * @throws Exception
	 */
	public static long diffDate(String dInit, String dEnd) throws Exception {
		SimpleDateFormat formato_w = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date init = formato_w.parse(dInit);
		Date end = formato_w.parse(dEnd);
		Long diff = end.getTime() - init.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	/**
	 * Metodo que retorna la fecha con la resta de los dias a la fecha actual
	 * @param dias
	 * @return
	 * @throws Exception
	 */
	public static Date fechaMenos(int dias) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fechaActual().getTime());
		cal.add(Calendar.DATE, -dias);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * Metodo que retorna la fecha con la resta de los dias a la fecha actual
	 * @param dias
	 * @return
	 * @throws Exception
	 */
	public static Date fechaMasMeses(int meses) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fechaActual().getTime());
		cal.add(Calendar.MONTH, meses);
		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * Metodo que retorna la fecha con la resta de los dias a la fecha actual
	 * @param dias
	 * @return
	 * @throws Exception
	 */
	public static Date fechaMasMeses(Date date,int meses) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.MONTH, meses);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 * @throws Exception
	 */
	public static Integer diffDay(String fecha) throws Exception {
		Long diff = diffDate(fecha, FechaHora());
		return Integer.parseInt(diff.toString());
	}

	/**
	 * Metodo que retorna el formato de la fecha yyyy-mm-dd, para insertar en base de datos
	 * @return
	 * @throws Exception
	 */
	public static Date fechaActual() throws Exception {
		Date fechaActual_w = new Date();
		SimpleDateFormat formato_w = new SimpleDateFormat("yyyy-MM-dd");
		return formato_w.parse(formato_w.format(fechaActual_w));
	}

	/**
	 * Retorna date en formato yyyy-mm-dd utilizando un String
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date fechaFormatter(String date) throws Exception {
		SimpleDateFormat formato_w = new SimpleDateFormat("yyyy-MM-dd");
		return formato_w.parse(date);
	}

	/**
	 * Retorna date en formato yyyy-mm-dd utilizando un String
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date fechaFormatterddmmyy(String date) throws Exception {
		SimpleDateFormat formato_w = new SimpleDateFormat("dd/MM/yyyy");
		return formato_w.parse(date);
	}

	/**
	 * Retorna date en formato yyyy-mm-dd utilizando un String
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Integer carcularEdad(Date date) throws Exception {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDate fechaNac = instant.atZone(defaultZoneId).toLocalDate();

		// Fecha actual
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		return periodo.getYears();
	}

	
	/**
	 * Metodo que realiza la validacion de DD y YYYY entre dos fechas
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean validateEqualDDYYYY(Date date1, Date date2) {

		//cal1
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		//Cal 2
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		//se valida el dia
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {
			return false;
		}

		//se valida el mes
		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		if(month1 != month2){
			return false;
		}
		
		return true;
	}
	
	public static int weekFechaActual() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek( Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek( 4 );
		calendar.setTime(fechaActual());
		
		int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		
		return numberWeekOfYear;
	}
	
	public static int weekFecha(Date fecha){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek( Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek( 4 );
		calendar.setTime(fecha);
		
		int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		
		return numberWeekOfYear;
	}
	
	public static int obtenerAnio(Date date){
		if (null == date){
			return 0;
		}
		else{
			String formato="yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(date));
		}
	}
	
	public static int obtenerMes(Date date){
		if (null == date){
			return 0;
		}
		else{
			String formato="MM";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return Integer.parseInt(dateFormat.format(date));
		}
	}
}
