var app = angular.module('studentEnrollmentApp.examsController', []);

app.controller('examsController', function($scope, $http, $routeParams, $window) {
	
	$scope.show = {};
	$scope.show.active = true;

    $scope.getExams = function() {
        $http.get('api/exam/all')
			.then(function (response) {
				$scope.exams = response.data;
			})
			.catch(function (response){
				alert('Error getting exams!')
			});
    };
	
    $scope.getExamStudents = function() {
        $http.get('api/examstudent/exam/' + $routeParams.id)
			.then(function (response) {
				$scope.examStudents = response.data;
				$scope.examId = $routeParams.id; //this will be needed for a link to individual student points
			})
			.catch(function (response){
				alert('Error getting exam students!')
			});
    };

    $scope.deleteExam = function(id) {
        $http.delete('api/exam/' + id)
			.then(function (response){
				if(response.status==200){
					alert('Exam is deleted!')
					$scope.getExams();
				}
			})
			.catch(function (response){
				if(response.status==400){
					alert('Exam that is connected to a study program or a student can\'t be deleted! It can only be deactivated!')
				}else{
					alert('Unexpected error occured while deleting exam!')
				}
			});
    };

    $scope.examActive = function(id) {
        $http.get('api/exam/' + id)
			.then(function (response) {
				if(response.data.active==true){
					response.data.active=false;
				}else{
					response.data.active=true;
				}
				$http.put('api/exam/' + response.data.id, response.data)
				.then(function (response) {
					alert('Exam\'s status is changed!')
					$scope.getExams();
				})
				.catch(function (response){
					if (response.status==404){
						alert('Exam with given id does not exist!')
					}else{
						alert('Unexpected error occured while changing exam status!')
					}
				});
			})
			.catch(function (response){
				if (response.status==404){
					alert('Exam with given id does not exist!')
				}else{
					alert('Unexpected error occured while getting exam!')
				}
			});
    };
	
    $scope.initExam = function() {
        $scope.exam = {};
		$scope.examDate = {};
        if ($routeParams && $routeParams.id) {
            // this is for edit page
            $http.get('api/exam/' + $routeParams.id)
				.then(function (response) {
					$scope.exam = response.data;
					if ($scope.exam.date!=null){
						var oldDate = new Date(exam.date);
						$scope.examDate.year = oldDate.getFullYear();
						$scope.examDate.month = oldDate.getMonth();
						$scope.examDate.day = oldDate.getDate();
						$scope.examDate.hour = oldDate.getHours();
						$scope.examDate.minute = oldDate.getMinutes();
					}
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
	
	$scope.initExamStudent = function() {
        $scope.examStudent = {};
        if ($routeParams && $routeParams.examStudentId) {
            // this is for edit page
            $http.get('api/examstudent/' + $routeParams.examStudentId)
				.then(function (response) {
					$scope.examStudent = response.data;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Student\'s exam with given id does not exist!')
					}else{
						alert('Unexpected error occured while getting student\'s exam!')
					}
				});
        }
    };
	
    $scope.saveExam = function() {
		if($scope.examDate.year!=null && $scope.examDate.month!=null && $scope.examDate.day!=null && $scope.examDate.hour!=null){
			if($scope.examDate.minute==null){
				$scope.examDate.minute=0;
			}
			var date = new Date($scope.examDate.year, $scope.examDate.month, $scope.examDate.day, $scope.examDate.hour, $scope.examDate.minute, 0, 0);
			$scope.exam.date = date.getTime();
		}
        if ($scope.exam.id) {
            // for edit page
            $http.put('api/exam/' + $scope.exam.id, $scope.exam)
				.then(function (response) {
					$window.location.href = "#!/exams";
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
					$window.location.href = "#!/exams";
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
	
	
	    $scope.saveExamPoints = function() {
            // for edit page
            $http.put('api/examstudent/' + $scope.examStudent.id, $scope.examStudent)
				.then(function (response) {
					$window.location.href = "#!/exam/points/"+$routeParams.examStudentId;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Exam with given id does not exist!')
					}else{
						alert('Unexpected error occured while editing exam!')
					}
				});

    };
	
	$scope.showDeactivated = function() {
		$scope.show.active = false;
	}
	
	$scope.showActivated = function() {
		$scope.show.active = true;
	}
	
});


