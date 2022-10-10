package com.codewithcaleb.spring_reactive_e3.proxy;

import com.codewithcaleb.spring_reactive_e3.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;

@Component
public class ProductProxy {
    private final WebClient webClient;

    public ProductProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    //calling the other service in flux fashion
    public Flux<Product> getAll(){

        //creating default products to be called incase my Service is down
        var p = new  Product();
        p.setName("Default");

       return webClient.get().uri("/products").exchangeToFlux
               (res -> res.bodyToFlux(Product.class))
              //  .onErrorResume(e -> Flux.just(p));
              //  .onErrorResume(WebClientRequestException.class,e -> Flux.just(p));
              // .onErrorResume(e->e.getMessage() == null, e->Flux.just(p));
              // .onErrorReturn(p);
              // .onErrorReturn(WebClientRequestException.class,p)
              .onErrorReturn(e-> e.getMessage() == null,p);


    }

}
