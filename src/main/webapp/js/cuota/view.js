
'use strict';


moduloCuota.controller('CuotaViewController', ['$scope', '$routeParams', 'serverService','$location',
    function ($scope, $routeParams, serverService,$location) {
        $scope.title = "Vista de Cuota";
        $scope.icon = "fa fa-fw fa-money";
        $scope.ob = 'cuota';
        $scope.id = $routeParams.id;
        serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
            $scope.bean = data.message;
        });
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/cuota/plist');
        };
        $scope.back = function () {
            window.history.back();
        };
    }]);