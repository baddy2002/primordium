package com.baddy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileUtils {

    //leggi e scrivi una stringa dal file
    public static String readFileToString(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
    }

    public static void writeStringToFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content, StandardCharsets.UTF_8);
    }

    //leggi e scrivi righe diverse
    public static List<String> readFileToList(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
    }

    public static void writeLinesToFile(String filePath, List<String> lines) throws IOException {
        Files.write(Path.of(filePath), lines, StandardCharsets.UTF_8);
    }

    public static boolean fileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }

    public static boolean deleteFile(String filePath) throws IOException {
        return Files.deleteIfExists(Path.of(filePath));
    }

    public static void deleteDirectory(String directoryPath) throws IOException {
        Path dirPath = Path.of(directoryPath);
        if (Files.exists(dirPath)) {
            Files.walk(dirPath)
                    .sorted((p1, p2) -> -p1.compareTo(p2)) // Ordine inverso per eliminare prima i file interni
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            throw new RuntimeException("Errore nell'eliminazione del file: " + p, e);
                        }
                    });
        }
    }

    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Path.of(sourcePath), Path.of(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void moveFile(String sourcePath, String destinationPath) throws IOException {
        Files.move(Path.of(sourcePath), Path.of(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String computeFileHash(String filePath, String algorithm) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        try (InputStream fis = Files.newInputStream(Path.of(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String computeMD5(String filePath) throws IOException, NoSuchAlgorithmException {
        return computeFileHash(filePath, "MD5");
    }

    public static String computeSHA256(String filePath) throws IOException, NoSuchAlgorithmException {
        return computeFileHash(filePath, "SHA-256");
    }

}
