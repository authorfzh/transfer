package cn.easyliao.transfer.task;

import cn.easyliao.transfer.Factory.SourcesTypeFactory;
import cn.easyliao.transfer.bean.ActionConfig;
import cn.easyliao.transfer.bean.ActiveAcquirement;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主动获取数据定时任务
 */
@Component
@Configuration
@EnableScheduling
public class SaticScheduleTask {

    /*@Mapper
    public interface CronMapper {
        //@Select("select * from * where * = ''")
        public String getCron();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;*/

    @Scheduled(fixedRate = 5000)
    private void configureTasks() {
        System.err.println("执行主动获取数据定时任务时间: " + LocalDateTime.now());
        List<Map<String, Object>> list = new ArrayList<>();

        //测试数据
        Map<String, Object> map0 = new HashMap<>();
        map0.put("dataSource", "touTiao");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("dataSource", "lianZhanSE");
        map1.put("config", "openKey=3ED6BB05103503FA6705F9EAC89F8E8E65F694\r\n" +
                "privateKey=AA0F99C33310785315549B46C362D36494B7C\r\n" +
                "url=https://guest.qudao.com/api/data");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("dataSource", "kmway");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("dataSource", "lianZhanTOF");
        map3.put("config", "url=http://ly.hao315.com/Interface_message.php");
        //list.add(map0);
        //list.add(map1);
        //list.add(map2);
        list.add(map3);

        for (Map<String, Object> map : list) {
            ActionConfig ac = new ActionConfig();
            ActiveAcquirement aa = new ActiveAcquirement();
            aa.setChannelId(1);//
            aa.setConfig(map.get("config") + "");//
            aa.setDataSource(map.get("dataSource") + "");//
            aa.setGroupId(1);//
            aa.setId(1);//
            aa.setStatus(1);//

            ac.setType(0);
            ac.setCompanyId(00000);//
            ac.setActiveAcquirement(aa);

            SourcesTypeFactory.getInstance(ac, null).setData(ac, null);

        }
    }
}
