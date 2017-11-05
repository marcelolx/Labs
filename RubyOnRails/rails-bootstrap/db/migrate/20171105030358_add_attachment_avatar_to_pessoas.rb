class AddAttachmentAvatarToPessoas < ActiveRecord::Migration[5.1]
  def self.up
    change_table :pessoas do |t|
      t.attachment :avatar
    end
  end

  def self.down
    remove_attachment :pessoas, :avatar
  end
end
