import PubSub from 'pubsub-js';

export default class TratadorErros {

  publicaErros(AErros) {
    for (var i = 0; i < AErros.errors.length; i++) {
      var erro = AErros.errors[i];
      PubSub.publish("erro-validacao", erro);
    }
  }
}
