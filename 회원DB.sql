create table member
(
	m_idx		int,
	m_name		varchar2(100)	not null,
	m_id		varchar2(100)	not null,
	m_pwd		varchar2(100)	not null,
	m_nickname	varchar2(100)	not null,
	m_grade		varchar2(100)	default '�Ϲ�' ,
	m_ip		varchar2(100)	not null,
	m_regdate	date
)

alter table member
	add constraint pk_member_m_idx primary key(m_idx);

create sequence seq_member_m_idx;

alter table member
	add constraint  unique_member_m_id unique(m_id);
	
alter table member
	add constraint ck_member_m_garde check( m_grade in( '������','�Ϲ�' ) )

insert into member values(seq_member_m_idx.nextval, 'ȫ�浿', 'hong', '1234', '�浿��',
							'�Ϲ�','192.168.0.134', sysdate);
insert into member values(seq_member_m_idx.nextval, '�����', 'admin', '1234', '����',
							'������','192.168.0.9', sysdate);
							
							
select * from member							