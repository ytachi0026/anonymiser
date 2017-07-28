const papa = require('papaparse');
const xml2js = require('xml2js');

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
  return 'anony_' + fileName + '.csv';
};

const parser = new xml2js.Parser({
  explicitArray: false,
  mergeAttrs: true,
  explicitRoot: false,
});

const isValidFile = (file, contenType) => {
  console.log(file);
  let band = true;
  if (file == null) {
    band = false;
  }

  if (file.type != contenType) {
    band = false;
  }

  console.log(band);
  return band;
};

export const anonymiseCOSDdata = (file) => {
  if (!isValidFile(file, 'application/xml')) {
    alert('ERROR: COSD file cannot be processed.');
    return;
  }

  let reader = new FileReader();
  reader.onload = (event) => {
    let data = event.target.result;
    parser.parseString(data, (err, result) => {
      const lungCancer = [];
      result.COSDRecord.forEach((item, index) => {
        //Processing LUNG CANCER
        if (item.hasOwnProperty('Lung')) {
          let lungCore = item.Lung.LungCore;
          let infoLung = new Object();
          infoLung.NHSNumber = lungCore.LungCoreLinkagePatientId.NHSNumber.extension;
          infoLung.PrimaryDiagnosis = lungCore.LungCoreLinkageDiagnosis.PrimaryDiagnosis.code;
          infoLung.TumourLaterality = lungCore.LungCoreLinkageDiagnosis.TumourLaterality.code;
          infoLung.ClinicalDateCancerDiagnosis =
            lungCore.LungCoreLinkageDiagnosis.ClinicalDateCancerDiagnosis;
          lungCancer.push(infoLung);
        }
      });
      console.log(lungCancer);
      let csvAnonimised = papa.unparse(lungCancer);
      console.log(csvAnonimised);
      downloadCSVFile(anonymiseName(file.name), csvAnonimised);
      console.log('Done');
    });
  };

  reader.readAsText(file);
};

export const anonymiseCWTdata = (file) => {
  if (!isValidFile(file, 'text/csv')) {
    alert('ERROR: CWT file cannot be processed.');
    return;
  }

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
