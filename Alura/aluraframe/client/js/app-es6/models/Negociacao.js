export class Negociacao {
  constructor(data, quantidade, valor) {

      this._data = new Date(data.getTime()); //garante que a data guardada na negociação não seja uma referência a um objeto e sim um novo objeto
      this._quantidade = quantidade;
      this._valor = valor;
      Object.freeze(this); //congela o objeto, não permite altera-lo
  }

  /*var não tem escopo de bloco
    let tem escopo de bloco  */
  /*Função dentro de classe = método, função fora de classe = função*/
  /*Underline é uma convenção que indica ao programador que
    esses atributos só podem ser acessadas pelos métodos da
    classe, ninguem de fora pode acessa-las.*/
  get volume() {
    return this._quantidade * this._valor;
  }

  get data() {
    return new Date(this._data.getTime());
  }

  get quantidade() {
    return this._quantidade;
  }

  get valor() {
    return this._valor;
  }

  isEquals(outraNegociacao) {
        return JSON.stringify(this) == JSON.stringify(outraNegociacao)
  }
}
