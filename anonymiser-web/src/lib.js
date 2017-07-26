const fs = require('fs'); //Call the java library here in the javascript.
const readline = require('readline');
const csv = require('csv-streamify');
const parser = csv();

const fileName = '/Users/ytachi/swprojects/PEACH-anonymiser/cwt.csv';

const test = () => {
  console.log(hashInformation('sexo'));
  parser.on('data', (line) => {
    console.log(line);
  });
  fs.createReadStream(fileName).pipe(parser);
};

const readFile = () => {
  let reading = readline.createInterface({
    input: fs.createReadStream(fileName),
  });
  reading.on('line', (line) => {
    console.log('Reading line:' + line);
  });
};

test(); //readFile();
