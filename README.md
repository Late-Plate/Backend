# 🍳 Late Plate – Backend API

This repository contains the Spring Boot–based backend service for the **Late Plate** mobile app. It provides RESTful endpoints to serve recipe data and support search functionality.

## 🚀 Features

- 🔍 Search recipes by:
  - Name
  - Ingredient
  - General keyword
- 📄 Paginated results
- ⚙️ Spring Boot API with clean separation of concerns

---

## 📁 Endpoints

### `GET /recipes`
Fetch all recipes with pagination.

**Query Parameters:**
- `page` (default: 0) – Page number
- `size` (default: 10) – Page size

---

### `GET /recipes/by-name`
Search recipes by exact or partial name.

**Query Parameters:**
- `name` – Recipe name (required)
- `page` (default: 0)
- `size` (default: 10)

---

### `GET /recipes/by-ingredient`
Search recipes containing a specific ingredient.

**Query Parameters:**
- `ingredient` – Ingredient name (required)
- `page` (default: 0)
- `size` (default: 10)

---

### `GET /recipes/general-search`
Search recipes using a general keyword across multiple fields.

**Query Parameters:**
- `term` – Search term (required)
- `page` (default: 0)
- `size` (default: 10)

---

## 🛠️ Tech Stack

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven or Gradle
- **Data Layer:** Spring Data JPA
- **Pagination:** Spring Data Pageable

---

## ▶️ Running the App

1. Clone the repository:
```bash
git clone https://github.com/YOUR_ORG/backend-api.git
cd backend-api
```

2. Run with Maven or from your IDE:
```bash
./mvnw spring-boot:run
```
3. Access the API at:
http://localhost:8080/recipes

Note: to enable access throw the mobile app we used ngrok to expose the port on a public URL

---

## 📄 License

Each model may be released under a separate license. Please refer to the `README.md` and `LICENSE` file in each branch for more details.

---

## 📬 Contact

For questions or contributions, feel free to open an issue or contact the team via the main [Late Plate organization page](https://github.com/YOUR_ORG).
