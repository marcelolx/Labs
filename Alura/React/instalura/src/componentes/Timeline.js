import React, { Component } from 'react';
import FotoItem from './Foto';
import PubSub from 'pubsub-js';

class Timeline extends Component {

  constructor(props){
    super();
    this.state = {ListaFotos:[]};
    this.login = props.login;
  }

  componentWillMount(){
    PubSub.subscribe('timeline', (topico, AObjetoFotos) => {
      this.setState({ListaFotos:AObjetoFotos.fotos});
    })

    PubSub.subscribe('atualiza-liker', (topico, objetoInfoLiker) => {
      const fotoAchada = this.state.ListaFotos.find(foto => foto.id === objetoInfoLiker.fotoId);

      const possivelLiker = fotoAchada.likers.find(liker => liker.login === objetoInfoLiker.liker.login);
      if (possivelLiker === undefined) {
        fotoAchada.likers.push(objetoInfoLiker.liker)
      }else {
        const novosLikers = fotoAchada.likers.filter(liker => liker.login !== objetoInfoLiker.liker.login);
        fotoAchada.likers = novosLikers;
      }
      this.setState({fotos:this.state.ListaFotos});

    });
  }

  componentDidMount() {
    this.carregaFotos();
  }

  componentWillReceiveProps(nextProps){
    if (nextProps.login !== undefined){
       this.login = nextProps.login;
       this.carregaFotos();
    }
  }

  like(fotoId) {

    fetch(`http://localhost:8080/api/fotos/${fotoId}/like?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,{method:'POST'})
      .then(response => {
        if(response.ok) {
          return response.json();
        } else {
          throw new Error("não foi possível realizar o like da foto");
        }
      })
      .then(liker => {
         PubSub.publish('atualiza-liker',{fotoId,liker});
      });
  }

  comenta(fotoId,textoComentario) {
      const requestInfo = {
        method:'POST',
        body:JSON.stringify({texto:textoComentario}),
        headers: new Headers({
          'Content-type':'application/json'
        })
      };

    fetch(`http://localhost:8080/api/fotos/${fotoId}/comment?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`,requestInfo)
    .then(response => {
        if(response.ok){
          return response.json();
        } else {
          throw new Error("não foi possível comentar");
        }
    })
    .then(novoComentario => {
        PubSub.publish('novos-comentarios',{fotoId,novoComentario});
    });
  }

  carregaFotos(){
     let urlPerfil;
     if (this.login.props.match === undefined) {
       urlPerfil = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
     }else {
       urlPerfil = `http://localhost:8080/api/public/fotos/${this.login.props.match.params.login}`;
     }

     fetch(urlPerfil)
       .then(response => response.json())
       .then(fotos => this.setState({ListaFotos:fotos}));
  }

  render() {
    return(
      <div className="fotos container">
         {
           this.state.ListaFotos.map(foto => {return(<FotoItem key={foto.id} foto={foto} like={this.like} comenta={this.comenta}/>);
           })
         }
      </div>
    );
  }
}

export default Timeline;
