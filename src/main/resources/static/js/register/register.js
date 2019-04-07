var app = angular.module('app', []);
app.controller('RegisterCtrl', function($scope,$http) {
 $scope.register=function(){
     var agree=$("input[type='checkbox']:checked");
     if(0==agree.length){
          zeroModal.alert("请选择同意协议");
          return;
     }
     var userName=$scope.checknull($scope.userName);
     var email=$scope.checknull($scope.emailAddr);
     var password=$scope.checknull($scope.password);
     var confirmPassword=$scope.checknull($scope.confirmPassword);
     if(''==userName){
      zeroModal.alert("用户名不能为空");
      return;
     }
     if(''==email){
      zeroModal.alert("邮箱不能为空");
       return;
     }
     if(''==password){
      zeroModal.alert("密码不能为空");
      return;
     }
     if(''==confirmPassword){
      zeroModal.alert("确认密码不能为空");
      return;
     }
     if(password!=confirmPassword){
      zeroModal.alert("两次输入密码不一致");
      return;
     }
     var obj=new Object();
     obj.userName=userName;
     obj.email=email;
     obj.password=password;
     $scope.httpservice(obj);
  }
 $scope.checknull=function(value){
            if('undefined'==typeof(value)){
               return '';
            }else{
               return value;
            }
 }
    $scope.httpservice=function(obj){
     $http({
       method: 'GET',
       params: obj,
       url: '/user/save'
      }).then(function successCallback(response) {

       }, function errorCallback(response) {

     });
   }

});
