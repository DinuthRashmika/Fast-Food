🔍 Microservices Overview
This system is composed of multiple independent services, each responsible for a specific domain in the food ordering and delivery workflow. Here’s what each does:

✅ User Service
Handles user registration, login, and authentication.

Uses JWT (JSON Web Tokens) to securely manage user sessions.

All user-related data and actions (like registration and login) are centralized here.

📦 Order Service
Responsible for placing new orders, tracking them, and maintaining order history.

Interacts with the Restaurant Service to confirm items, and with Delivery Service to assign delivery.

🍽️ Restaurant Service
Manages all restaurant-related data:

Restaurant profiles

Menus and menu items

Updates to offerings

Can be accessed and updated by restaurant owners.

🚚 Delivery Service
Manages delivery assignments:

Finds nearby delivery persons

Tracks delivery progress and updates order status

📢 Notification Service
Sends emails or SMS messages:

Order confirmations

Delivery updates

Can be implemented using services like Twilio, SendGrid, or SMTP servers

🌐 Client (Frontend)
A web application (likely using React, Angular, or Vue).

Offers different dashboards/interfaces for:

Customers (place orders, track them)

Admins (manage users, restaurants, delivery persons)

Restaurant owners (manage menus, orders)

Delivery persons (accept and complete deliveries)

Each of these services communicates over HTTP (REST APIs) or asynchronous messaging (like RabbitMQ/Kafka), but they are deployed and scaled independently.

🐳 Deployment Using Docker & Docker Compose
🔧 Pre-requisites
To deploy this system, you need two tools:

Docker

Allows you to package applications and their dependencies into containers.

Ensures that the app runs the same way on any system.

Docker Compose

A tool to define and manage multi-container Docker applications.

It uses a file called docker-compose.yml to describe services, networks, and volumes.

🔗 Installation links are provided:

Docker

Docker Compose
