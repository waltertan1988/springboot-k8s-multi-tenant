package org.walter.base.configuration;

import com.google.common.collect.Maps;

import javax.sql.DataSource;
import java.util.Map;

public class TenantDataSourceMapHolder {

    private TenantDataSourceMapHolder(){}

    private static Map<String, Map<String, DataSource>> tenantDataSourceMap = Maps.newHashMap();

    public static Map<String, DataSource> get(String dataSourceId){
        return tenantDataSourceMap.get(dataSourceId);
    }

    static void put(String dataSourceId, String tenantId, DataSource dataSource){
        if(!tenantDataSourceMap.containsKey(dataSourceId)){
            tenantDataSourceMap.put(dataSourceId, Maps.newConcurrentMap());
        }
        tenantDataSourceMap.get(dataSourceId).put(tenantId, dataSource);
    }
}
