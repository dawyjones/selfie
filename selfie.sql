use selfie;

create table if not exists user  (
	userId int not null primary key auto_increment,
	email varchar(255) unique,
    password varchar(255),
    phone int(15) unique,
    name varchar(255),
    birthday date,
    score int
);

<<<<<<< HEAD

create table if not exists picture (
    pictureID int primary key unique not null auto_increment,
    pictureJson varchar(20000),
=======
create table if not exists pictureMode (
	modeId int auto_increment unique primary key,
    mode int
);

create table if not exists picture (
	pictureID int primary key unique not null auto_increment,
	path varchar(255),
>>>>>>> klie-branch
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


create table if not exists SEQUENCE (
	SEQ_NAME varchar(255),
    SEQ_COUNT int(10)
);

INSERT INTO SEQUENCE (SEQ_NAME, SEQ_COUNT)
VALUES ("SEQ_GEN", 0);

