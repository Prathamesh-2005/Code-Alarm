# CodeAlarm: Coding Contest Tracker

CodeAlarm is a full-stack web application that helps users track upcoming and past coding contests from major platforms, set real-time email reminders, and manage their contest participation. The project is built with a modern stack: **Spring Boot** for the backend, **React.js** for the frontend, and **MySQL** as the database.

---

## 🚀 Features

- **Contest Aggregation:** Automatically fetches upcoming and past contests from LeetCode, Codeforces, and CodeChef.
- **Real-Time Email Reminders:** Users can set reminders for contests and receive real-time email notifications.
- **User Management:** Register, login, and manage your profile and reminders.

  
---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (Java)
- **Frontend:** React.js (Vite)
- **Database:** MySQL
- **Email:** SMTP (for real-time reminders)
- **APIs:** Fetches contest data from LeetCode, Codeforces, and CodeChef

---

## 📁 Folder Structure

```
Contest-Tracker/
├── Backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── jadhav/
│   │   │   │           └── contest_tracker/
│   │   │   │               ├── controller/
│   │   │   │               ├── model/
│   │   │   │               ├── repository/
│   │   │   │               ├── service/
│   │   │   │               └── ContestTrackerApplication.java
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
├── frontend/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── public/
│   ├── package.json
│   ├── vite.config.js
│   └── README.md
├── README.md
└── .gitignore
```

---

## ⚙️ Environment Variables

### Backend (`.env` or `application.properties`)

```properties
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/contest_tracker
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret_key
SPRING_MAIL_HOST=smtp.example.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=your_email@example.com
SPRING_MAIL_PASSWORD=your_email_password
```

### Frontend (`.env`)

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

---

## 🏃‍♂️ Quick Start

### Prerequisites

- Java 17+
- Node.js 16+
- MySQL 8.0+
- Maven

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Prathamesh-2005/Code-Alarm.git
   cd Code-Alarm
   ```

2. **Setup Database:**
   ```sql
   CREATE DATABASE contest_tracker;
   ```

3. **Backend Setup:**
   ```bash
   cd Backend
   # Configure your DB and email settings in application.properties or .env
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   The backend will start on `http://localhost:8080`.

4. **Frontend Setup:**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   The frontend will start on `http://localhost:3000`.

---

## 🔑 API Endpoints (Sample)

### Auth
- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Login and receive JWT
- `GET /api/auth/profile` — Get current user profile

### Contests
- `GET /api/contests/upcoming` — List all upcoming contests
- `GET /api/contests/past` — List all past contests

### Reminders
- `GET /api/reminders` — List user reminders
- `POST /api/reminders` — Create a reminder
- `DELETE /api/reminders/{id}` — Delete a reminder

---

## 🖥️ Screenshots

![image](https://github.com/user-attachments/assets/2e4ef8fe-d07f-4386-bcc6-0c1ca7562131)
![image](https://github.com/user-attachments/assets/3ab89619-b3a3-4bef-9a44-5ad2eb45d244)
![image](https://github.com/user-attachments/assets/02684018-eaf8-4605-9c16-a60e987eff4b)
![image](https://github.com/user-attachments/assets/bd4a6402-d471-4992-9e4e-23da6f9270a0)
![image](https://github.com/user-attachments/assets/c0226573-16b6-4891-a34c-3f04755af037)

---

## 📄 License

This project is licensed under the MIT License.

---

## 🙋‍♂️ Author

**Prathamesh Jadhav**  
[GitHub](https://github.com/Prathamesh-2005) | [LinkedIn](https://www.linkedin.com/in/prathamesh-jadhav-3a13b3285/)

---

⭐️ **If you like this project, give it a star!**
