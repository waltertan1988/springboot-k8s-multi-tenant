
检测 服务是否已启动
http://localhost:7081/ping

返回 默认数据源的数据和多租户数据源数据（A）的并集
http://localhost:7081/fund/listObject?tenantId=A

不同租户（A和B），同类型数据源（fund）的写事务回滚
http://localhost:7081/fund/deposit?fail=true&tenantId=A
http://localhost:7081/fund/deposit?fail=true&tenantId=B

不同租户（A和B），同类型数据源（fund）数据源的写事务提交
http://localhost:7081/fund/deposit?fail=false&tenantId=A
http://localhost:7081/fund/deposit?fail=false&tenantId=B

同一租户（A）不同数据源类型（product）的事务回滚
http://localhost:7081/product/addProductSpu?fail=true&tenantId=A

同一租户（A）不同数据源类型（product）的事务提交
http://localhost:7081/product/addProductSpu?fail=false&tenantId=A
