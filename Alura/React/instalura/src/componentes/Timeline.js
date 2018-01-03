import React, { Component } from 'react';
import FotoItem from './Foto';
import LogicaTimeline from '../logicas/LogicaTimeline'

class Timeline extends Component {

  constructor(props){
    super();
    this.state = {ListaFotos:[]};
    this.login = props.login;
    this.logicaTimeline = new LogicaTimeline([]);
  }

  componentWillMount(){
    this.logicaTimeline.subscribe(AListaFotos => {
      this.setState({ListaFotos:AListaFotos});
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
    this.logicaTimeline.like(fotoId);
  }

  comenta(fotoId,textoComentario) {
    this.logicaTimeline.comenta(fotoId, textoComentario);
  }

  carregaFotos(){
     let urlPerfil;
     if (this.login.props.match === undefined) {
       urlPerfil = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
     }else {
       urlPerfil = `http://localhost:8080/api/public/fotos/${this.login.props.match.params.login}`;
     }

     this.logicaTimeline.lista(urlPerfil);
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
