app.controller("rpt-ctrl", function($scope, $http) {
	let host = "http://localhost:8080/elise/rest/reports"
	$scope.soldProducts = []
	$scope.customers = []
	
	// lấy dữ liệu về
	$scope.initialize = function() {
		$http.get(`${host}/sold-products`).then(resp => {
			$scope.soldProducts = resp.data
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
		$http.get(`${host}/customers`).then(resp => {
			$scope.customers = resp.data
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	
	// tính tổng số doanh thu theo sản phẩm
	$scope.soldAmount = function(product){
		return product.price * product.totalSold
	}
	
	$scope.initialize()
})