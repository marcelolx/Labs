class CreatePessoas < ActiveRecord::Migration[5.1]
  def change
    create_table :pessoas do |t|
      t.string :nome
      t.string :string
      t.string :sobrenome
      t.string :string
      t.string :data_nascimento
      t.string :date
      t.string :email
      t.string :string
      t.string :senha
      t.string :string
      t.string :foto_url
      t.string :string

      t.timestamps
    end
  end
end
