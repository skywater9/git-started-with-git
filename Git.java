import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
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
        File index = new File("./git/INDEX");
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

    // Hashes file contents using SHA-1
    public String hashFile(String filePath) {
        try {
            String fileContents = Files.readString(Path.of(filePath));
            byte[] hash = MessageDigest.getInstance("SHA-1")
                    .digest(fileContents.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);

        } catch (Exception e) {
            System.out.println("File hashing exception:" + e);
        }
        return null;
    }

    // Turns file into BLOB and inserts into git/objects/, and records in git/INDEX
    public void add(String filePath) {
        String fileHash = this.hashFile(filePath);
        String blobPathString = "./git/objects/" + fileHash;

        File blob = new File(blobPathString);

        try {
            blob.createNewFile();
            byte[] contents = Files.readAllBytes(Path.of(filePath));
            Files.write(Path.of(blobPathString), contents);
        } catch (Exception e) {
            System.out.println("File adding exception:" + e);
        }

        String indexPathString = "/git/INDEX";
        String indexEntry = fileHash + "    " + filePath + "\n";

        try (FileWriter writer = new FileWriter(indexPathString, true)) {
            writer.write(indexEntry);
        } catch (IOException e) {
            System.out.println("Index writing exception: " + e);
        }
    }
}


