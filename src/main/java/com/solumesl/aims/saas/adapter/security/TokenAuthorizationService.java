package com.solumesl.aims.saas.adapter.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.solumesl.aims.saas.adapter.util.SolumSaasUtil;

import io.jsonwebtoken.ExpiredJwtException;
@Component
public class TokenAuthorizationService {

	public Optional<Map<String, Object>> findByToken(String token) {
		Map<String, Object> data = null;
		try {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));
			data = stringToMap(payload);
			//convertDate(data);
			return Optional.of(data);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		return  Optional.empty();
	}

	private Map<String, Object> stringToMap(String payload) throws JsonProcessingException, JsonMappingException {
		return  SolumSaasUtil.getoMapper().readValue(payload, Map.class);
	}
	
	  public void convertDate(Map<String, Object> data) {

	        try {
	        	long date = ((Number) data.get("exp")).longValue();
				System.out.println(date);

				LocalDate ld = Instant.ofEpochMilli((long) date)
				        .atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.println(ld);

				LocalDateTime ldt = Instant.ofEpochMilli((long) date)
				        .atZone(ZoneId.systemDefault()).toLocalDateTime();
				System.out.println(ldt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
}
