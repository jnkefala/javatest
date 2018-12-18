package db.mapping;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles", catalog = "user_db")
public class UserRoles {
	
	private int iduser_roles;
	private Integer idusers;
	private String user;
	private String role;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "iduser_roles", unique = true, nullable = false)
	public int getIduser_roles() {
		return iduser_roles;
	}
	public void setIduser_roles(int iduser_roles) {
		this.iduser_roles = iduser_roles;
	}
	
	@Column(name = "idusers", unique = true, nullable = false)
	public Integer getIdusers() {
		return idusers;
	}
	public void setIdusers(Integer idusers) {
		this.idusers = idusers;
	}
	
	@Column(name = "user", unique = true, nullable = false, length = 100)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Column(name = "role", nullable = false, length = 100)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
}
