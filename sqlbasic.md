ddl command:data definiation language 
define and modify the databaese structure of table or schema
ddl commandss::

	create
	alter 
	drop
	truncate
	rename
 

	desc <table_name>
	create table <tablename>(column1 datatype,column1 datatype);
	drop table <tablename>
	alter table <tablename> add <columnname> <datatype>

	alter table <tablename> drop <columnname>
	modifing column::
	alter table <tablename> modify <columnname> <datatype> default <value>
	default value::
	alter table <tablename> alter <columnname> default 'defaultvalue' ;

rename change tablename::

	alter table <tablenamn> rename to <newtablename>;

truncate::

	:truncate table student;(drop table columns ddl commands it cannot be rooled back table structre rename same but columns are deleted)
	:delete from student;(it is dml command)

in drop student( table completly remove )


DML commands::(data manipulation language ) act row of a table
insert 
update
delete

insert::

	insert into <tablename>(column1 ,column ... ) values (value1,value...),(value1,value...);

update::

	update <tablename> set <columnname>=value where <columnname>=value;

update student set age=10,name='dd' where sid=4;

delete::
	delete from <tablename> where <columnanme>=value;
	delete from <tablename> (whole table rows delete )


date function::
current date::
curdate();

MONTHS DIFF

	timestampdiff(month,'YYYY-MM-DD','YYYY-MM-DD')

	timestampdiff(day,'YYYY-MM-DD','YYYY-MM-DD')

select ename,salary,timestamdiff(year,hiredate,curdate()) as yearworked from employees;


::salary diff and increment salary of those employees whose hire date greater then 1 years::

	select ename,hiredate,timestampdiff(year,hiredate,curdate()) as yearworked,
	if(timestampdiff(year,hiredate,curdate())>1,salary+salary*0.1,salary) as newsalary from employees;

QUARTER function::

	select quarter(curdate());


	case :::
	select ename ,hiredate ,case
		when quarter(hiredate) 1 then 'firstquarter'
		when quarter(hiredate) 2 then 'secquarter'
		when quarter(hiredate) 3 then 'thrquarter'
		when quarter(hiredate) 4 then 'fourquarter'
		end as hireQuarter
	from employees;


current dayname::
dayname(curdate()):

case example::

	select case(dayname(curdate()))
		when 'sunday' then 'weekend'
		when 'saturday' then 'weekend'
		else 'weekday'
	end as dow;

::current months::

	select extract(month from curdate());


str_to_date function::

	select str_to_date('11-12-17','%m-%d-%y');
op:: 2017-11-12 

select clauses::return row by row

	select * from <tablename>;
	select columnname,... from <tablename>;
	select a from <tablename>  it goes to each row then retreive

where clause::
select ename,salary,salary*12 as 'yearsal' from employees;

select used by row by row

where clause use for filtering data

	select ename,salary,salary*12 as 'yearsal' from employees where deptid=20 and salary>12000;

	select ename,salary,salary*12 as 'yearsal' from employees where (deptid=20 or deptid=10) and salary>12000;

clasuse in sql::
in, between and likes::
in == exact matching
between == range matching
like == pattern matching

	select ename ,salary from employees where designation='abc' or designation='xyz';

in clause

	select ename ,salary from employees where designation in('abc','xyz') ;

	select ename ,salary from employees where deptid=10 or deptid=20;
	select ename ,salary from employees where deptid in(10,20) and salary>20000;

between::>= and <=

	select ename from employees where salary>=20000 and salary<=30000;
	select ename from employees where salary between 20000 and 30000;

	select ename from employees where salary between 20000 and 30000 and deptid=10;

	select ename from employees where salary between 20000 and 30000 and deptid in(10);


Like::%(match any string) _(single character matching)::

	select ename from employees where ename like 'n%'; start with n
	select ename from employees where ename like '%n'; end with n

	select ename from employees where ename like '___'; contain 3 char

	select ename ,designation from employees where desgination in('mangaer','developer') and ename like 'n%';


handling null values::

	null::undertermine value
	0::it is determine value

	null value +any arthematic operation is undertermine value
	null value cannt be equate:
	select ename from employees where commission =null

so we are using is clause:

	select ename from employees where commission is null
	select ename from employees where commission is not null


