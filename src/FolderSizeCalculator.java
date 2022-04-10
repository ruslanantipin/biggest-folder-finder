import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
        private Node node;
        private Long sizeLimit;

        public FolderSizeCalculator(Node node, Long sizeLimit) {
            this.node = node;
            this.sizeLimit = sizeLimit;
        }

        @Override
        protected Long compute() {

            File folder = node.getFolder();
            if(folder.isFile()){
                Long lenght = folder.length();
                node.setSize(lenght);
                return lenght;
            }

            long sum = 0;
            List<FolderSizeCalculator> subTasks = new LinkedList<>();

            File[] files = folder.listFiles();
            for(File file : files) {
                Node child = new Node(file, node);
                FolderSizeCalculator task = new FolderSizeCalculator(child, sizeLimit);
                task.fork(); // запустим асинхронно
                subTasks.add(task);
                node.addChild(child);
            }

            for(FolderSizeCalculator task : subTasks) {
                sum += task.join(); // дождёмся выполнения задачи и прибавим результат
            }

            node.setSize(sum);
            if (sum <= sizeLimit){
                node.getParent().deleteChild(node);
            }
            return sum;
        }

}
