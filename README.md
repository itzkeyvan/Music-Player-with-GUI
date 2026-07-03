# 🎵 Music Player Project (CLI + JavaFX GUI)

## 📌 Overview

This project was developed as part of the **Advanced Programming (BSc Computer Engineering)** course.

The goal was to design and implement a **Spotify-like music player** in Java in two progressive phases:

1. **Phase 1 – CLI Version:** A command-line based music player with core functionality.
2. **Phase 2 – GUI Version:** A full desktop application built using **JavaFX** and **Maven** , extending and improving the CLI version with a graphical interface.

The project demonstrates the evolution from a structured console application to a modern event-driven GUI application.

---

## 🧱 Project Structure

<pre class="overflow-visible! px-0!" data-start="926" data-end="1253"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute end-1.5 top-1 z-2 md:end-2 md:top-1"></div><div class="relative"><div class="pe-11 pt-3"><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>.</span><br/><span>├── Phase 1 - CLI/</span><br/><span>│   └── Console-based music player implementation</span><br/><span>│</span><br/><span>├── Phase 2 - GUI/</span><br/><span>│   └── JavaFX desktop application (main project)</span><br/><span>│</span><br/><span>├── Phase 2 - GUI - Screenshots/</span><br/><span>│   └── UI previews of the JavaFX application</span><br/><span>│</span><br/><span>├── AP_FirstProject_Phase1.pdf</span><br/><span>├── AP_FirstProject_Phase2.pdf</span><br/><span>├── .gitignore</span><br/><span>└── README.md</span></code></pre></div></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></pre>

---

## 🛠️ Technologies Used

### Core

- **Java** - Main programming language
- **JavaFX** - UI framework
- **Maven** - Build automation tool

### Media

- **JavaFX Media** - Audio playback
- **JLayer** - MP3 processing

### Data Management

- **In-memory database** (DataBase class)
- **Serialization** for data persistence (implicit)

---

## 📸 Screenshots

Screenshots of the GUI version can be found in:

<pre class="overflow-visible! px-0!" data-start="1971" data-end="2007"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute end-1.5 top-1 z-2 md:end-2 md:top-1"></div><div class="relative"><div class="pe-11 pt-3"><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>Phase 2 - GUI - Screenshots/</span></code></pre></div></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></pre>

Here are two examples:

![Welcome Page](Phase%202%20-%20GUI%20-%20Screenshots/Welcome%20Page.png)

![Home Page](Phase%202%20-%20GUI%20-%20Screenshots/Home%20Page.png)

---

## 📥 Installation

### Prerequisites

- Java 21 or higher
- Maven
- JavaFX SDK

## 🚀 How to Run

### ▶️ Phase 1 (CLI)

1. Clone the repository:
   <pre class="overflow-visible! px-0!" data-start="2280" data-end="2315"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="relative h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute inset-x-4 top-12 bottom-4"><div class="pointer-events-none sticky z-40 shrink-0 z-1!"><div class="sticky bg-token-border-light"></div></div></div><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class=""><div class="relative"><div class=""><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>git clone https://github.com/itzkeyvan/Music-Player-with-GUI.git</span></code></pre></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></div></div></pre>
2. Open the `Phase 1 - CLI` folder as a project
3. Compile and run the Main.java class
4. Follow the console menu instructions

---

### 🖥️ Phase 2 (GUI - Recommended)

1. Clone the repository:
   <pre class="overflow-visible! px-0!" data-start="2280" data-end="2315"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="relative h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute inset-x-4 top-12 bottom-4"><div class="pointer-events-none sticky z-40 shrink-0 z-1!"><div class="sticky bg-token-border-light"></div></div></div><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class=""><div class="relative"><div class=""><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>git clone https://github.com/itzkeyvan/Music-Player-with-GUI.git</span></code></pre></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></div></div></pre>
2. Open the `Phase 2 - GUI` folder as a project
3. Build the project using Maven:
   <pre class="overflow-visible! px-0!" data-start="2280" data-end="2315"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="relative h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute inset-x-4 top-12 bottom-4"><div class="pointer-events-none sticky z-40 shrink-0 z-1!"><div class="sticky bg-token-border-light"></div></div></div><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class=""><div class="relative"><div class=""><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>mvn clean install</span></code></pre></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></div></div></pre>
4. Run the Main.java class from your IDE or:
   <pre class="overflow-visible! px-0!" data-start="2367" data-end="2399"><div class="relative w-full mt-4 mb-1"><div class=""><div class="contents"><div class="border border-token-border-light border-radius-3xl corner-superellipse/1.1 rounded-3xl"><div class="relative h-full w-full border-radius-3xl bg-token-bg-elevated-secondary corner-superellipse/1.1 overflow-clip rounded-3xl lxnfua_clipPathFallback"><div class="pointer-events-none absolute inset-x-4 top-12 bottom-4"><div class="pointer-events-none sticky z-40 shrink-0 z-1!"><div class="sticky bg-token-border-light"></div></div></div><div class="relative"><div class="h-full min-h-0 min-w-0"><div class="h-full min-h-0 min-w-0"><div class=""><div class="relative"><div class=""><div class="relative z-0 flex max-w-full"><div id="code-block-viewer" dir="ltr" class="q9tKkq_viewer cm-editor z-10 light:cm-light dark:cm-light flex h-full w-full flex-col items-stretch ͼd ͼr"><div class="cm-scroller"><pre class="cm-content q9tKkq_readonly m-0"><code><span>mvn javafx:run</span></code></pre></div></div></div></div></div></div></div></div><div class=""><div class=""></div></div></div></div></div></div></div></div></pre>

---

## 🚀 Phase 2 - GUI Usage

### First Time Setup

1. **Sign Up** - Create a listener account
2. **Select Favorite Genres** - Choose up to 4 genres for personalized recommendations
3. **Start Exploring** - Browse audios, artists, and create playlists

### Main Navigation

| Button  | Function                                 |
| ------- | ---------------------------------------- |
| Home    | View recommended audios                  |
| Search  | Search for audios by title or artist     |
| Library | View your playlists and followed artists |
| Audios  | Browse all available audios              |
| Artists | Browse all artists                       |

### Player Controls

| Control       | Function                  |
| ------------- | ------------------------- |
| Play/Pause    | Toggle playback           |
| Previous/Next | Navigate through playlist |
| Progress Bar  | Seek within audio         |
| Volume Slider | Adjust volume             |

### Playlist Management

- Click "New Playlist" in Library to create a playlist
- Add audios to playlists from the audio detail page
- Remove audios from playlists
- Delete playlists

### Premium Features

Navigate to **Explore Premium** in your library to view subscription plans:

- **Bronze** - 30 days for $5
- **Silver** - 60 days for $9
- **Gold** - 180 days for $14

---

## 📚 Project Documentation

Detailed assignment descriptions are available in:

- `AP_FirstProject_Phase1.pdf`
- `AP_FirstProject_Phase2.pdf`

These files describe the academic requirements and implementation details for each phase.

---

## 💡 Notes

- This project was developed as part of a university assignment.
- The GUI version is the **final and recommended implementation** .
- The CLI version is included to demonstrate the project's evolution and early design decisions.

---

## 👨‍💻 Author

**Keivan Behravan**

- GitHub: [@itzkeyvan](https://github.com/itzkeyvan)
