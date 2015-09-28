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




           
