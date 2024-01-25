package com.jeanPiress.algafood.api.exceptionHandler;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

	public Integer status;
	public String type;
	public String title;
	public String detail;
	public List<Field> fields;
	
	
	@Getter
	@Builder
	public static class Field{
		
		private String name;
		private String userMessage;
	}
}


