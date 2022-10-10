**WebClient**

```yaml

It is the class used to make calls to other services in reactive applications.


```

**Proxy Package**

```yaml


The Proxy is where you make calls to the other service via web client.


```


**Error Handling**

```yaml

We should always be aware that as microservices communicate with each other,they may fail.

(Your proxy that communicates to the other microservice may not be able to reach it,


Important Methods,


(a)OnErrorResume--->It is the Method that is called incase i am not able to make webClient
Calls,

In a real world application the onError Resume can even make a Call to an entirely different
Service.

The OnError Resume method is overloaded thrice,


return webClient.get().uri("/products").exchangeToFlux
(res -> res.bodyToFlux(Product.class))
//  .onErrorResume(e -> Flux.just(p));
//  .onErrorResume(WebClientRequestException.class,e -> Flux.just(p));
//.onErrorResume(e->e.getMessage() == null, e->Flux.just(p));


(a).onErrorResume(e -> Flux.just(p))

Will handle everything when you are not able to reach to the Proxy.


(b).onErrorResume(WebClientRequestException.class,e -> Flux.just(p));

It can help me handle the specific exception that has been thrown.

It therefore means i can be able to have multiple onError resume and handle the 
right kind of exception on each.

(e)The third one takes in a Predicate which is any set of predefined conditions,

onErrorResume(e->e.getMessage() == null, e->Flux.just(p))

Means i can throw an error based on the exception that has been raised.

N/B

It is more recommended that we use the second and the third approach.


(b)onErrorReturn(p)

Its is an alternative method we can use in place of onErrorResume,

The advantage of on error return is that it is able to create a Publisher on our behalf.

Disadvantage of on error return is that we do not have access to the exception,unlike
on error resume,

return webClient.get().uri("/products").exchangeToFlux
(res -> res.bodyToFlux(Product.class))
//  .onErrorResume(e -> Flux.just(p));
//  .onErrorResume(WebClientRequestException.class,e -> Flux.just(p));
// .onErrorResume(e->e.getMessage() == null, e->Flux.just(p));
.onErrorReturn(p);


It also implements/is overloaded with the 2 other methods,


Never Raise Exceptions generally but handle each specifically.


```