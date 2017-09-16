var app = angular.module('studentEnrollmentApp.examsController', []);

app.controller('examsController', function($scope, $http, $routeParams) {

    $scope.getExams = function() {
        $http.get('api/exam/all')
			.then(function (response) {
				$scope.exams = response.data;
			})
			.catch(function (response){
				alert('Error getting exams!')
			});

    };

    $scope.deleteExam = function(id) {
        $http.delete('api/exam/' + id)
			.then(function (response){
				if(response.status==200){
					alert('Successfully deleted exam!')
					$scope.getExams();
				}
			})
			.catch(function (response){
				if(response.status==400){
					alert('Exam that is connected to a study program can\'t be deleted!')
				}else{
					alert('Unexpected error occured while deleting exam!')
				}
			});
    };


	
	//TEK TREBA DA TESTIRAM
    $scope.initExam = function() {
        $scope.exam = {};
        if ($routeParams && $routeParams.id) {
            // this is for edit page
            $http.get('api/exam/' + $routeParams.id)
				.then(function (response) {
					$scope.exam = response.data;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Exam with given id does not exist!')
					}else{
						alert('Unexpected error occured while getting exam!')
					}
				});
        }
    };
	
	//TEK TREBA DA TESTIRAM
    $scope.saveExam = function() {
        if ($scope.exam.id) {
            // for edit page
            $http.put('api/exam/' + $scope.exam.id, $scope.exam)
				.then(function (response) {
					//$location.path('/exam'); //will see where to redirect...
				})
				.catch(function (response){
					if (response.status==404){
						alert('Exam with given id does not exist!')
					}else{
						alert('Unexpected error occured while editing exam!')
					}
				});
        } else {
            // for add page
            $http.post('api/exam', $scope.exam)
				.then(function (response) {
					//$location.path('/exam'); //will see where to redirect...
				})
				.catch(function (response){
					if (response.status==400){
						alert('Exam with the same subject name already exists!')
					}else{
						alert('Unexpected error occured while saving exam!')
					}
				});
        }
    };
	
});


