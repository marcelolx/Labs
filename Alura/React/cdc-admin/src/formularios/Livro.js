import React, { Component } from 'react';
import $ from 'jquery';
import InputCustomizado from '../componentes/InputCustomizado';
import BotaoCustomizado from '../componentes/BotaoCustomizado';
import PubSub from 'pubsub-js';
import TratadorErros from  '../helpers/TratadorErros';

export default class LivroBox extends Component {

  constructor() {
    super();
    this.state = {lista: [], autores: []};
  }

  //Chamado após o render ser invocado pela primeira vez
  componentDidMount(){
    //ajax faz uma requisição assincrona, depois o setState chama o render de novo
    $.ajax({
        url:"http://cdc-react.herokuapp.com/api/livros",
        dataType: 'json',
        success:function(resposta){
          this.setState({lista:resposta});
          //setState chama o render novamente
        }.bind(this)//da bind no react e não no jquery
      }
    );

    $.ajax({
        url:"http://cdc-react.herokuapp.com/api/autores",
        dataType: 'json',
        success:function(resposta){
          this.setState({autores:resposta});
          //setState chama o render novamente
        }.bind(this)//da bind no react e não no jquery
      }
    );

    PubSub.subscribe('atualiza-lista-livros', function(topico, novaLista){
      this.setState({lista:novaLista});
    }.bind(this));
  }

  render(){
    return (
      <div>
        <div className="header">
          <h1>Cadastro de Livros!</h1>
        </div>

        <div className="content" id="content">
          <FormularioLivro autores={this.state.autores}/>
          <TabelaLivros lista={this.state.lista}/>
        </div>
      </div>
    );
  }
}

class TabelaLivros extends Component {

    render(){
      return(
        <div>
          <table className="pure-table">
            <thead>
              <tr>
                <th>Título</th>
                <th>Preço</th>
                <th>Autor</th>
              </tr>
            </thead>
            <tbody>
              {
                this.props.lista.map(livro => {
                    return (
                        //key = x ... facilita para o react atualizar os dados
                        <tr key={livro.id}>
                            <td>{livro.titulo}</td>
                            <td>{livro.preco}</td>
                            <td>{livro.autor.nome}</td>
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

class FormularioLivro extends Component {

  constructor() {
    super();
    console.log(this);
    this.state = {titulo:'',preco:'',autorId:''};
    this.enviaFormulario = this.enviaFormulario.bind(this);
    this.setTitulo = this.setTitulo.bind(this);
    this.setPreco = this.setPreco.bind(this);
    this.setAutorId = this.setAutorId.bind(this);
  }

  enviaFormulario(evento){
    evento.preventDefault();

    console.log(this);
    $.ajax({
      url:"http://cdc-react.herokuapp.com/api/livros",
      contentType: 'application/json',
      dataType: 'json',
      type:'post',
      data:JSON.stringify({titulo:this.state.titulo,preco:this.state.preco,autorId:this.state.autorId}),
      success:function(novaListagem){
          //disparar um aviso geral de nova listagem disponível
          PubSub.publish('atualiza-lista-livros', novaListagem);
          this.setState({titulo:'',preco:'',autorId:''});
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

  setTitulo(evento){
    this.setState({titulo:evento.target.value});
  }

  setPreco(evento){
    this.setState({preco:evento.target.value});
  }

  setAutorId(evento){
    this.setState({autorId:evento.target.value});
  }

  render(){
    return(
      <div className="pure-form pure-form-aligned">
        <div>
          <br/>
        </div>
        <form className="pure-form pure-form-aligned" onSubmit={this.enviaFormulario} method="post">

          <InputCustomizado id="titulo" type="text" name="titulo" value={this.state.titulo} onChange={this.setTitulo} label="Título"/>
          <InputCustomizado id="preco" type="text" name="preco" value={this.state.preco} onChange={this.setPreco}  label="Preço"/>

          <div className="pure-control-group">
            <label htmlFor="autorId">Autor</label>
            <select value={this.state.autorId} name="autorId" onChange={this.setAutorId}>
              <option value="">Selecione o Autor</option>
              {
                this.props.autores.map(function(autor){
                  return <option key={autor.id} value={autor.id}>{autor.nome}</option>
                })
              }
            </select>
          </div>

          <BotaoCustomizado type="submit" className="pure-button pure-button-primary" btnCaption="Gravar"/>
        </form>
      </div>
    );
  }
}
