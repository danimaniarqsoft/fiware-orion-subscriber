app.controller('dashboardController', function($scope) {
    $scope.headingTitle = "DashboardList";
});

app.controller('notificationController', function($scope) {
    $scope.headingTitle = "NotificationList";
});


app.controller('Hello', function($scope, $http) {
    $http.get('https://jsonplaceholder.typicode.com/posts/1').
        then(function(response) {
            $scope.greeting = response.data;
        });
});