# Simple Country API Wrapper

## Description

This project is a simple Java with Spring boot projects that wraps a few functionalities of [REST Countries API](https://gitlab.com/restcountries/restcountries) project.

Some of the key features of this project are 
- API first design
- REST endpoints
- Endpoints documentation
- Dockerized
- Cache utilization


## Table of Contents

- [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Steps](#steps)
- [Testing](#testing)
- [Run with Docker-Compose](#run-with-docker-compose)
- [Usage](#usage)
    - [Swagger](#swagger)
    - [Endpoints](#endpoints)
        - [GET /api/v1/population-density](#get-apiv1population-density)
        - [GET /api/v1/most-bordering/{region}](#get-apiv1most-borderingregion)
- [License](#license)

## Installation

### Prerequisites

* JDK 11+
* Docker (Optional - It is needed only if you want to run an image on container)

### Instructions

1. Clone the repository
2. Cd to the root directory
3. Run `./mvnw clean install` in the root directory
4. Run `./mvnw spring-boot:run` in the root directory
5. To see SwaggerUI, open `http://localhost:8080/swagger-ui/index.html` in your browser
6. To see the api doc in json format, use `http://localhost:8080/v3/api-docs` 

## Testing

1. Clone the repository
2. Cd to the root directory
3. Run `./mvnw clean install` in the root directory
4. Run `./mvnw test` in the root directory

## Run with Docker

1. Clone the repository
2. Cd to the root directory
3. Run `docker build -t sample/countryapi .`
4. Run `docker run -p 8080:8080 sample/countryapi`
5. To see SwaggerUI, open `http://localhost:8080/swagger-ui/index.html` in your browser
6. To see the api doc in json format, use `http://localhost:8080/v3/api-docs`

## Usage

### OpenApi

OpenApi is used to document the API. It can be accessed at `http://localhost:8080/swagger-ui/index.html`.

### Endpoints

#### GET /countries
Gets the list of countries from the external API with no filters

example:

```
curl -X 'GET' \
  'http://localhost:8080/countries' \
  -H 'accept: application/json'
```

#### GET /countries/population-density

Gets countries sorted by population density in descending order.

example:

```
curl -X 'GET' \
  'http://localhost:8080/countries/population-density' \
  -H 'accept: application/json'
```

#### GET /countries/population-density?region={region}

Gets a country by region with most bordering countries of a different region.

example:

```
curl -X 'GET' \
  'http://localhost:8080/countries/country-with-most-bordering?region=Asia' \
  -H 'accept: application/json'
```

## License
[LICENSE](LICENSE)
