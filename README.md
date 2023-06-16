1) Приложение работает на tomcat 10
2) Перед запуском приложения выполнить:
    * docker-compose up -d для запуска контейнера с БД
    * выполнить sql-скрипт init-database.sql
3) В приложении реализованы следующие связи:
    * Category -> Product (OneToMany)
    * Product -> Characteristic (ManyToMany)
4) приложении реализованы следующие эндпоинты:
    * GET /categories - получить список категорий
    * GET /categories/{id} - получить категорию по id
    * POST /categories - добавить категорию
    * GET /products - получить список товаров
    * GET /products/{id} - получить товар по id
    * POST /products - добавить товар
    * Все описанные эндпоинты находялся в коллекции webapp-servlets-task.postman_collection.json
