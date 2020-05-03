import com.jiker.keju.util.FileReaderUtil;
import org.junit.After;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FileReaderUtilTest {
    File file = new File("src/main/resources/test.txt");
    @After
    public void tearDown() {
        file.delete();
    }
    @Test(expected = FileNotFoundException.class)
    public void file_name_is_null_test() throws Exception {
        new FileReaderUtil().readFile("");
    }
    @Test(expected = FileNotFoundException.class)
    public void file_name_is_empty_test() throws Exception {
        new FileReaderUtil().readFile("");
    }
    @Test(expected = FileNotFoundException.class)
    public void file_not_exist_test() throws Exception {
        new FileReaderUtil().readFile("src/main/resources/test.txt");
    }
    @Test
    public void file_exist_but_no_lines_test() throws Exception{
        file.createNewFile();
        List<String> lines =  new FileReaderUtil().readFile("src/main/resources/test.txt");
        assertEquals("[]",lines.toString());
    }
    @Test
    public void file_exist_and_read_success_test() throws Exception {
        file.createNewFile();
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("2公里,等待0分钟\n4公里,等待1分钟\n10公里,等待2分钟");
        bufferedWriter.close();
        List<String> lines =  new FileReaderUtil().readFile("src/main/resources/test.txt");
        assertEquals("[2公里,等待0分钟, 4公里,等待1分钟, 10公里,等待2分钟]",lines.toString());
    }
    @Test
    public void file_exist_and_have_lines_and_read_success_test() throws Exception {
        List<String> lines =  new FileReaderUtil().readFile("src/main/resources/testData.txt");
        assertNotNull(lines);
    }
}