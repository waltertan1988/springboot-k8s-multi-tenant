package org.walter.app.entity.fund;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FUND_ACCOUNT", uniqueConstraints = {
	@UniqueConstraint(name = "uk_username_accountType", columnNames = {"USERNAME", "ACCOUNT_TYPE"})
})
@ToString(callSuper = true)
public class JpaFundAccount extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 255, nullable = false)
	private String username;

	@Column(name = "ACCOUNT_TYPE", length = 255, nullable = false)
	private String accountType;
	
	@Column(name = "BALANCE_AMOUNT", nullable = false)
	private BigDecimal balanceAmount = BigDecimal.ZERO;

}
