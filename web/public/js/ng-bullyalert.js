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
				var width = 500;
				var height = 50;
				var pad = 50;
				var messages = res.data.messages;
				var first_id = messages[0].id;
				var last_id = messages[0].id;
				var range_id = last_id - first_id;
				var range_id_width = width / range_id;
				console.log('IDS', first_id, last_id, range_id);
				var svg = d3.select("#graph")
					.append("svg")
					.attr("width", width + pad + pad)
					.attr("height", height + pad + pad);
				var circles = svg.selectAll("circle")
					.data(messages)
					.enter()
					.append("circle");
				circles.attr("cx", function(msg, i) {
					return ((msg.id - first_id) * range_id_width) + pad;
				});
				circles.attr("cy", function(msg, i) {
					return (msg.level * height) + pad;
				});
				circles.attr("r", function(msg, i) {
					return msg.retweet_count >= 10 ? pad : ((msg.retweet_count + 1) * pad / 10);
				});
			}, function(err) {
				$scope.last_error = err;
			});
		};

		$scope.stringify = function(o) {
			return JSON.stringify(o);
		};

	}

})();