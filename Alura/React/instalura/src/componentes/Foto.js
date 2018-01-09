import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Foto extends Component {
  render(){
    return(
      <div className="foto">
        <FotoHeader foto={this.props.foto}/>
        <img alt="foto" className="foto-src" src={this.props.foto.urlFoto}/>
        <FotoInfo foto={this.props.foto}/>
        <FotoAtualizacoes foto={this.props.foto} like={this.props.like} comenta={this.props.comenta}/>
      </div>
    );
  }
}

class FotoHeader extends Component {
  render() {
    return(
      <header className="foto-header">
        <figure className="foto-usuario">
          <img src={this.props.foto.urlPerfil} alt="foto do usuario"/>
          <figcaption className="foto-usuario">
            <Link to={`/timeline/${this.props.foto.loginUsuario}`}>
              {this.props.foto.loginUsuario}
            </Link>
          </figcaption>
        </figure>
        <time className="foto-data">{this.props.foto.horario}</time>
      </header>
    );
  }
}

class FotoInfo extends Component {
  render(){
    return(
      <div className="foto-info">
        <FotoInfoLikes foto={this.props.foto}/>
        <FotoInfoLegenda foto={this.props.foto}/>
        <FotoInfoComentario foto={this.props.foto}/>
      </div>
    );
  }
}

class FotoAtualizacoes extends Component{

  constructor(props){
    super(props);
    this.state = {likeada: this.props.foto.likeada};
  }

  like(event){
    event.preventDefault();//não recarrega a página
    this.setState({likeada : !this.state.likeada});
    this.props.like(this.props.foto.id);
  }


  comenta(event){
    event.preventDefault();
    this.props.comenta(this.props.foto.id,this.comentario.value);
    this.comentario.value = '';
  }

  //ainda teria ver se o usuário logado também curtiu a foto
  render(){
    return(
      <section className="fotoAtualizacoes">
        <a onClick={this.like.bind(this)} className={!this.state.likeada ? 'fotoAtualizacoes-like' : 'fotoAtualizacoes-like-ativo'}>Likar</a>
        <form className="fotoAtualizacoes-form" onSubmit={this.comenta.bind(this)}>
          <input type="text" placeholder="Adicione um comentário..." className="fotoAtualizacoes-form-campo" ref={input => this.comentario = input}/>
          <input type="submit" value="Comentar!" className="fotoAtualizacoes-form-submit"/>
        </form>

      </section>
    );
  }
}

class FotoInfoLikes extends Component{
  render(){
    return(
      <div className="foto-info-likes">
        {
          this.props.foto.likers.map(liker => {
            return <Link key={liker.login} to={`/timeline/${liker.login}`}> {liker.login} </Link>
          })
        }
      </div>
    );
  }
}

class FotoInfoLegenda extends Component{
  render(){
    return(
      <p className="foto-info-legenda">
        <Link to={`/timeline/${this.props.foto.loginUsuario}`} className="foto-info-autor">{this.props.foto.loginUsuario} </Link>
        {this.props.foto.comentario}
      </p>
    );
  }
}

class FotoInfoComentario extends Component{

  /*constructor(props){
    super(props);
    this.state = {comentarios:this.props.foto.comentarios};
  }*/

  render(){
    return(
      <ul className="foto-info-comentarios">
      {
        this.props.foto.comentarios.map(comentario => {
          return(
            <li className="comentario" key={comentario.id}>
              <Link to={`/timeline/${comentario.login}`} className="foto-info-autor">{comentario.login} </Link>
              {comentario.texto}
            </li>
          );
        })
      }
      </ul>
    );
  }
}
