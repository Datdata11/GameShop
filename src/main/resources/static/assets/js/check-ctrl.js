const myApp = angular.module("my-app", []);
myApp.controller("cart-ctrl", function($scope, $http, $rootScope) {

	$scope.order = {
		orderDate: new Date(),
		status: "queueing",
		payment: "COD",
		account: {
			username: $("#username").text()
		},
		get details() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					quantity: item.quantity
				}
			})
		},
		purchase() {
			var order = angular.copy(this)

			$http.post("/elise/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công")
				$scope.cart.clear()
				location.href = "/elise/account/order-history"
				console.log(resp)
			}).catch(error => {
				alert("Đặt hàng lỗi")
				console.log(error)
			})
		}
	}


})