create sequence hibernate_sequence;
create table post (
    id bigint not null primary key auto_increment,
    title varchar(128),
    content varchar(128),
    author varchar(32)
);

create table comment (
    id bigint not null primary key auto_increment,
    post_id bigint not null references post (id),
    comment varchar(160),
    author varchar(32)
);
