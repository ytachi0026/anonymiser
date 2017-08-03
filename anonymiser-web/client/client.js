import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, hashHistory } from 'react-router';

import Layout from './components/Layout';
import CSVAnonymiser from './components/CSVAnonymiser';
import COSDAnonymiser from './components/COSDAnonymiser';

import './sass/global.scss';

const app = document.getElementById('app-content');

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={Layout}>
      <IndexRoute component={CSVAnonymiser}></IndexRoute>
      <Route path="cosdanonymiser" name="cosdanonymiser" component={COSDAnonymiser}></Route>
    </Route>
  </Router>, app);
