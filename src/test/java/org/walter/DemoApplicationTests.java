package org.walter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.walter.app.entity.fund.JpaFundAccount;
import org.walter.app.repository.fund.FundAccountRepository;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.repository.AclUserRepository;
import org.walter.base.service.MultiTenantContextHolder;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
	@Autowired
	private AclUserRepository aclUserRepository;
	@Autowired
	private FundAccountRepository fundAccountRepository;

	@Test
	public void contextLoads() {
		JpaAclUser jpaAclUser = aclUserRepository.findByUsernameOrMobile("0009785");
		log.info(">>>>>> jpaAclUser: {}", jpaAclUser);
	}

	@Test
	public void testMultiTenant(){
		MultiTenantContextHolder.initUserTenantId("A");

		JpaAclUser jpaAclUser = aclUserRepository.findByUsernameOrMobile("0009785");
		log.info(">>>>>> jpaAclUser: {}", jpaAclUser);

		//TODO 此处有问题，执行时好像找了默认数据源，导致没有结果返回
		List<JpaFundAccount> jpaFundAccounts = fundAccountRepository.findAll();
		for (JpaFundAccount jpaFundAccount : jpaFundAccounts) {
			log.info(">>>>>> jpaFundAccount: {}", jpaFundAccount);
		}
	}
}
