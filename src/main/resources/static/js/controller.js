app.controller('dashboardController', function($scope) {
	$scope.headingTitle = "DashboardList";
});

app.controller('notificationController', function($scope, $http, APP_URL) {
	$http.get(APP_URL + '/app/users/notifications').then(function(response) {
		$scope.notifications = response.data;
	});
});

app.controller('tableController', function($scope, $http, APP_URL) {});
app.controller('userController', function($scope, $http, APP_URL) {
	$scope.list = [];
	$scope.info = 'info';
	$scope.submit = function() {
		$scope.info = $scope.user;
		$http.post(APP_URL + '/app/users', $scope.user).then(function(response) {
			demo.showNotification('bottom', 'center', '<p><b>SUCCESS:</b></p> The user have been updated successfully!', 2, 'check');
		}, function(response) {
			demo.showNotification('bottom', 'center', '<p><b>ERROR:</b></p> Something is wrong, please try to verify the information provided', 4, 'error_outline');
		});
	};
	$http.get(APP_URL + '/app/users/detail').then(function(response) {
		$scope.user = response.data;
		$scope.list.push('hola');
	});
});

app.controller('Hello', function($scope, $http) {
	$http.get('https://jsonplaceholder.typicode.com/posts/1').then(
		function(response) {
			$scope.greeting = response.data;
		});
});


app.controller('MainCtrl', function($scope, Poller) {
	$scope.name = 'World';
	$scope.data = Poller.data;
});
app.controller('StartCtrl', function() {});
app.run(function(Poller) {});

app.factory('Poller', function($http, $timeout, APP_URL) {
	var data = {
		response : {},
		calls : 0
	};
	var poller = function() {
		$http.get(APP_URL + '/app/users/notifications').then(function(r) {
			data.response = r.data;
			$timeout(poller, 1000);
		});
	};
	poller();

	return {
		data : data
	};
});