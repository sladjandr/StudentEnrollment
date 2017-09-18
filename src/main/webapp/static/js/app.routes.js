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
		.when('/studyprograms', {
        	templateUrl : 'static/html/StudyPrograms.html',
        	controller: 'studyProgramsController'
        })
		.when('/addexam', {
            templateUrl : 'static/html/AddEditExam.html',
            controller: 'examsController'
        })
		.when('/exam/edit/:id', {
        	templateUrl : 'static/html/AddEditExam.html',
        	controller: 'examsController'
        })
		.when('/addstudyprogram', {
            templateUrl : 'static/html/AddEditStudyProgram.html',
            controller: 'studyProgramsController'
        })
		.when('/studyprogram/edit/:id', {
        	templateUrl : 'static/html/AddEditStudyProgram.html',
        	controller: 'studyProgramsController'
        })
		.when('/addstudyprogram/exam/:id', {
        	templateUrl : 'static/html/AddEditStudyProgramExam.html',
        	controller: 'studyProgramsController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);