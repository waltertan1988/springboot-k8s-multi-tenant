package org.walter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.walter.base.entity.JpaAclUser;
import org.walter.base.repository.AclUserRepository;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
	@Autowired
	private AclUserRepository aclUserRepository;

	@Test
	public void contextLoads() {
		JpaAclUser jpaAclUser = aclUserRepository.findByUsernameOrMobile("0009785");
		log.info(">>>>>> jpaAclUser: {}", jpaAclUser);
	}

}
