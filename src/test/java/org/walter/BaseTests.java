package org.walter;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.walter.base.service.InitTenantIdInterceptor;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public abstract class BaseTests {
    @Autowired
    private InitTenantIdInterceptor initTenantIdInterceptor;

    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    protected void initUserTenantId(String tenantId){
        try {
            Mockito.when(request.getParameter(Mockito.anyString())).thenReturn(tenantId);
            initTenantIdInterceptor.preHandle(request, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
