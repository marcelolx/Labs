'use strict';

System.register(['./controllers/NegociacaoController.js', './polyfill/fetch.js'], function (_export, _context) {
  "use strict";

  var CurrentInstance, negociacaoController;
  return {
    setters: [function (_controllersNegociacaoControllerJs) {
      CurrentInstance = _controllersNegociacaoControllerJs.CurrentInstance;
    }, function (_polyfillFetchJs) {}],
    execute: function () {
      negociacaoController = CurrentInstance();


      document.querySelector('.form').onsubmit = negociacaoController.adiciona.bind(negociacaoController);
      document.querySelector('[type=button]').onclick = negociacaoController.apaga.bind(negociacaoController);
    }
  };
});
//# sourceMappingURL=boot.js.map