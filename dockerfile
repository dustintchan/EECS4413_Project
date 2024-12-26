# Use an official Tomcat image as the base image
FROM tomcat:9-jdk21

# Copy the WAR file to the Tomcat webapps directory
COPY 4413-TeamName-GroupProject.war /usr/local/tomcat/webapps/

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