orderby clause::by default asc order

	select ename ,salary from employees order by salary;

	select ename ,salary from employees order by salary desc;

first sort by deptid then if any dept id is repeated then they sort on bais of salary

	select ename ,salary from employees order by deptid, salary;
	like 
	depid salary
	30 3000
	30 4000
	20 2000
	20 3000

used order by with conjunction of where clause

	select ename ,salary from employees where deptid=20 order by salary desc;

limit key word::fetch me firsts three records

	select eid from employees limit 3;

indexing limits:: limit 1,3 one index and three rows

	select eid from employees limit 1,3; getting 3 rows

higehest salary::
	
	select eid from employees order by salary desc limit 1;

second highest salary

	select eid from employees order by salary desc limit 1,1; //indexing 1 and 1

case statements::
case <expression> 
when <case exmpression1> then command
when <case exmpression2> then command
else commands
end

find number is even or odd

	set @number=10;
	select case(@number%2)
	when 0 then 'even'
	when 1 then 'odd'
	end as ecode

for aliasing using as

conditionally increment sallary on basis of dept manage increase by 2000 if developer incr by 1000 else remain same

	select ename ,salary ,case(designation)
	when 'manger' then salary +2000
	when 'developer' then salary +1000
	else salary 
	end as netsalary 
	from employees;

adding prefix mr,mrs on the basis of gender

	select ename, case(gender)
	when 'm' then concat('mr',ename)
	when 'F' then concat('mrs',ename)
	else ename end as name
	from employees;

string function::
length function

	select length('abc');
	op :3

upper::

	select upper('abc');

substring::

	select substring('nikhil',2,4)
	op::ikhi (from second pos we extract 4 character)

instr() ::index pos find out

	select instr('nikhil' ,'i')
	first occurance of i
	op::2

concat('','') concat both of string and 2 level of nesting is perform

for adding first and last name with space seprator::

	select first,last,concat(first,concat(' ',last)) from employees;

ltrim()::remove left side  trailling spaces

	select ltrim('  abc');
	op abc

repeat ::repetion of particular value

	like repeat('*',5)::*****

replace('amit','a','v'):replace a with v

	op:::vmit


null releated function in mysql::

	ifnull(expr1,expr2)

if expr1 is null then it returns expr2
else it returns expr1

	select if(3,4);
op 4

	select if(null,4)
op 4

effective salary find out

	select ename ,ifnull(salary+commision,salary) from employees;

if condition::
select if(2>3,'aa','bb')
op bb

group functions::

	group function act on multiple row at a time like sum,avg,min,max,count

	group function do not oprate on null values

	group function evaluate after where clause


	select count(*) from employees;
	select sum(salary) from employees;
	select min(salary) from employees;
	select avg(salary) from employees;


group function always evaluated after filtering of rows:


	select count(name) from employees where salary  between 400 and 500;

	goup function cannt be evaluate for null values
	select count(commision ) from employee;

	give 8  instead of 10 because 2 values are null

	select ename extract(year from hiredate) as hiredate from employees;

	select enmae,timestampdiff(year,hiredate,curdate()) from employees
	select count(*) from employees where timestampdiff(year,hiredate,curdate())>1;

distinct keyword::

	select count (distinct deptid,desigmation ) from employees;

count of male and female

	select 
	sum(case(gender) when 'm' then 1 else 0 end) as males,
	sum(case(gender) when 'f' then 1 else 0 end) as females,
	from employees;

group by clause in mysql::

	group function cannt oprate on null values
	it execute after the where clause
	it can never be written inside where clause
	only the columns mentioned in the group by clause can be selected using the select clause


select distinct deptid from employees;

	select deptid ,count(*) as noemployee from employees group by deptid;

first grouping perform then group function applied.

	select deptid,max(salary) from employees group by deptid;

	select extract(year from hiredate) ,count(*) from employees group by extrct(year from hiredate);

	group clasue execute after where clause::

select deptid,count(*)
from employees where deptid in(10,20) group by deptid;


*********************************************
*********************************************

	1:flow of groups::
	2:where 
	3:group by
	4:group function
	5:having clause
	6:order by
	
