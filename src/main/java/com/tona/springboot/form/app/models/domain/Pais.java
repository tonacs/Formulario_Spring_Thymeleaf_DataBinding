package com.tona.springboot.form.app.models.domain;

//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pais {
	
		
		private Integer id;
		
//		@NotEmpty
		private String codigo;
		private String nombre;
		
		
		@Override
		public String toString() {
			
			return this.id.toString();
		}
		
		
		
}
