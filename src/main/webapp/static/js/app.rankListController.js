var app = angular.module('studentEnrollmentApp.rankListController', []);

app.controller('rankListController', function($scope, $http, $routeParams, $window) {
	
	$scope.show = {};
	$scope.show.year = 2017; //this will be replaced with year from student logged in
	
	$scope.getWishes = function() {
        $http.get('api/studyprogram/student/1') //will be replaced with student logged in
			.then(function (response) {
				$scope.studentStudyPrograms = response.data;
			})
			.catch(function (response){
				alert('Error getting wishes!')
			});
    };
	
	$scope.getStudyProgram = function() {
        $http.get('api/studyprogram/' + $routeParams.studyProgramId)
			.then(function (response) {
				$scope.studyProgram = response.data;
				console.log($scope.studyProgram.wishes);
			})
			.catch(function (response){
				alert('Error getting study program!')
			});
    };
	
});


