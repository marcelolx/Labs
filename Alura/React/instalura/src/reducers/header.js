export function notifica(state='', action) {
  if (action.type === 'NOTIFICA') {
    return action.mensagem;
  }

  return state;
}
