package ar.com.educacionit.movie.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
		name = "users_roles",  
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id") 
	)	
	private List<Rol> roles;
	
	public User() {
		
	}
	
	//se usa cuando el registro venga de la base
	public User(Long id, String username, String password) {
		//Anticurruption layer (D.D.D)
		if(id == null || username == null || password == null) {
			throw new IllegalArgumentException("Algun campo es null");
		}
		
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	//cuando se envie a grabar a la db
	public User(String username, String password) {
		if(username == null || password == null) {
			throw new IllegalArgumentException("Algun campo es null");
		}
		this.username = username;
		this.password = password;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Rol> getRoles() {
		return roles;
	}
	
}
