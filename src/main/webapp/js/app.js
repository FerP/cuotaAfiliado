/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
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
 */

'use strict';

//var appName = 'AjaxStockNg';

var openAusias = angular.module('myApp', [
    'ngRoute',
    'Filters',
    'Services',
    'systemControllers',
    'centroControllers',
    'empresaControllers',
    'usuarioControllers',    
    'tipodocumentoControllers',
    'ui.bootstrap',
    'ngSanitize' //http://stackoverflow.com/questions/9381926/insert-html-into-view-using-angularjs
]);

openAusias.config(['$routeProvider', function ($routeProvider) {
        
        $routeProvider.when('/', {templateUrl: '/home'});
        //------------
        $routeProvider.when('/home', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        //------------       $routeProvider.when('/documento/view/:id', {templateUrl: 'js/documento/view.html', controller: 'DocumentoViewController'});       $routeProvider.when('/documento/new', {templateUrl: 'js/documento/new.html', controller: 'DocumentoNewController'});

        //------------
        $routeProvider.when('/empresa/view/:id', {templateUrl: 'js/empresa/view.html', controller: 'EmpresaViewController'});
        $routeProvider.when('/empresa/new/', {templateUrl: 'js/empresa/new.html', controller: 'EmpresaNewController'});
        $routeProvider.when('/empresa/edit/:id', {templateUrl: 'js/empresa/edit.html', controller: 'EmpresaEditController'});
        $routeProvider.when('/empresa/remove/:id', {templateUrl: 'js/empresa/remove.html',   controller: 'EmpresaRemoveController'});
        $routeProvider.when('/empresa/plist/:page?/:rpp?', {templateUrl: 'js/empresa/plist.html', controller: 'EmpresaPListController'});
     
                $routeProvider.when('/centro/view/:id', {templateUrl: 'js/centro/view.html', controller: 'CentroViewController'});
        $routeProvider.when('/centro/new', {templateUrl: 'js/centro/new.html', controller: 'CentroNewController'});
        $routeProvider.when('/centro/edit/:id', {templateUrl: 'js/centro/edit.html', controller: 'CentroEditController'});
        $routeProvider.when('/centro/remove/:id', {templateUrl: 'js/centro/remove.html',   controller: 'CentroRemoveController'});
        $routeProvider.when('/centro/plist/:page?/:rpp?', {templateUrl: 'js/centro/plist.html', controller: 'CentroPListController'});
        //------------        
     
        $routeProvider.when('/usuario/selection/:page/:rpp', {templateUrl: 'js/usuario/selection.html', controller: 'UsuarioSelectionController'});   
        $routeProvider.when('/usuario/new', {templateUrl: 'js/usuario/new.html', controller: 'UsuarioNewController'});
        $routeProvider.when('/usuario/edit/:id', {templateUrl: 'js/usuario/edit.html', controller: 'UsuarioEditController'});
        $routeProvider.when('/usuario/plist/:page?/:rpp?', {templateUrl: 'js/usuario/plist.html', controller: 'UsuarioPListController'});
        $routeProvider.when('/usuario/remove/:id', {templateUrl: 'js/usuario/remove.html',   controller: 'UsuarioRemoveController'});
        $routeProvider.when('/usuario/view/:id', {templateUrl: 'js/usuario/view.html', controller: 'UsuarioViewController'});

        //------------
        $routeProvider.otherwise({redirectTo: '/'});

        //claves ajenas: usar un módulo compartido para apuntarse la url de llamada: http://stackoverflow.com/questions/12008908/how-can-i-pass-variables-between-controllers-in-angularjs
        //ejemplo claves ajenas con objeto promesa: http://stackoverflow.com/questions/14530251/angular-js-model-relationships

    }]);

var moduloSistema = angular.module('systemControllers', []);
var moduloEmpresa = angular.module('empresaControllers', []);
var moduloCentro = angular.module('centroControllers', []);
var moduloUsuario = angular.module('usuarioControllers', []);
var moduloTipodocumento = angular.module('tipodocumentoControllers', []);

