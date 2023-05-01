FROM clinisys/base-core:8-jre-alpine
ENV PROFILE=prod
ADD *.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /app.jar