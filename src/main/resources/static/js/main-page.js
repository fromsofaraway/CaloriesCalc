var modal = document.getElementById("loginModal");
var btn = document.getElementById("loginBtn");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

// document.getElementById("loginForm").addEventListener("submit", function(event) {
//     // event.preventDefault(); // Предотвращаем стандартное поведение формы (отправку)
//
//     // Здесь может быть код для проверки введенных данных, если нужно
//
//     // Редирект на другую страницу
//     // window.location.href = "/calc";
// });
