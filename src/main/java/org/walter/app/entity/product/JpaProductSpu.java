package org.walter.app.entity.product;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PRODUCT_SPU")
@ToString(callSuper = true)
public class JpaProductSpu extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SPU_SEQ", length = 255, nullable = false)
	private String spuSeq;

	@Column(name = "NAME", length = 255, nullable = false)
	private String name;
}
