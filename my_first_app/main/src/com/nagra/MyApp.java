package com.nagra;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class MyApp {
    public static void main(String[] args) throws Exception {
        while (true){

                URL url = new URL("https://icanhazdadjoke.com/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                Scanner scanner = new Scanner(connection.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                String joke = response.split("\"joke\":\"")[1].split("\",\"")[0];

                // Display the joke
                System.out.println(joke);
                //-----------------------------------------------------------------------------------------
                // Load the image fileex
                File imageFile = new File("C:\\Users\\HP\\assignment\\my_first_app\\my_first_app\\main\\resources\\download1.png");
                BufferedImage image = ImageIO.read(imageFile);

                // Resize the image to a smaller size for better ASCII representation
                int newWidth = image.getWidth() / 3;
                int newHeight = image.getHeight() / 8;
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
                Thread.sleep(15000);

        }
    }
}