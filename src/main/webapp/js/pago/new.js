/* 
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

'use strict';
moduloPago.controller('PagoNewController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService', '$filter',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService, $filter) {

        $scope.ob = 'pago';
        $scope.op = 'new';

        $scope.title = "Creación de un nuevo pago";
        $scope.icon = "fa-credit-card";

        $scope.result = null;

        $scope.obj = {};
        $scope.obj.obj_afiliado = {"id": 0};
        $scope.obj.obj_cuota = {"id": 0};
        $scope.obj.obj_recibo = {"id": 0};
        

        if (sharedSpaceService.getFase() == 0) {
            if ($routeParams.afiliado && $routeParams.afiliado > 0) {
                $scope.obj.obj_afiliado.id = $routeParams.afiliado;
            }
            if ($routeParams.cuota && $routeParams.cuota > 0) {
                $scope.obj.obj_cuota.id = $routeParams.cuota;
            }
            if ($routeParams.recibo && $routeParams.recibo > 0) {
                $scope.obj.obj_recibo.id = $routeParams.recibo;
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
            
            var dateFpagoAsString = $filter('date')($scope.obj.fpago, "dd/MM/yyyy");
           
            $scope.obj.fpago = dateFpagoAsString;
            
           
            
            //console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});            
            serverService.getDataFromPromise(serverService.promise_setOne($scope.ob, {json: JSON.stringify(serverService.array_identificarArray($scope.obj))})).then(function (data) {
                $scope.result = data;
            });
        };
        
        $scope.$watch('obj.obj_afiliado.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('afiliado', $scope.obj.obj_afiliado.id)).then(function (data2) {
                    $scope.obj.obj_afiliado = data2.message;
                });
            }
        });
        $scope.$watch('obj.obj_cuota.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('cuota', $scope.obj.obj_cuota.id)).then(function (data2) {
                    $scope.obj.obj_cuota = data2.message;
                });
            }
        });
        
        $scope.$watch('obj.obj_recibo.id', function () {
            if ($scope.obj) {
                serverService.getDataFromPromise(serverService.promise_getOne('recibo', $scope.obj.obj_recibo.id)).then(function (data2) {
                    $scope.obj.obj_recibo = data2.message;
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
            $location.path('/pago/plist');
        };

        //datepickers
        $scope.minDate = new Date(2016, 0, 1);
        $scope.maxDate = new Date(2019, 11, 31);

        //datepicker 1 (fecha de pago)
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

        /*datepicker 2 (fecha de alta)
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
*/

//        $scope.disabled = function (date, mode) {
//            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
//        };


       

    }]);