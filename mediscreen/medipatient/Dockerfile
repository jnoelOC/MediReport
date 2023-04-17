# Application medipatient

FROM eclipse-temurin:11-jre-alpine AS BUILD
RUN mkdir medipatient
WORKDIR medipatient

EXPOSE 9190

COPY target/medipatient-*.jar medipatient.jar

# Démarrage de medipatient
CMD ["java","-jar", "./medipatient.jar"]