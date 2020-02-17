package org.walter.app.controller.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.walter.app.controller.BaseController;
import org.walter.app.entity.product.JpaProductSpu;
import org.walter.app.repository.product.ProductSpuRepository;
import org.walter.app.service.product.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
//@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductSpuRepository productSpuRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/listObject")
    public List<Object> listObject(){
        List<Object> list = new ArrayList<>();
        list.addAll(listProductSpu());
        list.addAll(listAclUser());
        return list;
    }

    @GetMapping("/addProductSpu")
    public String addProductSpu(HttpServletRequest request) {
        String spuSeq = UUID.randomUUID().toString().replaceAll("\\-", "");
        String name = "商品" + spuSeq;
        Boolean isFail = Boolean.valueOf(request.getParameter("fail"));
        productService.addProductSpu(spuSeq, name, isFail);
        return "success";
    }

    private List<JpaProductSpu> listProductSpu() {
        List<JpaProductSpu> jpaProductSpus = productSpuRepository.findAll();
        for (JpaProductSpu jpaProductSpu : jpaProductSpus) {
            log.info(">>>>>> jpaProductSpu: {}", jpaProductSpu);
        }
        return jpaProductSpus;
    }
}
