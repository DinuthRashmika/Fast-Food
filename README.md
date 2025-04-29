## Microservices Overview

- *User Service* – Handles user registration, login, and JWT authentication.
- *Order Service* – Manages order placement, tracking, and order history.
- *Restaurant Service* – Maintains restaurant details, menus, and updates.
- *Delivery Service* – Coordinates delivery assignments and status updates.
- *Notification Service* – Sends email and SMS alerts for order confirmations and updates.
- *Client (Frontend)* – A web-based user interface for customers, admins, delivery persons and restaurant owners


## Deployment Using Docker & Docker Compose
### Pre-requisites

Install Docker and Docker Compose
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

### Steps to Deploy the System
- git clone https://github.com/your-username/food-ordering-system.git
- cd food-ordering-system

### Build and run
docker-compose up --build
This will Build Docker images for all microservices, start containers for services and databases, map service ports to localhost for testing and starts running

### Stop all services
docker-compose down
