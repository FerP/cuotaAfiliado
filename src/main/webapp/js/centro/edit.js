

'use strict';
moduloCentro.controller('CentroEditController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService', '$filter',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService, $filter) {
        $scope.obj = null;
        $scope.id = $routeParams.id;
        $scope.ob = 'centro';
        $scope.op = 'edit';
        $scope.result = null;
        $scope.title = "Edición de centro";
        $scope.icon = "fa-file-text-o";
        if (sharedSpaceService.getFase() == 0) {
            serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
                $scope.obj = data.message;
                //date conversion
//                $scope.obj.alta = serverService.date_toDate($scope.obj.alta);
//                $scope.obj.cambio = serverService.date_toDate($scope.obj.cambio);
            });
        } else {
            $scope.obj = sharedSpaceService.getObject();
            sharedSpaceService.setFase(0);
        }
        $scope.chooseOne = function (foreignObjectName) {
            sharedSpaceService.setObject($scope.obj);
            sharedSpaceService.setReturnLink('/' + $scope.ob + '/' + $scope.op + '/' + $scope.id);
            sharedSpaceService.setFase(1);
            $location.path('/' + foreignObjectName + '/selection/1/10');
        }
        $scope.save = function () {
            var dateAltaAsString = $filter('date')($scope.obj.alta, "dd/MM/yyyy");
            var dateCambioAsString = $filter('date')($scope.obj.cambio, "dd/MM/yyyy");
            $scope.obj.alta = dateAltaAsString;
            $scope.obj.cambio = dateCambioAsString;
            //console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});            
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };

        $scope.$watch('obj.obj_empresa.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('empresa', $scope.obj.obj_empresa.id)).then(function (data2) {
                    $scope.obj.obj_empresa = data2.message;
                });
            }
        });

        $scope.$watch('obj.obj_municipio.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('municipio', $scope.obj.obj_municipio.id)).then(function (data2) {
                    $scope.obj.obj_municipio = data2.message;
                });
            }
        });

        $scope.$watch('obj.obj_sector.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('sector', $scope.obj.obj_sector.id)).then(function (data2) {
                    $scope.obj.obj_sector = data2.message;
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
            $location.path('/centro/plist');
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


//        $scope.disabled = function (date, mode) {
//            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
//        };
    }]);