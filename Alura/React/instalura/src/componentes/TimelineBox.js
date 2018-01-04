import React, { Component } from 'react';
import Header from './Header';
import Timeline from './Timeline';
import timelineStore from '../logicas/TimelineStore';
import {createStore} from 'redux';

//REDUCER (Função redutora)
function timeline(state = [], action){
    if (action.type === 'LISTAGEM') {
      return action.fotos;
    }

    return state;
}

const store = createStore(timeline);


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
