var app = angular.module('studentEnrollmentApp.scheduleController', []);

app.controller('scheduleController', function($scope, $http, $routeParams, $window) {
	
	$scope.getStudentExams = function() {
        $http.get('api/examstudent/student/1')
			.then(function (response) {
				$scope.studentExams = response.data;
			})
			.catch(function (response){
				alert('Error getting student exams!')
			});
    };
	
});


