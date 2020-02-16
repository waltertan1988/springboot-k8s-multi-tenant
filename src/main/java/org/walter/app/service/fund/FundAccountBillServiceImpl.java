package org.walter.app.service.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.walter.app.entity.fund.JpaFundAccountBill;
import org.walter.app.repository.fund.FundAccountBillRepository;

import java.math.BigDecimal;

@Slf4j
@Service
public class FundAccountBillServiceImpl implements FundAccountBillService {
    @Autowired
    private FundAccountBillRepository fundAccountBillRepository;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JpaFundAccountBill addFundAccountBill(
            String username, String accountType, String billCode,
            BigDecimal beforeBalanceAmount, BigDecimal transferAmount, Boolean isFail) throws Exception {
        JpaFundAccountBill jpaFundAccountBill = new JpaFundAccountBill();
        jpaFundAccountBill.setUsername(username);
        jpaFundAccountBill.setAccountType(accountType);
        jpaFundAccountBill.setBillCode(billCode);
        jpaFundAccountBill.setBeforeBalanceAmount(beforeBalanceAmount);
        jpaFundAccountBill.setTransferAmount(transferAmount);
        if(isFail){
            jpaFundAccountBill.setUsername(null);
        }
        fundAccountBillRepository.save(jpaFundAccountBill);
        return jpaFundAccountBill;
    }
}
