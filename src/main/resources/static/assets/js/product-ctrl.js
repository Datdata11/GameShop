app.controller("prd-ctrl", function($scope, $http, $rootScope) {
	let host = "http://localhost:8080/elise/rest/products"
	$scope.products = []
	$scope.collections = []
	$scope.prdForm = {}
	// lấy dữ liệu về bảng
	$scope.initialize = function() {
		$http.get(host).then(resp => {
			$scope.products = resp.data
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
		$http.get("/elise/rest/collections").then(resp => {
			$scope.collections = resp.data
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	// đưa dữ liệu lên form
	$scope.edit = function(item) {
		$scope.prdForm = angular.copy(item)
	}
	// xóa form
	$scope.reset = function() {
		$scope.prdForm = {
			images: [
			{ name: "default-placeholder.png" }, 
			{ name: "default-placeholder.png" }, 
			{ name: "default-placeholder.png" }, 
			{ name: "default-placeholder.png" }]
		}
	}
	// tạo mới
	$scope.create = function() {
		var item = angular.copy($scope.prdForm)
		$http.post(host, item).then(resp => {
			$scope.products.push(resp.data)
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
		var item = angular.copy($scope.prdForm)
		$http.put(`${host}/${item.id}`, item).then(resp => {
			var index = $scope.products.findIndex(p => p.id == item.id)
			$scope.products[index] = item
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
			var index = $scope.products.findIndex(p => p.id == item.id)
			$scope.products.splice(index, 1)
			$scope.reset()
			alert("Delete success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Delete error")
			console.log("Error", error)
		})
	}
	// upload ảnh
	$scope.imageChange = function(files, i) {
		var data = new FormData()
		var index = Number.parseInt(i)
		data.append("file", files[0])
		$http.post("/elise/rest/upload/images", data, {
			transformRequest: angular.identity,
			headers: { "Content-Type": undefined }
		}).then(resp => {
			$scope.prdForm.images[index].name = resp.data.name
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Image upload error")
			console.log("Error", error)
		})
	}
	// phân trang
	$scope.pager = {
		page: 0,
		size: 10,
		get products() {
			var start = this.page * this.size
			return $scope.products.slice(start, start + this.size)
		},
		get count() {
			return Math.ceil($scope.products.length * 1.0 / this.size)
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