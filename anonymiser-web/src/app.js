import $ from 'jquery';
const request = require('superagent');

const downloadFile = 'http://localhost:8080/api/download';
const uploadFileRest = 'http://localhost:8080/api/upload';

export const readFileFromHTML = () => {
  let oFile = $('#fileToAnonymise').files;
  console.log(oFile);
};

export const readFile = (evt) => {
  let files = evt.target.files;
  let file = files[0];
  console.log(file);
  let reader = new FileReader();
  reader.onload = (event) => {
    let result = event.target.result;
    console.log(result);
  };

  reader.readAsArrayBuffer(file);
};

export const downloadAnonymisedVersion = () => {
  request
    .get(downloadFile)
    .end((err, res) => {
      console.log('Error');
      console.log(err);
      console.log('Repsonse');
      console.log(res);
    });
};

export const anonymiseForm = () => {
  event.preventDefault();
  let form = $('#formid')[0];
  let data = new FormData(form); //clear val(''); for the form
  $.ajax({
    type: 'POST',
    enctype: 'multipart/form-data',
    cache: false,
    contentType: false,
    processData: false,
    url: uploadFileRest,
    data: data, // serializes the form's elements.
    success: (data) => {
      console.log(data); // $('#fileResultId').value(data);//href="http://localhost:8080/api/download?filename="
      let urlAnonymitiy = 'http://localhost:8080/api/download?filename=' + data;
      $('#btnDownload').attr('href', urlAnonymitiy);
      $('#btnDownload').attr('download', data);
      $('#btnDownload').show();
    },

    error: (e) => {
      alert(e);
    },
  });
};

export const greetings = () => {
  console.log('Ytalo. Elias.');
};
