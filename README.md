# Spring Security & JWT with Spring Boot 2.0 Rest API application

Development environment

* Java 1.8.0
* Spring Boot 2.0.6
* H2
* java-jwt 3.4.0
* Maven 3.5.4

## Build & Run

using an embedded database H2.

```text
mvn clean package
```

```text
java -jar .\target\demo.jar
```

## test user

|email                  |password         |admin  |
|:----------------------|:----------------|:------|
|kkamimura@example.com  |iWKw06pvj        |true   |
|rsakuma@example.com    |sk10ZIaiq        |false  |
|tyukinaga@example.com  |me02yFufL        |false  |
|zsawatari@example.com  |FjqU39aia        |false  |
|ehiyama@example.com    |ruFOep18r        |false  |


## API

### login API

認証が成功するとトークンが取得できます。このトークンは認証が必要なAPIで使用します。

```text
curl -i -X POST "http://localhost:9000/app/login" -d "email=kkamimura@example.com" -d "pass=iWKw06pvj"
```

For example, the response is as follows

```text
HTTP/1.1 200
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwibmJmIjoxNTMzMDA2NDc0LCJleHAiOjE1MzMwMDcwNzQsImlhdCI6MTUzMzAwNjQ3NH0.HHrg8dGkexwgw3z06n5NGa69DLzJn--PWzlBYNQ8r3KXDLsXwJKNt7qwJK3xGHc2CP0zBi2fcdjAWV-2zs68wA
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Tue, 31 Jul 2018 03:07:54 GMT
```

### No authentication required API

```text
curl -i "http://localhost:9000/app/hello"
```

```text
curl -i "http://localhost:9000/app/hello/{message}"
```

```text
curl -i -X POST "http://localhost:9000/app/hello" -d "message=world"
```

### These APIs do not need roles

But the user needs to be authenticated.

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/memo/1"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/memo/list"
```

### These APIs requiring authentication and USER role

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/user"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/user/echo/{message}"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" -H "Content-Type:application/json" -X POST "http://localhost:9000/app/user/echo" -d "{\"message\": \"hello world\"}"
```

### These APIs requiring authentication and ADMIN role

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/admin"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/admin/echo/{message}"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" -H "Content-Type:application/json" -X POST "http://localhost:9000/app/admin/echo" -d "{\"message\": \"hello world\"}"
```
