var app = angular.module('studentEnrollmentApp.scheduleController', []);

app.controller('scheduleController', function($scope, $rootScope, $http, $routeParams, $window, authService) {
	
	$scope.getStudentExams = function() {
        $http.get('api/examstudent/student/1') //will be replaced with student logged in
			.then(function (response) {
				$scope.studentExams = response.data;
			})
			.catch(function (response){
				alert('Error getting student exams!')
			});
    };
	
	$scope.dateString = function(d) {
		if (d==null){
			return "";
		}
		var nd = new Date(d);
		stringDate = nd.toUTCString();
		stringDate = stringDate.slice(0, -3);
		return stringDate;
	}
	
});


