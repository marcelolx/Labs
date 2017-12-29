export class ProxyFactory {

  static create(objeto, props, acao) {

    return new Proxy(objeto, {

      /*get é chamado toda vez que é realizada uma alteração
      target é o objeto original, prop = propriedade que está
      sendo acessada, e o próprio proxy*/
      get(target, prop, receiver) {

          if (props.includes(prop) && ProxyFactory._ehFuncao(target[prop])) {

              return function() {

                let retorno =  Reflect.apply(target[prop], target, arguments);
                acao(target);
                return retorno;
              }
          }

          return Reflect.get(target, prop, receiver);
      },

      set(target, prop, value, receiver) {

        let retorno = Reflect.set(target, prop, value, receiver);
        if (props.includes(prop)) {
          acao(target);
        }

        return retorno;
      }
    });
  }

  static _ehFuncao(func) {

    return typeof(func) == typeof(Function);
  }

}
