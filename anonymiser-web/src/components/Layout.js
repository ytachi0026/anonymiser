import React from 'react';
import Header from './Header';
import FieldToAnonymise from './FieldToAnonymise';
import Footer from './Footer';
import { anonymiseGeneralCSVfile } from '../anonymiser';
import $ from 'jquery';

export default class Layout extends React.Component {
  constructor() {
    super();
    this.state = {
      file: null,
      headers: null,
      anonymityIn: [],
    };
    this.processHeaders = this.processHeaders.bind(this);
    this.anonymiseFile = this.anonymiseFile.bind(this);
  }

  anonymiseFile() {
    anonymiseGeneralCSVfile(this.state.file, this.state.anonymityIn);
  }

  addFieldToBeAnonymised(value) {
    let targets =  this.state.anonymityIn;

    let band = false;
    let position = 0;
    targets.map((item, index) => {
      if (item == value) {
        band = true;
        position = index;
      }
    });
    if (band) {
      targets.splice(position, 1);
    } else {
      targets.push(value);
    }

    this.setState({ file: this.state.file, headers: this.state.headers, anonymityIn: targets, });
  }

  processHeaders() {
    let $fileToBeProcessed = $('#inputFileToProcess')[0].files[0];

    let reader = new FileReader();
    reader.onload = (event) => {
      let result  = event.target.result;
      let lines =  result.split('\n');
      this.setState({ headers: lines[0], file: $fileToBeProcessed, });
    };

    reader.readAsText($fileToBeProcessed);
  }

  render() {
    return (
      <div>
        <Header/>

        <div>
          <label>Please select a file to be anonymised.</label>
          <input id='inputFileToProcess' accept='.csv' type="file" onChange={this.processHeaders}/>
        </div>
        <hr></hr>
        <FieldToAnonymise headers={this.state.headers}
          updateAnonymityIn={this.addFieldToBeAnonymised.bind(this)}/>
        <button className='anonymise-btn' onClick={this.anonymiseFile}>Anonymise</button>
        <Footer/>
      </div>
    );
  }
}
