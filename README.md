<div align="center">

# 🎵 Music Player Project (CLI + JavaFX GUI)

[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-UI-4E70A6?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/University-Project-6E56CF?style=for-the-badge)](#)

</div>

## 📌 Overview

This project was developed as part of the **Advanced Programming (BSc Computer Engineering)** course.

The goal was to design and implement a **Spotify-like music player** in Java in two progressive phases:

1. **Phase 1 – CLI Version:** A command-line based music player with core functionality.
2. **Phase 2 – GUI Version:** A full desktop application built using **JavaFX** and **Maven**, extending and improving the CLI version with a graphical interface.

The project demonstrates the evolution from a structured console application to a modern event-driven GUI application.

---

## 🧱 Project Structure

```text
.
├── Phase 1 - CLI/
│   └── Console-based music player implementation
│
├── Phase 2 - GUI/
│   └── JavaFX desktop application (main project)
│
├── Phase 2 - GUI - Screenshots/
│   └── UI previews of the JavaFX application
│
├── AP_FirstProject_Phase1.pdf
├── AP_FirstProject_Phase2.pdf
├── .gitignore
└── README.md
```

---

## 🛠️ Technologies Used

<table>
<tr>
<td valign="top" width="33%">

**Core**
- Java — Main programming language
- JavaFX — UI framework
- Maven — Build automation tool

</td>
<td valign="top" width="33%">

**Media**
- JavaFX Media — Audio playback
- JLayer — MP3 processing

</td>
<td valign="top" width="33%">

**Data Management**
- In-memory database (`DataBase` class)
- Serialization for data persistence (implicit)

</td>
</tr>
</table>

---

## 📸 Screenshots

Screenshots of the GUI version can be found in:

```text
Phase 2 - GUI - Screenshots/
```

Here are two examples:

<p align="center">
  <img src="Phase%202%20-%20GUI%20-%20Screenshots/Welcome%20Page.png" alt="Welcome Page" width="45%">
  &nbsp;&nbsp;
  <img src="Phase%202%20-%20GUI%20-%20Screenshots/Home%20Page.png" alt="Home Page" width="45%">
</p>

---

## 📥 Installation

### Prerequisites

- Java 21 or higher
- Maven
- JavaFX SDK

## 🚀 How to Run

### ▶️ Phase 1 (CLI)

1. Clone the repository:

   ```bash
   git clone https://github.com/itzkeyvan/Music-Player-with-GUI.git
   ```

2. Open the `Phase 1 - CLI` folder as a project
3. Compile and run the `Main.java` class
4. Follow the console menu instructions

---

### 🖥️ Phase 2 (GUI — Recommended)

1. Clone the repository:

   ```bash
   git clone https://github.com/itzkeyvan/Music-Player-with-GUI.git
   ```

2. Open the `Phase 2 - GUI` folder as a project
3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the `Main.java` class from your IDE, or:

   ```bash
   mvn javafx:run
   ```

---

## 🚀 Phase 2 - GUI Usage

### First Time Setup

1. **Sign Up** — Create a listener account
2. **Select Favorite Genres** — Choose up to 4 genres for personalized recommendations
3. **Start Exploring** — Browse audios, artists, and create playlists

### Main Navigation

| Button  | Function                                 |
|---------|-------------------------------------------|
| Home    | View recommended audios                  |
| Search  | Search for audios by title or artist     |
| Library | View your playlists and followed artists |
| Audios  | Browse all available audios              |
| Artists | Browse all artists                       |

### Player Controls

| Control       | Function                  |
|---------------|----------------------------|
| Play/Pause    | Toggle playback           |
| Previous/Next | Navigate through playlist |
| Progress Bar  | Seek within audio         |
| Volume Slider | Adjust volume             |

### Playlist Management

- Click **"New Playlist"** in Library to create a playlist
- Add audios to playlists from the audio detail page
- Remove audios from playlists
- Delete playlists

### Premium Features

Navigate to **Explore Premium** in your library to view subscription plans:

| Plan   | Duration | Price |
|--------|----------|-------|
| 🥉 Bronze | 30 days  | $5  |
| 🥈 Silver | 60 days  | $9  |
| 🥇 Gold   | 180 days | $14 |

---

## 📚 Project Documentation

Detailed assignment descriptions are available in:

- [`AP_FirstProject_Phase1.pdf`](./AP_FirstProject_Phase1.pdf)
- [`AP_FirstProject_Phase2.pdf`](./AP_FirstProject_Phase2.pdf)

These files describe the academic requirements and implementation details for each phase.

---

## 💡 Notes

- This project was developed as part of a university assignment.
- The GUI version is the **final and recommended implementation**.
- The CLI version is included to demonstrate the project's evolution and early design decisions.

---

## 👨‍💻 Author

**Keivan Behravan**

[![GitHub](https://img.shields.io/badge/GitHub-itzkeyvan-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/itzkeyvan)