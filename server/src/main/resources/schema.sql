create table if not exists products(
    id varchar (15) primary key,
    name varchar (255)
);

create table if not exists users(
    email varchar(255) primary key,
    name varchar(255),
    surname varchar(255)
);