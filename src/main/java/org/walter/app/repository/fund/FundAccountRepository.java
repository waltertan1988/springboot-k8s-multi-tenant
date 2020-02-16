package org.walter.app.repository.fund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.app.entity.fund.JpaFundAccount;

public interface FundAccountRepository extends JpaRepository<JpaFundAccount, Long>{

    JpaFundAccount findOneByUsernameAndAccountType(String username, String accountType);
}
