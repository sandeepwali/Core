package com.solumesl.aims.saas.adapter.util;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class SolumSaasUtil {
	private static ObjectMapper oMapper = new ObjectMapper();
	public static ObjectMapper getoMapper() {
		return oMapper;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o instanceof String) {
			return  getValidString(o).isEmpty();
		}else if (o instanceof Collection) {
			Collection c = (Collection)o;
			return ((c == null) || (c.isEmpty()));
		}else if (o instanceof Map) {
			Map m = (Map)o;
			return ((m == null) || (m.isEmpty()));
		}
		return (o == null);
	}

	public static  boolean isNotEmpty(Object o) {
		return (!(isEmpty(o)));
	}
	public static String getValidString(Object arg){
		return (arg == null) ? "" : arg.toString().trim();
	}

	public static String
	camelToSnake(String str)
	{
		try {
			// Regular Expression
			String regex = "([a-z])([A-Z]+)";

			// Replacement string
			String replacement = "$1_$2";

			// Replace the given regex
			// with replacement string
			// and convert it to lower case.
			str = str
					.replaceAll(
							regex, replacement)
					.toUpperCase();
		} catch (Exception e) {
			return str;
		}

		// return string
		return str;
	}

	public static boolean checkArrayLengthMatchesMapSize(String[] row, Map<String, String> configMap) {
		try {
			return configMap.size() == row.length;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Map> transformListObjToListMap(List<Object> result) {
		Objects.requireNonNull(result);
		return oMapper.convertValue(result, oMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

	}
	public static boolean checkSLabelCodeChecksum(String code) {
        if (code.matches("[0-9A-F]+") == false) {
            return false;
        } else if (CodeDigit.TWELVE == CodeDigit.valueOf(code.length())
                || CodeDigit.FOURTEEN == CodeDigit.valueOf(code.length())) {
            return generateCodeChecksum(code.substring(0, code.length() - 1))
					        .equalsIgnoreCase(code.substring(code.length() - 1));
        }
        return false;
    }

    public static String generateCodeChecksum(String subCode) {
        int xorChecksum = 0;
        for (char c : subCode.toCharArray()) {
            xorChecksum ^= Character.getNumericValue(c);
        }
        return Integer.toHexString(xorChecksum).toUpperCase();
    }


}