*********************************************
*********************************************

	select designation ,avg(salary)
	from employees where desgination in (''mager','tech');
	group by desgination;

group function can never be written inside where clause::


	select deptid ,count(*) from employees 
	where count(*)>2 (this is wrong due to inside where clause,for this we are using having clause) group by deptid 

Having clause::

	bcoz we cannt write group function inside where then having clause comes


select deptid,count(*) from employees group by deptid having count(*)>2 

select deptid,name ,count(*) as desgcount employees group by designation having desgcount=1

	select desgniation ,avg(salary) as avgsal
	 from employees where desgination in('a','b')
	having avgsal>2000 order by desgination desc;

select extract(year from hiredate) as yer .count(*) as coutyear from employees where extract(year from hiredate) in ('2015','2016')
group by yer
 having coutyear>2;


primary key constraint::

:set of columns so it can be identify uniquely
:it must contain unique and not null values
:composition primary key can have multiple columns whose combination alway be unique
:to add a primary key to an existing column use 
alter table <tablename> add constraint <constraintname > primary key (<column name>)
:to drop primary key use alter table <table name> drop primary key

	create table movie(mname varchar(44) primary key,director varchar(44));
	insert into movie values('abc','xyz'),('rst','xyz');

drop primary key::
	
	alter table movie drop primary key;

add primary key::

	alter table movie add constraint mname_movie_pk(colun_table_primaryke) primary key(mname);

composite primary key::

	alter table movie drop primary key;
	alter table movie add column ryear int;

	alter table movie add mname_ryear_movie_pk primary key(mname,ryear);

	:a table have only one primary key

	delete from movie where ryear=0;

metadataview::
	
	desc information_schema.key_column_usage;

******************************************************
meta data information getting from information_schema
*******************************************************

	not null constraint in mysql::
	it force to column not null 
	syntex::<column_name> <datatype> NOT NULL

TO add in existing columns::
<tablename> change <old_colmnname> <newcolumnname> <newcolumndefinition>

create table products(pid int,pname varchar(20) not null);
alter table products1 change pname pnamenew(or either pname) varchar(20) not null;

create table test11(sno int not null default 0);


foreign key constraints::

:refrential intergrity between two tables it points the primary key of another table
:it can be set to null
:it denote parent child relationship a child cann't be inserted unless parent exists and parnet cann't be deleted if child exists

to create a foreign key in create table statement use

key(<columnname>) references <tablename>(<columnname>) [on update action] [on delete action]

table structure::

suppose emp table (eid,ename,deptid)   ::child table
and in dept(deptid,dname)  ::parent table

to add a foreign key constraint to existing tables:

	alter table <tablename> add constraint <constraintname> FOREIGN KEY(<columnname>) references <tablename>(columnname) [on update action] [on delete action]

to drop a foreign key use ::

	alter table <tablename> drop foreign key <foreign key name>

::foreign key points to the the primary key of another key so foreign key always add child table :


	alter table emp add constraint emp_dept_deptid_fk foreign key(deptid) refrences dept(deptid);

	alter table dept add constraint dept_deptid+p primary key(deptid);

parent cannot be deleted until child be deleted yet

	create table emp12(eid int primary key,ename varchar(20), deptid int ,foreign key (deptid) refrences dept(deptid));

meta data view for foreign key::

	desc infromation_schema.key_column_usage;


drop table emp13;

to drop foreign key use

	alter 	table <tablename> drop foreign key <foreignkeyname>


	two clasue on update and delete actions::it can be set to cascade,set null,restrict

*****************************
show create table emp;
*****************************
alter table emp drop foreign key emp_dept_deptid_fk;


::on delete cascade::on delete parent row child row cascaded:

dependent child records also deleted.

	alter table emp add constraint emp_dept_deptid_fk foreign key(deptid) refrences dept(deptid) on delete cascade;

::in set null null value set in columns:

	alter table emp add constraint emp_dept_deptid_fk foreign
	 key(deptid) refrences dept(deptid) on delete set null;

::restrict value set in columns: it is restricting to deleting rows

	alter table emp add constraint emp_dept_deptid_fk foreign
	 key(deptid) refrences dept(deptid) on delete restrict;


::on update cascade;

	alter table emp add constraint emp_dept_deptid_fk foreign
	 key(deptid) refrences dept(deptid) on update cascade;

suppose dept id 10,20 in there we are updateing 20 to 30 then in on cascade update it update into child and parent to 30:

now create table of country,states and city::

	create table country (cid int primary key,cname varchar(20));

	create table state (sid int ,sname varchar(20),cid int);
	alter table state add constraint state_pk primary key(sid) 
	alter table state add constraint country_state_cid_fk foreign key (cid) refrences country(cid) on delete cacade;

create table city (cid int ,cname varchar(20));
alter table city add constraint state_city_cid_fk foreign key (sid) refrences state(sid) on delete cacade;


foreign key constrint on same table :::
suppose emp has eid,ename,deptid

alter table emp add mgrid int; (it is manaage id)
update emp set mgrid=1 where eid =2;

eid mgrid
1	null
2	1	
3	2	
4	40 (no manage has eid exits)

eid is primary key
eid and mgrid has constraint relationship::
alter table emp add constraint foreign key (mgrid) refrences emp(eid);

introduction to joins::

joins==used to fetch data from multiple table:

	cross join
	inner join
	left outer join
	right outer join
	self join

select * from emp1,dept1;
suppose emp1 has 2 rows and dept1 has 3 rows 
then op rows--6


	select e.ename ,d.dname from emp1 e cross join dept1 d

	select deptid from emp1 e cross join dept1 d becoz deptid is share b/w both then it gives ambiguse problem

	select d.deptid from emp1 e cross join dept1 d 

	select e.ename ,d.dname from emp1 as e cross join dept1 as d where e.deptid=d.deptid;

for inner join
select e.ename ,d.dname from emp1 as e inner join dept1 as d on e.deptid=d.deptid;


select e.ename ,d.location from employees e join dept d on e.deptid=d.deptid order by location where d.location='mum' ;

inner join
select c.cname ,s.sname from coutnry as c inner join states as s
on c.cid =s.cid ;

select c.cname ,s.sname from coutnry as c inner join states as s
on c.cid =s.cid group by c.cname where c.cname='usa' ;

select * from coutry c .satate s, city c wher s.sid=c.cid and s.sid=cit=s.sid

select ci.cityname ,s.sname,c.cname from coutry c join state s on
s.cid=c.cid
join 
city ci on s.cityid=ci.cityid;

select e.ename ,count(d.dname) as dn e.salary as s from emp as e join dept as d on e.deptid=d.deptid group  by dn order by s limit 1 having dn>3;

select  s.sname as nm ,cout(s.sname) ,ci.name as ciname from state as s join city as ci on s.id=ci.id group by ciname 

join 3
outerjoin
left and right outerjoin

suppose employees tables have
(eid,ename,salary,deptname,desgination,mgrid,commission,gender,hiredate)
and department table has 
(deptid,dname,location)

getting all employees respecting to dept id

select e.ename,d.dname from employees e join department d
on e.deptid=d.deptid

suppose table employee has washim but not have any deptid and in department deptid 40 not 
comming into employees table then result exclude both of rows in join query 
so this purpose we are useing outer join

select e.ename,d.dname from employees e left outer join department d
on e.deptid=d.deptid
//unmatch row of left side table selected in this query
//then output washim with deptid null also selected

select e.ename,d.dname from employees e right outer join department d
on e.deptid=d.deptid

//then o/p null training also selected in right join case

select e.ename,d.dname from employees e right outer join department d
on e.deptid=d.deptid where e.enmae is null;

select d.dname ,count(e.ename) from employees e right outer join department d
on e.deptid=d.deptid group by d.dname;

select d.dname ,if (count(e.ename)=0,'no','yes') as has emplyees from employees e right outer join department d
on e.deptid=d.deptid group by d.dname;

select d.dname report ,count(*) from employees e right outer join  department d 
on d.deptid=e.deptid group by gender;

 
self join table join iteself called self join::
suppose table emp2(empid,ename,mgrid)

select * from emp2 e ,emp2 m;

select e.enmae as employee ,m.ename as manager 
from emp2 as e join emp2 as m
on e.mid=m.empid;


select count(*) as coutn,m.ename as manager
from emp2 as e join emp2 as m
on e.empid=m.empid 
group by manager
order by manager
having coutn>3;


select e.ename, extract(years,e,hiredate ) as datehires from emp2 as e join emp2 as d on e.empid=d.empid
on extract(year,e.hiredate)=extract(year,d.hiredate) and e.ename!=d.ename where e.depid=20;
and d.deptid=20;

subquery ::query with in qury::
the innermost subquery executed first upon whose result immediatetly nested query is executed
single row subquery::when the subquery return only one row in this we use oprator like =,!=,>,<,>=,<=
multiple row subquery ==> when the subquery returns more then one row,in this case we use opreator 
like IN ANY and ALL
correleted subquery:: a subquery that takes information from outer query

suppose employee table has (eid,ename,salary ,deptid,designation,mid)
in department(deptid,dname,location)

select ename from emplyees where salary >(select salary from employees where ename='abc');

single row subquery::
select count(*) from employees where >(select salary from employees where ename='abc');

select count(* ) from employees where >(select avg(salary) from employees )

select enmae,extract(year,hiredate) as yeras from employees where yeras= select extract(year,hiredate) from employees where ename='ansita'
and enmae!='abc';
	
select ename from employees where eid=(select mid from employees where ename='abhinav')


select location from department where deptid=(select deptid from employees where ename='nikhil')

select location from department as d join employees as e on e.deptid=d.deptid where e.ename='nikhil';

select count(*) from employees where mid=(select eid from employees where ename='nikhil')


select count(*) from (
select e.enname as emp ,m.enmae as mgr
from employees e join employees m on 
e.mid=e.eid where m.enmae='nikhil') as a

)

