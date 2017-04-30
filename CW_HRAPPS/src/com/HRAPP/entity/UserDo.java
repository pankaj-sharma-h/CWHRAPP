package com.HRAPP.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserDo
 *
 */
@Entity
@Table(name="HRAPPS_USER")
public class UserDo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public UserDo() {
		super();
	}
	@Id
	@Column(name="USER_ID",length=255)
	private String userId;
	
	@Column(name="USER_NAME",length=255)
	private String userName;
	
	@Column(name="PASSWORD",length=255)
	private String password;
	
	@Column(name="ROLE",length=255)
	private String role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
   
}
