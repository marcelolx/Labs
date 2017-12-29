import React, {Component} from 'react';
import PubSub from 'pubsub-js';

export default class Header extends Component {

  pesquisa(event){
    event.preventDefault();

    fetch(`http://localhost:8080/api/public/fotos/${this.loginPesquisado.value}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        }else {
          throw new Error("Não foi possível realizar o like da foto!");
        }
      })
      .then(fotos => {
        PubSub.publish('timeline', {fotos});
        this.loginPesquisado.value = '';
      });
  }

  render() {
    return (
      <header className="header container">
        <h1 className="header-logo">
          Instalura
        </h1>

        <form className="header-busca" onSubmit={this.pesquisa.bind(this)}>
          <input type="text" name="search" placeholder="Pesquisa" className="header-busca-campo" ref={(input) => this.loginPesquisado = input}/>
          <input type="submit" value="Buscar" className="header-busca-submit"/>
        </form>


        <nav>
          <ul className="header-nav">
            <li className="header-nav-item">
              <a href="">

              </a>
            </li>
          </ul>
        </nav>
      </header>
    );
  }
}
