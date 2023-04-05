# API CADASTRO_FUNIONARIO

## Rotas

*ADMIN*

###
GET http://localhost:8080/api/admin/buscar/{{cpf}}

###
GET http://localhost:8080/api/admin/listar

###
POST http://localhost:8080/api/admin/cadastro

###
POST http://localhost:8080/api/admin/cadastro/lote

###
PUT http://localhost:8080/api/admin/editar/{{cpf}}

###
DELETE http://localhost:8080/api/admin/excluir/{{cpf}}

###

*Empresa*

###
GET http://localhost:8080/api/empresa/listar

###
GET http://localhost:8080/api/empresa/buscar/{{cnpj}}

###
GET http://localhost:8080/api/empresa/buscar/funcionarios/{{cnpj}}

###
POST http://localhost:8080/api/empresa/cadastro

###
POST http://localhost:8080/api/empresa/cadastro/lote

###
DELETE http://localhost:8080/api/empresa/excluir/{{cnpj}}

###

*Funcionario*

###
GET http://localhost:8080/api/funcionario/listar

###
GET http://localhost:8080/api/funcionario/buscar/{{cpf}}

###
POST http://localhost:8080/api/funcionario/cadastro/{{cnpj}}

###
POST http://localhost:8080/api/funcionario/cadastro/lote/{{cnpj}}

###
PUT http://localhost:8080/api/funcionario/editar/{{cpf}}

###
DELETE http://localhost:8080/api/funcionario/excluir/{{cpf}}

###

*Login*

###
GET http://localhost:8080/api/login/logout

###
POST http://localhost:8080/api/login

###
POST http://localhost:8080/api/login/cadastro

###

*Relatorio mensalr*

###
GET http://localhost:8080/api/relatorio/mensal/{{cpf}}

###
GET http://localhost:8080/api/relatorio/mensal/gerarExcel/{{cpf}}

###
POST http://localhost:8080/api/relatorio/mensal


###
DOCKER

Metricas, rodando docker compose 

```$ docker-compose up -d ```

*Grafana*

* usuario admin
* senha admin
localhost:3000

*Prometheus*

* localhost:9090