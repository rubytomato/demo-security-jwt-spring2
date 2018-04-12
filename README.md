# Spring Security with Spring Boot 2.0.1 Rest API application


## Build & Run

```text
mvn clean package
```

```text
java -jar .\target\demo-0.0.0-SNAPSHOT.jar
```


## API

### プレログインAPI

ログイン時に必要なCSRFトークンを取得する

```text
curl -i -c cookie.txt "http://localhost:9000/app/prelogin"
```

### ログインAPI

プレログインAPIで取得したCSRFトークンを_csrfパラメータに指定する

```text
curl -i -c cookie.txt -X POST "http://localhost:9000/app/login" -d "email=kkamimura@example.com" -d "pass=iWKw06pvj"
```

### ログアウトAPI

```text
curl -i -b cookie.txt -H "x-xsrf-token:{CSRF_TOKEN}" -X POST "http://localhost:9000/app/logout"
```

### 認証が不要なAPI

```text
curl -i -b cookie.txt "http://localhost:9000/app/hello"
```

```text
curl -i -b cookie.txt "http://localhost:9000/app/hello/{message}"
```

認証が不要でもPOST時はCSRFトークンが必要

```text
curl -i -b cookie.txt -X POST "http://localhost:9000/app/hello" -d "message=world" -d "_csrf={CSRF_TOKEN}"
```

### ロールが不要なAPI

```text
curl -i -b cookie.txt "http://localhost:9000/app/memo/1"
```

```text
curl -i -b cookie.txt "http://localhost:9000/app/memo/list"
```

### USERロールAPI

```text
curl -i -b cookie.txt "http://localhost:9000/app/user"
```

```text
curl -i -b cookie.txt "http://localhost:9000/app/user/echo/{message}"
```

```text
curl -i -b cookie.txt -H "Content-Type:application/json" -H "x-xsrf-token:{CSRF_TOKEN}" -X POST "http://localhost:9000/app/user/echo" -d "{\"message\": \"hello world\"}"
```

### ADMINロールAPI

```text
curl -i -b cookie.txt "http://localhost:9000/app/admin"
```

```text
curl -i -b cookie.txt "http://localhost:9000/app/admin/echo/{message}"
```

```text
curl -i -b cookie.txt -H "Content-Type:application/json" -H "x-xsrf-token:{CSRF_TOKEN}" -X POST "http://localhost:9000/app/admin/echo" -d "{\"message\": \"hello world\"}"
```

