import {View} from './View.js';
import {DateHelper} from '../helpers/DateHelper.js';
import {CurrentInstance} from '../controllers/NegociacaoController.js';

export class NegociacoesView extends View {

  constructor(elemento) {
    super(elemento); //passa para o construtor pai (View.js)

    elemento.addEventListener('click', function(event){

      if (event.target.nodeName == 'TH')
        CurrentInstance().ordena(event.target.textContent.toLowerCase());
    });
  }
  /*
    //varre a lista e retorna uma nova lista com os dados
    //em "html", com td e tr
    // e no final faz um join contatenando todas as strings retornadas
    //toda vez que adicionar uma nova negociacao, ele recarrega a lista toda
    //reduce(), irá processar o array e no fim, disponibiliza um único resultado.
  */
  template(modelo) {
    return `
      <table class="table table-hover table-bordered">
          <thead>
              <tr>
                  <th>DATA</th>
                  <th>QUANTIDADE</th>
                  <th>VALOR</th>
                  <th>VOLUME</th>
              </tr>
          </thead>

          <tbody>
              ${modelo.negociacoes.map(negocia =>`

                    <tr>
                        <td>${DateHelper.dataParaTexto(negocia.data)}</td>
                        <td>${negocia.quantidade}</td>
                        <td>${negocia.valor}</td>
                        <td>${negocia.volume}</td>
                    </tr>

                ` ).join('')}
          </tbody>

          <tfoot>
              <td colspan="3"></td>
              <td>${modelo.volumeTotal}</td>
          </tfoot>
      </table>`;
  }

}
