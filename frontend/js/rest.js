


	var request = require('./superagent');

	var url = 'http://localhost:8080/cods?description=hola';
	/*var url = 'https://api.github.com/repos/visionmedia/superagent'; */
	request
		.get(url)
		.end(function(err, res) {
			console.log(res.text);
		});

