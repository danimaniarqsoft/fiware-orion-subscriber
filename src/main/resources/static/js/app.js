var app = angular.module('app', ['ngRoute','ngResource']).constant("APP_URL", 'http://207.249.127.180:8080');
app.config(function($routeProvider){
    $routeProvider
        .when('/dashboard',{
            templateUrl: '/views/dashboard.html',
            controller: 'dashboardController'
        })
        .when('/notifications',{
            templateUrl: '/views/notifications.html',
            controller: 'notificationController'
        })
        .when('/table',{
            templateUrl: '/views/table.html',
            controller: 'tableController'
        })
        .when('/user',{
            templateUrl: '/views/user.html',
            controller: 'userController'
        })
        .when('/newUser',{
            templateUrl: '/views/newUser.html',
            controller: 'createUserController'
        })
        .when('/home',{
            templateUrl: '/views/home.html',
            controller: 'homeController'
        })
        .otherwise(
            { redirectTo: '/home'}
        );
});