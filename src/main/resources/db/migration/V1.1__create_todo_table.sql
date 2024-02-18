create table if not exists todos(
    id varchar(50) not null primary key,
    name varchar(50) not null,
    description varchar(255) not null,
    done bit not null,
    priority int not null
);