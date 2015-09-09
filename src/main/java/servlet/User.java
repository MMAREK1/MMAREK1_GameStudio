package servlet;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Named
@SessionScoped
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue
	private long id;
	@Size(min = 5, max = 10)
	private String name;
	
	@Size(min = 5, max = 10)
	@Pattern(regexp = ".*\\d.*")
	private String passwd;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String password) {
		this.passwd = password;
	}
}
