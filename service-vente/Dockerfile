FROM maven:3-openjdk-11 AS BUILDER
WORKDIR /app
COPY . .
RUN mvn -e -B -DskipTests clean package

FROM openjdk:11
COPY --from=BUILDER /app/target/service-vente-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","service-vente-0.0.1-SNAPSHOT.jar"]
