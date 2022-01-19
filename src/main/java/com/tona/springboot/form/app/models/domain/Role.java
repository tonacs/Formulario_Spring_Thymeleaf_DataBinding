package com.tona.springboot.form.app.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Role {
	private Integer id;
	private String nombre;
	private String role;
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Role)) {
			return false;
		}
		
		Role role=(Role) obj;
		return this.id != null & this.id.equals(role.getId());
	}
	
	
	
}
