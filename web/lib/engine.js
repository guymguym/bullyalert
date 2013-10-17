function analyze(args, callback) {
	console.log('ANALYZE ARGS', args);
	return callback(null, {
		level: 0.6
	});
}

exports.analyze = analyze;

exports.analyze_api = function(req, res) {
	analyze(req.body, function(err, result) {
		if (err) {
			return res.json(500, err);
		} else {
			return res.json(200, result);
		}
	});
};