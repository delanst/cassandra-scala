**Datastax cassandra with scalatests**

Demonstrates the use of the cassandra datastax driver in a couple of scala tests.
For this example to run set up your cassandra as described below
* create keyspace dev
... with replication = {'class':'SimpleStrategy','replication_factor':1};
* use dev;
* create table emp (empid int primary key,
... emp_first varchar, emp_last varchar, emp_dept varchar);
* insert into emp (empid, emp_first, emp_last, emp_dept)
... values (1,'fred','smith','eng');
* select * from emp;

Verify you have the table emp with one record.

***Cassandra connection***

* CassandraUriSpec

***Statements***

* CassandraSelectSpec
* CassandraUpdateSpec
* CassandraInsertSpec
* CassandraDeleteSpec
