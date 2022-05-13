# MENDEL TRANSACTIONS

## Version
![Version](https://badge.fury.io/gh/tterb%2FHyde.svg)


## Authors
- Mario Honorato

## How to run the API (docker)

El comando a ejecutar es el siguiente:

```
docker run -p 8080:8080 mhonorato84/mendel-transactions:latest
```
### Swagger
```
http://localhost:8080/swagger-ui/index.html
```

### Tests

El coverage logrado es del 80%, el comando para correr los mismos:

```shell 
./mvnw test  
```
### Miscelaneus
Para insertar una transacción sin parent_id (la primera por ejemplo), setear este campo con el valor 0

``` 
{
  "amount": 10,
  "parent_id": 0,
  "type": "Some Type"
}
```
