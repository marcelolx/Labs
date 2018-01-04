import React, { Component } from 'react';
import FotoItem from './Foto';
import TimelineAPI from '../logicas/TimelineAPI';

class Timeline extends Component {

  constructor(props){
    super();
    this.state = {ListaFotos:[]};
    this.login = props.login;
    //É instanciada no TimelineBox, deixando ela global, pra outros acessarem
    //this.store = new store([]);
  }

  componentWillMount(){
    this.props.store.subscribe(() => {
      this.setState({ListaFotos:this.props.store.getState()});
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
    this.props.store.like(fotoId);
  }

  comenta(fotoId,textoComentario) {
    this.props.store.comenta(fotoId, textoComentario);
  }

  carregaFotos(){
     let urlPerfil;
     if (this.login.props.match === undefined) {
       urlPerfil = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
     }else {
       urlPerfil = `http://localhost:8080/api/public/fotos/${this.login.props.match.params.login}`;
     }

     TimelineAPI.lista(urlPerfil, this.props.store);
     this.props.store.dispatch({type:'LISTAGEM', fotos:[]});
  }

  render() {
    return(
      <div className="fotos container">
         {
           this.state.ListaFotos.map(foto => {return(<FotoItem key={foto.id} foto={foto} like={this.like.bind(this)} comenta={this.comenta.bind(this)}/>);
           })
         }
      </div>
    );
  }
}

export default Timeline;
