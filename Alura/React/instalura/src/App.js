import React, { Component } from 'react';

class App extends Component {

  render() {
    return (
      <div id="main">
          {this.props.children}
      </div>
    );
  }
}

export default App;
