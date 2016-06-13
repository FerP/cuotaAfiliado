

'use strict';

moduloMunicipio.controller('MunicipioRemoveController', ['$scope', '$routeParams', 'serverService',
    function ($scope, $routeParams, serverService) {
        $scope.result = "";
        $scope.back = function () {
            window.history.back();
        };
        $scope.ob = 'municipio';
        $scope.id = $routeParams.id;
        $scope.title = "Borrado de un municipio";
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