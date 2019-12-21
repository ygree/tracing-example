# Akka-Java-Cinnamon-DataDog-Seed is based on Akka-Scala-Cinnamon-DataDog-Seed
Refer <a href="https://blog.knoldus.com/2017/07/02/monitoring-akka-based-applications-with-cinnamon-and-datadog/">this blog</a> for understanding the project. The start monitoring with this example
 
1). Create an account on DataDog and start the DataDog agent on your local machine(on which this application would be running)

2). Under Integrations menu in DataDog website, search for **lightbend reactive platform** and install the integration.

3). Open the terminal on root directory of your project and execute **mvn clean install** to run the application.

4). In terminal of your root directory start the application like this:
java -Dlogback.configurationFile=./src/main/resources/logback.xml -Dconfig.file=./src/main/resources/application.conf -javaagent:./target/lib/cinnamon-agent.jar -jar ./target/akka-java-cinnamon-datadog-seed-1.0-SNAPSHOT.jar

5). In your DataDog account, under the APM -> Service Map, you shpould see the service with name: akka-cinnamon-datadog and on service overview the servixce traces.