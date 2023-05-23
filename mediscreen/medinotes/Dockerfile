# Application medinotes

FROM eclipse-temurin:11-jre-alpine AS BUILD
RUN mkdir /data
RUN cd /
RUN mkdir /data/db
RUN cd /
RUN mkdir medinotes
WORKDIR medinotes

EXPOSE 9290

COPY target/medinotes-*.jar medinotes.jar

# Démarrage de medinotes
CMD ["java","-jar", "./medinotes.jar"]