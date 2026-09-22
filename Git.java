import java.io.*;

public class Git {
    public static void main(String[] args) {
        Git myGit = new Git();
        myGit.init();
    }

    // Initializes repository structure in ./git/: Objects/, index, and head
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
}


