"use strict";

System.register([], function (_export, _context) {
  "use strict";

  var _createClass, Negociacao;

  function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
      throw new TypeError("Cannot call a class as a function");
    }
  }

  return {
    setters: [],
    execute: function () {
      _createClass = function () {
        function defineProperties(target, props) {
          for (var i = 0; i < props.length; i++) {
            var descriptor = props[i];
            descriptor.enumerable = descriptor.enumerable || false;
            descriptor.configurable = true;
            if ("value" in descriptor) descriptor.writable = true;
            Object.defineProperty(target, descriptor.key, descriptor);
          }
        }

        return function (Constructor, protoProps, staticProps) {
          if (protoProps) defineProperties(Constructor.prototype, protoProps);
          if (staticProps) defineProperties(Constructor, staticProps);
          return Constructor;
        };
      }();

      _export("Negociacao", Negociacao = function () {
        function Negociacao(data, quantidade, valor) {
          _classCallCheck(this, Negociacao);

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


        _createClass(Negociacao, [{
          key: "isEquals",
          value: function isEquals(outraNegociacao) {
            return JSON.stringify(this) == JSON.stringify(outraNegociacao);
          }
        }, {
          key: "volume",
          get: function get() {
            return this._quantidade * this._valor;
          }
        }, {
          key: "data",
          get: function get() {
            return new Date(this._data.getTime());
          }
        }, {
          key: "quantidade",
          get: function get() {
            return this._quantidade;
          }
        }, {
          key: "valor",
          get: function get() {
            return this._valor;
          }
        }]);

        return Negociacao;
      }());

      _export("Negociacao", Negociacao);
    }
  };
});
//# sourceMappingURL=Negociacao.js.map