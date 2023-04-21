package com.nagra;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MyApp {
    public static void main(String[] args) {
        try {
            // Load the image file
            File imageFile = new File("image.png");
            BufferedImage image = ImageIO.read(imageFile);

            // Resize the image to a smaller size for better ASCII representation
            int newWidth = image.getWidth() /3;
            int newHeight = image.getHeight() /8;
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(image, 0, 0, newWidth, newHeight, null);

            // Convert the resized image to ASCII characters
            StringBuilder sb = new StringBuilder();
            String characters = "@#S%?*+;:,.";// ASCII characters from darkest to lightest
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    int rgb = resizedImage.getRGB(x, y);
                    int gray = (int) (0.2126 * ((rgb >> 16) & 0xff) + 0.7152 * ((rgb >> 8) & 0xff) + 0.0722 * (rgb & 0xff));
                    int index = (int) (gray / (255.0 / (characters.length() - 1)));
                    sb.append(characters.charAt(index));
                }
                sb.append("\n");
            }

            // Print the ASCII art
            System.out.println(sb.toString());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}