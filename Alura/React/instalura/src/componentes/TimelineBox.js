import React, { Component } from 'react';
import Header from './Header';
import Timeline from './Timeline';
import {createStore, applyMiddleware} from 'redux';
import thunkModdleware from 'redux-thunk';
import {timeline} from '../reducers/timeline';

const store = createStore(timeline, applyMiddleware(thunkModdleware));


class TimelineBox extends Component {
  render() {
    return (
      <div id="root">
        <div className="main">

          <Header/>
          <Timeline login={this} store={store}/>

        </div>
      </div>
    );
  }
}

export default TimelineBox;
