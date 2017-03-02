app.controller('dashboardController', function($scope) {
	$scope.headingTitle = "DashboardList";
});

app.controller('notificationController', function($scope, $http, APP_URL) {
	$http.get(APP_URL + '/app/users/notifications').then(function(response) {
		$scope.notifications = response.data;
	});
});


app.controller('mapController', function($scope, $http, APP_URL) {
	$().ready(function(){
        demo.initGoogleMaps();
    });
});


app.controller('notificationDeleteController', function($scope, $http, APP_URL) {
	$scope.deleteAll = function() {
		$http({
			method : 'DELETE',
			url : APP_URL + '/notifications'
		}).then(function successCallback(response) {
			demo.showNotification('bottom', 'center', '<p><b>SUCCESS:</b></p> All the notifications have been deleted', 2, 'check');
		}, function errorCallback(response) {
			demo.showNotification('bottom', 'center', '<p><b>ERROR:</b></p> Something is wrong, please try to verify the information provided', 4, 'error_outline');
		});
	};
});

app.controller('tableController', function($scope, $http, APP_URL) {});
app.controller('logoutController', function($scope, $http, APP_URL) {
	$scope.logoutinfo = "nothing";
	$scope.logout = function() {
		$http.defaults.headers.common.Authorization = '';
		$http.get(APP_URL + '/logout').then(function(response) {
			demo.showNotification('bottom', 'center', '<p><b>SUCCESS:</b></p> The user have been logged out successfully!', 2, 'check');
		}, function(response) {
			demo.showNotification('bottom', 'center', '<p><b>ERROR:</b></p> Something is wrong, please try to verify the information provided', 4, 'error_outline');
		});
	};
});
app.controller('userController', function($scope, $http, APP_URL) {
	$scope.list = [];
	$scope.info = 'info';
	$scope.diseaseEdit = {
		"id" : "",
		"name" : ""
	}
	$scope.submit = function() {
		$scope.info = $scope.user;
		$http.post(APP_URL + '/app/users', $scope.user).then(function(response) {
			demo.showNotification('bottom', 'center', '<p><b>SUCCESS:</b></p> The user have been updated successfully!', 2, 'check');
		}, function(response) {
			demo.showNotification('bottom', 'center', '<p><b>ERROR:</b></p> Something is wrong, please try to verify the information provided', 4, 'error_outline');
		});
	};
	$scope.addDiseases = function() {
		newDisease = {
			"id" : $scope.diseaseEdit.id,
			"name" : $scope.diseaseEdit.name
		}
		$scope.diseaseEdit.id = '';
		$scope.diseaseEdit.name = '';
		$scope.user.userProfile.healthProfile.diseases.push(newDisease);
	};
	$scope.removeDiseases = function(index) {
		$scope.user.userProfile.healthProfile.diseases.splice(index, 1);
	};

	$http.get(APP_URL + '/app/users/detail').then(function(response) {
		$scope.user = response.data;
	});
});

app.controller('createUserController', function($scope, $http, APP_URL) {
	$scope.list = [];
	$scope.info = 'info';
	$scope.diseaseEdit = {
		"id" : "",
		"name" : ""
	}
	$scope.submit = function() {
		$scope.info = $scope.user;
		$http.post(APP_URL + '/app/users', $scope.user).then(function(response) {
			demo.showNotification('bottom', 'center', '<p><b>SUCCESS:</b></p> The user have been updated successfully!', 2, 'check');
		}, function(response) {
			demo.showNotification('bottom', 'center', '<p><b>ERROR:</b></p> Something is wrong, please try to verify the information provided', 4, 'error_outline');
		});
	};
	$scope.addDiseases = function() {
		newDisease = {
			"id" : $scope.diseaseEdit.id,
			"name" : $scope.diseaseEdit.name
		}
		$scope.diseaseEdit.id = '';
		$scope.diseaseEdit.name = '';
		$scope.user.userProfile.healthProfile.diseases.push(newDisease);
	};
	$scope.removeDiseases = function(index) {
		$scope.user.userProfile.healthProfile.diseases.splice(index, 1);
	};

	$http.get(APP_URL + '/app/users/newUser').then(function(response) {
		$scope.user = response.data;
	});

});

app.controller('Hello', function($scope, $http) {
	$http.get('https://jsonplaceholder.typicode.com/posts/1').then(
		function(response) {
			$scope.greeting = response.data;
		});
});

app.controller('countController', function($scope, CountPoller){
	$scope.report = CountPoller.data;
});
app.run(function(CountPoller) {});
app.factory('CountPoller', function($http, $timeout, APP_URL) {
	var data = {
		response : {},
		calls : 0
	};
	var poller = function() {
		$http.get(APP_URL + '/notifications/count').then(function(r) {
			data.response = r.data;
			$timeout(poller, 1000);
		});
	};
	poller();

	return {
		data : data
	};
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