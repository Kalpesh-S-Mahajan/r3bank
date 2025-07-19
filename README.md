
# 🏦 R3Banks - Online Banking System

R3Banks is a secure and user-friendly online banking application built to simulate core banking functionalities including user authentication, balance inquiries, fund transfers, transaction history, and more.

## 📌 Features

- 🔐 Secure login/logout system
- 💰 View account balance
- 💸 Fund transfer between accounts
- 📜 View transaction history
- 🧾 Generate account statements
- 📞 Contact support form

## 🛠 Technologies Used

- **Frontend:** HTML5, CSS3, JavaScript, Bootstrap
- **Backend:** Java, JSP, Servlets
- **Database:** MySQL
- **Server:** Apache Tomcat
## Snapshot
<img width="1919" height="939" alt="image" src="https://github.com/user-attachments/assets/03b87ff1-2dea-428c-b819-b1c8de0be273" />

## 📁 Project Structure

```
R3Banks/
├── src/
│   ├── com.r3banks.servlets/
│   └── com.r3banks.utils/
├── WebContent/
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── transfer.jsp
│   ├── history.jsp
│   └── css/, js/, images/
├── DB/
│   └── r3banks.sql
└── README.md
```

## 🚀 Getting Started

1. Clone the repository:
```
git clone https://github.com/Kalpesh-S-Mahajan/r3banks.git
```

2. Import into Eclipse/IDE and configure Apache Tomcat server.

3. Create the database using `r3banks.sql` in MySQL.

4. Update your database credentials in the DB connection utility file.

5. Run the application on Tomcat and access it via `http://localhost:8080/R3Banks`.

## 🔒 Security Note

All sensitive operations are session-based and protected with server-side validation.

