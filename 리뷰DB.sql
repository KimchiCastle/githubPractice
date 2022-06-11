create table review
(
	r_idx		int,
	r_subject	varchar2(1000) 	not null,
	r_content	varchar2(4000) 	not null,
	r_regdate	date,
	r_jum		int,
	m_idx		int,
	r_count		int,
	c_idx		int

)

create sequence seq_review_r_idx;

alter table review
	add constraint pk_review_r_idx primary key(r_idx);
	
alter table review
	add constraint fk_reivew_m_idx foreign key(m_idx) 
	references member(m_idx);
	
alter table review
	add constraint fk_review_c_idx foreign key(c_idx)
	references cinema(c_idx)
	
