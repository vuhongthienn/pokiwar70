package com.server.pokiwar.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.ImageReader;
import java.util.ArrayList;
public class GifToSprite {
    public static void main(String[] args) throws IOException {
        File inputDir = new File("C:\\Users\\ASUS\\Downloads\\sprites");  // Đường dẫn tới thư mục chứa thư mục các file GIF
        File outputDir = new File("C:\\sprite");  // Đường dẫn tới thư mục lưu sprite sheets

        if (inputDir.exists() && inputDir.isDirectory()) {
            // Tạo thư mục đích nếu chưa tồn tại
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Lấy tất cả thư mục con trong thư mục cha
            File[] subDirs = inputDir.listFiles(File::isDirectory);

            if (subDirs != null) {
                int n=0;
                for (File subDir : subDirs) {
                    n++;
                    // Tìm file GIF trong mỗi thư mục con
                    File[] gifFiles = subDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".gif"));

                    if (gifFiles != null && gifFiles.length > 0) {
                        // Chỉ xử lý thư mục có chứa file GIF
                        File gifFile = gifFiles[0];  // Giả sử mỗi thư mục con chỉ có một file GIF

                        System.out.println("Đang xử lý: " + gifFile.getName() + " trong thư mục " + subDir.getName());

                        // Lấy tất cả frames từ file GIF
                        ArrayList<BufferedImage> allFrames = getGifFrames(gifFile);

                        // Tạo sprite sheet từ tất cả các frames
                        BufferedImage spriteSheet = createSpriteSheet(allFrames);

                        // Lấy kích thước của mỗi frame
                        int frameWidth = allFrames.get(0).getWidth();
                        int frameHeight = allFrames.get(0).getHeight();

                        // Tạo tên file đầu ra
                        String spriteSheetFileName = n + "_" + frameWidth + "x" + frameHeight + ".png";
                        File outputFile = new File(outputDir, spriteSheetFileName);  // Lưu vào thư mục output

                        // Lưu sprite sheet vào thư mục đích
                        ImageIO.write(spriteSheet, "PNG", outputFile);
                        System.out.println("Đã lưu sprite sheet: " + outputFile.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("Thư mục không hợp lệ.");
        }
    }

    public static ArrayList<BufferedImage> getGifFrames(File gifFile) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(gifFile);
        ImageReader reader = ImageIO.getImageReaders(iis).next();

        // Tắt chế độ seekForwardOnly để tránh lỗi xung đột
        reader.setInput(iis, false); // Tắt seekForwardOnly

        int totalFrames = reader.getNumImages(true);  // Đếm số frame trong GIF
        ArrayList<BufferedImage> frames = new ArrayList<>();

        // Đọc từng frame trong GIF
        for (int i = 0; i < totalFrames; i++) {
            BufferedImage frame = reader.read(i);
            frames.add(frame);
        }

        return frames;
    }

    public static BufferedImage createSpriteSheet(ArrayList<BufferedImage> frames) {
        // Kích thước của mỗi frame (giả sử tất cả frame có kích thước giống nhau)
        int frameWidth = frames.get(0).getWidth();
        int frameHeight = frames.get(0).getHeight();

        // Số lượng frames
        int totalFrames = frames.size();

        // Tính toán số dòng và cột cần thiết để chứa tất cả các frames
        int cols = (int) Math.ceil(Math.sqrt(totalFrames));  // số cột
        int rows = (int) Math.ceil((double) totalFrames / cols);  // số dòng

        // Tạo một sprite sheet mới đủ lớn để chứa tất cả các frame
        BufferedImage spriteSheet = new BufferedImage(cols * frameWidth, rows * frameHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = spriteSheet.createGraphics();

        // Vẽ từng frame lên sprite sheet
        for (int i = 0; i < totalFrames; i++) {
            int x = (i % cols) * frameWidth;
            int y = (i / cols) * frameHeight;
            g2d.drawImage(frames.get(i), x, y, null);
        }

        g2d.dispose();
        return spriteSheet;
    }
}
