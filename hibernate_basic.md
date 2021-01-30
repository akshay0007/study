Hibernate Basic---

persistent object

  hibernate properties xml mapping(configuration,session factory,sessiion,transaction,query,criteria)
  
JTA JDBC JNDi

database

data base configuration using hibernate.cfg.xml

hibarnate::


  transient − A new instance of a persistent class, which is not associated with a Session and has no representation in the database and no identifier value is considered transient by Hibernate.

  persistent − You can make a transient instance persistent by associating it with a Session. A persistent instance has a representation in the database, an identifier value and is associated with a Session.

  detached − Once we close the Hibernate Session, the persistent instance will become a detached instance.


class mapping setup 

Session Factory::

Configuration object is used to create a SessionFactory object which in turn configures Hibernate for the application using the supplied configuration file 
and allows for a Session object to be instantiated. The SessionFactory is a thread safe object and used by all the threads of an application.

The SessionFactory is a heavyweight object; it is usually created during application start up and kept for later use. 


Query Object ::uses sql or hibernate query language (HQL) 

Criteria Object::Criteria objects are used to create and execute object oriented criteria queries to retrieve objects.

  hibernate.properties, or as an XML file named hibernate.cfg.xml.

  <hibernate-configuration>
  <property name= "hibernate.dialect"></property>
  <property name="hibarnate.connection.driver_class">
  com.mysql.jdbc.Driver
  </property>


<!-- List of XML mapping files -->
      <mapping resource = "Employee.hbm.xml"/>
</>


A Session is used to get a physical connection with a database.
The session objects should not be kept open for a long time because they are not usually thread safe and they should be created and destroyed them as needed.

The main function of the Session is to offer, create, read, and delete operations for instances of mapped entity classes.


  transient − A new instance of a persistent class, which is not associated with a Session and has no representation in the database and no identifier value is considered transient by Hibernate.

  persistent − You can make a transient instance persistent by associating it with a Session. A persistent instance has a representation in the database, an identifier value and is associated with a Session.

  detached − Once we close the Hibernate Session, the persistent instance will become a detached instance.

Transient instances may be made persistent by calling <tt>save()</tt>,
 * <tt>persist()</tt> or <tt>saveOrUpdate()</tt>. Persistent instances may be made transient
 * by calling<tt> delete()</tt>. Any instance returned by a <tt>get()</tt> or
 * <tt>load()</tt> method is persistent. Detached instances may be made persistent
 * by calling <tt>update()</tt>, <tt>saveOrUpdate()</tt>, <tt>lock()</tt> or <tt>replicate()</tt>. 
 * The state of a transient or detached instance may also be made persistent as a new
 * persistent instance by calling <tt>merge()</tt>.<br>

<i>transient:</i> never persistent, not associated with any <tt>Session</tt><br>
 * <i>persistent:</i> associated with a unique <tt>Session</tt><br>
 * <i>detached:</i> previously persistent, not associated with any <tt>Session</tt><br>


    Session session=factory.openSession();
    Transaction tx=null;
    try{
    Transaction tx=session.beginTransaction();
    tx.commit();
    }catch(Exception ex){
    if(tx!=null) tx.rollback();
    }finally{
    session.close();
    }



<tt>Criteria</tt> is a simplified API for retrieving entities
 * by composing <tt>Criterion</tt> objects. This is a very
 * convenient approach for functionality like "search" screens
 * where there is a variable number of conditions to be placed
 * upon the result set.<br>

 The <tt>Session</tt> is a factory for <tt>Criteria</tt>.
 * <tt>Criterion</tt> instances are usually obtained via
 * the factory methods on <tt>Restrictions</tt>. eg.

    Criteria Cration::
    session.crateCriteria(Cat.class).add(Restrictions.like("name","IZ%"))
    .add(Restriction.get("weight",new Float(minweight)))
    .addOrder(Order.asc("age"))
    .list();
     List cats=session.crateCriteria(Cat.class)
    .add(Restriction.like('name','abc%'))
    .add(Restriction.gt("wight",new Float(4)))
    .addOrder(Order.asc("age"))
    .list();

    List cats=session.createCriteria(Cat.class)
    .setProjection(Projection.projectionList)
    .add(Projections.rowCount())
    .add(Projections.max("weight"))
    ).addOrder(Order.asc("color"))
    .list();

    Restrictions


