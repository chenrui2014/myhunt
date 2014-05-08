package com.huntech.huntoms.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "S_USER" , schema = "huntoms")
public class User {
	@Id
	@Column(name = "User_ID", nullable = false, precision = 19, scale = 0)
	@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER",allocationSize=1)
	@GeneratedValue(generator = "SEQ_USER")
	private Long userID;
	
	@Column(name = "ORGAN_ID")
	private Long organId;
	
	@Column(name = "USER_CODE", length = 50)
	private String userCode;
	
	@Column(name = "USER_NAME", length = 50)
	private String userName;
	
	@Column(name = "PASSWORD", length = 50)
	private String password;
	
	@Column(name = "TELLPHONE", length = 20)
	private String phone;
	
	@Column(name = "EMIAL", length = 50)
	private String email;
	
	@Column(name = "LOGIN_NAME", length = 50)
	private String loginName;

	@Column(name = "SEX", length = 4)
	private String sex;
	
	@Column(name = "EDUCATIONAL", length = 60)
	private String educational;
	
	@Column(name = "NATIONALITY", length = 60)
	private String nationality;
	
	@Column(name = "DUTY", length = 60)
	private String duty;
	
	@Column(name = "POSITION", length = 60)
	private String position;
	
	@Column(name = "FDESC", length = 225)
	private String Fdesc;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFdesc() {
		return Fdesc;
	}

	public void setFdesc(String fdesc) {
		Fdesc = fdesc;
	}
	
	
}