multiple row subqueries::
suppose we have city table and it has colums(city_id,city_name,sid)
and state has colums (sid,sname,cid)

select city_name from city where sid in(select sid from state where sid='up' and sname='mp')

suppose we have coutry table ,it has columns(cid,cname)
state(sid,sname,city)
city(city_id,city_name,sid)
select city_name from city where sid in(select sid from state where 
cid in (select cid from coutry where cname='india'))


select name from employee where eid in(select mid from employee)

select name from employee where eid not in (select mid from employee)


corelated subqueryies::
a subquery whose result dependent on the outer queyr is a correlated subquery
when an alias name for outer query table is used in the subqeury then the query become correlated subqeury


bottom to up approach followed by coreelated subquery
select ename from employees as e where salary>(select avg(salary) from employees where deptid=e.deptid);


select ename from employees as e where exits(select * from employees where mid=e.eid)

select deptname from department as d where not exits(select * from employees where deptid=d.deptid) 




=================================================================================================================
Advance lecutrues::

	views are database  object contains store query so it increase performance of database
	views are used to simplify complex queries
	it provide extra layer of security
	it can either be simple or complex


they are just logic oprators

creation of views::
create view vi as select * from employees;//it contains store query

drop view vi;

now we used view for getting datas

select eid ,ename from vi;

