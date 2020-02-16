package org.walter.app.repository.fund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.app.entity.fund.JpaFundAccountBill;

public interface FundAccountBillRepository extends JpaRepository<JpaFundAccountBill, Long>{
}
