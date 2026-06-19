import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class SCT_CS_2 {

    public static void encryptImage(String inputPath, String outputPath, int key) {
        try {
            BufferedImage image = ImageIO.read(new File(inputPath));

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {

                    int pixel = image.getRGB(x, y);

                    int a = (pixel >> 24) & 0xff;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;

                    r = (r + key) % 256;
                    g = (g + key) % 256;
                    b = (b + key) % 256;

                    int encryptedPixel = (a << 24) | (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, encryptedPixel);
                }
            }

            ImageIO.write(image, "png", new File(outputPath));
            System.out.println("Image Encrypted Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void decryptImage(String inputPath, String outputPath, int key) {
        try {
            BufferedImage image = ImageIO.read(new File(inputPath));

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {

                    int pixel = image.getRGB(x, y);

                    int a = (pixel >> 24) & 0xff;
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;

                    r = (r - key + 256) % 256;
                    g = (g - key + 256) % 256;
                    b = (b - key + 256) % 256;

                    int decryptedPixel = (a << 24) | (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, decryptedPixel);
                }
            }

            ImageIO.write(image, "png", new File(outputPath));
            System.out.println("Image Decrypted Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Encrypt Image");
        System.out.println("2. Decrypt Image");
        System.out.print("Choose Option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Input Image Path: ");
        String inputPath = sc.nextLine();

        System.out.print("Enter Output Image Path: ");
        String outputPath = sc.nextLine();

        System.out.print("Enter Key Value: ");
        int key = sc.nextInt();

        if (choice == 1) {
            encryptImage(inputPath, outputPath, key);
        } else if (choice == 2) {
            decryptImage(inputPath, outputPath, key);
        } else {
            System.out.println("Invalid Choice!");
        }

        sc.close();
    }
}
