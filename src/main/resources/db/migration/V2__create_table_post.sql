CREATE TABLE tb_post (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(255) NOT NULL,
    creator_id UUID REFERENCES tb_user(id)
);
