# Dependencies required:
docker + docker-compose
(maven), (mysql db)

# How to run:
Run with the command:
`docker-compose up`
Then wait for the the mysql db and the jdbc driver... 
and finally for the tomcat server to start.

# API Endpoints:
http://localhost:8080/product/list

includes Description field:
http://localhost:8080/product/detail

# Available query params for list api:
pageNumber
eg: http://localhost:8080/product/list?pageNumber=2

minPrice
maxPrice
eg: http://localhost:8080/product/list?minPrice=22&maxPrice=30

category
eg: http://localhost:8080/product/list?category=men's clothing