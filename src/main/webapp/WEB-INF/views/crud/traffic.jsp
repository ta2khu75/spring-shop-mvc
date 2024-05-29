<div>
    <canvas id="myChart"></canvas> 
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var jsonString = '<c:out value="${data}"/>'
    var jsonWithQuotes = jsonString.replace(/&#034;/g, '"');
    var ordersData = JSON.parse(jsonWithQuotes);
    console.log(ordersData);
    var ctx = document.getElementById("myChart").getContext("2d");

    var myChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: ordersData.map(order => order[0]+"/"+order[1]+"/"+order[2]), // Use formatted date strings
            datasets: [{
                label: "Total Money",
                data: ordersData.map(order => order[3]),
                lineTension: 0,
                backgroundColor: "transparent",
                borderColor: "#007bff",
                borderWidth: 4,
                pointBackgroundColor: "#007bff",
            }],
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false,
                    },
                }],
            },
            legend: {
                display: true,
            },
        },
    });
</script>