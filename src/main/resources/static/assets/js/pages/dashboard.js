$(document).ready(() => {
	// función para calcular y mostrar los porcentajes de aumento
	const updatePercentageIncreases = (currentProdCount, currentUserCount) => {
		const maxValue = 100;

		const prodPercentageIncrease = (currentProdCount / maxValue) * 100;
		const userPercentageIncrease = (currentUserCount / maxValue) * 100;

		$('#percentage-increase-products').text(prodPercentageIncrease.toFixed(2) + "%");
		$('#percentage-increase-users').text(userPercentageIncrease.toFixed(2) + "%");
		$('#count-products').text(currentProdCount);
		$('#count-users').text(currentUserCount);
	};

	// función para crear y renderizar el gráfico
	const createAndRenderChart = (data) => {
		new ApexCharts(document.querySelector("#reportsChart"), {
			series: data,
			chart: {
				height: 350,
				type: 'area',
				toolbar: {
					show: false
				},
			},
			markers: {
				size: 4
			},
			colors: ['#4154f1', '#2eca6a', '#ff771d'],
			fill: {
				type: "gradient",
				gradient: {
					shadeIntensity: 1,
					opacityFrom: 0.3,
					opacityTo: 0.4,
					stops: [0, 90, 100]
				}
			},
			dataLabels: {
				enabled: false
			},
			stroke: {
				curve: 'smooth',
				width: 2
			},
			xaxis: {
				type: 'datetime',
				categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
			},
			tooltip: {
				x: {
					format: 'dd/MM/yy HH:mm'
				},
			}
		}).render();
	};


	const getDashboardStadistics = () => {

		$.ajax({
			url: 'http://localhost:8088/MBS/api/dashboard/stadistics',
			dataType: 'json',
		}).done((data) => {

			const currentProdCount = data.countProds;
			const currentUserCount = data.countUsus;
			const lineChartData = data.lineChart;

			updatePercentageIncreases(currentProdCount, currentUserCount);
			createAndRenderChart(lineChartData);

		}).fail(() => {

			console.log('Error al obtener datos de la API');
		});
	};

	getDashboardStadistics();
});