create or replace view vi as select * from emp1 where deptid=10//if already have definintion of view then it replace with new one


create or replace view v2 as(
select e.ename as name from employees as e joins department as d on e.deptid=d.deptid
) ;


now on the basis of view v2 we are find all filter so that we can evaluate more complex queries

select ename from v2 where ename='abc';


create view deptreports as(
selct d.dname ,min(salary),max(salary) from employee e join department d on e.deptid=d.deptid
group by d.dname 
);

select * from deptreports;

in mysql views can also updatable we can also perform insert,delete,update oprations on tables

only simple view can be updatable view containing joins,aggregate funciotn,having clasuse,group by etc cannt be updatable


WITH CHECK OPTION is used to maintain consistency of views so this clasue is used for preventing  us from inserting 
and update rows which are not visible through views::

create view v1 as select * from employees;

insert into v1 values(1,'name','dd');

select * from v1;
select * from employees ;

//both are updated in that case

delete ,update,insert all three are works perfectly and show immediate response into employee tables;
vice versa 


meta data view information::
desc infromation_schema.views//getting all infromation in tablet format

select table_name,is_updatable from information_schma,views where table_schma='databaseschema'

with check option::this constraint only works in oracle databases

create veiw v2 as select * from employee where age>20;

update employee set age=99 where eid=1;
update employee set age=1 where eid=2;
update employee set age=0 where eid=3;

select * from v2;
op:o/p of row whose age is 99 bcoze it fullfill all constraints;

check options::
create veiw v2 as select * from employee where age>20 	WITHD CHECK OPTION;

insert into v2 values('bame','12')//age less then 20 not inserted into tables;


