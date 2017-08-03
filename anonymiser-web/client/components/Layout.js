import React from 'react';
import { Link } from 'react-router';
import './Layout.scss';
import Header from './Header';
import Footer from './Footer';

export default class Layout extends React.Component {

  render() {
    return <div>
      <Header/>
      <ul className="ul-nav">
        <li className="li-nav"><Link className="li-element" to="/">CSV Anonymiser</Link></li>
        <li className="li-nav"><Link className="li-element" to="/cosdanonymiser">COSD Anoymiser</Link></li>
      </ul>
      {this.props.children}
      <Footer/>
    </div>;
  }
}
