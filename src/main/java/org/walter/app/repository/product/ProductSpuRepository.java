package org.walter.app.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.walter.app.entity.product.JpaProductSpu;

public interface ProductSpuRepository extends JpaRepository<JpaProductSpu, Long>{
}
