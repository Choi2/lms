package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotEmpty
	@Length(min = 3, max = 8)
	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@NotEmpty
	@Email
	@Column(name = "email", nullable = false, length = 200)
	private String email;

	@NotEmpty
	@Pattern(regexp = "^[0-9a-zA-z]{4,12}$")
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@NotEmpty
	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "join_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
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


	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", joinDate="
				+ joinDate + "]";
	}

}
