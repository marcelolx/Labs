json.extract! pessoa, :id, :nome, :sobrenome, :data_nascimento, :cpf, :email, :telefone, :celular, :created_at, :updated_at
json.url pessoa_url(pessoa, format: :json)
