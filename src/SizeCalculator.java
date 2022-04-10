import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2multiplier = getMultiplires();

//    private static long getFolderSize(File folder){
//        if(folder.isFile()){
//            return folder.length();
//        }
//
//        Long sum = Long.valueOf(0);
//        File[] files = folder.listFiles();
//        for(File file: files){
//            sum += getFolderSize(file);
//        }
//
//        return sum;
//    }

    public static String getHumanReadableSize(Long size) {
        for(int i = 0; i < sizeMultipliers.length; i++)
        {
            double value = Double.valueOf(size) / Math.pow(1024., i);
            if(value < 1024){
                return Math.round(value * 100. / 100.) + "" + sizeMultipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return "Very big!";
//        Double tmpSize = 0.0;
//        if(size < KB_BYTE)
//        {
//            return size.toString() + "B";
//        }else if(size < MB_BYTE)
//        {
//            tmpSize = size.doubleValue() / KB_BYTE;
//            tmpSize = tmpSize * 100;
//            tmpSize = Double.valueOf(Math.round(tmpSize));
//            tmpSize = tmpSize / 100;
//            return Double.toString(tmpSize) + "K";
//        }else if(size < GB_BYTE)
//        {
//            tmpSize = size.doubleValue() / MB_BYTE;
//            tmpSize = tmpSize * 100;
//            tmpSize = Double.valueOf(Math.round(tmpSize));
//            tmpSize = tmpSize / 100;
//            return Double.toString(tmpSize) + "M";
//        }else
//        {
//            tmpSize = size.doubleValue() / GB_BYTE;
//            tmpSize = tmpSize * 100;
//            tmpSize = Double.valueOf(Math.round(tmpSize));
//            tmpSize = tmpSize / 100;
//            return Double.toString(tmpSize) + "G";
//        }

    }

    public static Long getSizeFromHumanReadable(String size)
    {

        char sizeFactor = size.replaceAll("[0-9\\s]+","").charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        Long lenght = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return lenght;
    }

    private static HashMap<Character, Integer> getMultiplires()
    {
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++)
        {
            char2multiplier.put(sizeMultipliers[i], (int) Math.pow(1024, i));
        }

        return char2multiplier;
    }
}
