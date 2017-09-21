var app = angular.module('studentEnrollmentApp.studentsController', []);

app.controller('studentsController', function($scope, $http, $routeParams, $window) {

	$scope.getStudents = function() {
        $http.get('api/student/all')
			.then(function (response) {
				$scope.students = response.data;
			})
			.catch(function (response){
				alert('Error getting students!')
			});
    };
	
	$scope.getStudyPrograms = function() {
        $http.get('api/studyprogram/all')
			.then(function (response) {
				$scope.studyPrograms = response.data;
			})
			.catch(function (response){
				alert('Error getting study programs!')
			});
    };
	
    $scope.initStudent = function() {
        $scope.student = {};
		$scope.studentPrograms = {};
        if ($routeParams && $routeParams.id) {
            // this is for edit page
            $http.get('api/student/' + $routeParams.id)
				.then(function (response) {
					$scope.student = response.data;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Student with given id does not exist!')
					}else{
						alert('Unexpected error occured while getting student!')
					}
				});
        }
    };
	
    $scope.saveStudent = function() {
        if ($scope.student.id) {
            // for edit page
            $http.put('api/student/' + $scope.student.id, $scope.student)
				.then(function (response) {
					$window.location.href = "#!/students";
				})
				.catch(function (response){
					if (response.status==404){
						alert('Student with given id does not exist!')
					}else{
						alert('Unexpected error occured while editing student!')
					}
				});
        } else {
            // for add page
			$scope.student.year = (new Date()).getFullYear(); //returns the current year
			$scope.student.studyPrograms = [];
			if($scope.studentPrograms.program1!=null){
				$scope.student.studyPrograms.push($scope.studentPrograms.program1);
			}
			if($scope.studentPrograms.program2!=null){
				$scope.student.studyPrograms.push($scope.studentPrograms.program2);
			}
			if($scope.studentPrograms.program3!=null){
				$scope.student.studyPrograms.push($scope.studentPrograms.program3);
			}
            $http.post('api/student', $scope.student)
				.then(function (response) {
					$window.location.href = "#!/students";
				})
				.catch(function (response){
					if (response.status==400){
						alert('Bad request!')
					}else{
						alert('Unexpected error occured while saving student!')
					}
				});
        }
    };
	
});


