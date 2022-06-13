

create or replace view review_view
as
select 
r.r_idx,
r_subject,
r_content,
r_regdate,
m_nickname,
r_jum,
c_subject
from 
review r left outer join member m  on r.m_idx = m.m_idx
left outer join cinema c on r.c_idx = c.c_idx


select * from review_view



select 
*
from 
review r left outer join member m  on m.m_idx = r.m_idx
left outer join cinema c on r.c_idx = c.c_idx


