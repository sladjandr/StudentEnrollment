var app = angular.module('studentEnrollmentApp.loginController', []);

app.controller('loginController', function($rootScope, $http, $scope, jwtHelper, authService) {
	
	$scope.credentials = {};
	
	
	$scope.login = function() {
		var param = "Basic " + btoa("trusted-app:secret");
		var data = { "username": $scope.credentials.username, "password": $scope.credentials.password, "grant_type" : "password"};
		var config = { headers: { "Authorization": param }};
	
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/oauth/token',	
			headers: {"Authorization": param },
			data: data,
			success: function(response){ 
				var base64Url = response.access_token.split('.')[1];
				var base64 = base64Url.replace('-', '+').replace('_', '/');
				
				var token = "Bearer " + response.access_token;
				$http.defaults.headers.common.Authorization = token;
				localStorage.setItem('jwt_token', response.access_token);
				$http.get('api/user/username/' + $scope.credentials.username)
				.then(function (response) {
					$http.get('api/user/username/' + $rootScope.username)
						.then(function (response) {
							localStorage.setItem('userId', response.data.id);
						})
						.catch(function (response){
							alert('Error getting user by username!')
						});
					if (authService.isAdmin()) {
						window.location = "/#!/exams"
					}else{
						$http.get('api/student/username/' + $rootScope.username)
						.then(function (response) {
							localStorage.setItem('loggedInStudentId', response.data.studentId);
							localStorage.setItem('loggedInStudentYear', response.data.year);
							window.location = "/#!/schedule";
						})
						.catch(function (response){
							alert('Error getting student by username!')
						});
					}
				})
				.catch(function (response){
					alert("Error!")
				});
			
			},
			error: function(response){ 
				alert("Bad credentials!")
				console.log("Bad credentials!");
			} 
		})
			
	};
	
});


