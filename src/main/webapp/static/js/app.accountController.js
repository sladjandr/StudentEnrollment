var app = angular.module('studentEnrollmentApp.accountController', []);

app.controller('accountController', function($scope, jwtHelper, $http, $routeParams, $window) {
	
	var userId = localStorage.getItem('userId');
	
	$scope.pass = "";
	$scope.repeat = "";
	
	$scope.changePassword = function() {
        $http.put('api/user/' + userId, $scope.pass)
		.then(function (response) {
			alert('Password successfully changed!')
		})
		.catch(function (response){
			console.log(response.status);
			alert('Unexpected error occured while changing password!')
		});
    };

});


