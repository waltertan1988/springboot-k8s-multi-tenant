package org.walter.base.entity;

import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACL_USER")
@ToString(callSuper = true)
public class JpaAclUser extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 255, unique = true, nullable = false)
	private String username;

	@Column(length = 255, name = "USER_REAL_NAME")
	private String userRealName;

	@Column(length = 255, nullable = false, name = "PASSWORD")
	private String password;

	@Column(length = 1, nullable = false, name = "GENDER")
	private String gender;
	
	@Column(length = 11, nullable = true, name = "MOBILE")
	private String mobile;
	
	@Column(name = "IS_EXPIRED", nullable = false)
	private boolean isExpired;
	
	@Column(name = "IS_LOCKED", nullable = false)
	private boolean isLocked;
	
	@Column(name = "IS_PASSWORD_EXPIRED", nullable = false)
	private boolean isPasswordExpired;
	
	@Column(name = "IS_ENABLED", nullable = false)
	private boolean isEnabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isPasswordExpired() {
		return isPasswordExpired;
	}

	public void setPasswordExpired(boolean isPasswordExpired) {
		this.isPasswordExpired = isPasswordExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
