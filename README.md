
<h1 align="center" style="font-weight: bold;">Northern Wonders 🌍</h1>

<p align="center">
<a href="#tech">Technologies</a> |
<a href="#started">Getting Started</a> |
<a href="#routes">API Endpoints</a> |
<a href="#author">Authorship</a>
</p>

<p align="center">This is a backend API project made with Spring that consists of a system/blog that has authentication and permissions, and users can do the full CRUD and share travel packages.</p>

<h2 id="technologies">💻 Technologies</h2>

- Java 21
- Spring Framework
- PostgreSQL

<h2 id="started">🚀 Getting started</h2>

<h3>Cloning</h3>

Run the following command to clone the repository:

```bash
git clone https://github.com/leticiaborchardt/northern-wonders-backend.git
```

<h3>Configuration</h2>

The `application.properties` configuration file in the `src/main/resources` folder must be configured with the information from your database credentials before you run the application.


<h2 id="routes">📍 API Endpoints</h2>

Here are the main application endpoints:
​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/login</kbd>     | Logs in to the application
| <kbd>POST /auth/register</kbd>     | Create a new admin user
| <kbd>POST /auth/logout</kbd>     | Logs the user out
| <kbd>GET /customer/{userId}</kbd>     | Returns the customer's information
| <kbd>POST /customer</kbd>     | Create a new customer with user
| <kbd>PUT /customer</kbd>     | Update an existing customer
| <kbd>GET /destination</kbd>     | Returns all destinations
| <kbd>GET /travelpackage</kbd>     | Returns all travel packages
| <kbd>GET /travelpackage/{id}</kbd>     | Returns the travel package with a specific id
| <kbd>POST /travelpackage</kbd>     | Create a new travel package
| <kbd>PUT /travelpackage/{id}</kbd>     | Update an existing travel package
| <kbd>DELETE /travelpackage/{id}</kbd>     | Remove a travel package


## 🔐 Authentication and Permissions

The system uses a role-based access control mechanism to protect the API endpoints. Below is a detailed explanation of the permissions configured for each route:

### Open Endpoints
- **POST `/auth/login`**: Allows all users to access this endpoint to log in. No prior authentication is required.
- **POST `/auth/register`**: Allows all users to register. No authentication is needed.
- **POST `/customer`**: Allows all users to create new customers with user. No prior authentication is required.

### Protected Endpoints
- **POST `/travelpackage`**: Restricted to users with the `ADMIN` role. Only administrators can create travel packages.
- **PUT `/travelpackage`**: Also restricted to users with the `ADMIN` role. Only administrators can update travel package information.
- **DELETE `/travelpackage`**: Only administrators (`ADMIN`) can delete travel packages.
- **GET `/travelpackage`**: Accessible to both administrators (`ADMIN`) and clients (`CLIENT`). Both user roles can view travel packages.

### General Rules
- **Any Other Requests**: For any endpoint not explicitly listed above, the user must be authenticated. This means a valid authentication token is required to access any other resources.

<h2 id="author">✍🏻 Authorship</h2>

- Letícia Borchardt (https://github.com/leticiaborchardt)