# Quick Start

## Windows PowerShell

### 1. Build and Run

```powershell
docker compose up -d --build
```

### 2. Register

```powershell
Invoke-RestMethod -Method Post `
-Uri "http://localhost:8080/api/auth/register" `
-Headers @{ "Content-Type" = "application/json" } `
-Body '{"email":"a@a.com","password":"pass"}'
```

### 3. Login

```powershell
Invoke-RestMethod -Method Post `
-Uri "http://localhost:8080/api/auth/login" `
-Headers @{ "Content-Type" = "application/json" } `
-Body '{"email":"a@a.com","password":"pass"}'
```

### 4. Process Text

```powershell
Invoke-RestMethod -Method Post `
-Uri "http://localhost:8080/api/process" `
-Headers @{
    "Content-Type" = "application/json"
    "Authorization" = "Bearer <token>"
} `
-Body '{"text":"test"}'
```

---

## Linux / macOS

### 1. Build and Run

```bash
docker compose up -d --build
```

### 2. Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'
```

### 3. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'
```

### 4. Process Text

```bash
curl -X POST http://localhost:8080/api/process \
-H "Authorization: Bearer <token>" \
-H "Content-Type: application/json" \
-d '{"text":"hello"}'
```
