class Pessoa < ApplicationRecord
  has_attached_file :avatar, :styles => {
    :medium => "64x64>",
    :thumb => "32x32>" }
  validates_attachment_content_type :avatar, :content_type => ["image/jpg", "image/jpeg", "image/png", "image/gif"]
end
