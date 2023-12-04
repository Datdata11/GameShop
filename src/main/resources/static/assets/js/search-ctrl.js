myApp.controller("search-ctrl", function($scope, $http) {
	$scope.searchResults = []
	$scope.getResult = function() {
		$http.get('http://localhost:8080/elise/search?keyword=' + $scope.key).then(resp => {
			$scope.searchResults = resp.data
			console.log("Success", resp.data)
		}).catch(error => {
			console.log("Error", error)
		})
	}
})