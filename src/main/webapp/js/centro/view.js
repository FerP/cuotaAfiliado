
'use strict';





moduloCentro.controller('CentroViewController', ['$scope', '$routeParams', 'serverService','$location',
    function ($scope, $routeParams, serverService,$location) {
        $scope.title = "Vista de Centro";
        $scope.icon = "fa-file-text-o";
        $scope.ob = 'centro';
        $scope.id = $routeParams.id;
        serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
            $scope.bean = data.message;
        });
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/centro/plist');
        };
        $scope.back = function () {
            window.history.back();
        };
    }]);