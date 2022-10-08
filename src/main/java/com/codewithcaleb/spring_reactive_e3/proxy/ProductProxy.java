package com.codewithcaleb.spring_reactive_e3.proxy;

import com.codewithcaleb.spring_reactive_e3.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ProductProxy {
    private final WebClient webClient;

    public ProductProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    //calling the other service in flux fashion
    public Flux<Product> getAll(){
       return webClient.get().uri("/products").exchangeToFlux
               (res -> res.bodyToFlux(Product.class));
    }
}
