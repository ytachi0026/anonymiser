import React from 'react';
import PeachLogo from '../../static/images/peach-logo.png';
import './Header.scss';

export default class Header extends React.Component {
  constructor() {
    super();
  }

  render() {
    return (
      <div className='header'>
        <div className='background-color'>
        <a className="navbar-brand" href="#">
          <img src = {PeachLogo}/>
        </a>
        </div>
        <h1>Anonymiser</h1>
        <h4>A tool to pseudo-anonymise data.</h4>
      </div>
    );
  }
}
