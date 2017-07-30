import React from 'react';

export default class FieldToAnonymise extends React.Component {
  constructor() {
    super();
    this.updateAnonymityIn = this.updateAnonymityIn.bind(this);
  }

  updateAnonymityIn(event) {
    console.log('props function');
    console.log(event.target.value);
    this.props.updateAnonymityIn(event.target.value);
  }

  render() {
    let headersOption = [];
    let message = null;

    if (this.props.headers) {
      message = 'Please, select the field that needs to be anonymised';
      headersOption = this.props.headers.split(',');
    }

    return <div className='target-anonymity'>
      <p>{message}</p>
      {headersOption.map((listValue, index) => {
        console.log('');
        return <label key={index}> {listValue} <input type='checkbox'
          key={index} onChange={this.updateAnonymityIn} value={listValue}></input> </label>;
      })}
      </div>;
  }
}
