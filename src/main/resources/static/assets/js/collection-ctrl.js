app.controller("clt-ctrl", function($scope, $http, $rootScope) {
	let host = "http://localhost:8080/elise/rest/collections"
	$scope.collections = []
	$scope.cltForm = {}
	// lấy dữ liệu về bảng
	$scope.initialize = function() {
		$http.get(host).then(resp => {
			$scope.collections = resp.data
			$scope.collections.forEach(c => {
				c.createDate = new Date(c.createDate)
			})
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	// đưa dữ liệu lên form
	$scope.edit = function(item) {
		$scope.cltForm = angular.copy(item)
	}
	// xóa form
	$scope.reset = function() {
		$scope.cltForm = {
			createDate: new Date(),
			banners: [{ name: "default-placeholder.png" }, { name: "default-placeholder.png" }, { name: "default-placeholder.png" }]
		}
	}
	// tạo mới
	$scope.create = function(){
		var item = angular.copy($scope.cltForm)
		$http.post(host, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.collections.push(resp.data)
			$scope.reset()
			alert("Insert success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Insert error")
			console.log("Error", error)
		})
	}
	// cập nhật
	$scope.update = function(){
		var item = angular.copy($scope.cltForm)
		$http.put(`${host}/${item.id}`, item).then(resp => {
			var index = $scope.collections.findIndex(c => c.id == item.id)
			$scope.collections[index] = item
			alert("Update success")
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Update error")
			console.log("Error", error)
		})
	}
	// xóa
	$scope.delete = function(item){
		$http.delete(`${host}/${item.id}`).then(resp => {
			var index = $scope.collections.findIndex(c => c.id == item.id)
			$scope.collections.splice(index, 1)
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
		$http.post("/elise/rest/upload/banners", data, {
			transformRequest: angular.identity,
			headers: { "Content-Type": undefined }
		}).then(resp => {
			$scope.cltForm.banners[index].name = resp.data.name
			console.log("Success", resp.data)
		}).catch(error => {
			alert("Image upload error")
			console.log("Error", error)
		})
	}
	// đổi mảng thành chuỗi
	$rootScope.toString = function(arr) {
		var str = arr[0].name
		for (var i = 1; i < arr.length; i++) {
			str = str + ", " + arr[i].name
		}
		return str;
	}
	// phân trang
	$scope.pager = {
		page: 0,
		size: 10,
		get collections(){
			var start = this.page * this.size
			return $scope.collections.slice(start, start + this.size)
		},
		get count(){
			return Math.ceil($scope.collections.length * 1.0 / this.size)
		}, 
		first(){
			this.page = 0
		},
		prev(){
			this.page--
			if(this.page < 0){
				this.last()
			}
		},
		next(){
			this.page++
			if(this.page >= this.count){
				this.first()
			}
		},
		last(){
			this.page = this.count() - 1
		}
	}
	$scope.reset()
	$scope.initialize()
})