### Features

- This is a REST API for Url shortener service like tiny url.
- This service includes user authentication as well as shortening
- The app also records URLs that you have previously shortened, and it can display how frequently that website has been accessed using that particular link.

# Setup
$ git clone git@github.com:kastourik12/url-shortner.git

$ cd url-shortner

## Docker:
------------

$ mvn clean package -DskipTests

$ docker build -t {image_name} .
> ( image name is used in the docker-compose file )

$ docker-compose up



##### add roles to the database

> this service still got no endpoint for adding roles ( it not necessary to add them tho, the system will save roles if its  not in the database )

$ docker exec -it {postgres_container_name} bash
> ( default : postgres_db )

$ psql -U {postgres_username}
> ( default : postgres)

postgres=# \c {database_name}
> (default: test_db)

test_db=# INSERT INTO roles (id,name) VALUES (1,'ROLE_USER');
test_db=#  INSERT INTO roles (id,name) VALUES (2,'ROLE_ADMIN');


------------
## without Docker:
------------

> Make sure you have access to local or any Postgres server
> this is was built using apache-maven-3.8.6 & java 17.0.4


$  ./mvnw spring-boot:run

------------

Now, all is up and runing you can perform http request using any tool (Postman , curl ...)



**Project Overview**

| Endpoint   |  Request Body |  Action |
| :------------: | :------------: | :------------: |
|  POST:/re/create | target url : String  |  return shortlink for the given url |
|  GET:/re/{shrtUrl} |     | Redirect to the original url   |
|  POST:/auth./sign-up | username, password & roles | save user   |
|  POST:/auth/sign-in |  username, password |  authenticate user and return access token (JWT) as a string |
| GET:/stats/all | target url : String  | return all shortned urls and their statiscs (Only admin access) |
|  GET:/stats/visited |   |  return all visited shorterned url (user access) |
| GET:/stats/{id}  |   | return all visites for a shortned url (admin acces)  |

> check : http://localhost:8082/swagger-ui.html for all request details .