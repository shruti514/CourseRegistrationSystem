# CMPE-272-DB-TESTING


- To run the project you should have maven, java and mysql on your machine
- To run it in eclipse 
        - Clone git respository
        - Open the project as Maven Project 
- To update all maven dependencies use following command
        -  mvn clean install 
- To create Executable jar file use following command
        -  mvn clean compile assembly:single 
- To connect to a database other than given in the code change **presistence.xml 
        - Change the value of the property **javax.persistence.jdbc.url in that file




           
