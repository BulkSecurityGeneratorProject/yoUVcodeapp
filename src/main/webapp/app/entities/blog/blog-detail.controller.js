(function() {
    'use strict';

    angular
        .module('yoUVcodeApp')
        .controller('BlogDetailController', BlogDetailController);

    BlogDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Blog', 'Category', 'SubCategory', 'BlogStatus', 'User'];

    function BlogDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Blog, Category, Subcategory, BlogStatus, User) {
        var vm = this;

        vm.blog = entity;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('yoUVcodeApp:blogUpdate', function(event, result) {
            vm.blog = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
