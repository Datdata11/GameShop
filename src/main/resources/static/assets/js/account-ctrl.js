app.controller("acc-ctrl", function($scope, $http, $rootScope) {
	let host = "http://localhost:8080/elise/rest/accounts"
	$scope.accounts = []
	$scope.accForm = {}
	// lấy dữ liệu về bảng
	$scope.initialize = function() {
		$http.get(host).then(resp => {
			$scope.accounts = resp.data
			$scope.accForm = {
				role: false
			}
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	// đưa dữ liệu lên form
	$scope.edit = function(data) {
		$scope.accForm = angular.copy(data)
	}
	// xóa form
	$scope.reset = function() {
		$scope.accForm = {
			role: false
		}
	}
	// tạo mới
	$scope.create = function() {
		var data = angular.copy($scope.accForm)
		$http.post(host, data).then(resp => {
			$scope.accounts.push(resp.data)
			$scope.reset()
			alert("Insert success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Insert error")
			console.log("Error", error)
		})
	}
	// cập nhật
	$scope.update = function() {
		var data = angular.copy($scope.accForm)
		$http.put(`${host}/${data.username}`, data).then(resp => {
			var index = $scope.accounts.findIndex(a => a.username == data.username)
			$scope.accounts[index] = data
			alert("Update success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Update error")
			console.log("Error", error)
		})
	}
	// xóa
	$scope.delete = function(data) {
		$http.delete(`${host}/${data.username}`).then(resp => {
			var index = $scope.accounts.findIndex(a => a.username == data.username)
			$scope.accounts.splice(index, 1)
			$scope.reset()
			alert("Delete success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Delete error")
			console.log("Error", error)
		})
	}
	// phân trang
	$scope.pager = {
		page: 0,
		size: 10,
		get accounts() {
			var start = this.page * this.size
			return $scope.accounts.slice(start, start + this.size)
		},
		get count() {
			return Math.ceil($scope.accounts.length * 1.0 / this.size)
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
	$scope.reset()
})