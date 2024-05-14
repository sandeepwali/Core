package com.solumesl.aims.saas.adapter.decoder;

import java.io.Reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.solumesl.aims.saas.adapter.exception.AccessToken401UnauthorizedException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
public class SaasCustomErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String s, Response response) {
		String message = null;
		Reader reader = null;
		try {
			reader = response.body().asReader();
			//Easy way to read the stream and get a String object
			String result = CharStreams.toString(reader);
			//use a Jackson ObjectMapper to convert the Json String into a 
			//Pojo
			ObjectMapper mapper = new ObjectMapper();
			//just in case you missed an attribute in the Pojo     
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			//init the Pojo
			ExceptionMessage exceptionMessage = mapper.readValue(result, 
					ExceptionMessage.class);

			message = exceptionMessage.getMessage();

		} catch (Exception e) {
		}finally {
			try {

				if (reader != null)
					reader.close();

			} catch (Exception e) {
			}
		}
		switch (response.status()) {

		case 401:
			return new AccessToken401UnauthorizedException(message == null ? "401 Unauthorized" : 
				message);
		default:
			return errorDecoder.decode(s, response);

		}


	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class ExceptionMessage{

		private String timestamp;
		private int status;
		private String error;
		private String message;
		private String path;

	}
}