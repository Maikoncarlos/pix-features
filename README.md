# pix-features

O Pix é o arranjo de pagamentos e recebimentos instantâneos, disponível todos os dias do
ano, com liquidação em tempo real de suas transações. Ou seja, permite a transferência
imediata de valores entre diferentes instituições, em todos os horários e dias, entre pessoas
físicas, empresas ou entes governamentais.
O Pix é uma forma de pagar e receber valores.

## Índice

- [Sobre](#sobre)
- [Instalação](#instalação)
- [Uso](#uso)
- 
## Sobre

Implementação de algumas funcionalidades para que seja feita a criação, atualização, desativação e buscas personalidadas.
Trazendo com estas implementações um código clean e utilizando de boas práticas de Solid e Designer Pattern regras
especificas para cada cenário e valores passados na requisição de cada implementação.

Tecnologias Utilizadas:
- Java 17;
- Spring Boot 3;
- Spring JPA;
- MongoDB;
- Docker e Docker Compose;
- Mapstruct;
- Lombok;
- Junit 5;
- Mockito;

TODOs:
- Logs na aplicação;
- Documentação com Open API e Swagger;
- Verificar a necessidade de Cache;
- Verificar a necessidade de Retornos com Paginação;


## Instalação

Passos para configurar o ambiente e instalar o projeto localmente:

Subir imagem do mongodb e buildar o projeto
```bash
docker compose up -d
mvn spring-boot:run
```
Baixe e Instale o MongoDb Compass, link:
https://www.mongodb.com/pt-br/docs/compass/current/install/

### Comandos Importante para Docker e Docker Compose:

1 - Verificar qual versão e se está instalado em sua máquina:
```bash
$docker compose version
$docker version
```
2 - Para o container com a imagem:
```bash
$docker compose stop
$docker stop CONTAINER
```

3 - Verificar quais imagens estão rodando:
```bash
$docker compose ps
@docker ps
```

Clone o repositório:
```bash
git clone https://github.com/Maikoncarlos/pix-features.git
```