CREATE TABLE users (
    id uuid primary key,
    nickname varchar(50) not null
);

CREATE TABLE posts (
    id uuid primary key,
    author_id uuid not null,
    title varchar(50) not null,
    text varchar(65535) not null,
    publication_date timestamp,
    foreign key(author_id) references users(id)
);

CREATE TABLE likes (
    id uuid primary key,
    author_id uuid not null,
    post_id uuid not null,
    foreign key(author_id) references users(id),
    foreign key(post_id) references posts(id)
);

CREATE TABLE comments (
    id uuid primary key,
    author_id uuid not null,
    post_id uuid not null,
    text varchar(65535) not null,
    foreign key(author_id) references users(id),
    foreign key(post_id) references posts(id)
);