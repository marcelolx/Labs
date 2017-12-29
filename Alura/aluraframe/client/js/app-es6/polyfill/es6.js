if (!Array.prototype.includes) {

  //se não existir, adiciona

  Array.prototype.includes  = function(elemento) {
      return this.indexOf(elemento) != -1;
  };
}
