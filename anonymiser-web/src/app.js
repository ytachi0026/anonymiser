import $ from 'jquery';
import {
  hash256Information
} from './sha256';
import {
  cwtSensitive
} from './ent/data';
import {
  downloadCSVFile
} from './download';

const request = require('superagent');
const papa = require('papaparse');

export const greetings = () => {
  console.log('Testing the library for PEACH. KKK');
};

export const processFile = () => {
  let $fileToBeProcessed = $('#fileToAnonymise')[0].files[0];
  let $cancerDataType = $("input[name='cancerDataType']:checked").val();
  console.log($fileToBeProcessed);
  if ($fileToBeProcessed) {
    console.log('BEGIN.');
    console.log($cancerDataType);
  } else {
    alert('Please, select a file.');
  }
};

export const readFile = (evt) => {
  let files = evt.target.files;
  let file = files[0];
  console.log(file);
  let reader = new FileReader();
  reader.onload = (event) => {
    let result = event.target.result;

    papa.parse(result, {
      header: true,
      complete: (results) => {
        results.data.forEach((item, index) => {
          cwtSensitive.forEach((identifier) => {
            if (item[identifier]) {
              item[identifier] = hash256Information(item[identifier]);
            }
          });
        });

        let csvAnonimised = papa.unparse(results.data);
        downloadCSVFile('test.csv', csvAnonimised);
      },
    });
  };

  reader.readAsText(file);
};

//Previous version, where the file was uploaded to the server and processed there
const downloadFile = 'http://localhost:8080/api/download';
const uploadFileRest = 'http://localhost:8080/api/upload';
export const downloadServerProcessedAnonymisedFile = () => {
  request
    .get(downloadFile)
    .end((err, res) => {
      console.log('Error');
      console.log(err);
      console.log('Repsonse');
      console.log(res);
    });
};

export const uploadToServerTheFileToBeAnonymised = () => {
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
