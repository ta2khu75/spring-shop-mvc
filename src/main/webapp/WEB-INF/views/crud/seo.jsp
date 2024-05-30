<div style="height: 80vh;" class="mb-5">
    <canvas id="product"></canvas>
    <h3>Sold Product Chart</h3>
</div>
<div style="height: 80vh;" class="mt-5">
    <canvas id="category"></canvas>
    <h3>Chart Of Product Types Sold</h3>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var jsonStringProduct = '<c:out value="${product}"/>'
    var jsonWithQuotesProduct = jsonStringProduct.replace(/&#034;/g, '"');
    var productData = JSON.parse(jsonWithQuotesProduct);
    console.log(productData);
    const productCtx = document.getElementById('product');
    new Chart(productCtx, {
        type: 'pie',
        data: {
            labels: productData.map(product => product[0])
            ,
            datasets: [{
                label: 'My First Dataset',
                data: productData.map(product => product[1]),
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

    var jsonStringCategory = '<c:out value="${category}"/>'
    var jsonWithQuotesCategory = jsonStringCategory.replace(/&#034;/g, '"');
    var categoryData = JSON.parse(jsonWithQuotesCategory);
    console.log(categoryData);
    const categoryCtx = document.getElementById('category');
    new Chart(categoryCtx, {
        type: 'pie',
        data: {
            labels: categoryData.map(category => category[0])
            ,
            datasets: [{
                label: 'My First Dataset',
                data: categoryData.map(category => category[1]),
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