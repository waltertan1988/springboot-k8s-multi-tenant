package org.walter.base.entity;

import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACL_TENANT")
@ToString(callSuper = true)
public class JpaAclTenant extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TENANT_ID", length = 255, unique = true, nullable = false)
	private String tenantId;

	@Column(name = "TENANT_NAME", length = 255, nullable = false)
	private String tenantName;
	
	@Column(name = "IS_ENABLED", nullable = false)
	private boolean isEnabled = false;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}
}
