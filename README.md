<p align="center">
<br>
  <img src="quc-webapp/src/assets/quc-logo.png" alt="Quero Um Carro" width="490">
<br>
<br>
  
  <img src="https://img.shields.io/badge/node-v10.16.0-green"> 
  <img src="https://img.shields.io/badge/typescript-v4.0.2-green">
  <img src="https://img.shields.io/badge/angular-v11.2.6-green">
  <img src="https://img.shields.io/badge/angularCLI-v11.2.6-green">
  <img src="https://img.shields.io/badge/java-v1.8-green">
  <img src="https://img.shields.io/badge/springboot-v2.5.1-green">
  <img src="https://img.shields.io/badge/mongoDB-v4.4.6-green">
</p>

<p align="center">QueroUmCarro [QUC] é um sistema de aluguel de carros, usado como exemplo para implementação da stack angular, springboot e mongodb utilizando swagger, javadoc, logs teste integrados e teste unitários.</p>

<hr>

# 📦 Como buildar o projeto

## 📝 Arquivos de configuração

Para o projeto webapp o aquivo de configuracao que será utilizado se encontra no diretorio: quc-webapp/src/environments/.

Para a base de dados o script de inicializacão se encontra na pasta: quc-db/mongo-init.js

Para o projeto da api o arquivo de configuracão se encontra na pasta: quc-api/src/main/resources/application.properties

## 🛠 Build dos projetos

Execute o comando abaixo em um terminal para gerar o artefato jar da api dentro do diretório _quc-api_.

```
mvn clean install 
```

Esse comando executará os testes também do projeto java. Após concluído execute o comando abaixo na pasta _quc-webapp_.

```
npm run build
```

Volte para a pasta raíz do projeto, nela encontrará um arquivo docker-compose.yml que pode ser utilizado para buildar o projeto através de containers docker, incluindo a base de dados. Execute o comando abaixo para criar os containers:

```
docker-compose -f .\docker-compose.yml -d --build
```

## 🖥️ Webapp

O projeto será exposto na url: http://localhost:23000/

## ⚙️ API

A API será exposta na url: http://localhost:24000

## 🗃️ Mongo Express

O projeto expõe o mongo express no recurso: http://localhost:8081/db/quc_db/. Com ele você poderá consultar a base de dados através da interface http.

## 🔎 Considerações

### 🤝 CrossOrigin

Os recursos da API estão com crossOrigin para localhost:23000, para a comunicação entre cliente e api funcionar, contudo localhost em crossOrigin não é a melhor abordagem, para deploy em produção o ideal é que se altere.

### 🔐 Autenticação

Nesse projeto não foi utilizado o oauth como autenticação e autorização. Em um projeto que precisa de segunrança o ideal é que seja implementado spring security na api e uma abordagem seria jwt para comunicação segura entre frontend e backend.
