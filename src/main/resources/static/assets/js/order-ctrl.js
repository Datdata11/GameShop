app.controller("ord-ctrl", function($scope, $http) {
	let host = "http://localhost:8080/elise/rest/orders"
	$scope.orders = []
	$scope.statusMap = [
        {key: "queueing", value: "Chưa xử lý"},
        {key: "delivering", value: "Đang giao"},
        {key: "completed", value: "Hoàn thành"},
        {key: "cancelled", value: "Đã hủy"}
    ]
	$scope.ordForm = {}
	// lấy dữ liệu về bảng
	$scope.initialize = function() {
		$http.get(host).then(resp => {
			$scope.orders = resp.data
			$scope.orders.forEach(o => {
				o.orderDate = new Date(o.orderDate)
			})
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	// đưa dữ liệu lên form
	$scope.edit = function(item) {
		$scope.ordForm = angular.copy(item)
	}
	// hủy thay đổi form
	$scope.reset = function() {
		var id = $scope.ordForm.id
		var index = $scope.orders.findIndex(o => o.id == id)
		$scope.ordForm = angular.copy($scope.orders[index])
	}
	// cập nhật
	$scope.update = function() {
		var item = angular.copy($scope.ordForm)
		item.total = $scope.totalAmount(item)
		$http.put(`${host}/${item.id}`, item).then(resp => {
			var index = $scope.orders.findIndex(o => o.id == item.id)
			$scope.orders[index] = item
			alert("Update success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Update error")
			console.log("Error", error)
		})
	}
	// xóa
	$scope.delete = function(item) {
		$http.delete(`${host}/${item.id}`).then(resp => {
			var index = $scope.orders.findIndex(o => o.id == item.id)
			$scope.orders.splice(index, 1)
			$scope.ordForm = {}
			alert("Delete success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Delete error")
			console.log("Error", error)
		})
	}
	$scope.deleteDetail = function(detail){
		var index = $scope.ordForm.details.findIndex(d => d.id == detail.id)
		$scope.ordForm.details.splice(index, 1)
	}
	// tính tổng số lượng
	$scope.totalQty = function(item){
		var length = item.details.length
		var sum = 0
		for(var i = 0; i < length; i++){
			sum += item.details[i].quantity
		}
		return sum
	}
	// tính tổng số tiền
	$scope.totalAmount = function(item){
		var length = item.details.length
		var sum = 0
		for(var i = 0; i < length; i++){
			sum += (item.details[i].quantity * item.details[i].product.price)
		}
		return sum >= 1500000 ? sum : (sum + 20000)
	}
	// phân trang
	$scope.pager = {
		page: 0,
		size: 10,
		get orders() {
			var start = this.page * this.size
			return $scope.orders.slice(start, start + this.size)
		},
		get count() {
			return Math.ceil($scope.orders.length * 1.0 / this.size)
		},
		first() {
			this.page = 0
		},
		prev() {
			this.page--
			if (this.page < 0) {
				this.last()
			}
		},
		next() {
			this.page++
			if (this.page >= this.count) {
				this.first()
			}
		},
		last() {
			this.page = this.count() - 1
		}
	}
	$scope.initialize()
})