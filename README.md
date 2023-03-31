# selan-deemantha-IS24-full-stack-competition-req97073

# Read Me First

This is the Spring Boot RESTful APIs backend for the IS-24 Full Stack Developer Position Code Challenge.

# Getting Started

1. Clone the project from https://github.com/sedeeman/selan-deemantha-IS24-full-stack-competition-req97073

2. Go to the backend directory:

#### cd backend

3. Build the Backend Docker image:

#### docker build -t api .

4. Run the Backend Docker container:

#### docker run -p 3000:3000 api

5. Server Starting: Go to http://localhost:3000/api/

6. Swagger Documentation: Go to http://localhost:3000/api/api-docs

7. Health Expose: Go to http://localhost:3000/api/actuator/health

8. Go to the front directory

#### cd frontend

9. Build the Frontend Docker image:

#### docker build -t my-react-app .

10. Run the Frontend Docker container:

#### docker run -p 3001:3001 my-react-app

11. App Starting: Go to http://localhost:3001/

12. To run the app locally, clone the project and open it using Visual Studio Code.

13. Add new terminal and navigate to backend

#### cd backend

#### ./mvnw spring-boot:run

14. Add another terminal and navigate to backend

#### cd frontend

#### npm i

#### npm start

