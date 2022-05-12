FROM openjdk:14
VOLUME /tmp
ADD ./target/transactions-0.0.1-SNAPSHOT.jar api-transactions.jar
ENTRYPOINT ["java","-jar","/api-transactions.jar"]