import React from 'react';

export default class Header extends React.Component {
  constructor() {
    super();
  }

  render() {
    return (
      <div>
        <h1>Anonymiser</h1>
        <h2>A tool to pseudo-anonymise data.</h2>
      </div>
    );
  }
}
