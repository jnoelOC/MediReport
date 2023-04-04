# Application medipatient

FROM eclipse-temurin:11-jre-alpine AS BUILD
#RUN mkdir /medipatient
WORKDIR ./medipatient
#COPY pom.xml .
#COPY src ./src
#RUN mvn package -DskipTests

EXPOSE 9190

COPY ./target/medipatient-0.0.1-SNAPSHOT.jar .

# Démarrage de l'application medipatient
CMD ["java","-jar", "./medipatient-0.0.1-SNAPSHOT.jar"]