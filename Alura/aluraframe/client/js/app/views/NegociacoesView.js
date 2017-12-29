'use strict';

System.register(['./View.js', '../helpers/DateHelper.js', '../controllers/NegociacaoController.js'], function (_export, _context) {
    "use strict";

    var View, DateHelper, CurrentInstance, _createClass, NegociacoesView;

    function _classCallCheck(instance, Constructor) {
        if (!(instance instanceof Constructor)) {
            throw new TypeError("Cannot call a class as a function");
        }
    }

    function _possibleConstructorReturn(self, call) {
        if (!self) {
            throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
        }

        return call && (typeof call === "object" || typeof call === "function") ? call : self;
    }

    function _inherits(subClass, superClass) {
        if (typeof superClass !== "function" && superClass !== null) {
            throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
        }

        subClass.prototype = Object.create(superClass && superClass.prototype, {
            constructor: {
                value: subClass,
                enumerable: false,
                writable: true,
                configurable: true
            }
        });
        if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
    }

    return {
        setters: [function (_ViewJs) {
            View = _ViewJs.View;
        }, function (_helpersDateHelperJs) {
            DateHelper = _helpersDateHelperJs.DateHelper;
        }, function (_controllersNegociacaoControllerJs) {
            CurrentInstance = _controllersNegociacaoControllerJs.CurrentInstance;
        }],
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

            _export('NegociacoesView', NegociacoesView = function (_View) {
                _inherits(NegociacoesView, _View);

                function NegociacoesView(elemento) {
                    _classCallCheck(this, NegociacoesView);

                    var _this = _possibleConstructorReturn(this, (NegociacoesView.__proto__ || Object.getPrototypeOf(NegociacoesView)).call(this, elemento));

                    //passa para o construtor pai (View.js)

                    elemento.addEventListener('click', function (event) {

                        if (event.target.nodeName == 'TH') CurrentInstance().ordena(event.target.textContent.toLowerCase());
                    });
                    return _this;
                }
                /*
                  //varre a lista e retorna uma nova lista com os dados
                  //em "html", com td e tr
                  // e no final faz um join contatenando todas as strings retornadas
                  //toda vez que adicionar uma nova negociacao, ele recarrega a lista toda
                  //reduce(), irá processar o array e no fim, disponibiliza um único resultado.
                */


                _createClass(NegociacoesView, [{
                    key: 'template',
                    value: function template(modelo) {
                        return '\n      <table class="table table-hover table-bordered">\n          <thead>\n              <tr>\n                  <th>DATA</th>\n                  <th>QUANTIDADE</th>\n                  <th>VALOR</th>\n                  <th>VOLUME</th>\n              </tr>\n          </thead>\n\n          <tbody>\n              ' + modelo.negociacoes.map(function (negocia) {
                            return '\n\n                    <tr>\n                        <td>' + DateHelper.dataParaTexto(negocia.data) + '</td>\n                        <td>' + negocia.quantidade + '</td>\n                        <td>' + negocia.valor + '</td>\n                        <td>' + negocia.volume + '</td>\n                    </tr>\n\n                ';
                        }).join('') + '\n          </tbody>\n\n          <tfoot>\n              <td colspan="3"></td>\n              <td>' + modelo.volumeTotal + '</td>\n          </tfoot>\n      </table>';
                    }
                }]);

                return NegociacoesView;
            }(View));

            _export('NegociacoesView', NegociacoesView);
        }
    };
});
//# sourceMappingURL=NegociacoesView.js.map