import React, { Component } from 'react';
import Header from './Header';
import Timeline from './Timeline';
import PropTypes from 'prop-types';

class TimelineBox extends Component {
  render() {
    return (
      <div id="root">
        <div className="main">

          <Header store={this.context.store}/>
          <Timeline login={this}/>

        </div>
      </div>
    );
  }
}

TimelineBox.contextTypes = {
  store : PropTypes.object.isRequired
}

export default TimelineBox;
