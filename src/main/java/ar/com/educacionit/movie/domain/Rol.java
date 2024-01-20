package ar.com.educacionit.movie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role")
	private String role;

	public Rol() {
		
	}
	
	public Rol(Long id, String role) {
		if(id == null || role == null) {
			throw new IllegalArgumentException("Algun campo es null");
		}
		this.id = id;
		this.role = role;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Rol(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", role=" + role + "]";
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
