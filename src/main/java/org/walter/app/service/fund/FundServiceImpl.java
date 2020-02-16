package org.walter.app.service.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class FundServiceImpl implements FundService{
    @Autowired
    private FundAccountService fundAccountService;
    @Autowired
    private FundAccountBillService fundAccountBillService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deposit(String username, String accountType, String billCode, BigDecimal transferAmount, Boolean isFail) throws Exception {
        BigDecimal beforeBalanceAmount = fundAccountService.fundAccountTransfer(username, accountType, transferAmount);
        fundAccountBillService.addFundAccountBill(username, accountType, billCode, beforeBalanceAmount, transferAmount, isFail);
    }
}
