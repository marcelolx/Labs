import React, { Component } from 'react';
import $ from 'jquery';
import InputCustomizado from '../componentes/InputCustomizado';
import BotaoCustomizado from '../componentes/BotaoCustomizado';
import PubSub from 'pubsub-js';
import TratadorErros from  '../helpers/TratadorErros';

class FormularioAutor extends Component {

  constructor() {
    super();
    console.log(this);
    this.state = {nome:'',email:'',senha:''};
    this.enviaFormulario = this.enviaFormulario.bind(this);
  }

  enviaFormulario(evento){
    evento.preventDefault();

    console.log(this);
    $.ajax({
      url:"http://cdc-react.herokuapp.com/api/autores",
      contentType: 'application/json',
      dataType: 'json',
      type:'post',
      data:JSON.stringify({nome:this.state.nome,email:this.state.email,senha:this.state.senha}),
      success:function(novaListagem){
          //disparar um aviso geral de nova listagem disponível
          PubSub.publish('atualiza-lista-autores', novaListagem);
          this.setState({nome:'',email:'',senha:''});
      }.bind(this), //this do react
      error: function(resposta){
        if (resposta.status === 400) {
            new TratadorErros().publicaErros(resposta.responseJSON);
        }
      },
      beforeSend: function() {
        PubSub.publish("limpa-erros", {});
      }
    });
  }

  salvaAlteracao(nomeInput, evento){
    this.setState({[nomeInput]:evento.target.value});
  }

  render(){
    return(
      <div className="pure-form pure-form-aligned">
        <div>
          <br/>
        </div>
        <form className="pure-form pure-form-aligned" onSubmit={this.enviaFormulario} method="post">

          <InputCustomizado id="nome" type="texto" name="nome" value={this.state.nome} onChange={this.salvaAlteracao.bind(this, 'nome')} label="Nome"/>
          <InputCustomizado id="email" type="email" name="email" value={this.state.email} onChange={this.salvaAlteracao.bind(this, 'email')}  label="Email"/>
          <InputCustomizado id="senha" type="password" name="senha" value={this.state.senha} onChange={this.salvaAlteracao.bind(this, 'senha')}  label="Senha"/>

          <BotaoCustomizado type="submit" className="pure-button pure-button-primary" btnCaption="Gravar"/>
        </form>
      </div>
    );
  }
}

class TabelaAutores extends Component {

    render(){
      return(
        <div>
          <table className="pure-table">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {
                this.props.lista.map(autor => {
                    return (
                        //key = x ... facilita para o react atualizar os dados
                        <tr key={autor.id}>
                            <td>{autor.nome}</td>
                            <td>{autor.email}</td>
                        </tr>
                    );
                })
              }
            </tbody>
          </table>
        </div>
      );
    }
}

export default class AutorBox extends Component {

  constructor() {
    super();
    this.state = {lista: []};
  }

  //usando o jquery aqui, mas ver possibilidade usar fetchAPI
  //chamado antes do render ser invocado pela primeira vez
  componentWillMount(){

  }

  //Chamado após o render ser invocado pela primeira vez
  componentDidMount(){
    //ajax faz uma requisição assincrona, depois o setState chama o render de novo
    $.ajax({
        url:"http://cdc-react.herokuapp.com/api/autores",
        dataType: 'json',
        success:function(resposta){
          this.setState({lista:resposta});
          //setState chama o render novamente
        }.bind(this)//da bind no react e não no jquery
      }
    );

    PubSub.subscribe('atualiza-lista-autores', function(topico, novaLista){
      this.setState({lista:novaLista});
    }.bind(this));
  }

  render(){
    return(
      <div>
        <div className="header">
          <h1>Cadastro do Autor!</h1>
        </div>

        <div className="content" id="content">
          <FormularioAutor/>
          <TabelaAutores lista={this.state.lista}/>
        </div>
      </div>
    );
  }
}
