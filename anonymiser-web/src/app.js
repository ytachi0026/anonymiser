import $ from 'jquery';
import { anonymiseCWTdata, anonymiseCOSDdata } from './anonymiser';

export const greetings = () => {
  console.log('Testing the library for PEACH.');
};

export const processFile = () => {
  let $fileToBeProcessed = $('#fileToAnonymise')[0].files[0];
  let $cancerDataType = $("input[name='cancerDataType']:checked").val();
  console.log($fileToBeProcessed);
  if ($fileToBeProcessed) {
    console.log('BEGIN.');
    console.log($cancerDataType);
    if ($cancerDataType == 'CWT') {
      console.log('CWT processing');
      anonymiseCWTdata($fileToBeProcessed);
    } else if ($cancerDataType == 'COSD') {
      console.log('COSD processing');
      anonymiseCOSDdata($fileToBeProcessed);
    } else if ($cancerDataType == 'SACT') {
      alert('SACT to be implemented.');
    }
  } else {
    alert('Please, select a file.');
  }
};
