var module = angular.module('myapp', ['ngResource']);

module.factory('Record', function ($resource) {
    return $resource(':username/records', {username: '@username'});
})
    .controller('RecordsController', function ($scope, Record) {
        var url = function () {
            return {username: $scope.username || 'rplatonov'};
        };

        var update = function () {
            console.log(url());
            $scope.records = Record.query(url());
        };

        $scope.update = update;

        $scope.add = function () {
            var record = new Record();
            record.text = $scope.text;
            record.$save(url(), function (data) {
                $scope.text = "";
                update();
            }, function (e) {
                alert(e.data.message);
            });
        };


        $scope.deleteNote = function (id) {
            Record.delete(angular.extend(url(), {id: id}));
            update();
        };

        update();
    });