Caching-

  session.evict(Object obj)//remove instace from session cache 

  session.flush()//Force this session to flush. Must be called at the end of a
     * unit of work, before committing the transaction and closing the
     * session (depending on {@link #setFlushMode(FlushMode)},


  session.setCacheMode()//Cache mode determines the manner in which this session can interact with
     * the second level cache.

//if isPutEnabled,isGetEnabled ==true  * The session may read items from the cache, and add items to the cache.

	IGNORE( false, false ),
* The session will never interact with the cache, except to invalidate
	 * cache items when updates occur.

CacheMode(boolean isPutEnabled,boolean isGetEnabled){
}

/**
	 * The session will never read items from the cache, but will add items
	 * to the cache as it reads them from the database.
	 */
	PUT( true, false ),


  session.load(class,serializeid,lockmode)//Return the persistent instance of the given entity class with the given identifier,



LockMode


Persist the state of the given detached instance, reusing the current
	 * identifier value. 
replicate(Object object, ReplicationMode replicationMode)


 * Persist the given transient instance, first assigning a generated identifier. (Or
	 * using the current value of the identifier property if the <tt>assigned</tt>
	 * generator is used.) This operation cascades to associated instances if the
	 * association is mapped with {@code cascade="save-update"}

	Serializable save(Object object);



 * Copy the state of the given object onto the persistent object with the same
	 * identifier. If there is no persistent instance currently associated with
	 * the session, it will be loaded. Return the persistent instance. If the
	 * given instance is unsaved, save a copy of and return it as a newly persistent
	 * instance. The given instance does not become associated with the session.
	 * This operation cascades to associated instances if the association is mapped
	 * with {@code cascade="merge"}

  Object merge(Object object);



* Make a transient instance persistent. This operation cascades to associated
	 * instances if the association is mapped with {@code cascade="persist"}

void persist(Object object);



 * Remove a persistent instance from the datastore. The argument may be
	 * an instance associated with the receiving <tt>Session</tt> or a transient
	 * instance with an identifier associated with existing persistent state.

void delete(Object object);



void refresh(Object object);

	 * Re-read the state of the given instance from the underlying database. It is
	 * inadvisable to use this to implement long-running sessions that span many




 * Return the persistent instance of the given entity class with the given identifier,
Object get(String entityName, Serializable id, LockMode lockMode);

All classes should contain an ID in order to allow easy identification of your objects within Hibernate and the database. This property maps to the primary key column of a database table.


  crate table Employee(
  id int not null auto_increment,
  firstname varchar(33) default null,
  lastname varchar(44) default null,
  salry int default null,
  primary key(id)
  )

   Employee.hbm.xml.
  <classname>.hbm.xml
  <hibernate-mapping>
  <class name="Employee" table="employee">
  <meta attribute="class dessc">
  </class>

  <id name="id" type="int" column="id">
  <genrator class="native"/>

  </id>


  <property name="first" column="first" type="string">
  </hibernate-mapping>


The <id> element maps the unique ID attribute in class to the primary key of the database table. The name attribute of the id element refers to the property 
in the class and the column attribute refers to the column in the database table. The type attribute holds the hibernate mapping type, this mapping types will convert 
from Java to SQL data type.

The <generator> element within the id element is used to generate the primary key values automatically. The class attribute of the generator element is set to native to 
let hibernate pick up either identity, sequence, or hilo algorithm to create primary key depending upon the capabilities of the underlying database.


The <property> element is used to map a Java class property to a column in the database table. The name attribute of the element refers to the property in the 
class and the column attribute refers to the column in the database table. The type attribute holds the hibernate mapping type, this mapping types will convert from Java 
to SQL data type.


Baic of creating Session Factory--

    SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    tx=session.beginTransaction();
    List employees=session.createQuery("From Employee").list;
    for(Iterator iterator=employee.iterator();iterator.hasnext();){
    Employee emp=(Employee)iterator.next();
    }
    tx.commit();


mapping of collections;
mapping of assocation b/w entity class;
component mapping

Map,Set,SortedMap,SortedSet,List 

//////////////////////////////
Association Mapping::
Set mapping::

    create table Employee(
    id int  not null  auto increment,
    first name varchar(33) default null,
    last_name varchar(22) default null,
    salary int default null,
    primary key(id)
    )

    create table certificate(
    id int not null auto increment,
    certificate name varchar(33) default null,
    employeeid int default null,
    primary key(id)
    )

    public class Employee {
       private int id;
       private String firstName; 
       private String lastName;   
       private int salary;
       private Set certificates;
    }



Mapping in xmls--

    <hibernate-mapping>
    <class name="Employee" table="employee">
    <meta attribute="class description">
    <id name="id" type="int" column="id">
    <genrator class="native">
    </id>

    <set name="certificates" cascade="all">
    <key column="employee_id">
    <one-to-many class="certificate">
    </set>

    <list name="certificates" cascade="all">
    <key column="employee_id"/>
    <list-index column="idx"/>

    //list 
    <list> element is used to set the relationship between Certificate and Employee classes
    <one-to-many class="certificate"/>
    </list>

    //collection
    <bag name="cetificates" cascade="all">
    <key column="employee_id"/>
    <one-to-many class="certificate"/>
    </bag>

    <property name="firstname" column="firstname" type="string">
    </meta>
    </class>
    </hibernate-mapping>


    <map name="certificates" cascade="all">
    <key column="employee_id"/>
    <index column="certificate_type" type="string"/>
    <one-to-many class="certificate"/>
    </map>



Association Mappings:::

    1	Many-to-One
    Mapping many-to-one relationship using Hibernate

    2	One-to-One
    Mapping one-to-one relationship using Hibernate

    3	One-to-Many
    Mapping one-to-many relationship using Hibernate

    4	Many-to-Many
    Mapping many-to-many relationship using Hibernate
'


Many to One::


crate table employee(
id int not null auto_increment,
firstname varchar(33) default null,
lasstname varchar(44) default null,
salary int default null,
address int not null,
primary key(id)
)

create table address(
id int not null auto_increment,
streetname varchar(44) default null,
cityname varchar(44) default null
statename varchar(454) default null,
zipcode varchar(33) default null
primary key(id)
)

class Employee{
int id;
String firstname;
String lastname;
private int salary;
private Address adddress;


}

public class Address{
int id
String street;
String city;
String state;
String zipcode;
}

<hibernate-mapping>
<meta attributes="class desc">the class contains des</meta>

<id name='id' type="int" column="id">
<genrate class="native">
<id>

<property naem ="firstname" column="firstname" type="string">

one-to-one::
A one-to-one association is similar to many-to-one association with a difference that the column will be set as unique. For example, an address object can
be associated with a single employee object.

A One-to-Many::
 mapping can be implemented using a Set java collection that does not contain any duplicate element. We already have seen how to map Set collection in hibernate, 
 so if you already learned Set mapping then you are all set to go with one-to-many mapping.


A many-to-one::
 association is the most common kind of association where an Object can be associated with multiple objects. For example, the same address object can
 be associated with multiple employee objects.


Caching is important to Hibernate as well. It utilizes a multilevel caching scheme as explained below −


  First-level Cache
  The first-level cache is the Session cache and is a mandatory cache through which all requests must pass. The Session object keeps an object under its own power before committing it to the database.

  Second-level Cache
  Second level cache is an optional cache and first-level cache will always be consulted before any attempt is made to locate an object in the second-level cache. The second level cache can be configured on a per-class and per-collection basis and mainly responsible for caching objects across sessions.


  Query-level Cache
  Hibernate also implements a cache for query resultsets that integrates closely with the second-level cache.


A concurrency strategy is a mediator, which is responsible for storing items of data in the cache and retrieving them from the cache.
If you are going to enable a second-level cache, you will have to decide, for each persistent class and collection, which cache concurrency strategy to use


  Transactional − Use this strategy for read-mostly data where it is critical to prevent stale data in concurrent transactions, in the rare case of an update.

  Read-write − Again use this strategy for read-mostly data where it is critical to prevent stale data in concurrent transactions, in the rare case of an update.

  Nonstrict-read-write − This strategy makes no guarantee of consistency between the cache and the database. Use this strategy if data hardly ever changes and a small likelihood of stale data is not of critical concern.

  Read-only − A concurrency strategy suitable for data, which never changes. Use it for reference data only.



 batch processing 


  Session session = SessionFactory.openSession();
  Transaction tx = session.beginTransaction();
  for ( int i=0; i<100000; i++ ) {
     Employee employee = new Employee(.....);
     session.save(employee);
     if( i % 50 == 0 ) { // Same as the JDBC batch size
        //flush a batch of inserts and release memory:
        session.flush();
        session.clear();
     }
  }
    tx.commit();
    session.close();

  ScrollableResults empcursor=session.crateQuery().scroll();


Programming Desing Association---

suppose we have cource review student and passwport table
cource(id ,name) 
review (id,rating,description,courseid)    
or is courseid is review id


  class Course{
  @OneToMany(mappedBy="course",fetch=FetctType.EaGER)//bcoz review is owing side
  List<Review > data;
  }

  class Review{
  @ManyToOne
  private Course course;
  }



manytomany

course:: id,name
student:: id name passportid


student_course::stu_id course_id


    Course{

    @ManyToMany()
    List<Student>
    }

    Student{
    @Manytomany
    List<Course> courses
    }

ManyToMany assocation--in that cse two join column created course_studnetn,studnet_course

    Course{

    @ManyToMany(mappedby="courses")
    List<Student>
    }


    Student{
    @Manytomany(fetch=FetchStratg.EAGER)
    List<Course> courses
}






in lazy initalize

    @Transactonal
    public void retireForCourse(){
    course.getReviews();//again hit for data base but due to lazy geting lazy initalize so used @Transactonal now it getting persist
    }



course have multiple student and vice versa::

Course Student passport Review::


Course{id,name}
Student {id,name} 
passport{id,number}
Review{id,rating,description}

course and student manytomany
student and passport onetoone
course and review onetomany vice versa manytoone



student and passport one to one cretae either stu_id or passprot_id

student id,name,passport_id

  class Student{
  @Onetoone
  Passport passport;//owining side of relationship
  }
  class Review{}
  public void savestudandpassport(){
  Passport pass=new Passport('433');
  em.persist(pass)//if we are not writing this line it through passport is not persist
  Student stu=new Student;
  stu.setPassport(pass)
  em.persist(sti)
  }



eager fatching::

  onetoone manytoone is by default eager fatching:

Lazy fetching::

  @OnetoOne(fetch=FetchType.Lazy)

lazy initailzation exc could not inintalize proxy  ::no session



  em.perssit()//create hibernate sequence and assign value into id


Transaction entity mananager and persistent context
either all things are succed or nothing succed rollback
persistent context for each beans in block of methods



  in @Trascation block persistent context create for this block and execute each query at the end of block


/////////////
OneToOne bydirectional mapping:::

  Class {
  OnetoOne
  Passport passport;
  }

  class Passport{
  @onetoOne
  Student stu
  }


both table create new entries student_id,passport_id
so used owing side of relationship properties
student to create owing side of relationship

  class Passport{//non owing side
  @OnetoOne(mappedby="passp")
  Student stu
  }

  class Student{//owing side 
  @OnetoOne
  Passport passp
  }


Transaction Exmples--

    @Transactional
    void somethig(){

    //create object
    em.persist(user1);//assign user to id but not save to db
    em.persist(user2);// ""

    //update
    change user1//now update into user1
    change user2//"" to user2

    }//all chages down to the db saves

    @Transactional
    void somethig(){

    //create object
    em.persist(user1);//assign user to id but not save to db
    em.persist(user2);// ""

    em.flush()//changes push down into db

    //update
    change user1//if there is problems it rollback from init
    change user2//"" to user2



    }//all chages down to the db saves



why u need transactonal :::
Repository=>EntittyManager
UnitTesting=>EntiityManager
entire logic within boundries is transactional
update in any block or changes by using entitymangaer directly we need tranasactional


///////////////////////////////////////////

  Class Student{
  @manytomany
  @JoinTable(name="student_course",
  joinColumn=@JoinColumn(name="student_id"),
  inversejoinColumn=@JoinColumn(name="course_id"))
  List<Course> course;
  }

  class Course{

  @ManytoMany(mappedBy="course")
  List<Student> students
  }


User user=em.find(USer.class,1)

List<Comment> comment=user.getComments();//if we are not write transacton in this block data base connection is not established
so require transacton

return comments

Testing Streategy -- dirty context ensure after test case completion revert back db--

  @Test
  @DirectiesContext
  inside block on testing method if all updates happens database 
  roll back
 

fetchStrategy=Strategy.Eager
call next value of hibernate sequence(id ===some value)

inheritance::
Tables::Employee ,FullTimeEmployee,PartTimeEmployee

types of inheritance::
singel table,tableperclasss,joined.mappedSuperClass


@Entity
@Inheritance(strategy=InheritanceTYpe.Single_Table)



IheritanceType{Single_table,Table_per_class,Joined}

Transaction Management::
200 300 deduct from a account
150 300 
150 350 deposit 50 from b account


ACID properties:::

  A::atomicity==>either trans. should be comp succesff or all trans revert back
  C::concurrent==>it should not matter how many tras running in parellel ,but end state of trans. should be same.either they are running in parellel or sequentially
  I::isolation==>multiple level of isolation one trans effect another trans. if thery are running in parellel.suppose A doing some trans. then update value of A is provided to B or not
  D::durablity==>if trans. running and due to system crash or something wrong happend end state of transaction should be persistent.


transactin ::A$50 to b and trans2 A$100 to C
Dirty read::read the value of transaction by another transaction before commiting tranansaction
non repetable read::when i am reading same values 2ys getting 2 different values
phantom read::at different time i am getting different rows in the same transacton.


		dirty read  non repetable phantom read
Read uncommited ::possible  ::possible  ::possible   lock on trans value
read commited   ::solve     ::possible  ::possible   lock on rows 
repeatable read ::solve     ::solve     ::possible   lock the row until trans complete then run another trans on sm row
serializable    ::solve     ::solve     ::solve      lock would be created on constraint applied on table then it create lock ,no delete update and insert perform on constraint after this opration perform



@Transactional(jax,spring)
if u are using two database changes then used spring tranasactional
if u are using one database changes then used javax tranasactional
//database1
//database2

@Transactoinal(isolation="")

//isolation level	
hibarnate.connection.isolation=2


Generated with SEQUENCE strategy

  And if you use the SEQUENCE, Hibernate performs an SQL SELECT statement to retrieve the next value from the database sequence. Hibernate then delays the INSERT statement until it flushes the persistence context. In this example, the flush happens when the transaction gets committed.


    INFO TestPersistSaveMerge:237 - Save entity
  
      select
          nextval ('hibernate_sequence')
  14:10:28,042  INFO TestPersistSaveMerge:240 - Commit transaction
  14:10:28,096 DEBUG SQL:92 - 
      insert
      into
          Author
          (firstName, lastName, version, id) 
      values
          (?, ?, ?, ?)


Generated with TABLE strategy

You shouldn’t use the TABLE strategy because it requires row level locks on the primary key table and doesn’t scale well. If you use this strategy anyways,
Hibernate performs an SQL SELECT statement to retrieve the next primary key value from the database and writes the new value to the database table. It delays the 
execution of the SQL INSERT statement for the new entity until it flushes the persistence context.

  14:11:17,368  INFO TestPersistSaveMerge:237 - Save entity
  14:11:17,482 DEBUG SQL:92 - 
      select
          tbl.next_val 
      from
          hibernate_sequences tbl 
      where
          tbl.sequence_name=? for update
              of tbl
  14:11:17,531 DEBUG SQL:92 - 
      insert
      into
          hibernate_sequences
          (sequence_name, next_val)  
      values
          (?,?)
  14:11:17,534 DEBUG SQL:92 - 
      update
          hibernate_sequences 
      set
          next_val=?  
      where
          next_val=? 
          and sequence_name=?
  14:11:17,584  INFO TestPersistSaveMerge:240 - Commit transaction
  14:11:17,655 DEBUG SQL:92 - 
      insert
      into
          Author
          (firstName, lastName, version, id) 
      values
          (?, ?, ?, ?)



How do you get the primary key values in your application? Do you use natural keys or do you generate technical IDs?


I prefer to generate simple, numerical, technical IDs like you can see in the following code snippet instead of using natural keys which often
require the combination of multiple attributes.

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  view rawPrimaryKey.java hosted with ❤ by GitHub
  Technical IDs are easier to manage and all involved systems, mainly the database and Hibernate, can index them very efficiently.
  This allows you to focus on the business logic of your application and avoids performance issues.  


  4 options to generate primary keys
  The JPA specification supports 4 different primary key generation strategies which generate the primary key values programmatically or use database 
  features, like auto-incremented columns or sequences. The only thing you have to do is to add the @GeneratedValue annotation to your primary key attribute 
  and choose a generation strategy.
  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  view rawGeneratedValue.java hosted with ❤ by GitHub


 
GenerationType.AUTO
The GenerationType.AUTO is the default generation type and lets the persistence provider choose the generation strategy.

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  view rawGenerationTypeAuto.java hosted with ❤ by GitHub
  If you use Hibernate as your persistence provider, it selects a generation strategy based on the database specific dialect. For most popular databases, 
  it selects GenerationType.SEQUENCE which I will explain later.  



GenerationType.IDENTITY
The GenerationType.IDENTITY is the easiest to use but not the best one from a performance point of view. It relies on an auto-incremented database 
column and lets the database generate a new value with each insert operation. From a database point of view, this is very efficient because the auto-increment 
columns are highly optimized, and it doesn’t require any additional statements.

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  view rawGenerationTypeIdentity.java hosted with ❤ by GitHub
  This approach has a significant drawback if you use Hibernate. Hibernate requires a primary key value for each managed entity and
  therefore has to perform the insert statement immediately. This prevents it from using different optimization techniques like JDBC batching.

 


GenerationType.SEQUENCE
The GenerationType.SEQUENCE is my preferred way to generate primary key values and uses a database sequence to generate unique values.

It requires additional select statements to get the next value from a database sequence. But this has no 
performance impact for most applications. And if your application has to persist a huge number of new entities, you can use some Hibernate 
specific optimizations to reduce the number of statements.

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    view rawGenerationTypeSequence.java hosted with ❤ by GitHub
    If you don’t provide any additional information, Hibernate will request the next value from its default sequence. You can change that by referencing the name of a @SequenceGenerator in the generator attribute of the @GeneratedValue annotation. The @SequenceGenerator annotation lets you define the name of the generator, the name, and schema of the database sequence and the allocation size of the sequence.

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    view rawSequenceGenerator.java hosted with ❤ by GitHub


GenerationType.TABLE
The GenerationType.TABLE gets only rarely used nowadays. It simulates a sequence by storing and updating its current value in a
database table which requires the use of pessimistic locks which put all transactions into a sequential order. This slows down your application, 
and you should, therefore, prefer the GenerationType.SEQUENCE, if your database supports sequences, which most popular databases do.

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    view rawGenerationTypeTable.java hosted with ❤ by GitHub
    You can use the @TableGenerator annotation in a similar way as the already explained @SequenceGenerator annotation to specify the database table which Hibernate shall use to simulate the sequence.

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "book_generator")
    @TableGenerator(name="book_generator", table="id_generator", schema="bookstore")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


hibernate merge and update::
You can use Hibernate’s update or JPA’s merge method to associate a detached entity with a persistence context.
After you’ve done that, Hibernate will update the database based on the entity attribute values.

ui web-->service-->data-->database

in data caching machanism apply..


hibenate two level canche::
firslevel
and second level
Persistence context-->FirstlevelCache-->Second level cache-->Database




  t1  t2 t3 running in parellel
  ||
  PersistenceContext pc1
  PersistenceContext pc2
  PersistenceContext pc3
  ||

  second level cache
  ||
  database


first time go to db ret data and placed into persitent context::
then all commn happend from persistenc context(first level cache)


across multiple transsaction and multiple user use it::
list of countries and stats cannot change for same countries
we can stored this data into second level cache..



first level cache for single transacton..

second level cache which is common across the user..




First level cache::

  @Transactional
  public void findById(){
  repository.findById(1000l);


  repository.findById(1001l);

boundry of transaction two different transactoin hit db for retrieveing data 


Second level cache configuration::
manually done.
ehcache ,redis etc..

enable second level cache
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
specifing the caching freamwor::EhCache
(EhcacheRegionFactory class)
spring.jpa.properties.hibernate.cache.region,factoryclass=EhcacheRegionFactory
only cache what i tell to cache
(sharedCacheMode enum)
spring.jpa.properties.javax.persistence.sharedCacheMode=Enable_selective
logging.level.net.sf.ehcache=debug
what data to cache?


performance tuning::


turn statics on
spring.jpa.properties.hibernate.genrate_statics=true
loggin.level.org.hibernate.stat=debug


Indexes::
good indexes.
execution plan


use appropriate caching::
first level caching::within single trans
second level caching::enable it by using second level cache diff tras on the same server same instance of app share same data
like courtries,states
distributed cache::u are running lots of appliation in pareellel then distrbute one app not suff. dist. load multiple application instance 
caching in mulitple application like hazelcast
be careful about size of first level cache.



Avoid N+1 problem::

for finding values we run one query and supporting 3 query run
for 1000 of queries 3*1000 queries

  solvingn+1proble(){
  EntityGraph<Course> entityGraph=em.createEntityGraph(Course.class)
  SubGraph<Object> subgraph=entityGraph.addSubgraph("student");
  em.createNameQuery("queery",Course.class)
  .setHint("javax.persistence.loadgreaph",entityGraph)
  .getResultList();
  }


