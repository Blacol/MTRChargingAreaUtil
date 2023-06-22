-- auto-generated definition
create table city
(
    id                  varchar(20) not null
        constraint city_pk
        primary key,
    name                text,
    ring_limit          integer default 10,
    area_type           integer default 0,
    charging_area_delta integer default 1,
    distance_delta      integer default 100,
    x int default 0,
    y int default 0
);

