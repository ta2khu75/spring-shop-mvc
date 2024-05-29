<div style="height: 80vh;">
    <canvas id="myChart"></canvas>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var jsonString = '<c:out value="${data}"/>'
    var jsonWithQuotes = jsonString.replace(/&#034;/g, '"');
    var ordersData = JSON.parse(jsonWithQuotes);
    console.log(ordersData);
    const ctx = document.getElementById('myChart');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ordersData.map(order => order[0])
            ,
            datasets: [{
                label: 'My First Dataset',
                data: ordersData.map(order => order[1]),
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(25, 99, 132)',
                    'rgb(54, 16, 235)',
                    'rgb(255, 205, 8)'
                ],
                borderWidth: 4,
                hoverOffset: 4
            }]
        }
    })
</script>