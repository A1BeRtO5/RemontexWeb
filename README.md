# RemontexWeb - Service Center Management System

Це веб-застосунок (CRUD) для управління замовленнями в сервісному центрі з ремонту техніки.
Проєкт розроблений з використанням тришарової архітектури.

## 🛠 Технології
-   **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate
-   **Database:** PostgreSQL (запускається через Docker)
-   **Frontend:** HTML, Thymeleaf
-   **Інструменти:** Maven, Docker, Lombok

## ⚙️ Функціонал
-   Створення, перегляд, редагування та видалення замовлень (CRUD).
-   Зміна статусу замовлення (в обробці, виконано, скасовано).
-   Збереження даних у реляційній базі даних.

## 📋 Управління завданнями та помилками
-   **Jira Board:** [RemontexWeb KAN Board](https://makshlbch.atlassian.net/jira/software/projects/KAN/boards/1)

## 🚀 Як запустити
1. Склонуйте репозиторій.
2. Запустіть базу даних: `docker-compose up -d`
3. Запустіть проєкт через Maven: `mvn spring-boot:run`
4. Відкрийте у браузері: `http://localhost:8080`