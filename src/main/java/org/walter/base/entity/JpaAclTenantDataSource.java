package org.walter.base.entity;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ACL_TENANT_DATASOURCE")
@ToString(callSuper = true)
public class JpaAclTenantDataSource extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TENANT_ID", length = 255, nullable = false)
	private String tenantId;

	@Column(name = "DATASOURCE_ID", length = 255, nullable = false)
	private String dataSourceId;

	@Column(name = "CONNECTION_STRING", length = 1000, nullable = false)
	private String connectionString;

	@Column(name = "CONNECTION_USERNAME", length = 255, nullable = false)
	private String connectionUsername;

	@Column(name = "CONNECTION_PASSWORD", length = 255, nullable = false)
	private String connectionPassword;
}
