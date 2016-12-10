(function() {
    'use strict';

    var blogInfo = {
        templateUrl: '/app/components/blog/blog-info.html',
        bindings: {
			blog : '<'
		},
        controller: blogInfoController
    };

    angular
        .module('yoUVcodeApp')
        .component('blogInfo', blogInfo);
   
    blogInfoController.$inject = ['$scope', 'AlertService'];

    function blogInfoController($scope) {
        var ctrl = this;

        $scope.$on('$destroy', function () {
        });
    }
})();
