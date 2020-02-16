package org.walter.app.service.fund;

import java.math.BigDecimal;

public interface FundAccountService {
    BigDecimal fundAccountTransfer(String username, String accountType, BigDecimal transferAmount);
}
