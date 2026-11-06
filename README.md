# Face Detection System

Real-time face detection application built using **Java** and **OpenCV**. It captures live video from the laptop webcam, detects human faces using the Haar Cascade Classifier, draws green rectangles around them and displays the total face count.

## Features

- Live webcam video capture
- Face detection using Haar Cascade Classifier
- Green rectangle around every detected face
- "Face Detected" label above each face
- Live face counter on screen
- Press `Q` or `ESC` to close the application

## Technologies Used

| Technology | Version |
| ---------- | ------- |
| Java (JDK) | 17 |
| OpenCV | 4.9.0-0 |
| Apache Maven | 3.10.0 |
| IDE | Visual Studio Code |

## Project Structure

```text
FaceDetectionSystem
├── src
│   └── main
│       ├── java
│       │   └── FaceDetection.java
│       └── resources
│           └── haarcascade_frontalface_default.xml
├── pom.xml
└── .gitignore
```

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/shreyanshipathsariya35-ship-it/FaceDetectionSystem.git
   ```
2. Open the folder in VS Code.
3. Make sure JDK 17 and Maven are installed.
4. Compile the project:
   ```bash
   mvn clean compile
   ```
5. Run the application:
   ```bash
   mvn exec:java
   ```

## How It Works

1. Capture a frame from the webcam using `VideoCapture`.
2. Convert the frame to grayscale using `Imgproc.cvtColor()`.
3. Detect faces using `CascadeClassifier.detectMultiScale()`.
4. Draw rectangles and labels around the detected faces.
5. Show the total face count on the video window.

## Author

Shreyanshi Pathsariya