FROM maven:3.6.3-ibmjava-8-alpine

EXPOSE 8080
COPY . /usr/app/Icecream-backend
WORKDIR /usr/app/Icecream-backend

RUN mvn clean compile package

ENTRYPOINT mvn spring-boot:run
