(function() {
    'use strict';

    angular
        .module('yoUVcodeApp')
        .directive('pageRibbon', pageRibbon);

    pageRibbon.$inject = ['ProfileService', '$rootScope', '$translate'];

    function pageRibbon(ProfileService, $rootScope, $translate) {
        /*var directive = {
            replace : true,
            restrict : 'AE',
            template : '<div class="ribbon hidden"><a href="" translate="global.ribbon.{{ribbonEnv}}">{{ribbonEnv}}</a></div>',
            link : linkFunc
        };*/
        var directive = {
                replace : true,
                restrict : 'AE',
                template : '<div class="ribbon hidden"><a href="" translate="{{ribbonEnv}}">{{ribbonEnv}}</a></div>',
                link : linkFunc
            };

        return directive;

        function linkFunc(scope, element, attrs) {
            ProfileService.getProfileInfo().then(function(response) {
                if (response.ribbonEnv) {
                    scope.ribbonEnv = response.ribbonEnv;
                    scope.ribbonEnv = "alpha";
                    element.addClass(response.ribbonEnv);
                    element.removeClass('hidden');
                }
            });
        }
    }
})();
