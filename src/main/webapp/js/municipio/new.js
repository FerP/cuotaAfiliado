


'use strict';
moduloMunicipio.controller('MunicipioNewController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService) {
        $scope.ob = 'municipio';
        $scope.op = 'new';

        $scope.title = "Creación de un nuevo municipio";
        $scope.icon = "fa-file-text-o";

        $scope.result = null;

        $scope.obj = {};
        $scope.obj.obj_comarca = {"id": 0};
        $scope.obj.obj_provincia = {"id": 0};

        if (sharedSpaceService.getFase() == 0) {
            if ($routeParams.comarca && $routeParams.comarca > 0) {
                $scope.obj.obj_comarca.id = $routeParams.comarca;
            }
            if ($routeParams.provincia && $routeParams.provincia > 0) {
                $scope.obj.obj_provincia.id = $routeParams.provincia;
            }
        } else {
            $scope.obj = sharedSpaceService.getObject();
            sharedSpaceService.setFase(0);
        }




        $scope.chooseOne = function (foreignObjectName) {
            sharedSpaceService.setObject($scope.obj);
            sharedSpaceService.setReturnLink('/' + $scope.ob + '/' + $scope.op);
            sharedSpaceService.setFase(1);
            $location.path('/' + foreignObjectName + '/selection/1/10');
        }

        $scope.save = function () {
            //console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});            
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };

        $scope.$watch('obj.obj_provincia.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('provincia', $scope.obj.obj_provincia.id)).then(function (data2) {
                    $scope.obj.obj_provincia = data2.message;
                });
            }
        });
        $scope.$watch('obj.obj_comarca.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('comarca', $scope.obj.obj_comarca.id)).then(function (data2) {
                    $scope.obj.obj_comarca = data2.message;
                });
            }
        });
        $scope.back = function () {
            window.history.back();
        };
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/municipio/plist');
        };

        //datepickers
        $scope.minDate = new Date(2016, 0, 1);
        $scope.maxDate = new Date(2019, 11, 31);

        //datepicker 1 (fecha de alta)
        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };
        $scope.dateOptions1 = {
            formatYear: 'yyyy',
            startingDay: 1
        };

        //datepicker 2 (fecha de alta)
        $scope.open2 = function () {
            $scope.popup2.opened = true;
        };
        $scope.popup2 = {
            opened: false
        };
        $scope.dateOptions2 = {
            formatYear: 'yyyy',
            startingDay: 1
        };



    }]);