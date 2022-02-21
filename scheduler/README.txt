Projecto da Poc do scheduler + inicialização pelo docker-compose

SpringTask e ScheduledFuture
+
Docker-Compose --> e subir DB, projecto e ELK

Notas:
se quiser correr os endpoints tirar a anotação da classe SchedulerApplication a anotação @ComponentScan(basePackages = "com.scheduler.scheduledExecuter")


Comandos para inicilizar pelo docker

-->Se fizer alguma alteração fazer o mvn clean package para gerar um novo jar

comando docker:
docker-compose build & docker-compose up


problemas?
eliminar containers, volumes e imagens
docker container rm $(docker ps -a -q) --force
docker image prune --all
docker volume prune

Links:
- kibana: http://localhost:5601/app/kibana
- SWAPI: https://swapi.dev/
- Tutorial: https://cassiomolin.com/2019/06/30/log-aggregation-with-spring-boot-elastic-stack-and-docker/

