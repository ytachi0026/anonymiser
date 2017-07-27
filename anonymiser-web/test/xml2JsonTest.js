const fs = require('fs');
const path = require('path');
const parser = require('xml2json');

const jsonfile = require('jsonfile');
const file = path.join(path.resolve(__dirname), 'data.json');
console.log(file);
const obj = {
  name: 'JP',
};

const sampleCOSD = path.join(path.resolve(__dirname), 'sampleCOSD.xml');
console.log(sampleCOSD);

fs.readFile(sampleCOSD, (err, data) => {
  let json = parser.toJson(data);
  console.log('to json->', json);
  jsonfile.writeFile(file, json, (err) => {
    console.error(err);
  });
});
