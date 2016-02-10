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
moduloCentro.controller('CentroNewController', ['$scope', '$routeParams', '$location', 'serverService', 'sharedSpaceService',
    function ($scope, $routeParams, $location, serverService, sharedSpaceService) {
        $scope.id = $routeParams.id;
        $scope.ob = 'centro';
        $scope.op = 'new';
        $scope.result = null;
        $scope.title = "Añadir un nuevo centro";
        $scope.icon = "fa-file-text-o";

        $scope.obj = {};
        $scope.obj.obj_empresa = {"id": 0};
        $scope.obj.obj_municipio = {"id": 0};
        $scope.obj.obj_sector = {"id": 0};

        if (sharedSpaceService.getFase() == 0) {
            if ($routeParams.empresa && $routeParams.empresa > 0) {
                $scope.obj.obj_empresa.id = $routeParams.empresa;
            }
            if ($routeParams.municipio && $routeParams.municipio > 0) {
                $scope.obj.obj_municipio.id = $routeParams.municipio;
            }
            if ($routeParams.sector && $routeParams.sector > 0) {
                $scope.obj.obj_sector.id = $routeParams.sector;
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
            console.log("save");
            console.log({json: JSON.stringify(serverService.array_identificarArray($scope.obj))});
            //strValues = serverService.array_identificarArray(thisObject.form_getFormValues(strClass));
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
    }]);