import React, { Component } from 'react';
import Header from './Header';
import Timeline from './Timeline';
import {createStore, applyMiddleware, combineReducers} from 'redux';
import thunkModdleware from 'redux-thunk';
import {timeline} from '../reducers/timeline';
import {notifica} from '../reducers/header';

const reducers = combineReducers({timeline, notificao: notifica}); //ecma script 6 permite isso, antigo usar timeline:timeline notificao:notifica
const store = createStore(reducers, applyMiddleware(thunkModdleware));

class TimelineBox extends Component {
  render() {
    return (
      <div id="root">
        <div className="main">

          <Header store={store}/>
          <Timeline login={this} store={store}/>

        </div>
      </div>
    );
  }
}

export default TimelineBox;
