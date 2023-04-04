# Application medireportui

FROM eclipse-temurin:11-jre-alpine AS BUILD
#RUN mkdir /medireportui
WORKDIR ./medireportui
#COPY pom.xml .
#COPY src ./src
#RUN mvn package -DskipTests

EXPOSE 9090

COPY ./target/medireportui-0.0.1-SNAPSHOT.jar .
#COPY --from=build ./target/medireportui-0.0.1-SNAPSHOT.jar .

# Démarrage de l'application medireportui
CMD ["java","-jar", "./medireportui-0.0.1-SNAPSHOT.jar"]