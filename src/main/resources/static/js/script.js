function sendPutRequest(url, body) {
    // Создаем новый XMLHttpRequest объект
    var xhr = new XMLHttpRequest();
    alert("Запрос начат!");
    // Устанавливаем метод запроса и URL
    xhr.open("PUT", url, true);

    // Устанавливаем заголовок Content-Type
    xhr.setRequestHeader("Content-Type", "application/json");

    // Устанавливаем обработчик события завершения запроса
    xhr.onreadystatechange = function () {
        // Проверяем, что запрос завершен успешно
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Действия при успешном завершении запроса
            alert("Запрос выполнен успешно");
        } else {
            // Действия при ошибке
            alert("Ошибка при выполнении запроса");
        }
    };

    // Преобразуем объект body в JSON строку и отправляем её
    xhr.send(JSON.stringify(body));
}

// Получаем ссылку на кнопку "Сохранить изменения"
var saveButton = document.getElementById("save_button");

// Добавляем обработчик события на клик по кнопке
saveButton.addEventListener("click", function() {
    // Получаем значения из полей ввода
    var surname = document.getElementById("surname").value;
    var name = document.getElementById("name").value;
    var patron = document.getElementById("patron").value;
    var email = document.getElementById("email").value;

    // Формируем объект с данными
    var body = {
        firstName: name,
        surname: surname,
        patron: patron,
        email: email
    };

    // URL, куда отправляется запрос
    var parts = window.location.href.split("/");
    var id = parts[parts.length - 2];
    var url = "http://localhost:8080/api/users/update/" + id;

    // Отправляем PUT запрос
    sendPutRequest(url, body);
});