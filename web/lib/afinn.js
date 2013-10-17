var fs = require('fs');

function text_to_json(text) {
	var words = {};
	var arr = text.toString().split('\n');
	for (var i = 0; i < arr.length; i++) {
		var line = arr[i].split('\t');
		words[line[0]] = line[1];
	}
	return words;
}

exports.words = null; // will be set after file is read
fs.readFile('./AFINN/AFINN-111.txt', function(err, data) {
	if (err) {
		console.error(err);
		process.abort();
		return;
	}
	exports.words = text_to_json(data);
});