create table authors (id  bigserial not null, first_name varchar(255) not null, last_name varchar(255), primary key (id))
create table posts (id  bigserial not null, content text, slug varchar(255), author_id int8 not null, primary key (id))
alter table posts add constraint post_slug_uniq  unique (slug)
create index post_author_idx on posts (author_id)
alter table posts add constraint FK_5srofo2nnf15n2hj8fb4qity7 foreign key (author_id) references authors
