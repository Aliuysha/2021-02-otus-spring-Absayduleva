create table books
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);


create table authors
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);

create table genres
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);

create table comments
(
    id      bigserial,
    book_id bigint references books (id) on delete cascade,
    text    varchar(1024),
    primary key (id)
);

create table book_authors
(
    book_id   bigint references books (id) on delete cascade,
    author_id bigint references authors (id),
    primary key (book_id, author_id)
);

create table book_genres
(
    book_id  bigint references books (id) on delete cascade,
    genre_id bigint references genres (id),
    primary key (book_id, genre_id)
);