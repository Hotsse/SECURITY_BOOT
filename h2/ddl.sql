CREATE TABLE user (
	username VARCHAR(100) primary key
	, password VARCHAR(72) NOT NULL
	, nickname VARCHAR(20) NOT NULL
	, use_yn CHAR(1) NOT NULL DEFAULT 'Y'
)

CREATE TABLE role (
	role_cd VARCHAR(20) primary key
	, role_nm VARCHAR(50) not null
	, description VARCHAR(200) not null
	, use_yn CHAR(1) NOT NULL DEFAULT 'Y'
)

CREATE TABLE role_user (
	role_cd VARCHAR(20) not null
	, username VARCHAR(100) not null
	, constraint role_user_pk primary key(role_cd, username)
	, foreign key (role_cd) references role(role_cd)
	, foreign key (username) references user(username)
)