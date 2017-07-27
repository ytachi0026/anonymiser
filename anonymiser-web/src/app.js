import $ from 'jquery';
import { anonymiseCWTdata } from './anonymiser';

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
    anonymiseCWTdata($fileToBeProcessed);
  } else {
    alert('Please, select a file.');
  }
};
