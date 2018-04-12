# Spring Security & JWT with Spring Boot 2.0.1 Rest API application

## Build & Run

埋め込みDBのH2を利用しています。

```text
mvn clean package
```

```text
java -jar .\target\demo-security-jwt-spring2-0.0.0-SNAPSHOT.jar
```

## テストユーザー

|email                  |password         |admin  |
|:----------------------|:----------------|:------|
|kkamimura@example.com  |iWKw06pvj        |true   |
|rsakuma@example.com    |sk10ZIaiq        |false  |
|tyukinaga@example.com  |me02yFufL        |false  |
|zsawatari@example.com  |FjqU39aia        |false  |
|ehiyama@example.com    |ruFOep18r        |false  |


## API

### ログインAPI

認証が成功するとトークンが取得できます。このトークンは認証が必要なAPIで使用します。

```text
curl -i -X POST "http://localhost:9000/app/login" -d "email=kkamimura@example.com" -d "pass=iWKw06pvj"
```

### 認証が不要なAPI

```text
curl -i "http://localhost:9000/app/hello"
```

```text
curl -i "http://localhost:9000/app/hello/{message}"
```

```text
curl -i -X POST "http://localhost:9000/app/hello" -d "message=world"
```

### ロールが不要なAPI

認証されているユーザーがアクセスできるAPI

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/memo/1"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/memo/list"
```

### USERロールAPI

USERロールを持つユーザーがアクセスできるAPI

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/user"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/user/echo/{message}"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" -H "Content-Type:application/json" -X POST "http://localhost:9000/app/user/echo" -d "{\"message\": \"hello world\"}"
```

### ADMINロールAPI

ADMINロールを持つユーザーがアクセスできるAPI

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/admin"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" "http://localhost:9000/app/admin/echo/{message}"
```

```text
curl -i -H "Authorization: Bearer {TOKEN}" -H "Content-Type:application/json" -X POST "http://localhost:9000/app/admin/echo" -d "{\"message\": \"hello world\"}"
```
