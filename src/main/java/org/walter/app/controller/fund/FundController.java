package org.walter.app.controller.fund;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.walter.app.controller.BaseController;
import org.walter.app.entity.fund.JpaFundAccountBill;
import org.walter.app.repository.fund.FundAccountBillRepository;
import org.walter.app.service.fund.FundService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@RestController
@RequestMapping(value = "/fund")
public class FundController extends BaseController {
    @Autowired
    private FundAccountBillRepository fundAccountBillRepository;
    @Autowired
    private FundService fundService;

    @GetMapping("/listObject")
    public List<Object> listObject(){
        List<Object> list = new ArrayList<>();
        list.addAll(listFundAccountBill());
        list.addAll(listAclUser());
        return list;
    }

    @GetMapping("/deposit")
    public String deposit(HttpServletRequest request) {
        String username = findAnyUserTenant().getUsername();
        String accountType = "1";
        String billCode = "deposit";
        BigDecimal beforeTransferAmount = new BigDecimal(0);
        BigDecimal transferAmount = new BigDecimal(100);
        Boolean isFail = Boolean.valueOf(request.getParameter("fail"));
        fundService.addFundAccountBill(username, accountType, billCode, beforeTransferAmount, transferAmount, isFail);
        return "success";
    }

    private List<JpaFundAccountBill> listFundAccountBill() {
        List<JpaFundAccountBill> jpaFundAccountBills = fundAccountBillRepository.findAll();
        for (JpaFundAccountBill jpaFundAccount : jpaFundAccountBills) {
            log.info(">>>>>> jpaFundAccountBill: {}", jpaFundAccount);
        }
        return jpaFundAccountBills;
    }
}
