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
					alert('Exam that is connected to a study program can\'t be deleted! It can only be deactivated!')
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
	
	$scope.initLocationAndDate = function() {
        $scope.lad = {};
		$scope.ladDate = {};
        if ($routeParams && $routeParams.id) {
            // getting current date and location from examStudents[0] inside exam
            $http.get('api/exam/' + $routeParams.id)
				.then(function (response) {
					$scope.exam = response.data;
					if($scope.exam.studentExams.length>0){
						var studentExamIndex = $scope.exam.studentExams.findIndex(isUnfinished);
						if(studentExamIndex!=-1){
							if ($scope.exam.studentExams[studentExamIndex].date!=null){
								var oldDate = new Date($scope.exam.studentExams[studentExamIndex].date);
								$scope.ladDate.year = oldDate.getFullYear();
								$scope.ladDate.month = oldDate.getMonth()+1;
								$scope.ladDate.day = oldDate.getDate();
								$scope.ladDate.hour = oldDate.getHours();
								$scope.ladDate.minute = oldDate.getMinutes();
							}
							if ($scope.exam.studentExams[studentExamIndex].location!=null){
								$scope.lad.location = $scope.exam.studentExams[studentExamIndex].location;
							}
						}else{
							alert("There are are no students waiting to take this exam.")
							$window.location.href = "#!/exams";
						}
					}else{
						alert("There are are no students waiting to take this exam.")
						$window.location.href = "#!/exams";
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
	
	function isUnfinished(studentExam) {
		return studentExam.finished == false;
	}
	
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
	
	$scope.setLocationAndDate = function() {
		if($scope.ladDate.year!=null && $scope.ladDate.month!=null && $scope.ladDate.day!=null && $scope.ladDate.hour!=null){
			if($scope.ladDate.minute==null){
				$scope.ladDate.minute=0;
			}
			var date = new Date($scope.ladDate.year, $scope.ladDate.month-1, $scope.ladDate.day, $scope.ladDate.hour, $scope.ladDate.minute, 0, 0);
			$scope.lad.date = date.getTime();
		}
        $http.put('api/examstudent/exam/' + $scope.exam.id, $scope.lad)
			.then(function (response) {
				$window.location.href = "#!/exams";
			})
			.catch(function (response){
				if (response.status==404){
					alert('Exam with given id does not exist!')
				}else{
					alert('Unexpected error occured while setting location and date!')
				}
			});

    };
	
	    $scope.saveExamPoints = function() {
            // for edit page
            $http.put('api/examstudent/' + $scope.examStudent.id, $scope.examStudent)
				.then(function (response) {
					$window.location.href = "#!/exam/points/"+$routeParams.examStudentId;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Student\'s exam with given id does not exist!')
					}else if (response.status==400){
						alert('Number of points entered is higher then allowed!')
					}else{
						alert('Unexpected error occured while editing student\'s exam!')
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


