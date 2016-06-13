

'use strict';

moduloCentro.controller('CentroRemoveController', ['$scope', '$routeParams', 'serverService',
    function ($scope, $routeParams, serverService) {
        $scope.result = "";
        $scope.back = function () {
            window.history.back();
        };
        $scope.ob = 'centro';
        $scope.id = $routeParams.id;
        $scope.title = "Borrado de un centro";
        $scope.icon = "fa-file-text-o";
        serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
            $scope.bean = data.message;
        });



        $scope.remove = function () {
            serverService.getDataFromPromise(serverService.promise_removeOne($scope.ob, $scope.id)).then(function (data) {
                $scope.result = data;
            });
        }
        ;
    }]);