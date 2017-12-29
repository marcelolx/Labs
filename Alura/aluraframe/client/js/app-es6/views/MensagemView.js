import {View} from './View.js';

export class MensagemView extends View {

  constructor(elemento) {
    super(elemento); //passa para o construtor pai (View.js)
  }

  template(modelo) {
    return modelo.texto ? `<p class="alert alert-info">${modelo.texto}</p>` : `<p></p>`;
  }
}
