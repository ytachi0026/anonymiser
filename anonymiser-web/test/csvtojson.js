const csv = require('csvtojson');
const filePath = '/Users/ytachi/swprojects/PEACH-anonymiser/cwt.csv';
const crypto = require('crypto');
const encoding = 'hex';

let info = [];

const cosdSensitive = ['NHS Number', 'Local Patient Identifier', 'Person Family Name',
  'Person Family Name (At Birth)', 'Patient Usual Address (At Diagnosis)',
  'Organisation Code (Code of Provider)',
];
const cwtSensitive = ['NHS Number', 'Patient Pathway Identifier (PPI)',
  'Organisation Code (PPI Identifier)', 'Site Code (of Provider Consultant upgrade)',
  'Site Code (of Provider First Seen)', 'Site Code (of Provider Decision To Treat Cancer)',
  'Site Code (of Treatment Start Date Cancer)',
];

const hashInformation = (target) => {
  let hash = crypto.createHash('sha256');
  hash.update(target);
  return hash.digest(encoding);
};

const anonymiseFile = (filePath) => {
  csv().fromFile(filePath).on('json', (jsonObj) => {
      // combine csv header row and csv line to a json object
      // jsonObj.a ==> 1 or 4
      console.log(jsonObj);
      info.push(jsonObj);
    })
    .on('done', (error) => {
      info.forEach((item, index) => {
        cwtSensitive.forEach((identifier) => {
          item[identifier] = hashInformation(item[identifier]);
        });
      });
      console.log(info);
    });
};

anonymiseFile(filePath);
