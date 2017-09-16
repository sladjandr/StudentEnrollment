var app = angular.module('studentEnrollmentApp.routes', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
        	templateUrl : 'static/html/aaa.html',
        	controller: 'aaaController'
        })
		.when('/exams', {
        	templateUrl : 'static/html/Exams.html',
        	controller: 'examsController'
        })
		.when('/addexam', {
            templateUrl : 'static/html/AddEditExam.html',
            controller: 'examsController'
        })
		.when('/exam/edit/:id', {
        	templateUrl : 'static/html/AddEditExam.html',
        	controller: 'examsController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);