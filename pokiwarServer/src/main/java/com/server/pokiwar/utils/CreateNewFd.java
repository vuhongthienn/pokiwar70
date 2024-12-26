package com.server.pokiwar.utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.madgag.gif.fmsware.AnimatedGifEncoder;
public class CreateNewFd {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\ASUS\\Downloads\\pokiwarTest\\pokiwarTest\\pokiwar\\Assets\\Resources\\large";
        String baseURL = "https://aola.100bt.com/play/fightassets/pet";
        String fileExtension = ".swf";
        String downloadFolder = "C:\\DownloadedFiles";

        try {
            createFolder(downloadFolder);

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        String fileName = file.getName();
                        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
                        String fileURL = baseURL + baseName + fileExtension;
                        String savePath = downloadFolder + File.separator + baseName + fileExtension;

                        System.out.println("Đang tải: " + fileURL);
                        downloadFile(fileURL, savePath);
                        System.out.println("Tệp đã được lưu: " + savePath);
                    }
                } else {
                    System.out.println("Không tìm thấy tệp PNG nào trong thư mục: " + folderPath);
                }
            } else {
                System.out.println("Thư mục không tồn tại hoặc không hợp lệ: " + folderPath);
            }

            System.out.println("Hoàn tất tải xuống tất cả các tệp!");
        } catch (IOException e) {
            System.err.println("Lỗi khi tải tệp: " + e.getMessage());
        }
    }

    public static void createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Thư mục đã được tạo: " + folderPath);
            } else {
                System.err.println("Không thể tạo thư mục: " + folderPath);
            }
        } else {
            System.out.println("Thư mục đã tồn tại: " + folderPath);
        }
    }

    public static void downloadFile(String fileURL, String savePath) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        int responseCode = httpConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedInputStream inputStream = new BufferedInputStream(httpConnection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            System.err.println("Không thể tải tệp: " + fileURL + ". Phản hồi: " + responseCode);
        }

        httpConnection.disconnect();
    }


}
