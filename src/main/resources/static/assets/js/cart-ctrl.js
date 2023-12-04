const myApp = angular.module("my-app", []);
myApp.run(function($http, $rootScope){
    $http.get(`http://localhost:8080/elise/rest/authentication`).then(resp => {
    	if(resp.data){
    		$auth = $rootScope.$auth = resp.data;
        	$http.defaults.headers.common["Authorization"] = $auth.token;
    	}
    });
})
myApp.controller("cart-ctrl", function($scope, $http, $rootScope) {

	var $cart = $scope.cart = {
		items: [],
		//thêm sp vào giỏ
		add(id) {
			var item = this.items.find(item => item.id == id)
			if (item) {
				item.quantity++
				this.saveToLocalStorage()
			} else {
				$http.get(`http://localhost:8080/elise/rest/products/${id}`).then(resp => {
					resp.data.quantity = 1
					this.items.push(resp.data)
					this.saveToLocalStorage()
				})
			}
		},
		// xóa sp khỏi giỏ
		remove(id) {
			var index = this.items.findIndex(item => item.id == id)
			this.items.splice(index, 1)
			this.saveToLocalStorage()
		},
		// xóa tất cả sp
		clear() {
			this.items = []
			this.saveToLocalStorage()
		},
		// tính tổng số lượng các sp trong giỏ
		get count() {
			return this.items.map(item => item.quantity).reduce((total, qty) => total += qty, 0)
		},
		// tổng thành tiền các mặt hàng trong giỏ
		get amount() {
			return this.items.map(item => item.quantity * item.price).reduce((total, amount) => total += amount, 0)
		},
		// tính phí ship
		get delivery() {
			return this.amount >= 1500000 ? 0 : 20000
		},
		// lưu giỏ hàng vào local store
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items))
			localStorage.setItem("cart", json)
		},
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart")
			this.items = json ? JSON.parse(json) : []
		}
	}

	$scope.cart.loadFromLocalStorage()

	$scope.order = {
		orderDate: new Date(),
		status: "queueing",
		payment: "COD",
		total: $cart.amount + $cart.delivery,
		get account() {
			return { username: $rootScope.$auth.account.username }
		},
		get details() {
			return $cart.items.map(item => {
				return {
					product: { id: item.id },
					quantity: item.quantity
				}
			})
		},
		purchase() {
			var order = angular.copy(this)

			$http.post("http://localhost:8080/elise/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công")
				$cart.clear()
				location.href = "http://localhost:8080/elise/account/order-history"
				console.log(resp)
			}).catch(error => {
				alert("Đặt hàng lỗi")
				console.log(error)
			})
		}
	}
		
	$scope.add_and_order = function(id){
		$cart.add(id)
		location.href = "http://localhost:8080/elise/account/checkout"
	}
})