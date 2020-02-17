package org.walter.app.service.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walter.app.entity.fund.JpaFundAccountBill;
import org.walter.app.repository.fund.FundAccountBillRepository;

import java.math.BigDecimal;

@Slf4j
@Service
public class FundService {
    @Autowired
    private FundAccountBillRepository fundAccountBillRepository;

    @Transactional(transactionManager = "fundMultiTenantJpaTransactionManager", rollbackFor = Exception.class)
    public JpaFundAccountBill addFundAccountBill(
            String username, String accountType, String billCode,
            BigDecimal beforeBalanceAmount, BigDecimal transferAmount, Boolean isFail) {
        JpaFundAccountBill jpaFundAccountBill = new JpaFundAccountBill();
        jpaFundAccountBill.setUsername(username);
        jpaFundAccountBill.setAccountType(accountType);
        jpaFundAccountBill.setBillCode(billCode);
        jpaFundAccountBill.setBeforeBalanceAmount(beforeBalanceAmount);
        jpaFundAccountBill.setTransferAmount(transferAmount);
        fundAccountBillRepository.save(jpaFundAccountBill);
        if(isFail){
            log.info("故意制造一个异常", 1/0);
        }
        return jpaFundAccountBill;
    }
}
