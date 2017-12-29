export class DateHelper {

  constructor() {
    throw new Error('DateHelper não pode ser instânciado.');
  }

  //evita criar uma instância da classe
  //templateString usa crase = ``
  static dataParaTexto(data) {
    return `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`;
  }

  static textoParaData(texto) {
    //arrow function, já entende que tem que dar o return, exemplo só para uma linha, para mais precisa das chaves
    //expressão regular
    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(texto))
       throw new Error('Deve estar no formato dd/mm/aaaa');
    return new Date(...texto.split('/').reverse().map((item, indice) => item - indice % 2));
  }

}
