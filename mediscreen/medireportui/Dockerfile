# Application medireportui

FROM eclipse-temurin:11-jre-alpine AS BUILD
RUN mkdir medireportui
WORKDIR medireportui

EXPOSE 9090

COPY target/medireportui-*.jar medireportui.jar

# Démarrage de l'application medireportui
CMD ["java","-jar", "./medireportui.jar"]