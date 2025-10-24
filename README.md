# 🔗 URL Shortener

A simple **Spring Boot-based URL Shortener** application that converts long URLs into short ones and allows redirection from the shortened link.  
It uses **H2 Database** for persistence and **Thymeleaf** for the frontend.

---

## 🚀 Features

- Shorten any valid external URL (e.g., `https://www.google.com`)
- Auto-generated short code for each URL
- Redirection support — visiting the short link redirects to the original URL
- Validates URLs (rejects localhost and invalid URLs)
- Integrated **H2 Database Console** for viewing stored URLs
- Simple **Thymeleaf UI**

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (REST API)
- **Frontend:** Thymeleaf (HTML)
- **Database:** H2 (File-based)
- **Language:** Java 17+

---

## ⚙️ Project Structure

src/
├── main/
│ ├── java/com/example/urlshortener/
│ │ ├── controller/
│ │ │ └── UrlController.java
│ │ ├── service/
│ │ │ └── UrlService.java
│ │ ├── model/
│ │ │ └── UrlEntity.java
│ │ └── UrlShortenerApplication.java
│ └── resources/
│ ├── templates/
│ │ └── index.html
│ ├── application.properties
│ └── static/
└── test/

---

## ⚡ API Endpoints

### 1️⃣ Shorten URL
 **Endpoint:**  
 `POST /api/url/shorten`

 **Request Param:**  
 | Name | Type | Description |
 |------|------|--------------|
 | longUrl | String | Original long URL |

 **Example Request:**
 
 curl -X POST "http://localhost:8080/api/url/shorten?longUrl=https://www.google.com"

 **Response:**

 http://localhost:8080/api/url/abc123
 
---

##2️⃣ Redirect Short URL

Endpoint:
GET /api/url/{shortCode}

Behavior:
Redirects to the original long URL stored in the database.

##💾 H2 Database
Access Console

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/urlshortenerdb

Username: sa

Password: (leave blank)

#🖥️ Running the Project

##Clone Repository

git clone https://github.com/yourusername/url-shortener.git
cd url-shortener

##Run Application

mvn spring-boot:run
