export class ListaNegociacoes {

  constructor() {
    this._negociacoes = [];
  }

  adiciona(negociacao) {

    this._negociacoes.push(negociacao);
  }

  get negociacoes() {
    //cria um novo objeto na memória com os dados da negociacao
    //garante que a lista original não seja alterada
    return [].concat(this._negociacoes);
  }

  esvazia() {
    this._negociacoes = [];
  }

  get volumeTotal() {
      return this._negociacoes.reduce((total, n) => total + n.volume, 0.0);
   }

   ordena(criterio){
     this._negociacoes.sort(criterio);
   }

   inverteOrdem() {
     this._negociacoes.reverse();
   }
}
