import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import TimelineBox from './componentes/TimelineBox';
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Login from './componentes/Login';
import Logout from './componentes/Logout';
import {createStore, applyMiddleware, combineReducers} from 'redux';
import thunkModdleware from 'redux-thunk';
import {timeline} from './reducers/timeline';
import {notifica} from './reducers/header';
import {Provider} from 'react-redux';

import './css/timeline.css';
import './css/reset.css';
import './css/login.css';

function verificaAutenticacao(){

  if((localStorage.getItem('auth-token') != null)){
       return true;
  }
}

const reducers = combineReducers({timeline, notificao: notifica}); //ecma script 6 permite isso, antigo usar timeline:timeline notificao:notifica
const store = createStore(reducers, applyMiddleware(thunkModdleware));

ReactDOM.render(
  (<Provider store={store}>
     <Router>
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
    </Router>
  </Provider>),
  document.getElementById('root')
);

registerServiceWorker();
