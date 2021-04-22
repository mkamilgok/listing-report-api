# Coding Challenge: AutoScout24 Listing Report

In this project, I implemented an API to provide necessary reports. The data for these reports are obtained from the given CSV files.

As the tech stack, I used Java, Spring Boot. 

Due to my busy schedule specifically in these 3 days, I prefered to use a tech stack that I know instead of recommended ones(Scala, Typescript), unfortunately.

The API has 4 endpoints for these 4 requirements:

- Average Listing Selling Price per Seller Type
- Distribution (in percent) of available cars by Make
- Average price of the 30% most contacted listings
- The Top 5 most contacted listings per Month

The application is deployed on both ElasticBeanstalk and Heroku, in case of any problem.
- [The app on ElasticBeanstalk](http://autoscout24reportapi-env.eba-tprtpigm.us-east-2.elasticbeanstalk.com/swagger-ui.html)
- [The app of Heroku](https://autoscout24-project.herokuapp.com/swagger-ui.html)

### How to see reports on swagger by using one of these links:

1- Click "Expand Operations" for any endpoint that you want to check.

![expand](https://github.com/mkamilgok/listing-report-api/blob/main/src/main/resources/static/expand.png)

2- Click "Try it out" to see the report for the specific endpoint.

![tryitout](https://github.com/mkamilgok/listing-report-api/blob/main/src/main/resources/static/tryItOut.png)

### How to start program on local and test:

In order to use program on local, the project can be cloned. The setup of Maven dependencies can take a couple of seconds or minutes when the project is started for the first time. Intellij Idea is recommended.

- The application can start by running the main method. To run it, right click to "ReportingApiApplication" and click "Run".

![run](https://github.com/mkamilgok/listing-report-api/blob/main/src/main/resources/static/runApp.png)

- To start the tests, right click on "autoscout24.reporting_api" and click "Run Tests". Since the most of the business logic is in service layer, only the methods in service layer are tested.

![runTests](https://github.com/mkamilgok/listing-report-api/blob/main/src/main/resources/static/runTests.png)

Now you are ready to go!
