var async = require('async');
var _ = require('underscore');
var afinn = require('./afinn');
var twitter = require('./twitter_service');
var analyze_tweet = require('./analyze_tweet');

function analyze(args, callback) {
	console.log('ANALYZE ARGS', args);

	return async.waterfall([

		function(next) {
			return twitter.tweet_search(args.query, function(err, messages, user_map) {
				if (err) {
					return next(err);
				}
				return next(null, messages, user_map);
			});
		},

		function(messages, user_map, next) {
			var i;

			// calculate level per twit
			for (i = 0; i < messages.length; i++) {
				analyze_tweet.get_tweet_score(messages[i], args.query);
			}

			// group the messages by user id
			var group_by_user_id = _.groupBy(messages, function(msg) {
				return msg.user_id;
			});

			// process each user and detect bullys
			var users = [];
			var total_level = 0;

			function sort_reverse_level(o) {
				return -o.level;
			}
			for (var user_id in group_by_user_id) {
				var user_messages = _.sortBy(group_by_user_id[user_id], sort_reverse_level);
				var user_level = 0;
				for (i = 0; i < user_messages.length; i++) {
					var msg = user_messages[i];
					user_level += msg.level;
				}
				total_level += user_level;
				if (user_level >= 10) {
					user_level = 10;
				}
				user_level /= 10;
				var user = {
					info: user_map[user_id],
					level: user_level,
					messages: user_messages,
				};
				if (user_level > 0.2) {
					users.push(user);
				}
			}
			users = _.sortBy(users, sort_reverse_level);
			if (total_level >= 20) {
				total_level = 20;
			}
			total_level /= 20;

			return next(null, {
				level: total_level,
				users: users,
				messages: messages
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