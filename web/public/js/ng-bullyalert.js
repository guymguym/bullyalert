(function() {

	// define the angular module
	var bullyalert_app = angular.module('bullyalert_app', []);


	bullyalert_app.controller('BullyCtrl', [
		'$scope', '$http', '$location', BullyCtrl
	]);

	function BullyCtrl($scope, $http, $location) {
		$scope.location = $location;

		$scope.analyze = function() {
			$scope.last_query = $scope.query;
			$scope.last_result = '';
			$scope.last_error = null;
			$http({
				method: 'POST',
				url: '/api/analyze',
				data: {
					query: $scope.last_query
				}
			}).then(function(res) {
				$scope.last_result = res.data;
			}, function(err) {
				$scope.last_error = err;
			});
		}

	}

})();