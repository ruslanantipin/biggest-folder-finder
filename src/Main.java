import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static int KB_BYTE = 1024;
    private static int MB_BYTE = 1048576;
    private static int GB_BYTE = 1073741824;

    public static void main(String[] args) {

        ParametrsBag parametrs = new ParametrsBag(args);

        String folderPath = parametrs.getPath();
        Long sizeLimit = parametrs.getLimit();

        File file = new File(folderPath);
        Node root = new Node(file);

        Long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root, sizeLimit);
        ForkJoinPool pool = new ForkJoinPool();
        Long size = pool.invoke(calculator);
        System.out.println(root);
        //System.out.println(getSizeFromHumanReadable(getHumanReadableSize(root.getSize())));
        //System.out.println(getFolderSize(file));

        Long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");
    }


}
