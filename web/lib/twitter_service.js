/* jshint -W099 */
var async = require('async');
var twitter = require('twitter');
var _ = require('underscore');

var consumer_key = '1RsaHtdnJxMWAQIU5gah5Q';
var consumer_secret = 'g7KNom2T55oiSwp66BwGi2PbzwwzP45I9Juvx3E8Q';
var request_token_url = 'https://api.twitter.com/oauth/request_token';
var authorize_url = 'https://api.twitter.com/oauth/authorize';
var access_token_url = 'https://api.twitter.com/oauth/access_token';
var access_token_key = '102069611-nhCgrP7Yhs0mpciDqhtcoTms1oFIocxQtiNymV4r';
var access_token_secret = 'YbrTeF7x1l2gCyZnIjdeoQ63cowVHalsGdDG4MncI';
var access_level = 'Read-only';

console.log('test');

var util = require('util'),
	twitter = require('twitter');
var twit = new twitter({
	consumer_key: consumer_key,
	consumer_secret: consumer_secret,
	access_token_key: access_token_key,
	access_token_secret: access_token_secret
});

// var user_info = {};
twit.search('@grolnik', function(data) {
	// console.log(util.inspect(data));
	var tweets = data.statuses;
	_.each(data.statuses, function(tweet) {

		console.log('----------------------------');
		// in_reply_to_screen_name
		// console.log('tweet: ', tweet);
		var ctweet = _.pick(tweet,
			'id',
			'text',
			'created_at',
			'in_reply_to_screen_name',
			'retweet_count',
			'geo',
			'coordinates',
			'place',
			'favorite_count'
		);
		// console.log('ctweet.user: ', ctweet.user);
		var user_info = _.pick(tweet.user,
			'id',
			'name',
			'screen_name',
			'followers_count',
			'friends_count',
			'location',
			'geo_enabled',
			'statuses_count',
			'profile_image_url'
		);
		console.log('ctweet: ', ctweet);
		console.log('user_info: ', user_info);

		// ctweet.user: {
		// 	id: 10124722,
		// 	id_str: '10124722',
		// 	name: 'Yacov Bar-Haim',
		// 	screen_name: 'Yacovb',
		// 	location: 'ÜT: 45.689499,-0.317699',
		// 	description: '',
		// 	url: null,
		// 	entities: {
		// 		description: {
		// 			urls: []
		// 		}
		// 	},
		// 	protected: false,
		// 	followers_count: 75,
		// 	friends_count: 193,
		// 	listed_count: 0,
		// 	created_at: 'Sat Nov 10 12:27:06 +0000 2007',
		// 	favourites_count: 32,
		// 	utc_offset: 10800,
		// 	time_zone: 'Jerusalem',
		// 	geo_enabled: true,
		// 	verified: false,
		// 	statuses_count: 143,
		// 	lang: 'en',
		// 	contributors_enabled: false,
		// 	is_translator: false,
		// 	profile_background_color: 'C0DEED',
		// 	profile_background_image_url: 'http://abs.twimg.com/images/themes/theme1/bg.png',
		// 	profile_background_image_url_https: 'https://abs.twimg.com/images/themes/theme1/bg.png',
		// 	profile_background_tile: false,
		// 	profile_image_url: 'http://a0.twimg.com/profile_images/1235694143/PICT0081A_normal.JPG',
		// 	profile_image_url_https: 'https://si0.twimg.com/profile_images/1235694143/PICT0081A_normal.JPG',
		// 	profile_link_color: '0084B4',
		// 	profile_sidebar_border_color: 'C0DEED',
		// 	profile_sidebar_fill_color: 'DDEEF6',
		// 	profile_text_color: '333333',
		// 	profile_use_background_image: true,
		// 	default_profile: true,
		// 	default_profile_image: false,
		// 	following: false,
		// 	follow_request_sent: false,
		// 	notifications: false
		// }



		// console.log('ctweet: ', ctweet);
		// _.pick({name: 'moe', age: 50, userid: 'moe1'}, 'name', 'age');
		// => {name: 'moe', age: 50}


		// console.log('tweet.text: ',tweet.text);
	});


});