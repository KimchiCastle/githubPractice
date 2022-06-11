create sequence seq_cinema_c_idx;

create table cinema(
	c_idx			int,
	c_category		varchar2(100)  not null,
	c_subject		varchar2(100)  not null,
	c_info			varchar2(4000) not null,
	c_filename		varchar2(1000) not null,
	c_regdate		date
)

alter table cinema
	add constraint pk_cinema_c_idx primary key(c_idx);
	

insert into cinema values(seq_cinema_c_idx.nextVal, '액션', '어벤져스', '오락영화', '어벤져스.jpg', sysdate);

