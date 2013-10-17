var async = require('async');
var _ = require('underscore');
var afinn = require('./afinn');
var twitter = require('./twitter_service');
var analyze_tweet = require('./analyze_tweet');

function analyze(args, callback) {
	console.log('ANALYZE ARGS', args);

	var users_map = {};

	return async.waterfall([

		function(next) {
			return twitter.tweet_search(args.query, function(err, data) {
				if (err) {
					return next(err);
				}
				return next(null, data.statuses);
			});
			/*
			var num = Math.floor(Math.random() * 100);
			var messages = new Array(num);
			for (var i = 0; i < num; i++) {
				var msg = messages[i] = {
					user: {
						id: Math.floor(Math.random() * 5),
						name: 'bullyname'
					},
					text: 'bla'
				};
			}
			return next(null, messages);
			*/
		},

		function(messages, next) {
			var i, msg;
			console.log(messages);

			// calculate level per twit
			for (i = 0; i < messages.length; i++) {
				msg = messages[i];
				analyze_tweet.get_tweet_score(msg, args.query);
			}

			// group the messages by user id
			var group_by_user = _.groupBy(messages, function(msg) {
				return msg.user.id;
			});

			// process each user and detect bullys
			var total_level = 0;
			var total_count = 0;
			var bullys = [];
			for (var id in group_by_user) {
				var list = group_by_user[id];
				var avg_level = 0;
				for (i = 0; i < list.length; i++) {
					msg = list[i];
					avg_level += msg.level;
					total_level += msg.level;
					total_count++;
				}
				avg_level /= list.length;
				var user = {
					user: list[0].user,
					avg_level: avg_level,
					twits: list,
				};
				if (avg_level > 0.5) {
					bullys.push(user);
				}
				users_map[id] = user;
			}
			total_level /= total_count;

			return next(null, {
				level: total_level,
				bullys: bullys,
			});
		}

	], callback);
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