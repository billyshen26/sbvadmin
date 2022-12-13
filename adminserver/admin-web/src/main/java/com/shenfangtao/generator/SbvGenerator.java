package com.shenfangtao.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.shenfangtao.mapper.PermissionMapper;
import com.shenfangtao.model.Permission;
import com.shenfangtao.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Notes: 用于生成低代码
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/7 15:53
 */
@SpringBootTest
public class SbvGenerator {

    @Autowired
    public PermissionMapper permissionMapper;

    private List<String> inputTables;
    @Test
    public void genCode() {
//        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
//                .globalConfig(builder -> builder.outputDir("/Users/billyshen/Documents/idea_workspace/gened"))
////                .strategyConfig(builder -> builder.addInclude("permission"))
//                .execute();
        /**
         * Notes:  1.生成低代码文件
         * Author: 涛声依旧 likeboat@163.com
         * Time: 2022/12/13 17:15
         **/
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
            .globalConfig(builder -> {
                builder.author("billy") // 设置作者
                    .disableOpenDir() //禁止打开输出目录
                    .outputDir("src/main/java/com/shenfangtao/generator/tempFiles"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.shenfangtao") // 设置父包名
                        .entity("model") //设置entity包名
                        .other("vue") // 前端页面生成放入的临时包
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src/main/java/com/shenfangtao/generator/tempFiles")); // 设置mapperXml生成路径
            })
            // 策略配置
            .strategyConfig((scanner, builder) ->  {
                inputTables = getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"));
                builder.addInclude(inputTables)
                    .controllerBuilder().enableRestStyle().enableHyphenStyle().superClass("com.shenfangtao.controller.BaseController")
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
        moveFiels();
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
            customFile.forEach((key, value) -> {
                VueFileEnum vueFile = VueFileEnum.valueOf(key);
                String fileName = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String currentTime = sdf.format(new Date());
                if (vueFile.getDir() == "view")
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + tableName + File.separator + entityName + vueFile.getFileName();
                else if (vueFile.getDir() == "sql")
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + "V"+currentTime+"__"+ entityName + vueFile.getFileName();
                else
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + entityName + vueFile.getFileName();
                this.outputFile(new File(fileName), objectMap, value);
            });



//            // 生成菜单 废弃  使用flyway生成
//            Permission permission = new Permission();
//            permission.setComponent("/sbvadmin/" + tableName + File.separator + entityName + "Index.vue");
//            permission.setName(tableInfo.getComment());
//            permission.setPath(tableName);
//            permission.setPid(2L);  // 默认加入到系统管理下面
//            permission.setRequestMethod("GET"); // 默认用列表页get请求
//            permission.setRequestUrl("/api/" + tableName + "s"); // 这里和controller中的配置要一致
//            permission.setType((byte) 1); // 默认为菜单类型
//            permission.setStatus((byte) 1); // 默认启用
//            permission.setTitle("routes." + entityName + "." + tableName); // 使用i18n配置标题名称
//            permissionService.save(permission);
        }

        /**
         * Notes:  自定义map，主要为了解决flyway sql中主键id的获取
         * @param: [config, tableInfo]
         * @return: java.util.Map<java.lang.String,java.lang.Object>
         * Author: 涛声依旧 likeboat@163.com
         * Time: 2022/12/12 21:52
         **/
        @Override
        public Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
            // 获取实体类名字
            String entityName = tableInfo.getEntityName();
            // 获取object map
            Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);

            // 创建wapper，查询当前permission表中主键最大值
            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
            wrapper.select("max(id) as id");
            Permission permission = permissionMapper.selectOne(wrapper);
            System.out.println("maxId=" + permission.getId());

            // 自定义permission自增主键
            objectMap.put("permissionId", permission.getId() + 1);
            return objectMap;
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
    public void moveFiels() {
        InputStreamReader stdISR = null;
        InputStreamReader errISR = null;
        Process process = null;
        String command = "src/main/java/com/shenfangtao/generator/move_files.sh";
        try {
            process = Runtime.getRuntime().exec(command);
            int exitValue = process.waitFor();

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
}
