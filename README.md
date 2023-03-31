# selan-deemantha-IS24-full-stack-competition-req97073

# Read Me First
Spring Boot RESTful APIs backend and Reactjs frontend for IS-24 Full Stack Developer Position Code Challenge

# Getting Started

# Clone the project from
https://github.com/sedeeman/selan-deemantha-IS24-full-stack-competition-req97073

# Go to project dir backend 
cd backend

# Build Backend Docker image
docker build -t api .

# Run Backend Docker container
docker run -p 3000:3000 api

# Server Starting  
http://localhost:3000/api/

# Swagger Documentation
http://localhost:3000/api/api-docs

# Health Expose
http://localhost:3000/api/actuator/health



# Go to project dir frontend
cd frontend

# Build Frontend Docker image
docker build -t my-react-app .

# Run Frontend Docker container
docker run -p 3001:3001 my-react-app

# App Starting
http://localhost:3001/


