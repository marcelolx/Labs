import {ProxyFactory} from '../services/ProxyFactory.js';

export class Bind {
  constructor(modelo, view, ...props) {

    let proxy = ProxyFactory.create(modelo, props, modelo => view.update(modelo));
    view.update(modelo);

    return proxy;
  }
}
