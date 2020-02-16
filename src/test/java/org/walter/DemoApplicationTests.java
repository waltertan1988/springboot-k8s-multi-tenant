package org.walter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.walter.app.entity.fund.JpaFundAccount;
import org.walter.app.repository.fund.FundAccountRepository;
import org.walter.app.service.fund.FundAccountBillService;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.repository.AclUserRepository;

import java.util.List;

@Slf4j
public class DemoApplicationTests extends BaseTests{
	@Autowired
	private AclUserRepository aclUserRepository;
	@Autowired
	private FundAccountRepository fundAccountRepository;
	@Autowired
	private FundAccountBillService fundService;

	@Test
	public void testMultiTenantRead(){
		super.initUserTenantId("A");

		JpaAclUser jpaAclUser = aclUserRepository.findByUsernameOrMobile("0009785");
		log.info(">>>>>> jpaAclUser: {}", jpaAclUser);

		List<JpaFundAccount> jpaFundAccounts = fundAccountRepository.findAll();
		for (JpaFundAccount jpaFundAccount : jpaFundAccounts) {
			log.info(">>>>>> jpaFundAccount: {}", jpaFundAccount);
		}

		super.initUserTenantId("B");
		jpaFundAccounts = fundAccountRepository.findAll();
		for (JpaFundAccount jpaFundAccount : jpaFundAccounts) {
			log.info(">>>>>> jpaFundAccount: {}", jpaFundAccount);
		}
	}
}
