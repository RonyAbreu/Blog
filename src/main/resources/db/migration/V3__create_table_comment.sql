CREATE TABLE tb_comment (
    id SERIAL PRIMARY KEY,
    text VARCHAR(155) NOT NULL,
    creator_id UUID REFERENCES tb_user(id),
    post_id SERIAL REFERENCES tb_post(id)
);
