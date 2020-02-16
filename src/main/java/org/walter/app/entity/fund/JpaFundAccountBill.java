package org.walter.app.entity.fund;

import lombok.Data;
import lombok.ToString;
import org.walter.base.audit.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FUND_ACCOUNT_BILL")
@ToString(callSuper = true)
public class JpaFundAccountBill extends AbstractAuditable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 255, nullable = false)
	private String username;

	@Column(name = "ACCOUNT_TYPE", length = 255, nullable = false)
	private String accountType;

	@Column(name = "BILL_CODE", length = 255, nullable = false)
	private String billCode;
	
	@Column(name = "BEFORE_BALANCE_AMOUNT", nullable = false)
	private BigDecimal beforeBalanceAmount = BigDecimal.ZERO;

	@Column(name = "TRANSFER_AMOUNT", nullable = false)
	private BigDecimal transferAmount = BigDecimal.ZERO;
}
