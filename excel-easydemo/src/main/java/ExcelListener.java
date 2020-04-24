import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ReadData> {
    //创建list集合封装最终的数据
    List<ReadData> list = new ArrayList<ReadData>();



    //一行一行去读取excle内容
    @Override
    public void invoke(ReadData user, AnalysisContext analysisContext) {
        System.out.println("***"+user);
        list.add(user);
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer,String> headMap, AnalysisContext context){
        System.out.println("表头信息："+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public static void main(String[] args) throws Exception {
        // 写法1：
        String fileName = "D:\\ProgramFiles\\课程分类模块文件.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ReadData.class, new ExcelListener()).sheet().doRead();

        /*// 写法2：
        InputStream in = new BufferedInputStream(new FileInputStream("F:\\01.xlsx"));
        ExcelReader excelReader = EasyExcel.read(in, ReadData.class, new ExcelListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();

        */
    }
}
