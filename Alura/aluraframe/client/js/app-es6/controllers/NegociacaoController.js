import {ListaNegociacoes} from '../models/ListaNegociacoes.js';
import {Mensagem} from '../models/Mensagem.js';
import {NegociacoesView} from '../views/NegociacoesView.js';
import {MensagemView} from '../views/MensagemView.js';
import {NegociacaoService} from '../services/NegociacaoService.js';
import {DateHelper} from '../helpers/DateHelper.js';
import {Bind} from '../helpers/Bind.js';
import {Negociacao} from '../models/Negociacao.js';

class NegociacaoController {

  constructor() {
    /*querySelector vai para o $, porém ainda mantem uma associação com o document*/
    /*pegando os dados no constructor, evita percorrer o DOM toda vez que for chamado*/
    let $ = document.querySelector.bind(document);
    this._inputData = $('#data');
    this._inputQuantidade = $('#quantidade');
    this._inputValor = $('#valor');

    this._listaNegociacoes = new Bind(new ListaNegociacoes(), new NegociacoesView($('#negociacoesView')), 'adiciona', 'esvazia', 'ordena', 'inverteOrdem');
    this._mensagem = new Bind(new Mensagem(), new MensagemView($('#mensagemView')), 'texto');
    this._ordemAtual = '';

    this._service = new NegociacaoService();
    this._init();

    /* Método não usando 100% arrow function
    ConnectionFactory
      .getConnection()
      .then(connection => {
        new NegociacaoDAO(connection)
            .listaTodos()
            .then(negociaocoes => {

                negociaocoes.forEach(negociacao => {
                    this._listaNegociacoes.adiciona(negociacao);
                });
            });
      });*/

  }

  _init() {

    this._service
        .lista()
        .then(negociacoes =>
          negociacoes.forEach(negociacao =>
            this._listaNegociacoes.adiciona(negociacao)))
        .catch(erro => this._mensagem.texto = erro);

        setInterval(() => {
          this.importaNegociacoes();
        }, 3000);
  }

  adiciona(event) {
    event.preventDefault(); //serve para não recarregar a página

    let negociacao = this._criaNegociacao();

    this._service
        .cadastra(negociacao)
        .then(mensagem => {
            this._listaNegociacoes.adiciona(negociacao);
            this._mensagem.texto = mensagem;
            this._limpaFormulario();
        })
        .catch(error => this._mensagem.texto = erro);

  }

  ordena(coluna) {

    if (this._ordemAtual == coluna){
        this._listaNegociacoes.inverteOrdem();
    } else {
        this._listaNegociacoes.ordena((a,b) => a[coluna] - b[coluna]);
    }
    this._ordemAtual = coluna;

  }

  importaNegociacoes() {
    //Padrão de projeto Promise
    this._service
        .importa(this._listaNegociacoes.negociacoes)
        .then(negociacoes => {
          negociacoes.forEach(negociacao => this._listaNegociacoes.adiciona(negociacao));
          this._mensagem.texto = 'Negociações do período importadas com sucesso!';
        })
        .catch(erro => this._mensagem.texto = erro);
  }
    /*
    .obterNegociacoes retorna as negociacoes dos três perídos
    Promise.all([
      service.obterNegociacoes('negociacoes/semana'),
      service.obterNegociacoes('negociacoes/anterior'),
      service.obterNegociacoes('negociacoes/retrasada')]
    ).then((negociacoes) => {
      negociacoes
            .reduce((arrayAchatado, array) => arrayAchatado.concat(array), []) //retorna um único array
            .forEach(negociacao => this._listaNegociacoes.adiciona(negociacao));
      this._mensagem.texto = 'Negociações importadas com sucesso!';
    })
    .catch((erro) => this._mensagem.texto = erro);

    */

    /* Promise de forma assincrona, não respeita a ordem
    service.obterNegociacoesDaSemana()
             .then(negociacoes => {
               negociacoes.forEach(negociacao => this._listaNegociacoes.adiciona(negociacao))
               this._mensagem.texto = 'Negociações da semana obtidas com sucesso!';
             })
              .catch(erro => this._mensagem.texto = erro);

    service.obterNegociacoesDaSemanaAnterior()
            .then(negociacoes => {
              negociacoes.forEach(negociacao => this._listaNegociacoes.adiciona(negociacao))
              this._mensagem.texto = 'Negociações da semana passada obtidas com sucesso!';
            })
             .catch(erro => this._mensagem.texto = erro);

    service.obterNegociacoesDaSemanaRetrasada()
            .then(negociacoes => {
               negociacoes.forEach(negociacao => this._listaNegociacoes.adiciona(negociacao))
               this._mensagem.texto = 'Negociações da semana retrasada obtidas com sucesso!';
             })
              .catch(erro => this._mensagem.texto = erro);
    */


  apaga() {

    this._service
        .apaga()
        .then(mensagem => {
            this._mensagem.texto = mensagem;
            this._listaNegociacoes.esvazia();
        })
        .catch(erro => this._mensagem.texto = erro);
  }

  _criaNegociacao() {
    return new Negociacao(
      DateHelper.textoParaData(this._inputData.value),
      parseInt(this._inputQuantidade.value),
      parseFloat(this._inputValor.value));
  }

  _limpaFormulario() {

    this._inputData.value = '';
    this._inputQuantidade.value = 1;
    this._inputValor.value = 0.0;
    this._inputData.focus();
  }

}
//let data = new Date(this._inputData.value.replace(/-/g, ','));
//let data = new Date(this._inputData.value.split('-'));
//os três ... entendem que o valor passado tem que ser splitado em três valores
//varre o array com o map e decrementa um valor no segundo parametro passado como um indice
/*let data = new Date(...
  this._inputData.value
      .split('-')
      .map(function(item, indice) {
        //quando for o indice 1, o modulo será um e vai decrementar 1. somente para datas
        return item - indice % 2;
      })
);*/


let negociacaoController = new NegociacaoController();

export function CurrentInstance() {

  return negociacaoController;
}
