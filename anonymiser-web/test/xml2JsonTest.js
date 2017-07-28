const fs = require('fs');
const path = require('path');
const xml2js = require('xml2js');
const papa = require('papaparse');

//const jsonfile = require('jsonfile');
// const file = path.join(path.resolve(__dirname), 'data.json');
// console.log(file);
// const obj = {
//   name: 'JP',
// };
const parser = new xml2js.Parser({
  explicitArray: false,
  mergeAttrs: true,
  explicitRoot: false,
});
const sampleCOSD = path.join(path.resolve(__dirname, '../sample'), 'sampleCOSD.xml');
console.log(sampleCOSD);

fs.readFile(sampleCOSD, (err, data) => {
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
    let csv = papa.unparse(lungCancer);
    console.log(csv);
    console.log('Done');
  });

  // jsonfile.writeFile(file, json, (err) => {
  //   console.error(err);
  // });
});

function listAllProperties(o) {
  var objectToInspect;
  var result = [];
  for (objectToInspect = o; objectToInspect !== null;
    objectToInspect = Object.getPrototypeOf(objectToInspect)) {
    result = result.concat(Object.getOwnPropertyNames(objectToInspect));
  }

  return result;
}
