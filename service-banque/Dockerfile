FROM maven:3-openjdk-11 AS BUILDER
WORKDIR /app
COPY . .
RUN mvn -e -B -DskipTests clean package

FROM openjdk:11
COPY --from=BUILDER /app/target/service-banque-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD ["java","-jar","service-banque-0.0.1-SNAPSHOT.jar"]
