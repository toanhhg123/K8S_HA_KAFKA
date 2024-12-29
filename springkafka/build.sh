TAG=$(openssl rand -base64 6 | tr -dc 'a-zA-Z0-9')

echo "TAG: $TAG"

mvn clean install

docker build -t toanhhg123/spring-kafka:$TAG .

docker push toanhhg123/spring-kafka:$TAG