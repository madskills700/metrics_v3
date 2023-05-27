create table if not exists users(
    id uuid default uuid_generate_v4() primary key,
    name varchar(255) not null,
    created bigint default (extract(epoch from now()) * 1000)::bigint,
    changed bigint default (extract(epoch from now()) * 1000)::bigint,
    last_update_id bigint not null
);