create view v2 as select * from emp where mobile like '91__________' WITH CHECK OPTION;
insert into v2 values('hja','9099')//not inserted
insert into v2 values('hja','911919191919')//inserted along with 10 digits long number



==========================================================================
triggers in sql:
a trigger is a set of sql statement stored in the database catalog which are executed and fire when some events
associated with table occures

trigger are special type of store procedure that are executed automatically on firing of some events 

trigger ares used
Data intergrity
schedule task and audit the changed table data


trigger creation ""
create trigger <triger name > <trigger time(before after)> <trigger event(insert,delete,update)> On <tablename> begin end;

delimiter $$
create trigger trig_emp before insert
on emp
for each row
begin


end $$
delimter ;

create table trig_log(logs varchar(222));

delimiter $$
create trigger trig_emp after insert
on emp
for each row
begin
	insert into trig_logs values('a new rows is inserted into it');

end $$
delimter ;


delimiter $$
create trigger trig_emp after update
on emp
for each row
begin
	update emp set ename='' where eid ='';

end $$
delimter ;

delete from trig_logs;

drop trigger trig_emp;



insert ::NEW
update ::NEW,OLD
delete ::OLD

delimiter $$
create trigger trim_emp after insert
on emp
for each row
begin 
insert into trig_logs values(concat('a new data is inserted',NEW.ename))
end $$

delimiter $$
create trigger trim_emp after update
on emp
for each row
begin 
insert into trig_logs values(concat('a new data is inserted',NEW.ename))
insert into trig_logs values(concat('you have removed name ',OLD.ename))
end $$

delimiter $$
create trigger trim_emp after delete
on emp
for each row
begin 
insert into trig_logs values(concat('delte name value',OLD.ename))

end $$


Triggers::
trigger to maintain the audit infromation of emp table having information about operation time,oldname ,deptid,newname 
and deptid

trigger for automatic id genrateion of emp table


crate table audit_info(operation varchar(22),timeops datetime,vold varchar(222),vnew varchar(222));

create trigger insert_emp insert on emp 
for each row
begin 
insert into audit_emp values('insert',curdate(),null,concat(NEW.ename,NEW,deptid));


create trigger trig_emp before insert on emp 
for each row
begin 
declare maxsno int;
select max (substring (eid,6,length(eid)) into maxsno from emp)
if maxsno is null then 
	set maxsno=0;
endif;
set NEW.eid=concat('MATHO',(maxsno+1))
end $$


indexes::
index are used by queries to find data quickly

if there is no index to help the query then query engine perform entire table scan

to create a index use create index <index name> on <tablename> (<column name>)

to drop index drop index <index name> on <table name>


suppsos emp table
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| eid         | int(11)     | NO   | PRI | NULL    |       |
| ename       | varchar(44) | YES  |     | NULL    |       |
| salary      | varchar(44) | YES  |     | NULL    |       |
| desgination | varchar(44) | YES  |     | NULL    |       |
| mgrid       | int(11)     | YES  |     | NULL    |       |
| gender      | varchar(1)  | YES  |     | NULL    |       |
| hiredate    | datetime    | YES  |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+

===============================================================
explain select ename from employees where salary=5000

like number of rows have been scans 
==================================================================

if u create index on salary it is allocating some different memmory for salary then stored in sorted order
address associated with salary 
always scondary space is allocated


index is like searching in books for certain page no.


create index index2 on employees(salary);
drop index index2 on employees;

select ename form employees where salary =5000;
if salary on 50 rows it traverse 50 rows and time is take by query is more

but if u create index::
create index index2 on employees(salary);
then search for :::
select ename form employees where salary =5000;

then it travers only one rows

show desc ::using explain qeury:::::
explain select ename from employees where salary=5000

meta data information::
desc information_schema.innodb_sys_tables;


select * from employee where salary=6000;
then it go through all data and 

idexes on commision column 

create index index3 on employees (commission);

explain select ename from employees where commission =50000;


cluster indexes;
secondary index allocation is differently


indexes stored values in sorted order by default ascending order;;


searching containt in books from indexs


genral cluster indexes;;
storing data of the table into insertion order::


cluster and secondary indexes::
a clustered index determines the physical order of data in a table it is analogus to a telephone directory,it doesnot required any addition disk space.

a table can have only one cluster index .

primary key constraint creates a clustered index automatically

