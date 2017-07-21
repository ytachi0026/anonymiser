const request = require('superagent');

const test = 'http://localhost:8080/cods?description=hola';

const uploadFileRest = 'http://localhost:8080/api/upload';
const fileToAnonymise = 'fileToAnonymise';

window.anonymiseFile = function() {
  let files = document.getElementById(fileToAnonymise).files;
  let formData = new FormData();

  for (var key in files) {
    console.log(key);

    // is the item a File?
    if (files.hasOwnProperty(key) && files[key] instanceof File) {
      formData.append(key, files[key]);
    }
  }

  request
    .post(uploadFileRest)
    .send(formData)
    .end(function(err, res) {
      console.log(res.text);
      console.log('d');
    });

};

window.test = function() {
  request
    .get(test)
    .end(function(err, res) {
      console.log(res.text);
    });

};
