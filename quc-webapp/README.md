# README #

Para rodar a aplicação:

> npm ci *(este comando usa o package-lock.json para garantir que as dependências dos ambientes serão as mesmas, caso não sejam, o comando terá uma saída de erro)*

> npm run start *(este comando faz o build e sobe o lite server, um servidor local que expõe a pasta dist/quc-webapp em https, mais detalhes abaixo)*

É importante rodar pelo npm run start pois está configurado esse comando no package.json para o ng cli fazer o build concorrentemente com o lite server que vai subir o servidor local através do lite server. O lite server foi utilizado para subir a aplicação localmente no protocolo https. Para alterar alguma configuração do lite server, olhar o arquivo bs-config.js.

Acessar o recurso https da api no browser anteriormente para "liberar o acesso" é necessário.

O build da aplicação fica em dist/quc-webapp, verificar sempre se o bs-config está apontando para o path correto.

Ao acessar a página vai ser redirecionado para a autenticação utilizando as credencias do azure.

## Tabela com a versão dos componentes  utilizados nesse projeto

Component | Version
---------- | ---------
Angular | 11.2.6
Angular CLI | 11.2.5
Node | 10.16.0
NPM | 6.12.0
typescript | 4.0.2

## Tabela com a versão de compatibilidade para auxiliar na construção de ambientes

Angular CLI version	| Angular version	| Node.js version	| TypeScript version
------------------- | --------------- | --------------- | ------------------
N/A	|	2.x	|	6.0.x or later minor version	|	2.0.x
1.0.6	| 4.0.x/4.1.x	|	6.9.x or later minor version	|	2.2.x
1.1.3	| 4.0.x/4.1.x	|	6.9.x or later minor version	|	2.3.x
1.2.7	| 4.0.x/4.1.x	|	6.9.x or later minor version	|	2.3.x
1.3.2	| 4.2.x/4.3.x/4.4.x	|	6.9.x or later minor version	|	2.4.x
1.4.10	| 4.2.x/4.3.x/4.4.x	|	6.9.x/8.9.x or later minor version	|	2.4.x
1.5.6	| 5.0.x/5.1.x	|	6.9.x/8.9.x or later minor version	|	2.4.x/2.5.x
1.6.7	| 5.2.x	|	6.9.x/8.9.x or later minor version	|	2.5.x
1.7.4	|	5.2.x	|	6.9.x/8.9.x or later minor version	|	2.5.x
6.0.8	|	6.0.x	|	8.9.x or later minor version	|	2.7.x
6.1.5	|	6.1.x	|	8.9.x or later minor version	|	2.7.x
6.2.9	|	6.1.x	|	8.9.x or later minor version	|	2.9.x
7.0.7	|	7.0.x	|	8.9.x/10.9.x or later minor version	|	3.1.x
7.1.4	|	7.1.x	|	8.9.x/10.9.x or later minor version	|	3.1.x
7.2.4	|	7.2.x	|	8.9.x/10.9.x or later minor version	|	3.2.x
7.3.9	|	7.2.x	|	8.9.x/10.9.x or later minor version	|	3.2.x
8.0.6	|	8.0.x	|	10.9.x or later minor version	|	3.4.x
8.1.3	|	8.1.x	|	10.9.x or later minor version	|	3.4.x
8.2.2	|	8.2.x	|	10.9.x or later minor version	|	3.4.x
8.3.29	|	8.2.x	|	10.9.x or later minor version	|	3.5.x
9.0.7	|	9.0.x	|	10.13.x/12.11.x or later minor version	|	3.6.x/3.7.x
9.1.x	|	9.1.x	|	10.13.x/12.11.x or later minor version	|	3.6.x/3.7.x/3.8.x
10.0.8	|	10.0.x	|	10.13.x/12.11.x or later minor version	|	3.9.x
10.1.7	|	10.1.x	|	10.13.x/12.11.x or later minor version	|	3.9.x/4.0.x
10.2.x	|	10.2.x	|	10.13.x/12.11.x or later minor version	|	3.9.x/4.0.x
11.0.7	|	11.0.x	|	10.13.x/12.11.x or later minor version	|	4.0.x
11.1.4	|	11.1.x	|	10.13.x/12.11.x or later minor version	|	4.0.x/4.1.x
11.2.x	|	11.2.x	|	10.13.x/12.11.x or later minor version	|	4.0.x/4.1.x
12.0.x	|	12.0.x	|	12.13.x/14.15.x or later minor version	|	4.2.x
