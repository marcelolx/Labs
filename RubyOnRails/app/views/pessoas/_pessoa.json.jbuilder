json.extract! pessoa, :id, :nome, :string, :sobrenome, :string, :data_nascimento, :date, :email, :string, :senha, :string, :foto_url, :string, :created_at, :updated_at
json.url pessoa_url(pessoa, format: :json)
