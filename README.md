# CMPE-272-DB-TESTING


- To run the project you should have maven, java and mysql on your machine
- To run it in eclipse
        - Clone git respository
        - Open the project as Maven Project
- To update all maven dependencies use following command
        -  mvn clean install
- To create Executable jar file use following command
        -  mvn clean compile assembly:single
- To connect to the database while running the Bootstrap class (main class) provide following arguments:
  -DIPADDRESS=localhost
  -DPORT=3306
  -DDBNAME=hibernate_test
  -DUSER=root
  -DPASS=root

- To set the Isolation Level for our projectfor MySQL, fire the query:
   SET @@GLOBAL.tx_isolation = 'REPEATABLE-READ';

- To check the Isolation Level on your MySQL DB, fire the query:
   SELECT @@GLOBAL.tx_isolation, @@tx_isolation;


- To build, deploy and run application on Tomcat
-- Download Apache Tomcat 7 and install it on your machine
-- Pull latest changes from our git repository
-- To deploy application for the first time execute
    mvn tomcat7:deploy
-- For the consequent deployments use
    mvn tomcat7:redeploy

-- Once application is deployed successfully you can try -
   http://localhost:8080/courseregistration/students/

   Should return a list of students




