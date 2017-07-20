const request = require('superagent');
const test = 'http://localhost:8080/cods?description=hola';

request
  .get(test)
  .end(function (err, res) {
    console.log(res.text);
  });
