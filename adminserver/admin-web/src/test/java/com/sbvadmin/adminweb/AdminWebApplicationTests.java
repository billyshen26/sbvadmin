package com.sbvadmin.adminweb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@Slf4j
class AdminWebApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {
        String sysName = System.getProperty("os.name").toLowerCase();
        String userDir = System.getProperty("user.dir");
        // 判断要backup文件是否存在
        File file = new File(userDir + "/backup");
        if (!file.exists()) {
            file.mkdirs();
        }

        String cmd = "java -version";
        Process exec = Runtime.getRuntime().exec(cmd);

        if (exec.waitFor() == 0){
            log.info("操作操作命令成功！！！" + exec);
        }
    }

}
