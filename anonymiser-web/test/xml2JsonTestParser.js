const fs = require('fs');
const path = require('path');
const xml2js = require('xml2js');
const papa = require('papaparse');
const saxpath = require('saxpath');
const sax = require('sax');
const util = require('util');
const get = require('get-value');

const sampleCOSD = path.join(path.resolve(__dirname, '../sample'), 'sampleCOSD.xml');

const saxParser = sax.createStream(true);
const streamer = new saxpath.SaXPath(saxParser, '//COSDRecord/Lung');
const fileStream = fs.createReadStream(sampleCOSD);

const parser = new xml2js.Parser({
  explicitArray: false,
  mergeAttrs: true,
  explicitRoot: false,
});
const cancerDataLung = [];
streamer.on('match', (xml) => {
  parser.parseString(xml, (err, result) => {
    let infoLung = new Object();
    console.dir(result);

    //No. tumours diagnosed per month
    infoLung.PrimaryDiagnosis =
      get(result, 'LungCore.LungCoreLinkageDiagnosis.PrimaryDiagnosis.code');
    infoLung.ClinicalDateCancerDiagnosis =
      get(result, 'LungCore.LungCoreLinkageDiagnosis.ClinicalDateCancerDiagnosis');
    infoLung.SiteCodeOfDiagnosis =
      get(result, 'LungCore.LungCoreDiagnosis.SiteCodeOfDiagnosis.extension');

    //No. of recurrences diagnosed in the calendar month
    infoLung.AgreedDateOfRecurrence =
      get(result, 'LungCore.LungCoreLinkageDiagnosis.AgreedDateOfRecurrence');

    //Single Pathway
    infoLung.FirstCancerSpecSiteCode =
      get(result, 'LungCore.LungCoreReferralAndPatientPathway.FirstCancerSpecSiteCode.extension');
    infoLung.LungCoreReferralAndPatientPathwayFirstProviderSiteCode =
      get(result, 'LungCore.LungCoreReferralAndPatientPathway.FirstProviderSiteCode.extension');

    //No. with a treatment record submitted
    infoLung.CancerTreatmentEventType =
      get(result, 'LungCore.LungCoreTreatment.CancerTreatmentEventType.code');
    infoLung.LungCoreTreatmentFirstProviderSiteCode =
      get(result, 'LungCore.LungCoreTreatment.CancerTreatmentModality.code');
    infoLung.CancerTreatmentStartDate =
      get(result, 'LungCore.LungCoreTreatment.CancerTreatmentStartDate');
    infoLung.ProviderTreatmentStartSiteCode =
      get(result, 'LungCore.LungCoreTreatment.ProviderTreatmentStartSiteCode.extension');

    //No. with a basis of diagnosis
    infoLung.BasisOfCancerDiagnosis =
      get(result, 'LungCore.LungCoreDiagnosis.BasisOfCancerDiagnosis.code');

    //No. patients by Souce of Referral
    infoLung.SourceOfReferralOutPatients =
      get(result,
        'LungCore.LungCoreReferralAndPatientPathway.SourceOfReferralOutPatients.code');

    //No. patients by Morphology code
    infoLung.MorphologyICDODiagnosis =
      get(result, 'LungCore.LungCoreDiagnosis.MorphologyICDODiagnosis.code');
    infoLung.MorphologySNOMEDDiagnosis =
      get(result, 'LungCore.LungCoreDiagnosis.MorphologySNOMEDDiagnosis.code');

    //No. had contact with CNS
    infoLung.ClinicalNurseIndCode =
      get(result, 'LungCore.LungCoreDiagnosis.ClinicalNurseIndCode.code');

    //No. patients aged under 25
    infoLung.Birthdate =
      get(result, 'LungCore.LungCoreLinkagePatientId.Birthdate');

    //No. with full stage at diagnosis
    infoLung.FinalPreTreatmentTNMStageGrouping =
      get(result, 'LungCore.LungCoreStaging.FinalPreTreatmentTNMStageGrouping');

    //No. discussed at MDT Meeting
    infoLung.TeamMeetingDate =
      get(result, 'LungCore.LungCoreMultidisciplinaryTeamMeetings.TeamMeetingDate');

    //No. with Performance Status
    infoLung.AdultPerformanceStatus =
      get(result, 'LungCore.LungCoreDiagnosis.AdultPerformanceStatus.code');

    infoLung.BronchoscopyPerformedIndicator =
      get(result, 'LungCore.LungCoreTreatment.LungCoreSurgeryAndOtherProcedures.Bronchoscopy.PerformedIndicator.code');

    infoLung.CancerImagingModality =
      get(result, 'LungCore.LungCoreImaging.CancerImagingModality.code');

    infoLung.SmokingStatus =
      get(result, 'LungCore.LungCoreCancerCarePlan.LungCancerCarePlan.SmokingStatus.code');

    infoLung.AmountForcedExpVol1Sec =
      get(result, 'LungCore.LungCoreCancerCarePlan.LungCancerCarePlan.AmountForcedExpVol1Sec.value');

    infoLung.PercentForcedExpVol1Sec =
      get(result, 'LungCore.LungCoreCancerCarePlan.LungCancerCarePlan.PercentForcedExpVol1Sec.value');

    cancerDataLung.push(infoLung);

  });
});

streamer.on('end', () => {
  console.log('Final Result');
  console.log(cancerDataLung);
});

fileStream.pipe(saxParser);
