app.controller("rpt-ctrl", function($scope, $http) {
	let host = "http://localhost:8080/elise/rest/reports"
	$scope.soldProducts = []
	$scope.customers = []

	// lấy dữ liệu về
	$scope.initialize = function() {
		var date = new Date()
		$scope.thisMonth = date.getFullYear() + "-" + (date.getMonth() + 1)
		$scope.selectedMonth = new Date($scope.thisMonth)
		$scope.selectedMonth2 = new Date($scope.thisMonth)
		$http.get(`${host}/sold-products`).then(resp => {
			$scope.soldProducts = resp.data
			$scope.monthFilterProducts = $scope.soldProducts.filter(item => item.month == ($scope.selectedMonth.getMonth() + 1)
				&& item.year == $scope.selectedMonth.getFullYear())
			console.log("Success", resp.data)
		}).catch(error => {
			location.href = "http://localhost:8080/auth/access/denied"
			console.log("Error", error)
		})
		$http.get(`${host}/customers`).then(resp => {
			$scope.customers = resp.data
			$scope.monthFilterCustomers = $scope.customers.filter(item => item.month == ($scope.selectedMonth2.getMonth() + 1)
				&& item.year == $scope.selectedMonth2.getFullYear())
			console.log("Success", resp.data)
		}).catch(error => {
			location.href = "http://localhost:8080/auth/access/denied"
			console.log("Error", error)
		})
	}

	// tính tổng số doanh thu theo sản phẩm
	$scope.soldAmount = function(product) {
		return product.price * product.totalQty
	}

	$scope.initialize()

	$scope.change_month_product = function() {
		$scope.monthFilterProducts = $scope.soldProducts.filter(item => item.month == ($scope.selectedMonth.getMonth() + 1)
			&& item.year == $scope.selectedMonth.getFullYear())
	}

	$scope.change_month_customer = function() {
		$scope.monthFilterCustomers = $scope.customers.filter(item => item.month == ($scope.selectedMonth2.getMonth() + 1)
			&& item.year == $scope.selectedMonth2.getFullYear())
	}

	$scope.exportToExcel = function(tableId) {
		var fileName = ""
		if (tableId.includes("Product")) {
			fileName = "Revenue by Products _ " + ($scope.selectedMonth.getMonth() + 1) + "-" + $scope.selectedMonth.getFullYear()
		} else {
			fileName = "Revenue by Customers _ " + ($scope.selectedMonth2.getMonth() + 1) + "-" + $scope.selectedMonth2.getFullYear()
		}
		const table = document.getElementById(tableId)
		const workbook = new ExcelJS.Workbook()
		const worksheet = workbook.addWorksheet(fileName)

		const headerRow = worksheet.addRow([])
		const headerCells = table.getElementsByTagName("th")
		for (let i = 0; i < headerCells.length; i++) {
			headerRow.getCell(i + 1).value = headerCells[i].innerText// đổi màu header
			headerRow.getCell(i + 1).fill = {
				type: 'pattern',
				pattern: 'solid',
				fgColor: { argb: '009900' }
			}
			headerRow.getCell(i + 1).font = {
				color: { argb: "FFFFFF" },
				bold: true,
			}
		}

		const rows = table.getElementsByTagName("tr")
		for (let i = 0; i < rows.length; i++) {
			const cells = rows[i].getElementsByTagName("td")
			const rowData = []
			for (let j = 0; j < cells.length; j++) {
				rowData.push(cells[j].innerText)
			}
			worksheet.addRow(rowData)
		}

		// chỉnh kích thước cột
		worksheet.columns.forEach((column) => {
			let maxLength = 0;
			column["eachCell"]({ includeEmpty: true }, (cell) => {
				const columnLength = cell.value ? cell.value.toString().length + 3 : 10;
				if (cell.type === ExcelJS.ValueType.Date) {
					maxLength = 20;
				}
				else if (columnLength > maxLength) {
					maxLength = columnLength + 3;
				}
			});
			column.width = maxLength < 10 ? 10 : maxLength;
		})

		workbook.xlsx.writeBuffer().then((buffer) => {
			const blob = new Blob([buffer], {
				type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
			})
			saveAs(blob, fileName)
		})
	}

})