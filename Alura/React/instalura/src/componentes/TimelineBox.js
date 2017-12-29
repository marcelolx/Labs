import React, { Component } from 'react';
import Header from './Header';
import Timeline from './Timeline';

class TimelineBox extends Component {
  render() {
    return (
      <div id="root">
        <div className="main">

          <Header/>
          <Timeline login={this}/>

        </div>
      </div>
    );
  }
}

export default TimelineBox;
