import com.jiker.keju.util.CalculateFee;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
public class CalculateFeeTest {
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void parsing_line_format_error_test(){
        new CalculateFee().getWaiteTimeAndDistanceFee("10公里;等待0分钟");
    }
    @Test(expected = NumberFormatException.class)
    public void distince_not_number_test(){
        new CalculateFee().getWaiteTimeAndDistanceFee("十公里,等待0分钟");
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void waitTime_not_number_test() {
        new CalculateFee().getWaiteTimeAndDistanceFee("10公里;等待分钟");
    }
    @Test
    public void parsing_line_success_and_distance_le_2(){
        Map map = new CalculateFee().getWaiteTimeAndDistanceFee("1公里,等待0分钟");
        assertEquals("{distance=1, waiteTime=0}",map.toString());
        assertEquals(6,new CalculateFee().getOneReceipt(map));
    }
    @Test
    public void parsing_line_success_and_distance_gt_2_and_le_8(){
        Map map = new CalculateFee().getWaiteTimeAndDistanceFee("7公里,等待0分钟");
        assertEquals("{distance=7, waiteTime=0}",map.toString());
        assertEquals(10,new CalculateFee().getOneReceipt(map));
    }
    @Test
    public void parsing_line_success_and_distance_gt_8(){
        Map map = new CalculateFee().getWaiteTimeAndDistanceFee("10公里,等待2分钟");
        assertEquals("{distance=10, waiteTime=2}",map.toString());
        assertEquals(14,new CalculateFee().getOneReceipt(map));
    }
    @Test
    public void more_than_one_line_test(){
        List<String> lines = new ArrayList<>();
        lines.add("1公里,等待0分钟");
        lines.add("7公里,等待1分钟");
        lines.add("12公里,等待3分钟");
        String receipt = new CalculateFee().getRecepit(lines);
        assertEquals("收费6元\n收费10元\n收费16元\n", receipt);
    }
}