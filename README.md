# Процедура запуска автотестов
# 1. Клонируем репозиторий
- открываем дипломный проект на `GutHub` `https://github.com/TaisiaKos/QADiploma2`;
- копируем ссылку на репозиторий;
- для клонирования репозитория на локальную машину, выполняем в терминали команду `git clone git@github.com:TaisiaKos/QADiploma2.git`;
- открываем проект в `Intelligent Idea`.

# 2. Настройка проекта
- установка `Docker`;
- установка, запуск и сборка контейнеров командой `docker-compose up --build` в терминале;
- запуск веб-сервиса:
1. Для postgresql
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`.
1. Для для mysql 
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`

# 3. Запуск автотестов
1. Для postgresql
`./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`.
1. Для mysql 
`./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`.

# 4. Получение отчета
Для автоматической генерации отчета, нужно ввести в терминали команду `./gradlew allureServe`.

# 5. Завершение работы
Для завершения работы нужно:
Остановить работу веб-приложения - для этого в терминали его запуска вводим команду `Ctrl + C`.
Остановить работу и удалить контейнеры -для этого в терминали их запуска вводим команду `Ctrl + C`. Затем в той же терминали вводим команду `docker-compose down`. Далее, удаляем папку `data` из проекта.