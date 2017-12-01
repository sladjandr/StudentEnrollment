var app = angular.module('studentEnrollmentApp.rankListController', []);

app.controller('rankListController', function($scope, $http, $routeParams, $window) {
	
	$scope.show = {};
	$scope.show.year = localStorage.getItem('loggedInStudentYear'); 
	
	var studentId = localStorage.getItem('loggedInStudentId'); 
	
	$scope.getWishes = function() {
        $http.get('api/studyprogram/student/' + studentId)
			.then(function (response) {
				$scope.studentStudyPrograms = response.data;
			})
			.catch(function (response){
				alert('Error getting wishes!')
			});
    };
	
	$scope.getStudyProgram = function() {
        $http.get('api/studyprogram/' + $routeParams.studyProgramId + '/student/' + studentId)
			.then(function (response) {
				if (!response.data){
					alert('Rank list has not been determined yet!')
					$window.location.href = "#!/ranklists";
				}
				$scope.studyProgram = response.data;
			})
			.catch(function (response){
				alert('Error getting study program!')
			});
    };
	
});


