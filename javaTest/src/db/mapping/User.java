package db.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;

@Entity
@Table(name = "users", catalog = "user_db")
public class User {
	
	private Integer idusers;
	private String user;
	private String firstName;
	private String lastName;
	private String phone;
	private String company;
	private String pass;
	private String newPass;
	private String reenteredNewPass;
	private byte[] photo;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	
	@Column(name = "pass", nullable = false, length = 100)
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Column(name = "firstName", nullable = true, length = 100)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "lastName", nullable = true, length = 100)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "phone", nullable = true, length = 20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "company", nullable = true, length = 100)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Lob
	@Column(name = "photo", nullable = true, columnDefinition = "mediumblob")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public String getReenteredNewPass() {
		return reenteredNewPass;
	}
	public void setReenteredNewPass(String reenteredNewPass) {
		this.reenteredNewPass = reenteredNewPass;
	}

}
