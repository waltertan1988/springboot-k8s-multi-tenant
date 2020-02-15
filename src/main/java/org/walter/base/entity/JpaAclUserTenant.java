package org.walter.base.entity;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ACL_USER_TENANT")
@ToString(callSuper = true)
public class JpaAclUserTenant extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 255, nullable = false)
	private String username;

	@Column(name = "TENANT_ID", length = 255, nullable = false)
	private String tenantId;
}
