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
        vm.blog.hits = ++vm.blog.hits;
        
        vm.save = save;
        save();
        
        function save () {
            vm.isSaving = true;
            if (vm.blog.id !== null) {
                Blog.update(vm.blog, onSaveSuccess, onSaveError);
            } else {
                Blog.save(vm.blog, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('yoUVcodeApp:blogUpdate', result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        var unsubscribe = $rootScope.$on('yoUVcodeApp:blogUpdate', function(event, result) {
            vm.blog = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
