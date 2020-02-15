package org.walter.base.entity;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ACL_DATASOURCE")
@ToString(callSuper = true)
public class JpaAclDataSource extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATASOURCE_ID", length = 255, unique = true, nullable = false)
	private String username;

	@Column(name = "DATASOURCE_NAME", length = 255, nullable = false)
	private String userRealName;
}
