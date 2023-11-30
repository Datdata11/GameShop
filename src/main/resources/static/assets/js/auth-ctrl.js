app.controller("auth-ctrl", function($scope, $http) {
	var url = "http://localhost:8080/elise/rest/authorities";
	$scope.initialize = function() {
		$http.get(url).then(resp => {
			$scope.db = resp.data;
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}

	$scope.index_of = function(username, role) {
		return $scope.db.authorities.findIndex(a => a.account.username == username && a.role.id == role)
	}

	$scope.update = function(username, role) {
		var index = $scope.index_of(username, role);
		if (index >= 0) {
			var id = $scope.db.authorities[index].id;
			$http.delete(`${url}/${id}`).then(resp => {
				$scope.db.authorities.splice(index, 1);
				console.log("Success", resp)
			}).catch(error => {
				console.log("Error", error)
			})
		} else {
			var authority = {
				account: { username: username },
				role: { id: role }
			};
			$http.post(url, authority).then(resp => {
				$scope.db.authorities.push(resp.data);
				console.log("Success", resp)
			}).catch(error => {
				console.log("Error", error)
			})
		}
	}
	$scope.initialize()
})