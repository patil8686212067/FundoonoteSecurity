package com.fundoonotes.userservice;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@DynamicUpdate(value=true)
@Table(name = "User")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "user", strategy = "increment")
	@GeneratedValue(generator = "user")
	private int userId;
    private String name;
	private String email;
	private String password;
	private String mobileNumber;
	private boolean isActive;
	private String randomId;
	private String role;
	 @JsonIgnore
		@Lob
		@Column
		private byte[] userProfilePic;
	
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public User() {

	}
	public User(UserDto userDto) {
		this.setName(userDto.getName());
		this.setEmail(userDto.getEmail());
		this.setMobileNumber(userDto.getMobileNumber());
		this.setRole(userDto.getRole());
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getRandomId() {
		return randomId;
	}
	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public byte[] getUserProfilePic() {
		return userProfilePic;
	}
	public void setUserProfilePic(byte[] userProfilePic) {
		this.userProfilePic = userProfilePic;
	}
}
