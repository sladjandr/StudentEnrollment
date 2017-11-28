var app = angular.module('studentEnrollmentApp.loginController', []);

app.controller('loginController', function($rootScope, $http, $scope, jwtHelper) {
	
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
				////////////////////////////////
				var token = localStorage.getItem('jwt_token');
				var payload = jwtHelper.decodeToken(token);
				console.log(payload);
				console.log(payload.user_name);
				console.log(payload.authorities);
				///////////////////
				$http.get('api/user/username/' + $scope.credentials.username)
				.then(function (response) {
					$scope.user = data;
					localStorage.setItem('userId', $scope.user.id);
					$rootScope.userId = localStorage.getItem('userId');
					if ($scope.user.role=='STUDENT') {
						window.location = "/#!/schedule";
					}else{
						window.location = "/#!/exams"
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


