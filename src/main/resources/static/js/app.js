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
        .when('/table',{
            templateUrl: '/views/table.html',
            controller: 'tableController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

app.controller('MainCtrl', function($scope, Poller) {
	  $scope.name = 'World';
	  $scope.data = Poller.data;
	});
	app.controller('StartCtrl',function(){});
	app.run(function(Poller) {});

	app.factory('Poller', function($http, $timeout) {
	  var data = { response: {}, calls: 0 };
	  var poller = function() {
	    $http.get('http://consumer.fiware.infotec.mx:8080/notifications').then(function(r) {
	      data.response = r.data;
	      $timeout(poller, 1000);
	    });
	  };
	  poller();
	  
	  return {
	    data: data
	  };
	});
