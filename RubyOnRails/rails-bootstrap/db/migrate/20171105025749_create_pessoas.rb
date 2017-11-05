class CreatePessoas < ActiveRecord::Migration[5.1]
  def change
    create_table :pessoas do |t|
      t.string :nome
      t.string :sobrenome
      t.date :data_nascimento
      t.string :cpf
      t.string :email
      t.string :telefone
      t.string :celular

      t.timestamps
    end
  end
end
