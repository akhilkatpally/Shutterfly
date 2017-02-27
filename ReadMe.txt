#Shutterfly Customer LifeTime Value

I have Implemented it Using HashMap and Arralist datastructures for storing the events. Since the analysis is primarily on the order events i have used HashMap(time for retrieving and searching is very less) and the remaining events in the arraylist.

Main DataStructure is in Data.java File and the main method is in ShutterflyCLV.java.

Ingestion.java contains method for ingesting events into data. Assuming events passed to the data are strings.

Analyze.java contains method for analysing.

Used Jackson json parser for parsing of the event.

#Data cleaning and validations

-Checked for all the required fields and if not present, throws errors and doesn't ingest the event.

-System handles different ordering of the events as well as ordering of the fields inside the event.

#Important Assumptions:

-Assuming all the events date belongs to the same time zone. Didn't handle for different time zones.

-More Assumptions are present in the respective files.

#Improvements

-Well Same problem can be solved in Hadoop Ecosystem, using Kafka(for ingesting events), AeroSpike or Redis (for storing data- in-memory) and Spark for processing. Ideally it should be done in this way but i need bit of learning and also because of time constraint i chose to implement in normal programatic way.
 Using the same datastructures could have been handled large data and also handle fluctuating frequency.

-In the current System, more data clenaing or validation could have been added.


I appreciate any feedback on the design and implementation, Looking forward to hear from you soon.


