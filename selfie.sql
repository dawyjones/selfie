use selfie;

create table if not exists user  (
    userId int not null primary key auto_increment,
    email varchar(255) unique,
    password varchar(255),
    phone int(15) unique,
    name varchar(255),
    birthday date,
    score int,
    gender varchar(1) not null
);


create table if not exists picture (
    pictureID int primary key unique not null auto_increment,
    picture varchar(20000),
    author int(255),
    foreign key (author) references user(userId),
    date date,
    vote int
);

create table if not exists vote (
    voteId int unique auto_increment primary key,
    pictureId int,
    foreign key (pictureId) references picture(pictureId),
    date date
);

create table if not exists points (
    pointId int unique auto_increment primary key,
    owner int,
    foreign key (owner) references user(userId),
    value int
);



