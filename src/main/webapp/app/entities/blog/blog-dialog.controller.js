(function() {
    'use strict';

    angular
        .module('yoUVcodeApp')
        .controller('BlogDialogController', BlogDialogController);

    BlogDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Blog', 'Category', 'SubCategory', 'BlogStatus', 'User'];

    function BlogDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Blog, Category, SubCategory, BlogStatus, User) {
        var vm = this;

        vm.blog = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.categories = Category.query();
        vm.subcategories = SubCategory.query();
        vm.blogstatuses = BlogStatus.query();
        vm.users = User.query();
        var quill = {};

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        $timeout(function (){
          var el = $('#editor-container').get(0);
          quill = new Quill(el, {
        	  modules: {
        		    toolbar: [
        		      ['bold', 'italic'],
        		      ['link', 'blockquote', 'code-block', 'image'],
        		      [{ list: 'ordered' }, { list: 'bullet' }]
        		    ]
        		  },
            placeholder: 'Enter the description...',
            theme: 'snow'  // or 'bubble'
          });
          quill.setText(vm.blog.description);
        }, 100);

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            vm.blog.description = quill.getText();
            vm.blog.description = vm.blog.description.slice(0, -1);
            if (vm.blog.id !== null) {
              console.log("quill->Text "+quill.getText());
                Blog.update(vm.blog, onSaveSuccess, onSaveError);
            } else {
            	vm.blog.hits=1;
            	vm.blog.blogUrl=vm.blog.blogTitle;
                Blog.save(vm.blog, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('yoUVcodeApp:blogUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.publishDate = false;
        vm.datePickerOpenStatus.created_date = false;
        vm.datePickerOpenStatus.last_chng_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }


    }
})();
