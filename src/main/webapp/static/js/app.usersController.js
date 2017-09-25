var app = angular.module('studentEnrollmentApp.usersController', []);

app.controller('usersController', function($scope, $http, $routeParams, $window) {
	
	$scope.show = {};
	$scope.show.role = "ADMIN";

    $scope.getUsers = function() {
        $http.get('api/user/all')
			.then(function (response) {
				$scope.users = response.data;
			})
			.catch(function (response){
				alert('Error getting users!')
			});
    };

    $scope.deleteUser = function(id) {
        $http.delete('api/user/admin/' + id)
			.then(function (response){
				if(response.status==200){
					alert('User is deleted!')
					$scope.getUsers();
				}
			})
			.catch(function (response){
					alert('Unexpected error occured while deleting user!')
			});
    };

    $scope.initUser = function() {
        $scope.user = {};
    };
	
    $scope.saveUser = function() {
        // for add page
        $http.post('api/user/admin', $scope.user)
			.then(function (response) {
				$window.location.href = "#!/users";
			})
			.catch(function (response){
				if (response.status==400){
					alert('User with the same username already exists!')
				}else{
					alert('Unexpected error occured while saving user!')
				}
			});

    };
	
	$scope.showStudents = function() {
		$scope.show.role = 'STUDENT';
	}
	
	$scope.showAdmins = function() {
		$scope.show.role = 'ADMIN';
	}
	
});