secondary index is same as index of text book where data stored in one place and index into another,index is a pointer of data ,

in secondary index data storage in asceding order and a single table has more then one secondary indexes.

select table_id from information_schema.innodb_sys_talbes wher name='test/emp'

primary key create automatically cluster index into table ;	


disadvantages::
insertion , deletion and updataion is create more complexcity into tables.


stored procedures:::
it is segement of decalarative statement stored inside database catalogs

it increase performance ,reduce network traffic,provide code resusality and security..

stored in pass in complile format inside database;

create procedure <procedure name>(parm_list)
begin
  varialble declaration;
  perform some operation
end

paramert passed inside store procedure::
3 fromat::
IN, OUT and INOUT;


set @a=2;

create  procedure pp5(IN a integer)
begin 
set a=10;
end &

o/p::2
create  procedure pp5(OUT a integer)
begin 
set a=10;
end &

o/p::10

create  procedure pp5(INOUT a integer)
begin 
set a=10;
end &

o/p::

call pp5(@a);
op
 
delimiter &

set @a=4;
create procedure pp1(IN a integer)
begin 
set a=a+10;
end &


number is odd or even ::
delimiter &

create procedure odd_even(IN num integer)
begin 
if num%2 =0 then
select 'the number is even';
else 
select 'number is odd';
end if;
end &

loops::
while loops::


delimiter &
create procedure id_prime(IN a integer)
begin 
declare count integer;
declare num integer;
set num=2;
set count=0;

while num<=a/2 do
if a%num=0 then
set count=count+1;
end if;
set num=num+1;
end while
if count=0 then
select 'the number is prime';
else 
select 'the number is composite';
end if
end &

delimiter &
create procedure num_employees (IN dnamve varchar(44))
begin 
declare cnt integer;
select count(*) into cnt from employees where deptid=(select deptid from department where dname=dnamve);
select cnt;
end &


second approach::

delimiter &
create procedure num_employees (IN dnamve varchar(44),INOUT cnt integer,INOUT avg integer)
begin 

select count(*) into cnt from employees where deptid=(select deptid from department where dname=dnamve);

select avg(salary) into avg from employees where deptid=(select deptid from department where dname=dnamve);

end &

set @cnt=0;
set @avg=0;
call num_employees('sales',@cnt,@avg);

select @cnt;
select @avg;


create procedure emp_dept(IN eid integer,INOUT assignname varchar(100))
begin 
select dname into assignname  from department where deptid=(select deptid from employees where eid=eid);

end


select last_day('1993-07-01');
+------------------------+
| last_day('1993-07-01') |
+------------------------+
| 1993-07-31             |
+------------------------+


Cursor::not very performace good
IT allow to iterate a set of rows returns by query and process them accordingly

we create cursor inside stored procedure to handle result sett returned by query

mysql cursor are read only and non scrollable

declare cursor (and it always associate with select cows)
steps of cursor:
declare
open
fetch
close

Declare <cursor name> for select_statement

Open <cursor_name> initailaze the result set for operation

Fetch cursor_name into varialbe list to retrieve the next row pointed by the cursorr and move the cursor to the next row in the result set

Close <cursor_name> to deactive the cursor and release memory associated by cursor




select * from employees ;
result is resultset and all results is proceessed by row by row


delimiter &
create procedure procemp()
begin
declare v_ename varchar(100);
declare v_salary int;
declare v_finished integer default 0;

declare  c1 cursor for select ename,salary from employees;
declare continue handler for NOT FOUND set v_finished=1;
open c1;

get_emp:LOOP
 fetch c1 into v_ename,v_salary;
 if v_finished=1 then 
	leave get_emp;
 end if;
 select concat(v_ename,v_salary);
END LOOP get_emp;

close c1;
end &

delimiter &
create procedure listemp(IN deptidpass int(44),inout listdata varchar(1000))
begin

declare v_ename varchar(1000);
declare v_finished integer default 0;

declare c1 cursor for  select empname from employees where deptid=deptidpass;
declare continue handler for NOT FOUND set v_finished=1;

get_emp:LOOP
fetch c1 into v_ename;
if v_finished=1 then
 leave get_emp;
end if;
set emplist=concat(listdata,concat('',v_ename));


END LOOP getemp;
close c1;
end &
