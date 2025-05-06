CREATE TABLE tb_movie_category (
    movie_id    INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_movie_category_movie FOREIGN KEY (movie_id) REFERENCES tb_movies(id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY (category_id) REFERENCES tb_category(id)
);

CREATE TABLE tb_movie_streaming (
    movie_id    INTEGER,
    streaming_id INTEGER,
    CONSTRAINT fk_movie_streaming_movie FOREIGN KEY (movie_id) REFERENCES tb_movies(id),
    CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY (streaming_id) REFERENCES tb_streaming(id)
)
