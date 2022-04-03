import java.io.File;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Base1c\\СредстваУправленияДоступом";
        File file = new File(folderPath);

        System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }

        Long sum = Long.valueOf(0);
        File[] files = folder.listFiles();
        for(File file: files){
            sum += getFolderSize(file);
        }

        return sum;
    }
}
