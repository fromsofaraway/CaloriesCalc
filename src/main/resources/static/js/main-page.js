function setupModal(modalId, btnId) {
    var modal = document.getElementById(modalId);
    var btn = document.getElementById(btnId);
    var span = modal.getElementsByClassName("close")[0];

    btn.onclick = function () {
        modal.style.display = "block";
    }

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

}

function clearRegistrationForm() {
    document.getElementById('regUsername').value = '';
    document.getElementById('regPassword').value = '';
}

function clearForm(username, password) {
    document.getElementById(username).value = '';
    document.getElementById(password).value = '';
}

function checkColor(response) {
    return response.status >= 400 && response.status < 500 ?
        "linear-gradient(to right, #ff5f5f, #ff5f5f)" : // Красный для 4xx ошибок
        "linear-gradient(to right, #00b09b, #96c93d)"; // Зеленый для всех остальных ответов
}


document.addEventListener("DOMContentLoaded", function () {
    setupModal("loginModal", "loginBtn");
    setupModal("registerModal", "registerBtn");
});

document.getElementById('registerForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы

    var username = document.getElementById('regUsername').value;
    var password = document.getElementById('regPassword').value;

    var registerDto = {
        username: username,
        password: password
    };


    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerDto)
    })
        .then(response => {
            var toastBackgroundColor = checkColor(response); // Используем функцию для проверки цвета
            response.json().then(data => {
                Toastify({
                    text: data.message, // Сообщение с сервера
                    duration: 3000, // Время отображения в мс
                    close: true, // Показать кнопку закрытия
                    gravity: "top", // "top" или "bottom"
                    position: "right", // "left", "center", "right"
                    backgroundColor: toastBackgroundColor, // Цвет фона, возвращенный функцией
                }).showToast();

                if (response.ok) {
                    clearRegistrationForm(); // Очистить форму после регистрации
                }
            });
        })
        .catch(error => {
            document.getElementById('registerErrorMessage').style.display = 'block';
            document.getElementById('registerErrorMessage').textContent = 'Registration failed: ' + error.message;
            document.getElementById('registerSuccessMessage').style.display = 'none';
            console.error('Error:', error);
        });
});

// document.getElementById('loginForm').addEventListener('submit', function(event) {
//
//     event.preventDefault();
//     var username = document.getElementById('username').value;
//     var password = document.getElementById('password').value;
//
//     var loginDto = {
//         username: username,
//         password: password
//     };
//
//     fetch('/login', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(loginDto)
//     })
//         .then(response => {
//             if (!response.ok) {
//                 response.json().then(data => {
//                     Toastify({
//                         text: data.message || "Login failed",
//                         duration: 3000,
//                         close: true,
//                         gravity: "top",
//                         position: "right",
//                         backgroundColor: "linear-gradient(to right, #ff5f5f, #ff5f5f)",
//                     }).showToast();
//                 });
//             } else {
//                 window.location.href = "/calc";
//
//             }
//         })
//         .catch(error => {
//             // Обработка ошибок сети
//             Toastify({
//                 text: "Network error: " + error.message,
//                 duration: 3000,
//                 close: true,
//                 gravity: "top",
//                 position: "right",
//                 backgroundColor: "linear-gradient(to right, #ff5f5f, #ff5f5f)",
//             }).showToast();
//         });
// });


document.getElementById('loginForm').addEventListener('submit', function (event) {

    event.preventDefault();
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('password', password);

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData.toString()
    })
        .then(response => {
            if (!response.ok) {
                response.json().then(data => {
                    Toastify({
                        text: data.message || "Login failed",
                        duration: 3000,
                        close: true,
                        gravity: "top",
                        position: "right",
                        backgroundColor: "linear-gradient(to right, #ff5f5f, #ff5f5f)",
                    }).showToast();
                });
            } else {
                    Toastify({
                        text: "Login successful",
                        duration: 3000,
                        close: true,
                        gravity: "top",
                        position: "right",
                        backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
                    }).showToast();

                    setTimeout(() => {
                        window.location.href = "/calc"
                    }, 1000);

            }
        })
        .catch(error => {
            // Обработка ошибок сети
            Toastify({
                text: "Network error: " + error.message,
                duration: 3000,
                close: true,
                gravity: "top",
                position: "right",
                backgroundColor: "linear-gradient(to right, #ff5f5f, #ff5f5f)",
            }).showToast();
        });
});
