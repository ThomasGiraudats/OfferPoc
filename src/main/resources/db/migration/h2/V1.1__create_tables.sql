create sequence offer_user_id_seq start with 10 increment by 50;

create table offer_user (
                            user_id bigint default offer_user_id_seq.nextval,
                            user_name varchar(255) not null,
                            user_country_id bigint,
                            user_gender bigint,
                            user_phone varchar(255),
                            user_birthdate timestamp,
                            user_created_at timestamp,
                            user_updated_at timestamp,
                            primary key (user_id)
);


create sequence offer_country_id_seq start with 10 increment by 50;
create table offer_country(
                              country_id bigint default offer_country_id_seq.nextval,
                              country_code varchar(255) not null,
                              country_label varchar(255) not null,
                              primary key (country_id)
);