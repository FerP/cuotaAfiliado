
'use strict';





moduloEmpresa.controller('EmpresaViewController', ['$scope', '$routeParams', 'serverService','$location',
    function ($scope, $routeParams, serverService,$location) {
        $scope.title = "Vista de Empresa";
        $scope.icon = "fa-building-o";
        $scope.ob = 'empresa';
        $scope.id = $routeParams.id;
        serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
            $scope.bean = data.message;
        });
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/empresa/plist');
        };
        $scope.back = function () {
            window.history.back();
        };
    }]);