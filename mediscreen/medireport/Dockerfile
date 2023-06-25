# Application medireport

FROM eclipse-temurin:11-jre-alpine AS BUILD
RUN mkdir medireport
WORKDIR medireport

EXPOSE 9390

COPY target/medireport-*.jar medireport.jar

# Démarrage de l'application medireport
CMD ["java","-jar", "./medireport.jar"]