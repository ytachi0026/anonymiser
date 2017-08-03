const fs = require('fs');
const path = require('path');
const xml2js = require('xml2js');
const papa = require('papaparse');
const saxpath = require('saxpath');
const sax = require('sax');
const util = require('util');

const parser = new xml2js.Parser({
  explicitArray: false,
  mergeAttrs: true,
  explicitRoot: false,
});

const sampleCOSD = path.join(path.resolve(__dirname, '../sample'), 'sampleCOSD.xml');

console.log('FILE to be processed');
console.log(sampleCOSD);

fs.readFile(sampleCOSD, (err, data) => {
  parser.parseString(data, (err, result) => {
    const lungCancer = [];
    result.COSDRecord.forEach((item, index) => {
      //Processing LUNG CANCER
      if (item.hasOwnProperty('Lung')) {
        let lungCore = item.Lung.LungCore;
        console.log(lungCore);
        let infoLung = new Object();
        infoLung.PrimaryDiagnosis = lungCore.LungCoreLinkageDiagnosis.PrimaryDiagnosis.code;
        infoLung.ClinicalDateCancerDiagnosis =
          lungCore.LungCoreLinkageDiagnosis.ClinicalDateCancerDiagnosis;
        infoLung.TumourLaterality = lungCore.LungCoreLinkageDiagnosis.TumourLaterality.code;
        infoLung.SiteCodeOfDiagnosis = lungCore.LungCoreDiagnosis.SiteCodeOfDiagnosis.extension;
        lungCancer.push(infoLung);
        console.log('-- END --');
      }
    });
    let csv = papa.unparse(lungCancer);
    console.log('Transformation into CSV');
    console.log(csv);
    console.log('Done');
  });
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
