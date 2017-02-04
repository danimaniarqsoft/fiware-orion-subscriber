var app = angular.module('app', ['ngRoute','ngResource']);
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
        .otherwise(
            { redirectTo: '/'}
        );
});

