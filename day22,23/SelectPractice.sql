#查询姓'李'的老师的个数
select count(*) from teacher where tname like '李%';
#查询男女生人数个数
select count(*) from student where sex='男'
select count(*) from student where sex='女';
#查询同名同姓学生名单,并统计同名人数
select name ,count(sid) from student group by name having count(sid)>=1;
#1981年出生的学生名单
select name from student where year(birthday) = '1998';
#查询平均成绩大于60分的同学的学号和平均成绩
select sid,avg(score) from sc group by sid having avg(score) > 60;
#求选了课程的学生人数
select count(distinct sid) from sc;
#查询至少选修两门课程的学生学号
select sid from sc group by sid having count(*) >=2;
#查询各科成绩最高和最低的分.以如下形式显示:课程ID,最高分,最低分
select cid,max(score),min(score) from sc group by cid;
#统计每门课程的学生选修人数.要求输出课程号和选修人数,查询结果按人数降序排列,若人数相同,按课程号升序排列
select cid,count(sid) from sc group by cid order by count(sid) desc,cid asc;
#打印入职时间超过38年的员工信息
select ename,job,deptno from emp where 2018-year(hiredate) > 38;
#把hiredate列看做是员工的生日,求本月过生日的员工
select ename from emp where month(hiredate) = 12;
#把hiredate列看做是员工的生日,求下月过生日的员工
select ename from emp where month(hiredate) = 1;
#求1980年下半年入职的员工
select ename,hiredate from emp where month(hiredate) > 6;
#请用两种的方式查询所有名字长度为4的员工的员工编号,姓名
select ename,empno from emp where char_length(ename)=4;
#显示各种职位的最低工资
select job,min(sal) from emp group by job;
#求1980年各个月入职的的员工个数
select month(hiredate),count(empno) from emp group by month(hiredate) order by month(hiredate) asc;
#查询每个部门的最高工资
select deptno,max(sal) from emp group by deptno;
#查询每个部门,每种职位的最高工资
select deptno,job,max(sal) from emp group by deptno,job;
#查询各部门的总工资和平均工资
select deptno,sum(sal),avg(sal) from emp group by deptno;
#查询10号部门,20号部门的平均工资(尝试用多种写法)
select avg(sal) from emp where deptno = 10 OR deptno = 20;
#查询平均工资高于2000元的部门编号和平均工资
select deptno,avg(sal) from emp group by deptno having avg(sal) > 2000;
#统计公司里经理的人数
select count(*) from emp where job = 'manager';
#查询工资最高的3名员工信息
select ename,sal from emp order by sal desc limit 3;
#查询工资由高到低第6到第10的员工信息
select ename,sal from emp order by sal desc limit 6,4;


#查询李四学习的课程,考试分数,课程的授课老师
select cname,score,tname
from sc,student,course,teacher
where student.name = '李四' and student.sid = sc.sid and sc.cid = course.cid and course.tid = teacher.tid;
#查询王五有哪些课程没选,显示这些课程名称
select distinct cname from sc,course where sc.cid not in(select distinct cid from student,sc
where student.sname="王五" and sc.sid=student.sid) and sc.cid=course.cid;
#查询所有同学的学号,姓名,选课数,总成绩
select student.sid,student.name,count(sc.cid),sum(score)
from student left Outer join sc on student.sid=sc.sid
group by student.sid,name
#查询所有课程成绩都小于等于60分的同学的学号,姓名
select sid,name
from student
where sid not in (select student.sid from student,sc where student.sid=sc.sid and score>60);
#查询没有学全所有课的同学的学号,姓名
select student.sid,student.name
from student,SC
where student.sid=SC.sid group by  student.sid,student.name having count(cid) <(select count(cid) from course);
#查询每门课程选修人数,格式为课程名称,人数
select cname,count(*) from sc,course where sc.cid=course.cid group by course.cid;
#查询出只选修了一门课程的全部学生的学号和姓名
select student.sid,sname
from student,sc
where student.sid=sc.sid
group by student.sid having count(*)=1;
#查询每门课程的平均成绩,结果按平均成绩升序排列,平均成绩相同时,按课程号降序排列
select cname,avg(score)
from sc, course where sc.cid=course.cid
group by course.cid
order by avg(score),course.cid desc;
#查询学生平均成绩大于80的所有学生的学号,姓名和平均成绩
select student.sid,sname,avg(score)
from student,sc where student.sid=sc.sid
group by student.sid having avg(score)>80;
#查询课程相同且成绩相同的的学生的学号,课程号,学生成绩
select st1.sid,st2.sid,sc1.cid,sc1.score
from student st1,student st2,sc sc1,sc sc2
where st1.sid=sc1.sid and st2.sid=sc2.sid and st1.sid!=st2.sid and
sc1.cid=sc2.cid and sc1.score=sc2.score;
#查询全部学生都选修的课程的课程号和课程名
select course.cid,cname
from course,sc where course.cid=sc.cid
group by course.cid having count(*)=7;
#查询两门以上不及格课程的同学的学号及其平均成绩
select student.sid,avg(score) from student,sc
where student.sid=sc.sid
group by student.sid having count(score<60)>2;


查询所有课程成绩都小于等于60分的同学的学号,姓名
select sid,sname from student where sid in
(select sid from sc group by sid having max(score)<60);
查询没有学全所有课的同学的学号,姓名
select sid,sname from student where sid in
(select sid from sc group by sid having count(*)<7);
查询每门课程选修人数,格式为课程名称,人数
select cid,count(*) from sc where sc.cid in(select cid from course)
group by sc.cid;
查询全部学生都选修的课程的课程号和课程名
select cid,cname from course where cid in
(select cid from sc  group by cid having count(*)=7);
查询两门以上不及格课程的同学的学号及其平均成绩
select sid,avg(score) from sc group by sid having count(score<60)>2;
查询2号课程成绩比1号课程成绩低的学生的学号,姓名
select sid,sname from student where sid in
(select sc1.sid from sc sc1,sc sc2 where
sc1.sid=sc2.sid and sc1.cid=2 and sc2.cid = 1 and sc1.score<sc2.score);
查询学过1号课程并且也学过编号2号课程的同学的学号,姓名
select sid,sname from student where sid in
(select sc1.sid from sc sc1,sc sc2 where
sc1.sid=sc2.sid and sc1.cid=2 and sc2.cid = 1);
查询没学过'叶平'老师课的同学的学号,姓名
select sid,sname from student where sid not in
(select distinct sid from sc where cid  in
(select cid from course where tid in
(select tid from teacher where tname='叶平')));