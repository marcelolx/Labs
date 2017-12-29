export class View {

  constructor(elemento) {

    this._elemento = elemento;
  }

  template() {
    throw new Error('Você deve implementar o método _template.');
  }

  update(modelo) {
    this._elemento.innerHTML = this.template(modelo);
  }
}
