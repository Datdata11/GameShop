const myApp = angular.module("my-app", []);
myApp.controller("cart-ctrl", function($scope, $http, $rootScope) {
	$scope.cart = {
		items: [],
		//thêm sp vào giỏ
		add(id) {
			var item = this.items.find(item => item.id == id)
			if (item) {
				item.quantity++
				this.saveToLocalStorage()
			} else {
				$http.get(`/elise/rest/products/${id}`).then(resp => {
					resp.data.quantity = 1
					this.items.push(resp.data)
					this.saveToLocalStorage()
				})
			}
		},
		// xóa sp khỏi giỏ
		remove(id) {
			var index = this.items.findIndex(item => item.id == id)
			items.splice(index, 1)
			this.saveToLocalStorage()
		},
		// xóa tất cả sp
		clear() {
			this.items = []
			this.saveToLocalStorage()
		},
		// tính thành tiền của 1 sp
		amount_of(item) { },
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


})