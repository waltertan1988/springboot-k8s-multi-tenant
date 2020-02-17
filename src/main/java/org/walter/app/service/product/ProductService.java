package org.walter.app.service.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walter.app.entity.product.JpaProductSpu;
import org.walter.app.repository.product.ProductSpuRepository;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductSpuRepository productSpuRepository;

    @Transactional(transactionManager = "productMultiTenantJpaTransactionManager", rollbackFor = Exception.class)
    public JpaProductSpu addProductSpu(String spuSeq, String name, Boolean isFail) {
        JpaProductSpu jpaProductSpu = new JpaProductSpu();
        jpaProductSpu.setSpuSeq(spuSeq);
        jpaProductSpu.setName(name);
        productSpuRepository.save(jpaProductSpu);
        if(isFail){
            log.info("故意制造一个异常", 1/0);
        }
        return jpaProductSpu;
    }
}
