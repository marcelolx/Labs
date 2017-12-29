import React, { Component } from 'react';

class Login extends Component{

  constructor(props){
    super(props);
    const params = new URLSearchParams(this.props.location.search);
    this.state = {msgErro:params.get('msg')};
  }

  Logar(event){
    event.preventDefault();

    const requestInfo = {
        method:'POST',
        body:JSON.stringify({login:this.login.value,senha:this.password.value}),
        headers: new Headers({
          'Content-type':'application/json'
        })
    };

    fetch('http://localhost:8080/api/public/login', requestInfo)
      .then(response => {
          if (response.ok) {
            return response.text();
          } else {
             throw new Error('Não foi possível fazer o login');
          }
      })
      .then(token => {
        localStorage.setItem('auth-token',token);
        this.props.history.push('/timeline');
      })
      .catch(erro => {
        this.setState({msgErro:erro.message});
      });
  }

  render() {
    return (
      <div className="login-box">
        <h1 className="header-logo">Instalura</h1>
        <span>{this.state.msgErro}</span>
        <form onSubmit={this.Logar.bind(this)}>
          <input type="text" ref={(input) => this.login = input}/>
          <input type="password" ref={(input) => this.password = input}/>
          <input type="submit" value="login"/>
        </form>
      </div>
    );
  }
}

export default Login;
