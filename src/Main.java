import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        String folderPath = "E:\\С телефона";
        File file = new File(folderPath);

        Long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        Long size = pool.invoke(calculator);
        System.out.println(size);
        //System.out.println(getFolderSize(file));

        Long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");
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
