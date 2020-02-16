package org.walter.app.service.fund;

import org.walter.app.entity.fund.JpaFundAccountBill;

import java.math.BigDecimal;

public interface FundAccountBillService {
    JpaFundAccountBill addFundAccountBill(
            String username, String accountType, String billCode,
            BigDecimal beforeBalanceAmount, BigDecimal transferAmount, Boolean isFail) throws Exception;
}
