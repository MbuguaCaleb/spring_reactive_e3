package com.codewithcaleb.spring_reactive_e3.service;


import com.codewithcaleb.spring_reactive_e3.model.Product;
import com.codewithcaleb.spring_reactive_e3.proxy.ProductProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductService {

    //service consumes myProxy
    private final ProductProxy productProxy;

    public ProductService(ProductProxy productProxy) {
        this.productProxy = productProxy;
    }


    public Flux<Product> getAll(){
        return productProxy.getAll();
    }
}
