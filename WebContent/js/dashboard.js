var app = angular.module("app", []);
app.controller('ctrl', function ($scope, $http) {
			$scope.username;
			$scope.carInfo;
			$scope.serviceInfo;
			$scope.showServiceEntry = "";
			$scope.dashboard_view = true;
			$scope.car_view = false;
			$scope.selectedCar;
			$scope.selectedService;
			$scope.car_edit_disable = true;
			$scope.car_inserting = false;
		    $scope.reverse = true;
		    $scope.propertyName = 'type';
			$scope.service_inserting = false;
			$scope.service_editing = false;	

			$scope.getUsername = function() {
				$http({
					method: "GET",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?type=username"
				}).then(function (response) {
					$scope.username = response.data;

					console.log(response);
					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});
			}

			$scope.getUsername();

			setTimeout(function () {
			$scope.sortBy = function(propertyName) {
				$scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
				$scope.propertyName = propertyName;
			};

			$scope.open_car_view = function (el) {
				$scope.dashboard_view = false;
				$scope.car_view = true;
				$scope.selectedCar = el;
			};

			$scope.car_view_cancel = function () {
				$scope.car_view = false;
				$scope.dashboard_view = true;
				$scope.car_edit_disable = true;
			};

			$scope.car_edit_save = function () {
				$scope.car_view = true;
				$scope.dashboard_view = false;
				$scope.car_edit_disable = true;
				$scope.saveCarData();
			};

			$scope.service_view_cancel = function () {
				
				$scope.service_editing = false;
				$scope.service_inserting = false;
				$scope.dashboard_view = true;
			};

			$scope.service_edit_save = function () {
				$scope.service_editing = false;
				$scope.dashboard_view = true;
				$scope.saveServiceData();
			};

			$scope.car_edit = function () {
				$scope.car_edit_disable = false;
			};

			$scope.insert_new_car = function() {
				$scope.selectedCar = {make: "",
					mileage: 0,
					model: "",
					nickName: "",
					username: $scope.username,
					vin: "",
					year: ""};				
				$scope.dashboard_view = false;
				$scope.car_inserting = true;
			};

			$scope.insert_new_car = function() {
				$scope.selectedCar = {make: "",
					mileage: 0,
					model: "",
					nickName: "",
					username: $scope.username,
					vin: "",
					year: ""};				
				$scope.dashboard_view = false;
				$scope.car_inserting = true;
			};

			$scope.insert_new_service = function(vinNum) {
				$scope.selectedService = {
					type: "",
					shop: "",
					cost: "",
					date: "",
					username: $scope.username,
					vin: vinNum,
				};				
				$scope.dashboard_view = false;
				$scope.service_inserting = true;
				$scope.service_editing = false;
			};
			
			$scope.car_insert_cancel = function() {
				$scope.dashboard_view = true;
				$scope.car_inserting = false;	
				$scope.car_view = false;
			};

			$scope.service_insert_cancel = function() {
				$scope.dashboard_view = true;
				$scope.service_inserting = false;
				$scope.service_editing = false;	
			};

			$scope.confirm_insertion = function() {
				$scope.dashboard_view = true;
				$scope.car_inserting = false;				
				setTimeout($scope.insertCarData(), 100);
				$scope.getConnection();
			};

			$scope.confirm_service_insertion = function() {
				$scope.dashboard_view = true;
				$scope.service_inserting = false;	
				$scope.service_editing = false;			
				setTimeout($scope.insertServiceData(), 100);
				$scope.getConnection();
			};


			$scope.getConnection = function () {
				$scope.getCarData();
				$scope.getServiceData();
			};

			$scope.setServiceVisible = function (selectedVin) {
				if ($scope.showServiceEntry != selectedVin) {
					$scope.showServiceEntry = selectedVin;
				} else {
					$scope.showServiceEntry = "";
				}
			}

			$scope.editServiceEntry = function(data) {
				$scope.service_editing = true;
				$scope.dashboard_view = false;
				console.log("editing service");
				$scope.selectedService = data;
				console.log("selectedService");
				console.log($scope.selectedService);
			}

			$scope.getCarData = function () {
				$http({
					method: "GET",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?username=" + $scope.username + "&type=car"
				}).then(function (response) {
					$scope.carInfo = response;

					console.log(response);
					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});
			}

			$scope.saveCarData = function () {
				$http({
					method: "POST",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?type=updateC",
					data: $scope.selectedCar
				}).then(function (response) {

					console.log("post result:")
					console.log(response);
					$scope.getConnection();

					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});

				console.log($scope.selectedService);		

			}

			$scope.saveServiceData = function () {
				$http({
					method: "POST",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?type=updateS",
					data: $scope.selectedService
				}).then(function (response) {

					console.log("post result:")
					console.log(response);
					$scope.getConnection();

					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});		

				console.log($scope.selectedService);		
			};


			$scope.insertCarData = function () {
				$http({
					method: "POST",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?type=insertC",
					data: $scope.selectedCar
				}).then(function (response) {

					console.log("post result:")
					console.log(response);
					$scope.getConnection();

					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});

			}

			$scope.insertServiceData = function() {
				$http({
					method: "POST",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?type=insertS",
					data: $scope.selectedService
				}).then(function (response) {

					console.log("post result:")
					console.log(response);
					$scope.getConnection();				

					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});

			}

			$scope.getServiceData = function () {
				$http({
					method: "GET",
					url: "http://localhost:8080/MotorMinder/DashboardServlet?username=" + $scope.username + "&type=service"
				}).then(function (response) {
					$scope.serviceInfo = response;

					console.log(response);
					return response;
				}, function (err) {
					console.log("error: ")
					console.log(err);
				});
			}

			$scope.getConnection();
		}, 1000);

});