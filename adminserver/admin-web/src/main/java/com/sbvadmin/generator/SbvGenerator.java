package com.sbvadmin.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.sbvadmin.mapper.PermissionMapper;
import com.sbvadmin.model.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Notes: 用于生成低代码
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/7 15:53
 */
@SpringBootTest
@ActiveProfiles("dev")
public class SbvGenerator {

    @Autowired
    public PermissionMapper permissionMapper;

    private List<String> inputTables;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.database}")
    private String database;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.application.vbenName}")
    private String vbenName;

    @Test
    public void genCode() {
        /**
         * Notes:  1.生成低代码文件
         * Author: 涛声依旧 likeboat@163.com
         * Time: 2022/12/13 17:15
         **/
        String dir = System.getProperty("user.dir");
        FastAutoGenerator.create(url, username, password)
            .globalConfig(builder -> {
                builder.author(name) // 设置作者
                    .disableOpenDir() //禁止打开输出目录
                    .outputDir("src/main/java/com/sbvadmin/generator/tempFiles"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.sbvadmin") // 设置父包名
                        .entity("model") //设置entity包名
                        .other("vue") // 前端页面生成放入的临时包
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src/main/java/com/sbvadmin/generator/tempFiles")); // 设置mapperXml生成路径
            })
            // 策略配置
            .strategyConfig((scanner, builder) ->  {
                inputTables = getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"));
                builder.addInclude(inputTables)
                    .controllerBuilder().enableRestStyle().enableHyphenStyle().superClass("com.sbvadmin.controller.BaseController")
                    .entityBuilder().enableLombok().addTableFills(
                            new Column("create_time", FieldFill.INSERT)
                    ).build();
            })
            .injectionConfig((scanner, builder) -> {
                Map<String,String> customFile = new HashMap<>();
                customFile.put("LIST_VIEW",VueFileEnum.LIST_VIEW.getTemplate());
                customFile.put("ADD_EDIT_VIEW",VueFileEnum.ADD_EDIT_VIEW.getTemplate());
                customFile.put("VIET_DATA",VueFileEnum.VIET_DATA.getTemplate());
                customFile.put("API",VueFileEnum.API.getTemplate());
                customFile.put("MODEL",VueFileEnum.MODEL.getTemplate());
                customFile.put("I18N_EN",VueFileEnum.I18N_EN.getTemplate());
                customFile.put("I18N_ZH",VueFileEnum.I18N_ZH.getTemplate());
                customFile.put("MENU_SQL",VueFileEnum.MENU_SQL.getTemplate()); // 生成菜单的sql
                customFile.put("CONTROLLER",VueFileEnum.CONTROLLER.getTemplate()); // 自定义controller
                builder.customFile(customFile);
            })
            .templateEngine(new EnhanceFreemarkerTemplateEngine())
            .execute();

        /**
         * Notes:  2.把自动生成的文件放到对应的位置
         * Author: 涛声依旧 likeboat@163.com
         * Time: 2022/12/13 17:15
         **/
        moveFiles();
    }


    /**
     * Notes:  内部类，前端代码生成，自定义
     * @param:
     * @return:
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/11/27 21:32
     **/
    public final class EnhanceFreemarkerTemplateEngine extends VelocityTemplateEngine {

        protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
            String entityName = tableInfo.getEntityName();
            String tableName = tableInfo.getName();
            String otherPath = this.getPathInfo(OutputFile.other);
            try {
                Thread.sleep(1000); // 休眠一秒，解决版本重复问题
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTime = sdf.format(new Date());

            // 自定义map，主要为了解决flyway sql中主键id的获取
//            // 创建wapper，查询当前permission表中主键最大值 废弃2023/3/13
//            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
//            wrapper.select("max(id) as id");
//            Permission permission = permissionMapper.selectOne(wrapper);
//            Long maxid1 = permission.getId();
//            System.out.println("maxId1=" + maxid1);

            /**
             * Notes: 1.在init.sql中预留1-999给sbvamin框架，方便框架升级
             *        2.此处直接获取AutoIncrement的值，然后赋值
             * Author: 涛声依旧 likeboat@163.com
             * Time: 2023/3/13 18:00
             **/
            Long maxid = permissionMapper.getAutoIncrement(database);
            System.out.println("maxId=" + maxid);
            // 自定义permission自增主键
            objectMap.put("permissionId", maxid +  inputTables.indexOf(tableName));

            customFile.forEach((key, value) -> {
                VueFileEnum vueFile = VueFileEnum.valueOf(key);
                String fileName = null;
                if (vueFile.getDir() == "view")
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + tableName + File.separator + entityName + vueFile.getFileName();
                else if (vueFile.getDir() == "sql")
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + "V"+currentTime+"__"+ entityName + vueFile.getFileName();
                else
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + entityName + vueFile.getFileName();
                this.outputFile(new File(fileName), objectMap, value);
            });
        }
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    /**
     * Notes:  把mp生成的文件放到对应的位置
     * @param: []
     * @return: void
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/11/15 09:36
     **/
    @Test
    public void moveFiles() {
        InputStreamReader stdISR = null;
        InputStreamReader errISR = null;
        Process process = null;
        String command = "";
        if(System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
            command = "src/main/java/com/sbvadmin/generator/move_files.cmd"; // TODO
        }else{
            command = "src/main/java/com/sbvadmin/generator/move_files.sh " + vbenName; // 前端工程项目文件夹名，方便自己对项目取名
        }
        try {
            process = Runtime.getRuntime().exec(command);

            String line = null;

            stdISR = new InputStreamReader(process.getInputStream());
            BufferedReader stdBR = new BufferedReader(stdISR);
            while ((line = stdBR.readLine()) != null) {
                System.out.println("STD line:" + line);
            }

            errISR = new InputStreamReader(process.getErrorStream());
            BufferedReader errBR = new BufferedReader(errISR);
            while ((line = errBR.readLine()) != null) {
                System.out.println("ERR line:" + line);
            }

            int exitValue = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stdISR != null) {
                    stdISR.close();
                }
                if (errISR != null) {
                    errISR.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e) {
                System.out.println("正式执行命令：" + command + "有IO异常");
            }
        }
    }


    /**
     * Notes:  生成flyway的sql文件，方便版本管理
     * @param: []
     * @return: void
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/12/15 20:02
     **/
    @Test
    public void genFlywaySql() throws IOException, InterruptedException {

        Scanner input = new Scanner(System.in);
        System.out.println("请输入表名，多个英文逗号分隔");
        String tableName = input.nextLine();

        inputTables = Arrays.asList(tableName.split(","));
        for (String inputTable : inputTables) {
            Thread.sleep(1000); // 休眠一秒，解决版本重复问题
            String fileData = "--\n" +
                    "-- Structure for " + inputTable + "\n" +
                    "--";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTime = sdf.format(new Date());
            String fileName = "src" + File.separator +"main" + File.separator + "resources" +
                    File.separator + "db" +  File.separator +
                    "migration" +  File.separator +
                    "V" + currentTime + "__create_table_" + inputTable + ".sql";
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(fileData.getBytes());
            fos.flush();
            fos.close();
        }

    }
}
