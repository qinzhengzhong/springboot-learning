package com.allan.springbootcommon.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qinzz
 *
 */
public class WebUtil {
	public static final String EMPTY_STRING = "";
	
	/**
	 * Extrae un parámetro de tipo String del request. Si el parámetro no existe devuelve
	 * un String vacio.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @return El valor String del parámetro o un String vacio si no existe.
	 */
	public static final String getString(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		String stringValue = EMPTY_STRING;
		if (value != null) {
			/*
			try {
				return new String(value.getBytes("ISO-8859-1"), "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				// Ignore
			}
			*/
			return value;
		}
		return stringValue;
	}
	
	public static final String getDecodeString(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		String stringValue = EMPTY_STRING;
		if (value != null) {
			try {
				return new String(value.getBytes("ISO-8859-1"), "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				// Ignore
			}
			return value;
		}
		return stringValue;
	}

	/**
	 * Extrae un parámetro de tipo String del request. Si el parámetro no existe devuelve
	 * el valor por defecto especificado.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @param Valor per defecto del parámetro.
	 * @return El valor String del parámetro o el valor por defecto si no existe.
	 */
	public static final String getString(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getParameter(name);
		String stringValue = defaultValue; 
		if (value != null&&value.trim().length()>0) {
			/*try {
			return new String(value.getBytes("ISO-8859-1"), "UTF-8").trim();
		} catch (UnsupportedEncodingException e) {
			// Ignore
		}*/
		return value;
		}
		return stringValue;
	}
	
	/**
	 * Extrae un parámetro de tipo String del request. Si el parámetro no existe devuelve
	 * un String vacio.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @return El valor String del parámetro o un String vacio si no existe.
	 */
	public static final List<String> getStringList(HttpServletRequest request, String name) {
		String[] value = request.getParameterValues(name);
		List<String> stringValue = new ArrayList<String>();
		if (value != null) {
			/*try {
				for (int i=0; i<value.length; i++) {
					stringValue.add(new String(value[i].getBytes("ISO-8859-1"), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				// Ignore
			}*/
			for (int i=0; i<value.length; i++) {
				if(value[i].trim().length()>0)
					stringValue.add(value[i]);
			}
		}
		return stringValue;
	}
	
	/**
	 * Extrae un parámetro de tipo entero del request. 
	 * Si el parámetro no existe o no es valido devuelve 0.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @return El valor int del parámetro o 0 si no existe o no es valido.
	 */
	public static final int getInt(HttpServletRequest request, String name) {
		String strValue = request.getParameter(name);
		int intValue = 0;
		if (strValue != null && !EMPTY_STRING.equals(strValue)) {
			try {
				intValue = Integer.parseInt(strValue);
			} catch (Throwable t) {
				// Ignore
			}
		}
		return intValue;
	}
	/**
	 * 获取数值数组参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static final Integer[] getIntArr(HttpServletRequest request, String name) {
		String[] strValue = request.getParameterValues(name);
		if (strValue != null && strValue.length > 0) {
			Integer[] intArr = new Integer[strValue.length];
			try {
				for(int i=0;i<strValue.length;i++)
				{
					intArr[i] = Integer.parseInt(strValue[i]);
				}
				return intArr;
			} catch (Throwable t) {
				return null;
			}
		}else{
			return null;
		}
		
	}
	
	/**
	 * Extrae un parámetro de tipo entero del request. 
	 * Si el parámetro no existe o no es valido devuelve el valor por defecto especificado.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @param defaultValue Valor per defecto
	 * @return El valor int del parámetro o el valor por defecto especificado si no existe o no es valido.
	 */
	public static final int getInt(HttpServletRequest request, String name, int defaultValue) {
		String strValue = request.getParameter(name);
		int intValue = defaultValue;
		if (strValue != null && !EMPTY_STRING.equals(strValue)) {
			try {
				intValue = Integer.parseInt(strValue);
			} catch (Throwable t) {
				// Ignore
			}
		}
		return intValue;
	}
	
	/**
	 * 接收和返回Integer类型，可生产null类型默认值
	 * @author: chenzehe
	 */
	public static final Integer getInt(HttpServletRequest request, String name, Integer defaultValue) {
		String strValue = request.getParameter(name);
		Integer intValue = defaultValue;
		if (strValue != null && !EMPTY_STRING.equals(strValue)) {
			try {
				intValue = Integer.parseInt(strValue);
			} catch (Throwable t) {
				// Ignore
			}
		}
		return intValue;
	}
	/**
	 * 获取日期类型的参数
	 */
	public static final Date getDate(HttpServletRequest request, String name, String pattern) {
		String strValue = request.getParameter(name);
		Date dt = new Date();
		if (strValue != null && !EMPTY_STRING.equals(strValue)) 
		{
			try {
				dt = DateUtil.parse(strValue, pattern);
			} catch (Throwable t) {
				// Ignore
			}
		}
		return dt;
	}

	/**
	 * Extrae un parámetro de tipo long del request. 
	 * Si el parámetro no existe o no es valido devuelve 0.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @return El valor int del parámetro o 0 si no existe o no es valido.
	 */
	public static final long getLong(HttpServletRequest request, String name) {
		String strValue = request.getParameter(name);
		long longValue = 0;
		if (strValue != null && !EMPTY_STRING.equals(strValue)) {
			try {
				longValue = Long.parseLong(strValue);
			} catch (Throwable t) {
				// Ignore
			}
		}
		return longValue;
	}
	
	/**
	 * Extrae un parámetro de tipo booleano del request. 
	 * Si el parámetro existe y no esta vacio devuelve true, en caso contrario devuelve false.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @return true si el parámetro existe y no esta vacio, false en caso contrario.
	 */
	public static final boolean getBoolean(HttpServletRequest request, String name) {
		String strValue = request.getParameter(name);
		return (strValue != null && !strValue.equals(EMPTY_STRING) && !strValue.equals("false"));
	}
	
	/**
	 * Extrae un parámetro de tipo booleano del request. 
	 * Si el parámetro existe y es igual al valor especificado devuelve true, en caso
	 * contrario devuelve false.
	 * @param request Petición de la que extraer el parámetro.
	 * @param name Nombre del parámetro
	 * @param trueValue Valor considerado true.
	 * @return true si el parámetro existe y es igual a trueValue, false en caso contrario.
	 */
	public static final boolean getBoolean(HttpServletRequest request, String name, String trueValue) {
		String strValue = request.getParameter(name);
		return (strValue != null && strValue.equals(trueValue));
	}
}
