package org.walter.base.service;

public class MultiTenantContextHolder {

    private MultiTenantContextHolder(){};

    private static final ThreadLocal<String> contextTenantId = new ThreadLocal<>();

    private static final ThreadLocal<String> userTenantId = new ThreadLocal<>();

    /**
     * 初始化用户态下的租户ID
     * @param userTenantId
     */
    static void initUserTenantId(String userTenantId){
        MultiTenantContextHolder.userTenantId.set(userTenantId);
    }

    /**
     * 获取当前上下文的租户ID（可以是默认态或用户态）
     * @return
     */
    public static String getContextTenantId(){
        return contextTenantId.get();
    }

    /**
     * 切换当前上下文的租户ID为默认态
     */
    public static void switchToDefaultTenant(){
        contextTenantId.remove();
    }

    /**
     * 切换当前上下文的租户ID为用户态
     */
    public static void switchToUserTenant(){
        contextTenantId.set(userTenantId.get());
    }
}
