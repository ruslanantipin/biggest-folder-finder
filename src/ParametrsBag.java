import java.io.File;

public class ParametrsBag {
    private String path;
    private Long limit = Long.valueOf(0);

    public ParametrsBag(String args[])
    {
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-d")){
                this.path = args[i+1];
            }else if (args[i] == "-p"){
                this.limit = Long.valueOf(args[i+1].trim());
            }
        }

        System.out.println(this.path);
        File file = new File(this.path);
        if(!(file.isFile() || file.isDirectory())){
            throw new IllegalArgumentException("Указанная папка не существует");
        }

    }

    public Long getLimit()
    {
        return limit;
    }

    public String getPath()
    {
        return path;
    }
}
