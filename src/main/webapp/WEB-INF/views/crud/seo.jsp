<div>
    <canvas id="myChart"></canvas>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var jsonString = '<c:out value="${data}"/>'
    var jsonWithQuotes = jsonString.replace(/&#034;/g, '"');
    var ordersData = JSON.parse(jsonWithQuotes);
    console.log(ordersData);
    const ctx = document.getElementById('myChart');
    const data = {
        labels: [
            'Red',
            'Blue',
            'Yellow'
        ],
        datasets: [{
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
        }]
    };
</script>