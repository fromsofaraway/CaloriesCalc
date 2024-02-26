document.addEventListener('DOMContentLoaded', function() {
    const prevDateBtn = document.getElementById('prevDate');
    const nextDateBtn = document.getElementById('nextDate');
    const dateInput = document.getElementById('date');

    // Функция для обновления таблицы данными
    function updateTableForDate(date) {
        fetch(`/api/calculate/1?date=${date}`, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(intakeDto => {
                // Очищаем текущие строки таблицы, кроме заголовка
                const table = document.querySelector('table tbody');
                table.innerHTML = '<tr><th>Date</th><th>Product</th><th>Quantity</th><th>Fats</th><th>Proteins</th><th>Carbs</th><th>Calories</th></tr>';

                // Предположим, что `intakeDto.products` содержит массив продуктов для дня
                intakeDto.products.forEach(item => {
                    const row = `<tr>
                            <td>${item.date}</td> <!-- или item.date, если оно возвращается для каждого продукта отдельно -->
                            <td>${item.product}</td>
                            <td>${item.quantity}</td>
                            <td>${item.fats}</td>
                            <td>${item.proteins}</td>
                            <td>${item.carbs}</td>
                            <td>${item.calories}</td>
                         </tr>`;
                    table.innerHTML += row;
                });
            })
            .catch(error => console.error('Error fetching data:', error));
    }


    // Функция для изменения даты
    function changeDate(days) {
        const currentDate = new Date(dateInput.value);
        currentDate.setDate(currentDate.getDate() + days);
        dateInput.valueAsDate = currentDate;
        updateTableForDate(dateInput.value);
    }

    // Обработчики событий для стрелок
    prevDateBtn.addEventListener('click', function() {
        changeDate(-1);
    });

    nextDateBtn.addEventListener('click', function() {
        changeDate(1);
    });

    // Обработчик события изменения даты
    dateInput.addEventListener('change', function() {
        updateTableForDate(dateInput.value);
    });

    // Инициализация таблицы для текущей даты
    if (!dateInput.value) {
        dateInput.valueAsDate = new Date();
    }
    updateTableForDate(dateInput.value);
});
