ALTER TABLE tb_movies ADD CONSTRAINT check_rating_range
CHECK (rating >= 0 AND rating <= 100);