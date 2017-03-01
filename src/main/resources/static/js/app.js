var app = angular.module('app', ['ngRoute','ngResource']).constant("APP_URL", 'http://localhost:8080');
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
        .otherwise(
            { redirectTo: '/'}
        );
});