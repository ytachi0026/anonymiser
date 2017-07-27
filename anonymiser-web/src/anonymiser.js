const papa = require('papaparse');
import {
  cwtSensitive
} from './ent/data';
import {
  hash256Information
} from './sha256';
import {
  downloadCSVFile
} from './download';

const anonymiseName = (fileName) => {
  console.log('Name to be anonymised');
  return 'anony_' + fileName;
};

export const anonymiseCWTdata = (file) => {
  papa.parse(file, {
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
      downloadCSVFile(anonymiseName(file.name), csvAnonimised);
    },
  });
};

//DEPRECATED
const anonymiseCWT = (file) => {
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
        console.log(csvAnonimised);
        downloadCSVFile('test.csv', csvAnonimised);
      },
    });
  };

  reader.readAsText(file);
};
