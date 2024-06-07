# ProgramTech Users Microservice

## Описание

ProgramTech Users Microservice - это микросервис для управления пользователями, предназначенный для использования в архитектуре микросервисов. Проект построен с использованием Spring Boot, JPA, Hibernate и PostgreSQL.

## Функциональные возможности

- Создание пользователя
- Получение информации о пользователе по ID
- Обновление информации о пользователе
- Удаление и восстановление пользователя
- Получение списка всех пользователей с пагинацией
- Подсчет количества пользователей

## Технологии

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Maven
- Lombok

## Требования

- Java 21
- Docker и Docker Compose
- PostgreSQL

## Установка

1. Клонируйте репозиторий:
    ```sh
    git clone https://github.com/DaddyCalcifer/ProgramTech_UsersMicroservice.git
    cd ProgramTech_UsersMicroservice
    ```

2. Соберите проект с помощью Maven:
    ```sh
    mvn clean install
    ```

3. Запустите контейнеры Docker:
    ```sh
    docker-compose up --build
    ```

## Использование

### API Эндпоинты

- **Создание пользователя**
    ```http
    POST /api/users/add
    Content-Type: application/json
    {
      "firstName": "John",
      "surname": "Doe",
      "lastName": "Smith",
      "email": "john.doe@example.com",
      "passwordHash": "hashed_password",
      "role": 1
    }
    ```

- **Получение информации о пользователе по ID**
    ```http
    GET /api/users/{id}
    ```

- **Получение списка всех пользователей с пагинацией**
    ```http
    GET /api/users?page=0&size=10
    ```

- **Обновление информации о пользователе**
    ```http
    PUT /api/users/update/{id}
    Content-Type: application/json
    {
      "firstName": "John",
      "surname": "Doe",
      "lastName": "Smith",
      "email": "john.doe@example.com",
      "passwordHash": "new_hashed_password",
      "role": 1
    }
    ```

- **Удаление пользователя**
    ```http
    PATCH /api/users/delete/{id}
    ```

- **Восстановление пользователя**
    ```http
    PATCH /api/users/recover/{id}
    ```

- **Подсчет количества пользователей**
    ```http
    GET /api/users/count
    ```

## Тестирование

Проект включает юнит-тесты для контроллеров. Тесты находятся в каталоге `src/test/java`.

Для запуска тестов используйте Maven:
```sh
mvn test
