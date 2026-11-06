import nu.pattern.OpenCV;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.Videoio;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FaceDetection {

    public static void main(String[] args) throws Exception {

        // Load OpenCV
        OpenCV.loadLocally();

        System.out.println("OpenCV Loaded Successfully!");

        // Load Haar Cascade XML
        Path cascadeFile = Files.createTempFile("haarcascade", ".xml");

        try (InputStream input = FaceDetection.class
                .getResourceAsStream("/haarcascade_frontalface_default.xml")) {

            if (input == null) {
                throw new RuntimeException("Haar Cascade XML not found!");
            }

            Files.copy(input, cascadeFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        CascadeClassifier faceDetector =
                new CascadeClassifier(cascadeFile.toString());

        Files.deleteIfExists(cascadeFile);

        if (faceDetector.empty()) {
            System.out.println("Error loading Face Detector!");
            return;
        }

        System.out.println("Face Detector Loaded Successfully!");

        // Create webcam object
        VideoCapture camera = new VideoCapture(0,Videoio.CAP_DSHOW);

        if (!camera.isOpened()) {
            System.out.println("Error: Webcam not detected!");
            return;
        }

        System.out.println("Webcam Connected Successfully!");
        System.out.println("Press Q or ESC to close camera.");

        // Create frame objects
        Mat frame = new Mat();
        Mat gray = new Mat();
        MatOfRect faces = new MatOfRect();

        // Read live webcam frames
        while (true) {

            if (!camera.read(frame) || frame.empty()) {
                System.out.println("Unable to read webcam frame.");
                break;
            }

            // Convert frame into grayscale
            Imgproc.cvtColor(
                    frame,
                    gray,
                    Imgproc.COLOR_BGR2GRAY
            );

            // Detect faces
            faceDetector.detectMultiScale(
                    gray,
                    faces,
                    1.1,
                    5,
                    0,
                    new Size(30, 30),
                    new Size()
            );
               // Count detected faces
int faceCount = faces.toArray().length;

// Display face count
Imgproc.putText(
        frame,
        "Faces Detected: " + faceCount,
        new org.opencv.core.Point(20, 40),
        Imgproc.FONT_HERSHEY_SIMPLEX,
        1.0,
        new Scalar(0, 255, 0),
        2
);
            // Draw rectangle around detected faces
            for (Rect face : faces.toArray()) {

                Imgproc.rectangle(
                        frame,
                        face.tl(),
                        face.br(),
                        new Scalar(0, 255, 0),
                        3
                );
                   Imgproc.putText(
        frame,
        "Face Detected",
        new org.opencv.core.Point(face.x, face.y - 10),
        Imgproc.FONT_HERSHEY_SIMPLEX,
        0.6,
        new Scalar(0, 255, 0),
        2
);

            }

            // Display webcam video
            HighGui.imshow("Face Detection System", frame);

            // Exit when Q or ESC is pressed
            int key = HighGui.waitKey(30);

            if (key == 'q' || key == 'Q' || key == 27) {
                break;
            }
        }

        // Release resources
        camera.release();
        frame.release();
        gray.release();
        faces.release();

        HighGui.destroyAllWindows();

        System.out.println("Webcam Closed Successfully!");
    }
}
