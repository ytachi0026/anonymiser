import React from 'react';
import $ from 'jquery';
import { processCOSDFile } from '../api/cosdanonymiser';

export default class COSDAnonymiser extends React.Component {
  anonymiseFile() {
    let $fileToBeProcessed = $('#inputCOSDFileToProcess')[0].files[0];

    let reader = new FileReader();
    reader.onload = (event) => {
      let result  = event.target.result;
      processCOSDFile(result, $fileToBeProcessed.name);
    };

    reader.readAsText($fileToBeProcessed);
  }

  render() {
    return <div>
      <h3>Anonymiser for only COSD Oncology data: Lung Cancer. </h3>
      <div>
        <label>Please select a file to be anonymised.</label>
        <br/>
        <input id='inputCOSDFileToProcess' accept='.xml' type="file"/>
      </div>
      <hr></hr>
      <button onClick={this.anonymiseFile}>Anonymise</button>
    </div>;
  }
}
