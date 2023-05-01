FROM clinisys/base-core:8-jre-alpine-curl

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /app.jar --thin.root=/m2

ENV PROFILE=prod

ARG JAR_FILE
ADD m2 m2

HEALTHCHECK --start-period=20m CMD curl -f http://localhost/gestion-access-core/health || exit 1

ADD target/${JAR_FILE} app.jar



