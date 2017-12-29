import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import TimelineBox from './componentes/TimelineBox';
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Login from './componentes/Login';
import Logout from './componentes/Logout';

import './css/timeline.css';
import './css/reset.css';
import './css/login.css';

function verificaAutenticacao(){

  if((localStorage.getItem('auth-token') != null)){
       return true;
  }
}

ReactDOM.render(
  (<Router>
      <App>
        <Switch>
            <Route exact path="/" component={Login}/>
            <Route path="/timeline/:login" component={TimelineBox}/>
            <Route path="/timeline" render={() => (
                verificaAutenticacao() ? (
                    <TimelineBox/>
                ) : (
                    <Redirect to="/?msg=Você precisa estar logado para acessar a Timeline!"/>
                )
            )}/>
            <Route path="/logout" component={Logout}/>
        </Switch>
      </App>
  </Router>),
  document.getElementById('root')
);

registerServiceWorker();
