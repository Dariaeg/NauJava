import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File implements Task {
    private final String fileUrl;
    private final String destinationPath;
    private final boolean isStopped;

    public File(String fileUrl, String destinationPath) {
        this.fileUrl = fileUrl;
        this.destinationPath = destinationPath;
        this.isStopped = false;
    }

    @Override
    public void start() {
        Thread downloadThread = new Thread(() -> {
            try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
                 FileOutputStream out = new FileOutputStream(destinationPath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {

                    out.write(buffer, 0, bytesRead);
                }

                System.out.println("Файл успешно загружен: " + destinationPath);

            } catch (IOException e) {
                if (!isStopped) {
                    System.err.println("Ошибка при загрузке файла: " + e.getMessage());
                    cleanUpPartialDownload();
                }
            }
        });

        downloadThread.start();
    }


    private void cleanUpPartialDownload() {
        try {
            Path path = Paths.get(destinationPath);
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Частично загруженный файл удален");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при удалении частично загруженного файла: " + e.getMessage());
        }
    }
}

interface Task {
    void start();
}

class Main {
    public static void main(String[] args) {
        String fileUrl = "http://schooln45.ru/assets/mymedia/Uchebniki/1-4%20kl/%D0%9C%D0%BE%D1%80%D0%BE%20%D0%9C.%D0%98.,%20%D0%92%D0%BE%D0%BB%D0%BA%D0%BE%D0%B2%D0%B0%20%D0%A1.%D0%98.,%20%D0%A1%D1%82%D0%B5%D0%BF%D0%B0%D0%BD%D0%BE%D0%B2%D0%B0%20%D0%A1.%D0%92.%20%D0%9C%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0.%201%20%D0%BA%D0%BB%D0%B0%D1%81%D1%81.%20%D0%A7%D0%B0%D1%81%D1%82%D1%8C%202%202011.pdf";
        String destination = "downloaded_file.zip";

        Task downloadTask = new File(fileUrl, destination);

        downloadTask.start();
    }
}