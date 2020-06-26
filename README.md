## Teste Digito Unico Banco Inter
### Versões e tecnologias
#### Obrigatórias
1. Java 1.8 (ou posterior) [Jdk](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).
2. Mavem 3.6 (ou posterior) [Maven](https://maven.apache.org/download.cgi).
3. SpringBoot 2.3.1 [SpringBoot](https://start.spring.io/).
3. Banco de dados H2 (o mesmo sera iniciado pela aplicação) acesso pelo link [Banco de dados](localhost:8080/h2).
#### Portas das Apis no Back-end
- ###### Api de Digito Unico - 8080 (teste-banco-inter)
#### Como rodar a aplicação
###### Acesse a raiz do projeto 
1. Pode-se executar o comando mvn spring-boot:run que sera executado o projeto 
2. Ou mvn install para gerar um .jar que ficara disponivel na pasta target, sendo assim pode-se executar o comando java -jar target/BancoInter-0.0.1-SNAPSHOT.jar
3. Ou direto de sua ide
4. Caso queira rodar os testes basta dar o comando mvn test
#### O json do postam se encontra na raiz do projeto
#### para acessar a documentação  
##### Foi ultilizado o OpenApi 1.3.9 , mas existe uma arquivo de configuração para o swagger puro caso queira futuramente efetuar a troca
1. Verssão grafica do swagger [swagger](http://localhost:8080/swagger).
2. Para acessar o json com os dados [json](http://localhost:8080/api-docs).
3. Caso queira baixar o json foramtado em yml [yml](http://localhost:8080/api-docs.yaml).
###### Observaçoes
1. Apenas se criptografa dados de usuarios no banco com id que foi salva
2. Sera salvo o calculo do Digito se hover id de usuario associado 