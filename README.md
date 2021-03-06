******************************
#### Project Name: aws-app
******************************

### Project URL: [aws-app](http://www.rupalmartin.com/AWSApp-1.0-SNAPSHOT)

* University Name: http://www.sjsu.edu/
* Course: Cloud Technologies
* Professor Sanjay Garje
* ISA: Divyankitha Urs
* Student: Rupal Martin 
* Linkedin URL:[Rupal Martin](https://www.linkedin.com/in/rupal-martin-34272272/)

### Table of Contents
* Project Introduction
* Sample Demo Screenshoots
* Prerequisite setup
* How to set up and run project locally

### Project Introduction
A Spring MVC web application to
* Upload files to S3
* Dowload files to S3
* Update files to S3
* Delete file from S3

### Sample Demo Screenshots

* Login: Login using credentials or register as a new user

![Login](/images/login.jpg?raw=true "Login screen")

![login2](/images/login2.jpg?raw=true "login2")

  * Database Entry: User details in database:
select * from users;

![login2](images/db_login.jpg?raw=true "login2")

* Upload: Choose the File you want to Upload and Enter the file name

![login2](images/upload.jpg?raw=true "upload")

  * File uploaded successfully(sample.jpeg)

![login2](images/upload2.jpg?raw=true "login2")

  * For each file upload, application is track following fields of Users:

![login2](images/upload_db.jpg?raw=true "login2")

* Update:

![login2](images/upload.jpg?raw=true "upload")

   *  File uploaded successfully(sample.jpeg) and file time updated in update time.

![login2](images/update.jpg?raw=true "login2")

  * For each file upload, application is track following fields of Users

![login2](images/update_db.jpg?raw=true "login2")

* Download

![login2](images/download.jpg?raw=true "login2")

 * Deleting a file:

![login2](images/delete.jpg?raw=true "login2")

  * Confirm the deletion message.

![login2](images/delete_confirm.jpg?raw=true "login2")

  * Success message is displayed after deletion of the file. 

![login2](images/delete_success.jpg?raw=true "login2")

  * Record deleted from Database:

![login2](images/delete_db.jpg?raw=true "login2")

### Pre-requisites Set Up

* AWS configurations

* Signup for an account in AWS
Go to IAM and create groups and attach policies ao that they have full access to S3 , RDS ,CloudWatch,CloudFront,Route 53,Lambda and     SNS

  * Attach a user to this group.
  * IAM creates a link with which we can log in.

  * S3

  * Select S3 from services menu.Click on create bucket.

   * Provide a bucket name, specify the region where you want the bucket to be created,enable versioning and click on create.

   * An empty bucket is created.

  * Attach a life policy under Management tab.I have attached a policy to transfer the contents of the bucket to Standard IA after 75 days and later after 1 year to Amazon Glacier.

  * Enable Replication.

 * Enable SNS events, so that each time an object is put into object an alert is sent to the user.Configuration of SNS is explained later in the section.

* RDS

   *  Select RDS from services menu

   * Click on Launch Services

    *  Select the db instance you want to create.I have chosen Mysql.

   * Next step choose a use case - Dev/Test

   * Give DB instance

   * Click No for multi AZ development

   * Create a name for the instance and provide master username and password.

   * Give a database name

  * And leave the rest as default and click on launch instance.

  * Once it is launched you can install Workbench to work in your local.

  * The db instance created will give you a host end point with which we log into our workbench.

  * Provide end point, master username and password creted at the time of creation of the db instance.

* SNS(Simple Service notification)

  * SNS provides notofication services to end users.
  
  * I have enabled email notification service.
  
  * Select SNS from services menu.
  
  * Create a topic.
  
  * Give where you want the notification to be sent.You can give either your mobile number or Email.
  
  * A confirmation mail will be sent to your email/mobile.Confirm it and start using the service.
CloudWatch

 * CloudWatch is used for monitoring metrics of various resources in AWS

   * Click on Create Alarm

   * Choose for which service you want the alarm to be set.I have set an alarm to notify the user when more than two objects are pushed into S3 bucket.

   * Give a name for your alarm.

   * Set the threshold.

   * Set the state - which is alarm

   * Once the alarm is sent you ll be notified by email/mobile(depending on what you have given in SNS)

* Project Deployment on EC2 instance

  * I have chosen deployed my web application on EC2 instance
  
  * I have installed apache tomcat server  version 7 on ec2 instance.
  
  * Now in order to deploy our file - Create a war of your project and upload it in this environment.Click on deploy.If everything goes
  
  * well you will get a URL with which you access your application.
  
* Route 53

  * I have registered my domain on godaddy.com and have added the Name servers of Route 53 to it. It connects the end users to your applcation by converting domain names to IP addresses
 
  * Click on Route 53

   * For Route 53

  * Click on Create Hosted Zones

  * Give a Domain anme of your choice.

  * Click on create.

  * You ll get a confirmation mail once your name is registered.

  * To make our applcation publicly available with the registered domain name, click on record set in route 53.Give a name For example : "www".Click yes for alias and select the beanstalk URL.Click on Create.It might take quite sometime for the domain to be available.



### Local Set Up

* Intelij - I have downloaded Intelij 2.5 version for wirting JAVA code in my local.
* SPRING MVC framework - I have used spring mvc framework in my application.I have imported all the jars into my lib folder in my project.
* AWS SDK and JAVA SDK - These SDK's are necessary for integrating amazon services with out code and eclipse.
* Apache Tomcat V8 - web server has been installed in my Intelij.

### How to set up and run project locally

* Open Intelij. Click on New project and select Dynamic Web Application.Check the checkbox for generating web.xml file.Click on Finish.You can see the project in project explorer.

* Click on helpin Intelij and install new software.

* Select the path where you have your web servers folder and click on install.

* Apache tomcat will be installed in Intelij now.

* Right click on the project and click on properties.

* Select targeted runtimes and select apache tomcat 8 for building and deploying the project

* To run the applcation, right click on the project and give run as and select run on server.

* Apache tomcat will be started.

* Give localhost:8080/yourapplicationname

* This opens the web application in the browser.



