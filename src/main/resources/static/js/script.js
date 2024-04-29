function sendPutRequest(url, body) {
    // ������� ����� XMLHttpRequest ������
    var xhr = new XMLHttpRequest();
    alert("������ �����!");
    // ������������� ����� ������� � URL
    xhr.open("PUT", url, true);

    // ������������� ��������� Content-Type
    xhr.setRequestHeader("Content-Type", "application/json");

    // ������������� ���������� ������� ���������� �������
    xhr.onreadystatechange = function () {
        // ���������, ��� ������ �������� �������
        if (xhr.readyState === 4 && xhr.status === 200) {
            // �������� ��� �������� ���������� �������
            alert("������ �������� �������");
        } else {
            // �������� ��� ������
            alert("������ ��� ���������� �������");
        }
    };

    // ����������� ������ body � JSON ������ � ���������� �
    xhr.send(JSON.stringify(body));
}

// �������� ������ �� ������ "��������� ���������"
var saveButton = document.getElementById("save_button");

// ��������� ���������� ������� �� ���� �� ������
saveButton.addEventListener("click", function() {
    // �������� �������� �� ����� �����
    var surname = document.getElementById("surname").value;
    var name = document.getElementById("name").value;
    var patron = document.getElementById("patron").value;
    var email = document.getElementById("email").value;

    // ��������� ������ � �������
    var body = {
        firstName: name,
        surname: surname,
        patron: patron,
        email: email
    };

    // URL, ���� ������������ ������
    var parts = window.location.href.split("/");
    var id = parts[parts.length - 2];
    var url = "http://localhost:8080/api/users/update/" + id;

    // ���������� PUT ������
    sendPutRequest(url, body);
});