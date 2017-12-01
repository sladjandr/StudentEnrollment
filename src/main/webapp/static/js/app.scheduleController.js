var app = angular.module('studentEnrollmentApp.scheduleController', []);

app.controller('scheduleController', function($scope, $http, $routeParams, $window) {
	
	var studentId = localStorage.getItem('loggedInStudentId');
	
	$scope.getStudentExams = function() {
        $http.get('api/examstudent/student/' + studentId)
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


