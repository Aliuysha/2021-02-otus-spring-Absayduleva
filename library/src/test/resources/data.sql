insert into authors(name)
values ('Stiven King'),
       ('Донцова Д');

insert into genres(name)
values ('ужасы'),
       ('детектив');

insert into books (name)
values ('Оно'),
       ('Убийство на яхте'),
       ('Общее творение');

insert into comments(text, book_id)
values ('классная книга', 1),
       ('книга топ всем советую', 1),
       ('скучно и сюжет не очень', 2);

insert into book_authors(book_id, author_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (3, 2);

insert into book_genres(book_id, genre_id)
values (1, 1),
       (2, 2),
       (3, 1),
       (3, 2);