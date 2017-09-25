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
		.when('/students', {
        	templateUrl : 'static/html/Students.html',
        	controller: 'studentsController'
        })
		.when('/users', {
        	templateUrl : 'static/html/Users.html',
        	controller: 'usersController'
        })
		.when('/addexam', {
            templateUrl : 'static/html/AddEditExam.html',
            controller: 'examsController'
        })
		.when('/exam/edit/:id', {
        	templateUrl : 'static/html/AddEditExam.html',
        	controller: 'examsController'
        })
		.when('/exam/points/:id', {
        	templateUrl : 'static/html/ExamPoints.html',
        	controller: 'examsController'
        })
		.when('/exam/points/:examId/student/:examStudentId', {
        	templateUrl : 'static/html/ExamPointsStudent.html',
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
		.when('/addstudyprogram/exam/', {
        	templateUrl : 'static/html/AddEditStudyProgramExam.html',
        	controller: 'studyProgramsController'
        })
		.when('/addstudyprogram/exam/:id', {
        	templateUrl : 'static/html/AddEditStudyProgramExam.html',
        	controller: 'studyProgramsController'
        })
		.when('/addstudent', {
            templateUrl : 'static/html/AddEditStudent.html',
            controller: 'studentsController'
        })
		.when('/student/edit/:id', {
        	templateUrl : 'static/html/AddEditStudent.html',
        	controller: 'studentsController'
        })
		.when('/adduser', {
            templateUrl : 'static/html/AddUser.html',
            controller: 'usersController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);