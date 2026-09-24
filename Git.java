import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Git {
    public static void main(String[] args) {
        Git myGit = new Git();
        myGit.init();
    }

    // Initializes repository structure in ./git/: Objects/, Index, and HEAD
    public void init() {
        File git = new File("./git");
        File objects = new File("./git/objects");
        File index = new File("./git/index");
        File HEAD = new File("./git/HEAD");

        try {
            if (git.mkdirs() || objects.mkdirs() || index.createNewFile() || HEAD.createNewFile()) {
                System.out.println("Git Repository Created");
            } else {
                System.out.println("Git Repository Already Exists");
            }

        } catch (Exception e) {
            System.out.println("File creation exception:" + e);
        }
    }

    //
    public String hashFile(String filePath) {
        try {
            String fileContents = Files.readString(Path.of(filePath));
            byte[] hash = MessageDigest.getInstance("SHA-1")
                    .digest(fileContents.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}


