(function() {

	// define the angular module
	var bullyalert_app = angular.module('bullyalert_app', []);


	bullyalert_app.controller('BullyCtrl', [
		'$scope', '$http', '$location', BullyCtrl
	]);

	function BullyCtrl($scope, $http, $location) {
		$scope.location = $location;

		$scope.query = '@elizabeth_tice';
		$scope.query = '@MileyCyrus';
		$scope.query = '@yahoomail';
		$scope.query = '@KarenGravanoVH1';
		$scope.query = '@jenny_sad';

		$scope.analyze = function() {
			d3.select("#graph").select("svg").remove();
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
				var svg = d3.select("#graph")
					.append("svg")
					.attr("width", 500)
					.attr("height", 100);
				svg.selectAll("circle").data(res.data.messages).enter();
			}, function(err) {
				$scope.last_error = err;
			});
		};

		$scope.stringify = function(o) {
			return JSON.stringify(o);
		};

	}

})();