var app = angular.module('studentEnrollmentApp.studyProgramsController', []);

app.controller('studyProgramsController', function($scope, $http, $routeParams, $window) {

	$scope.show = {};
	$scope.show.active = true;
	
	$scope.getStudyPrograms = function() {
        $http.get('api/studyprogram/all')
			.then(function (response) {
				$scope.studyPrograms = response.data;
			})
			.catch(function (response){
				alert('Error getting study programs!')
			});
    };
	
	$scope.getExams = function() {
        $http.get('api/exam/all')
			.then(function (response) {
				$scope.exams = response.data;
			})
			.catch(function (response){
				alert('Error getting exams!')
			});
    };
	
	$scope.deleteStudyProgram = function(id) {
        $http.delete('api/studyprogram/' + id)
			.then(function (response){
				if(response.status==200){
					alert('Study program is deleted!')
					$scope.getStudyPrograms();
				}
			})
			.catch(function (response){
				if(response.status==400){
					alert('Study program that is connected to exam or student can\'t be deleted! It can only be deactivated!')
				}else{
					alert('Unexpected error occured while deleting study program!')
				}
			});
    };
	
	$scope.studyProgramActive = function(id) {
        $http.get('api/studyprogram/' + id)
			.then(function (response) {
				if(response.data.active==true){
					response.data.active=false;
				}else{
					response.data.active=true;
				}
				$http.put('api/studyprogram/' + response.data.id, response.data)
				.then(function (response) {
					alert('Study program\'s status is changed!')
					$scope.getStudyPrograms();
				})
				.catch(function (response){
					if (response.status==404){
						alert('Study program with given id does not exist!')
					}else{
						alert('Unexpected error occured while changing study program status!')
					}
				});
			})
			.catch(function (response){
				if (response.status==404){
					alert('Study program with given id does not exist!')
				}else{
					alert('Unexpected error occured while getting study program!')
				}
			});
    };
	
	$scope.initStudyProgram = function() {
		$scope.studyProgram = {};
		if ($routeParams && $routeParams.id) {
			// this is for edit page
			$http.get('api/studyprogram/' + $routeParams.id)
				.then(function (response) {
					$scope.studyProgram = response.data;
				})
				.catch(function (response){
					if (response.status==404){
						alert('Study program with given id does not exist!')
					}else{
						alert('Unexpected error occured while getting study program!')
					}
				});
		}
	};
	
	$scope.saveStudyProgram = function() {
        if ($scope.studyProgram.id) {
            // for edit page
            $http.put('api/studyprogram/' + $scope.studyProgram.id, $scope.studyProgram)
				.then(function (response) {
					$window.location.href = "#!/studyprograms";
				})
				.catch(function (response){
					if (response.status==404){
						alert('Study program with given id does not exist!')
					}else{
						alert('Unexpected error occured while editing study program!')
					}
				});
        } else {
            // for add page
            $http.post('api/studyprogram', $scope.studyProgram)
				.then(function (response) {
					$window.location.href = "#!/studyprograms";
				})
				.catch(function (response){
					if (response.status==400){
						alert('Error! Study program with the same program name already exists, or level of studies is invalid!')
					}else{
						alert('Unexpected error occured while saving study program!')
					}
				});
        }
    };
	
	$scope.addExam = function(exam) {
		var alreadyAdded = false;
		for(i=0;i<$scope.studyProgram.exams.length;i++){
			if ($scope.studyProgram.exams[i].subjectName == exam.subjectName){
				alreadyAdded = true;
			}
		}
		if (alreadyAdded == false){
			$scope.studyProgram.exams.push(exam);
		}else{
			alert("This exam is already connected to this study program!")
		}
		$http.put('api/studyprogram/' + $scope.studyProgram.id, $scope.studyProgram)
			.then(function (response) {
				var loc =  "#!/studyprogram/edit/" + $scope.studyProgram.id
				$window.location.href = loc;
			})
			.catch(function (response){
				if (response.status==404){
					alert('Study program with given id does not exist!')
				}else{
					alert('Unexpected error occured while editing study program!')
				}
			});
	}
	
	$scope.removeExam = function(exam) {
		var index = $scope.studyProgram.exams.indexOf(exam);
		$scope.studyProgram.exams.splice(index,1);
		$http.put('api/studyprogram/' + $scope.studyProgram.id, $scope.studyProgram)
			.then(function (response) {
			})
			.catch(function (response){
				if(response.status==404){
					alert('Study program with given id does not exist!')
				}else{
					alert('Unexpected error occured while editing study program!')
				}
			});
	}
	
	
	$scope.showDeactivated = function() {
		$scope.show.active = false;
	}
	
	$scope.showActivated = function() {
		$scope.show.active = true;
	}
	
	
});


