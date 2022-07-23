create table delivery(

    id bigint not null auto_increment,
    customer_id bigint not null,
    fee decimal(10, 2) not null,
    status varchar(20) not null,
    order_date datetime not null,
    finalization_date datetime,

    recipient_name varchar(60) not null,
    recipient_street varchar(255) not null,
    recipient_number varchar(30) not null,
    recipient_complement varchar(60) not null,
    recipient_neighborhood varchar(30) not null,

    primary key (id)
);

alter table delivery add constraint fk_delivery_customer
foreign key (customer_id) references customer (id);