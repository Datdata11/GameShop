const app = angular.module("admin-app", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider
		.when("/collection", {
			templateUrl: "/assets/manager/collection.html",
			controller: "clt-ctrl"
		})
		.when("/product", {
			templateUrl: "/assets/manager/product.html",
			controller: "prd-ctrl"
		})
		.when("/account", {
			templateUrl: "/assets/manager/account.html",
			controller: "acc-ctrl"
		})
		.when("/report", {
			templateUrl: "/assets/manager/report.html",
			controller: "rpt-ctrl"
		})
		.when("/authorize", {
			templateUrl: "/assets/manager/authorize.html",
			controller: "auth-ctrl"
		})
		.when("/order", {
			templateUrl: "/assets/manager/order.html",
			controller: "ord-ctrl"
		})
		.otherwise({ redirectTo: "/collection" })
})
app.run(function($http, $rootScope) {
	$http.get(`http://localhost:8080/elise/rest/authentication`).then(resp => {
		if (resp.data) {
			$auth = $rootScope.$auth = resp.data;
			$http.defaults.headers.common["Authorization"] = $auth.token;
		}
	});
})