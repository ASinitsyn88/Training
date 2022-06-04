см. курс https://www.youtube.com/watch?v=7uxROJ1nduk

-- Отправка логина и пароля для получения токена
POST http://localhost:8080/api/v1/auth/login
{
	"email":"user@mail.com",
	"password":"user"
}

-- Получение информации о разработчиках
GET http://localhost:8080/api/v1/developers
HEADERS:
Authorization: вставить токен, полученный в api/v1/auth/login