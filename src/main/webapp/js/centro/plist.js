

'use strict';
/* Controllers */

moduloCentro.controller('CentroPListController', ['$scope', '$routeParams', 'serverService', '$location','sharedSpaceService',
    function ($scope, $routeParams, serverService, $location) {


        $scope.visibles = {};
        $scope.visibles.id = true;
        $scope.visibles.nombre = true;
        $scope.visibles.direccion= true;
        $scope.visibles.cp = true;
        $scope.visibles.telefono = true;
        $scope.visibles.id_municipio= true;
        $scope.visibles.id_sector= true;
        $scope.visibles.id_empresa= true;
        


        $scope.ob = "centro";
        $scope.op = "plist";
        $scope.title = "Listado de centros";
        $scope.icon = "fa-circle";
        $scope.neighbourhood = 2;

        if (!$routeParams.page) {
            $routeParams.page = 1;
        }

        if (!$routeParams.rpp) {
            $routeParams.rpp = 5;
        }

        $scope.numpage = $routeParams.page;
        $scope.rpp = $routeParams.rpp;





        //$scope.rppPad = serverService.getNrppBar($scope.ob, $scope.op, $scope.numpage, $scope.rpp);

//        $scope.order = $routeParams.order;
//        $scope.ordervalue = $routeParams.value;
//
//        $scope.filter = $routeParams.filter;
//        $scope.filteroperator = $routeParams.filteroperator;
//        $scope.filtervalue = $routeParams.filtervalue;
//
//        $scope.systemfilter = $routeParams.systemfilter;
//        $scope.systemfilteroperator = $routeParams.systemfilteroperator;
//        $scope.systemfiltervalue = $routeParams.systemfiltervalue;


        $scope.order = "";
        $scope.ordervalue = "";
        $scope.filter = "id";
        $scope.filteroperator = "like";
        $scope.filtervalue = "";
        $scope.systemfilter = "";
        $scope.systemfilteroperator = "";
        $scope.systemfiltervalue = "";
        $scope.params = "";
        $scope.paramsWithoutOrder = "";
        $scope.paramsWithoutFilter = "";
        $scope.paramsWithoutSystemFilter = "";




        if ($routeParams.order && $routeParams.ordervalue) {
            $scope.order = $routeParams.order;
            $scope.ordervalue = $routeParams.ordervalue;
            $scope.orderParams = "&order=" + $routeParams.order + "&ordervalue=" + $routeParams.ordervalue;
            $scope.paramsWithoutFilter += $scope.orderParams;
            $scope.paramsWithoutSystemFilter += $scope.orderParams;
        } else {
            $scope.orderParams = "";
        }

        if ($routeParams.filter && $routeParams.filteroperator && $routeParams.filtervalue) {
            $scope.filter = $routeParams.filter;
            $scope.filteroperator = $routeParams.filteroperator;
            $scope.filtervalue = $routeParams.filtervalue;
            $scope.filterParams = "&filter=" + $routeParams.filter + "&filteroperator=" + $routeParams.filteroperator + "&filtervalue=" + $routeParams.filtervalue;
            $scope.paramsWithoutOrder += $scope.filterParams;
            $scope.paramsWithoutSystemFilter += $scope.filterParams;
        } else {
            $scope.filterParams = "";
        }

        if ($routeParams.systemfilter && $routeParams.systemfilteroperator && $routeParams.systemfiltervalue) {
            $scope.systemFilterParams = "&systemfilter=" + $routeParams.systemfilter + "&systemfilteroperator=" + $routeParams.systemfilteroperator + "&systemfiltervalue=" + $routeParams.systemfiltervalue;
            $scope.paramsWithoutOrder += $scope.systemFilterParams;
            $scope.paramsWithoutFilter += $scope.systemFilterParams;
        } else {
            $scope.systemFilterParams = "";
        }

        $scope.params = ($scope.orderParams + $scope.filterParams + $scope.systemFilterParams);
        //$scope.paramsWithoutOrder = $scope.paramsWithoutOrder.replace('&', '?');
        //$scope.paramsWithoutFilter = $scope.paramsWithoutFilter.replace('&', '?');
        //$scope.paramsWithoutSystemFilter = $scope.paramsWithoutSystemFilter.replace('&', '?');
        $scope.params = $scope.params.replace('&', '?');

        serverService.getDataFromPromise(serverService.promise_getSome($scope.ob, $scope.rpp, $scope.numpage, $scope.filterParams, $scope.orderParams, $scope.systemFilterParams)).then(function (data) {
            if (data.status != 200) {
                $scope.status = "Error en la recepción de datos del servidor";
            } else {
                $scope.pages = data.message.pages.message;
                if (parseInt($scope.numpage) > parseInt($scope.pages))
                    $scope.numpage = $scope.pages;

                $scope.page = data.message.page.message;
                $scope.registers = data.message.registers.message;
                $scope.status = "";
            }
        });


//        $scope.pages = serverService.getPages($scope.ob, $scope.rpp, null, null, null, null, null, null).then(function (datos5) {
//            $scope.pages = data['data'];
//            if (parseInt($scope.page) > parseInt($scope.pages))
//                $scope.page = $scope.pages;
//            //$location.path( "#/clientes/" +$scope.pages + "/" + $scope.pages);
//        });


//        $scope.$watch('pages', function () {
//            $scope.$broadcast('myApp.construirBotoneraPaginas');
//        }, true)
//

        $scope.getRangeArray = function (lowEnd, highEnd) {
            var rangeArray = [];
            for (var i = lowEnd; i <= highEnd; i++) {
                rangeArray.push(i);
            }
            return rangeArray;
        };
        $scope.evaluateMin = function (lowEnd, highEnd) {
            return Math.min(lowEnd, highEnd);
        };
        $scope.evaluateMax = function (lowEnd, highEnd) {
            return Math.max(lowEnd, highEnd);
        };

        $scope.dofilter = function () {
            if ($scope.filter != "" && $scope.filteroperator != "" && $scope.filtervalue != "") {
                //console.log('#/' + $scope.ob + '/' + $scope.op + '/' + $scope.numpage + '/' + $scope.rpp + '?filter=' + $scope.filter + '&filteroperator=' + $scope.filteroperator + '&filtervalue=' + $scope.filtervalue + $scope.paramsWithoutFilter);
                if ($routeParams.order && $routeParams.ordervalue) {
                    if ($routeParams.systemfilter && $routeParams.systemfilteroperator) {
                        $location.path($scope.ob + '/' + $scope.op + '/' + $scope.numpage + '/' + $scope.rpp).search('filter', $scope.filter).search('filteroperator', $scope.filteroperator).search('filtervalue', $scope.filtervalue).search('order', $routeParams.order).search('ordervalue', $routeParams.ordervalue).search('systemfilter', $routeParams.systemfilter).search('systemfilteroperator', $routeParams.systemfilteroperator).search('systemfiltervalue', $routeParams.systemfiltervalue);
                    } else {
                        $location.path($scope.ob + '/' + $scope.op + '/' + $scope.numpage + '/' + $scope.rpp).search('filter', $scope.filter).search('filteroperator', $scope.filteroperator).search('filtervalue', $scope.filtervalue).search('order', $routeParams.order).search('ordervalue', $routeParams.ordervalue);
                    }
                } else {
                    $location.path($scope.ob + '/' + $scope.op + '/' + $scope.numpage + '/' + $scope.rpp).search('filter', $scope.filter).search('filteroperator', $scope.filteroperator).search('filtervalue', $scope.filtervalue);
                }
            }
            return false;
        };
        //$scope.$on('myApp.construirBotoneraPaginas', function () {
        //    $scope.botoneraPaginas = serverService.getPaginationBar($scope.ob, $scope.op, $scope.page, $scope.pages, 2, $scope.rpp);
        //})
//
//        $scope.prettyFieldNames = serverService.getPrettyFieldNames($scope.ob).then(function (datos4) {
//            datos4['data'].push('acciones');
//            $scope.prettyFieldNames = datos4['data'];
//        });
//
//        $scope.clientes = serverService.getPage($scope.ob, $scope.page, null, null, $scope.rpp, null, null, null, null, null, null).then(function (datos3) {
//            $scope.clientes = datos3['list'];
//
//        });
//
//        $scope.fieldNames = serverService.getFieldNames($scope.ob).then(function (datos6) {
//            $scope.fieldNames = datos6['data'];
//            $scope.selectedFilterFieldName = null;
//        });
//
//
//        $scope.$watch('numPagina', function () {
//            $scope.$broadcast('myApp.construirPagina');
//        }, true)
//
//        $scope.$on('myApp.construirPagina', function () {
//
//            $scope.clientes = serverService.getPage($scope.ob, $scope.page, null, null, $scope.rpp, null, null, null, null, null, null).then(function (datos3) {
//                $scope.clientes = datos3['list'];
//
//            });
//
//        })
//
//        $scope.filtrar = function () {
//            alert("f")
//
//
//        };

// $scope.$watch('filteroperator', function () {
//           console.log($scope.filter);
//           console.log($scope.filteroperator);
//           console.log($scope.filtervalue);
//        }, true)


    }]);