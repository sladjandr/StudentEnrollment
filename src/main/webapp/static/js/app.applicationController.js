var app = angular.module('studentEnrollmentApp.applicationController', []);

app.controller('applicationController', function($scope, authService) {
	
	$scope.isLoggedIn = authService.isLoggedIn;
					
	$scope.isAdmin = authService.isAdmin;
					
	$scope.isStudent = authService.isStudent;

	$scope.logout = authService.logout;
	
});


