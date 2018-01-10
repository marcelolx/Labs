import React, { Component } from 'react';
import FotoItem from './Foto';
import TimelineAPI from '../logicas/TimelineAPI';
import {connect} from 'react-redux';

class Timeline extends Component {

  constructor(props){
    super();
    this.login = props.login;
  }

  componentDidMount() {
    this.carregaFotos();
  }

  componentWillReceiveProps(nextProps){
    if (nextProps.login.props.match !== undefined) {
      if (nextProps.login.props.match.params.login !== this.login.props.match.params.login) {
        this.login = nextProps.login;
        this.carregaFotos();
      }
    }
  }

  carregaFotos(){
     let urlPerfil;
     if (this.login.props.match === undefined) {
       urlPerfil = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
     }else {
       urlPerfil = `http://localhost:8080/api/public/fotos/${this.login.props.match.params.login}`;
     }

     this.props.listar(urlPerfil);
  }

  render() {
    return(
      <div className="fotos container">
         {
           this.props.ListaFotos.map(foto => {return(<FotoItem key={foto.id} foto={foto} like={this.props.like} comenta={this.props.comenta}/>);
           })
         }
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {ListaFotos: state.timeline};
};

const mapDispatchToProps = dispatch => {
  return {
    like : (fotoId) => {
      dispatch(TimelineAPI.like(fotoId));
    },
    comenta : (fotoId, textoComentario) => {
      dispatch(TimelineAPI.comenta(fotoId, textoComentario));
    },
    listar : (urlPerfil) => {
      dispatch(TimelineAPI.lista(urlPerfil));
    }
  }
}

const TimelineContainer = connect(mapStateToProps, mapDispatchToProps)(Timeline);


export default TimelineContainer;
