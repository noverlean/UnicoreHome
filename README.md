## 🌐 Языки / Languages

- 🇷🇺 [Русский](README.ru.md)

# 🏠 UnicoreHome — A Smart Home Capstone Project with Spring + React + Arduino

UnicoreHome is a full-featured web and hardware application for managing smart home environments. Developed as a capstone project, it includes a backend built with Java Spring Framework, a frontend developed in React, and hardware devices based on ESP8266 programmed in C++ using Arduino. The system allows users to manage digital environments, connect smart devices, collaborate with other users, and interact with a support service.

---

## 🌐 Architecture

- 🖥️ Backend: Java 17, Spring Boot, Spring Security, Hibernate, Liquibase, PostgreSQL
- 📬 Email: FreeMarker for templated emails (verification, notifications)
- 💻 Frontend: React (developed by another contributor)
- 📡 Hardware: ESP8266 + Arduino C++
- 🔁 Communication: Short polling (to bypass NAT and CORS preflight restrictions)
- 🐳 Docker: Containerization of backend and database

---

## 🔧 Features

### 👤 User Functionality

- User registration
- Email verification via code sent to inbox
- Login and logout
- Password recovery (planned)
- Profile viewing and editing

### 🏘️ Environments

- Create one or more environments (e.g., "Home", "Office", "Cottage")
- Invite other users to your environment
- Accept invitations and join other users' environments
- Manage environment members (remove, assign roles)
- View all environments the user is part of

### 🔌 Devices

- Connect smart home devices to an environment
- Support for multiple device types:
  - 💡 Light bulb
  - 🔦 Spot light
  - 🔘 Switch
- Connect devices to a Wi-Fi network with internet access
- Communicate with the server via short polling (NAT/CORS-safe)
- Control device state via UI (on/off, brightness, etc.)
- Display real-time device status
- Remove and reconnect devices

### 🧑‍🤝‍🧑 User Collaboration

- Invite users to environments via email
- Accept or decline invitations
- View list of environment participants
- Manage access rights (planned)

### 🆘 Support System

- Create support tickets
- View all personal tickets
- Chat with an administrator within the ticket thread
- Close tickets (tickets remain archived and viewable)
- Persistent ticket history

### 📬 Email Notifications

- Verification code sent during registration
- Notifications for environment invitations
- Notifications for new messages in support tickets
- Email templates styled with FreeMarker
