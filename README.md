# EECS4413_Project

Deploying and Running the Application with Docker Instructions

-------------------------------------------
Source Code Location:
-------------------------------------------

1) Submitted on eClass as a .war file.

2) .war file contents found within /source_code


-------------------------------------------
Instruction on Running the Project:
-------------------------------------------

## Running the Project Locally with Docker

### Prerequisites
1. **Docker Installed**: Ensure Docker is installed and running on your machine.
   - [Download Docker](https://www.docker.com/products/docker-desktop)
2. **WAR File**: Obtain the `4413-TeamName-GroupProject.war` file from e-class submission.
3. **Dockerfile**: Ensure the `Dockerfile` is available in your project directory.

### Steps to Build and Run the Docker Container

1. **Navigate to the Project Directory**
  terminal command:
   cd /path/to/your/project
 

2. **Build the Docker Image**
   Build the Docker image using the Dockerfile:
   terminal command:
   docker build -t 4413-teamname-groupproject .
  
   
3. **Run the Docker Container**
   Run the container and map the application to port 8080 on your local machine:
   terminal command:
   docker run -d -p 8080:8080 4413-teamname-groupproject
 

4. **Access the Application**
   - Open your browser and navigate to: http://localhost:8080/4413-TeamName-GroupProject


-------------------------------------------
Additional Information:
-------------------------------------------

- Admin Security Code for signup view: '1111'
