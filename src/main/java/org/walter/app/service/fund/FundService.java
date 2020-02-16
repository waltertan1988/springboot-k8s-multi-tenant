package org.walter.app.service.fund;

import java.math.BigDecimal;

public interface FundService {
    void deposit(String username, String accountType, String billCode, BigDecimal transferAmount, Boolean isFail) throws Exception;
}
