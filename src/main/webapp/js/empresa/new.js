


'use strict';
moduloEmpresa.controller('EmpresaNewController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService','$filter',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService, $filter) {
        $scope.id = $routeParams.id;
        $scope.ob = 'empresa';
        $scope.op = 'new';

        $scope.title = "Creación de una nueva empresa";
        $scope.icon = "fa-building-o";

        $scope.result = null;

          if (sharedSpaceService.getFase() == 0) {
            $scope.obj = {};
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
         var dateAltaAsString = $filter('date')($scope.obj.alta, "dd/MM/yyyy");
            var dateCambioAsString = $filter('date')($scope.obj.cambio, "dd/MM/yyyy");
            $scope.obj.alta = dateAltaAsString;
            $scope.obj.cambio = dateCambioAsString;
            
            //console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});            
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };
      
        $scope.back = function () {
            window.history.back();
        };
        $scope.close = function () {
            $location.path('/home');
        };
        $scope.plist = function () {
            $location.path('/empresa/plist');
        };
        //datepickers
        //$scope.minDate = new Date(2016, 0, 1);
        //$scope.maxDate = new Date(2019, 11, 31);

       
        //datepicker 1 (fecha de cambio)
        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };
        $scope.popup1 = {
            opened: false
        };
        $scope.dateOptions1 = {
            formatYear: 'yyyy'
            
        };
        
        
        
        
        
        
        
        
        
    }]);