# docker compose
go to /src/deploy/docker and do "./stack up"

# run native image
container image pull goafabric/observation-service-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)
"${(@z)${CRUNTIME:-docker run --pull always}}" --name observation-service-quarkus --rm -p 50700:50700 \
-e "quarkus.datasource.jdbc.url=jdbc:postgresql://$(container ls | grep postgres | awk '{print $6}' | cut -d/ -f1):5432/postgres" -e 'quarkus.datasource.username=postgres' -e 'quarkus.datasource.password=postgres' goafabric/observation-service-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)