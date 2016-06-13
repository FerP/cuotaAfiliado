
'use strict';

//var appName = 'AjaxStockNg';

var cuotaAfiliado = angular.module('myApp', [
    'ngRoute',
    'Filters',
    'Services',
    'Directives',
    'systemControllers',
    'centroControllers',
    'sectorControllers',
    'empresaControllers',
    'provinciaControllers',
    'comarcaControllers',
    'municipioControllers',
    'cuotaControllers',
    'afiliadoControllers',
    'reciboControllers',
    'pagoControllers',
    'PagoAfiliadoControllers',
    'ReciboAfiliadoControllers',
//    'usuarioControllers',
//    'tipodocumentoControllers',
    'ui.bootstrap',
    'ngSanitize'
]);

cuotaAfiliado.config(['$routeProvider', function ($routeProvider) {

$routeProvider.when('/', {templateUrl: 'js/system/home.html', controller: 'HomeController'});

$routeProvider.when('/home', {templateUrl: 'js/system/home.html', controller: 'HomeController'});

$routeProvider.when('/empresa/view/:id', {templateUrl: 'js/empresa/view.html', controller: 'EmpresaViewController'});
$routeProvider.when('/empresa/new/', {templateUrl: 'js/empresa/new.html', controller: 'EmpresaNewController'});
$routeProvider.when('/empresa/edit/:id', {templateUrl: 'js/empresa/edit.html', controller: 'EmpresaEditController'});
        $routeProvider.when('/empresa/remove/:id', {templateUrl: 'js/empresa/remove.html', controller: 'EmpresaRemoveController'});
        $routeProvider.when('/empresa/plist/:page?/:rpp?', {templateUrl: 'js/empresa/plist.html', controller: 'EmpresaPListController'});
        $routeProvider.when('/empresa/selection/:page/:rpp', {templateUrl: 'js/empresa/selection.html', controller: 'EmpresaSelectionController'});
        //------------ 



        $routeProvider.when('/centro/view/:id', {templateUrl: 'js/centro/view.html', controller: 'CentroViewController'});
        $routeProvider.when('/centro/new', {templateUrl: 'js/centro/new.html', controller: 'CentroNewController'});
        $routeProvider.when('/centro/edit/:id', {templateUrl: 'js/centro/edit.html', controller: 'CentroEditController'});
        $routeProvider.when('/centro/remove/:id', {templateUrl: 'js/centro/remove.html', controller: 'CentroRemoveController'});
        $routeProvider.when('/centro/plist/:page?/:rpp?', {templateUrl: 'js/centro/plist.html', controller: 'CentroPListController'});
        $routeProvider.when('/centro/selection/:page/:rpp', {templateUrl: 'js/centro/selection.html', controller: 'CentroSelectionController'});
         //------------        
        
        
        $routeProvider.when('/sector/new', {templateUrl: 'js/sector/new.html', controller: 'SectorNewController'});
        $routeProvider.when('/sector/edit/:id', {templateUrl: 'js/sector/edit.html', controller: 'SectorEditController'});
        $routeProvider.when('/sector/remove/:id', {templateUrl: 'js/sector/remove.html', controller: 'SectorRemoveController'});
        $routeProvider.when('/sector/view/:id', {templateUrl: 'js/sector/view.html', controller: 'SectorViewController'});
        $routeProvider.when('/sector/plist/:page?/:rpp?', {templateUrl: 'js/sector/plist.html', controller: 'SectorPListController'});
        $routeProvider.when('/sector/selection/:page/:rpp', {templateUrl: 'js/sector/selection.html', controller: 'SectorSelectionController'});
        
        //------------        
        
          //------------        

        $routeProvider.when('/provincia/selection/:page/:rpp', {templateUrl: 'js/provincia/selection.html', controller: 'ProvinciaSelectionController'});
        $routeProvider.when('/provincia/new', {templateUrl: 'js/provincia/new.html', controller: 'ProvinciaNewController'});
        $routeProvider.when('/provincia/edit/:id', {templateUrl: 'js/provincia/edit.html', controller: 'ProvinciaEditController'});
        $routeProvider.when('/provincia/remove/:id', {templateUrl: 'js/provincia/remove.html', controller: 'ProvinciaRemoveController'});
        $routeProvider.when('/provincia/view/:id', {templateUrl: 'js/provincia/view.html', controller: 'ProvinciaViewController'});
        $routeProvider.when('/provincia/plist/:page?/:rpp?', {templateUrl: 'js/provincia/plist.html', controller: 'ProvinciaPListController'});
        //------------

          

        $routeProvider.when('/comarca/selection/:page/:rpp', {templateUrl: 'js/comarca/selection.html', controller: 'ComarcaSelectionController'});
        $routeProvider.when('/comarca/new', {templateUrl: 'js/comarca/new.html', controller: 'ComarcaNewController'});
        $routeProvider.when('/comarca/edit/:id', {templateUrl: 'js/comarca/edit.html', controller: 'ComarcaEditController'});
        $routeProvider.when('/comarca/remove/:id', {templateUrl: 'js/comarca/remove.html', controller: 'ComarcaRemoveController'});
        $routeProvider.when('/comarca/view/:id', {templateUrl: 'js/comarca/view.html', controller: 'ComarcaViewController'});
        $routeProvider.when('/comarca/plist/:page?/:rpp?', {templateUrl: 'js/comarca/plist.html', controller: 'ComarcaPListController'});
        //------------

        
        

          

        $routeProvider.when('/municipio/selection/:page/:rpp', {templateUrl: 'js/municipio/selection.html', controller: 'MunicipioSelectionController'});
        $routeProvider.when('/municipio/new', {templateUrl: 'js/municipio/new.html', controller: 'MunicipioNewController'});
        $routeProvider.when('/municipio/edit/:id', {templateUrl: 'js/municipio/edit.html', controller: 'MunicipioEditController'});
        $routeProvider.when('/municipio/remove/:id', {templateUrl: 'js/municipio/remove.html', controller: 'MunicipioRemoveController'});
        $routeProvider.when('/municipio/view/:id', {templateUrl: 'js/municipio/view.html', controller: 'MunicipioViewController'});
        $routeProvider.when('/municipio/plist/:page?/:rpp?', {templateUrl: 'js/municipio/plist.html', controller: 'MunicipioPListController'});
        
        
        //------------
        
      
          

        $routeProvider.when('/cuota/selection/:page/:rpp', {templateUrl: 'js/cuota/selection.html', controller: 'CuotaSelectionController'});
        $routeProvider.when('/cuota/new', {templateUrl: 'js/cuota/new.html', controller: 'CuotaNewController'});
        $routeProvider.when('/cuota/edit/:id', {templateUrl: 'js/cuota/edit.html', controller: 'CuotaEditController'});
        $routeProvider.when('/cuota/remove/:id', {templateUrl: 'js/cuota/remove.html', controller: 'CuotaRemoveController'});
        $routeProvider.when('/cuota/view/:id', {templateUrl: 'js/cuota/view.html', controller: 'CuotaViewController'});
        $routeProvider.when('/cuota/plist/:page?/:rpp?', {templateUrl: 'js/cuota/plist.html', controller: 'CuotaPListController'});
        
        
        //------------
        
        
         
        $routeProvider.when('/afiliado/selection/:page/:rpp', {templateUrl: 'js/afiliado/selection.html', controller: 'AfiliadoSelectionController'});
        $routeProvider.when('/afiliado/new/', {templateUrl: 'js/afiliado/new.html', controller: 'AfiliadoNewController'});
        $routeProvider.when('/afiliado/edit/:id', {templateUrl: 'js/afiliado/edit.html', controller: 'AfiliadoEditController'});
        $routeProvider.when('/afiliado/remove/:id', {templateUrl: 'js/afiliado/remove.html', controller: 'AfiliadoRemoveController'});
        $routeProvider.when('/afiliado/view/:id', {templateUrl: 'js/afiliado/view.html', controller: 'AfiliadoViewController'});
        $routeProvider.when('/afiliado/plist/:page?/:rpp?', {templateUrl: 'js/afiliado/plist.html', controller: 'AfiliadoPListController'});
        $routeProvider.when('/pagoafiliado/plist/:page?/:rpp?/:id_afiliado?', {templateUrl: 'js/pagoafiliado/plist.html', controller: 'PagoAfiliadoPListController'});
        
        
        //------------ 
        
        
        $routeProvider.when('/recibo/selection/:page/:rpp', {templateUrl: 'js/recibo/selection.html', controller: 'ReciboSelectionController'});
        $routeProvider.when('/recibo/new/', {templateUrl: 'js/recibo/new.html', controller: 'ReciboNewController'});
        $routeProvider.when('/recibo/edit/:id', {templateUrl: 'js/recibo/edit.html', controller: 'ReciboEditController'});
        $routeProvider.when('/recibo/remove/:id', {templateUrl: 'js/recibo/remove.html', controller: 'ReciboRemoveController'});
        $routeProvider.when('/recibo/view/:id', {templateUrl: 'js/recibo/view.html', controller: 'ReciboViewController'});
        $routeProvider.when('/recibo/plist/:page?/:rpp?', {templateUrl: 'js/recibo/plist.html', controller: 'ReciboPListController'});        
        $routeProvider.when('/reciboafiliado/plist/:page?/:rpp?/:id_recibo?', {templateUrl: 'js/reciboafiliado/plist.html', controller: 'ReciboAfiliadoPListController'});
        
        
        
        //$routeProvider.when('/pagoafiliado/plist/:page?/:rpp?', {templateUrl: 'js/pagoafiliado/plist.html', controller: 'PagoafiliadoPListController'});  

//        $routeProvider.when('/usuario/selection/:page/:rpp', {templateUrl: 'js/usuario/selection.html', controller: 'UsuarioSelectionController'});
//        $routeProvider.when('/usuario/new', {templateUrl: 'js/usuario/new.html', controller: 'UsuarioNewController'});
//        $routeProvider.when('/usuario/edit/:id', {templateUrl: 'js/usuario/edit.html', controller: 'UsuarioEditController'});
//        $routeProvider.when('/usuario/remove/:id', {templateUrl: 'js/usuario/remove.html', controller: 'UsuarioRemoveController'});
//        $routeProvider.when('/usuario/view/:id', {templateUrl: 'js/usuario/view.html', controller: 'UsuarioViewController'});
        
        
        //------------
      
        $routeProvider.when('/pago/selection/:page/:rpp', {templateUrl: 'js/pago/selection.html', controller: 'PagoSelectionController'});
        $routeProvider.when('/pago/new/', {templateUrl: 'js/pago/new.html', controller: 'PagoNewController'});
        $routeProvider.when('/pago/edit/:id', {templateUrl: 'js/pago/edit.html', controller: 'PagoEditController'});
        $routeProvider.when('/pago/remove/:id', {templateUrl: 'js/pago/remove.html', controller: 'PagoRemoveController'});
        $routeProvider.when('/pago/view/:id', {templateUrl: 'js/pago/view.html', controller: 'PagoViewController'});
        $routeProvider.when('/pago/plist/:page?/:rpp?', {templateUrl: 'js/pago/plist.html', controller: 'PagoPListController'}); 
        
        //Rutas pagoAfiliado
        $routeProvider.when('/pagoAfiliado/plist/:page?/:rpp?/:id_afiliado?', {templateUrl: 'js/pagoAfiliado/plist.html', controller: 'PagoAfiliadoPListController'});
        $routeProvider.when('/pagoAfiliado/view/:id', {templateUrl: 'js/pagoAfiliado/view.html', controller: 'PagoAfiliadoViewController'});
        
        //Rutas reciboAfiliado
        $routeProvider.when('/reciboAfiliado/plist/:page?/:rpp?/:id_recibo?', {templateUrl: 'js/reciboAfiliado/plist.html', controller: 'ReciboAfiliadoPListController'});
        $routeProvider.when('/reciboAfiliado/view/:id', {templateUrl: 'js/reciboAfiliado/view.html', controller: 'ReciboAfiliadoViewController'});        
        
        
        
        
        $routeProvider.otherwise({redirectTo: '/'});


    }]);

var moduloSistema = angular.module('systemControllers', []);
var moduloEmpresa = angular.module('empresaControllers', []);
var moduloCentro = angular.module('centroControllers', []);
var moduloSector = angular.module('sectorControllers', []);
var moduloProvincia = angular.module('provinciaControllers', []);
var moduloComarca = angular.module('comarcaControllers', []);
var moduloMunicipio = angular.module('municipioControllers', []);
var moduloCuota = angular.module('cuotaControllers', []);
var moduloAfiliado = angular.module('afiliadoControllers', []);
var moduloRecibo = angular.module('reciboControllers', []);
var moduloPago = angular.module('pagoControllers', []);
var moduloPagoAfiliado = angular.module('PagoAfiliadoControllers', []);
var moduloReciboAfiliado = angular.module('ReciboAfiliadoControllers', []);


//var moduloUsuario = angular.module('usuarioControllers', []);
//var moduloTipodocumento = angular.module('tipodocumentoControllers', []);


