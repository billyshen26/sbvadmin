package com.shenfangtao.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Notes: 用于生成低代码
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/7 15:53
 */
public class SbvGenerator {

    private List<String> inputTables;
    @Test
    public void testSimple() {
//        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
//                .globalConfig(builder -> builder.outputDir("/Users/billyshen/Documents/idea_workspace/gened"))
////                .strategyConfig(builder -> builder.addInclude("permission"))
//                .execute();
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
            .globalConfig(builder -> {
                builder.author("billy") // 设置作者
//                    .enableSwagger() // 开启 swagger 模式
//                    .outputDir("/Users/billyshen/Documents/idea_workspace/gened"); // 指定输出目录
//                    .disableOpenDir() //禁止打开输出目录
                    .outputDir("src/main/java/com/shenfangtao/generator/tempFiles"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.shenfangtao") // 设置父包名
                        .entity("model") //设置entity包名
//                    .moduleName("system") // 设置父包模块名
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
                builder.customFile(customFile);
            })
            .templateEngine(new EnhanceFreemarkerTemplateEngine())
//            .strategyConfig(builder -> {
//                builder.entityBuilder().enableLombok()
//                        .enableFileOverride();
//            })
            .execute();

        moveFiels(); //把自动生成的文件放到对应的位置
    }

    /**
     * Notes:  前端代码生成，自定义
     * @param:
     * @return:
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/11/27 21:32
     **/
    public final class EnhanceFreemarkerTemplateEngine extends VelocityTemplateEngine {

        @Override
        protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
            String entityName = tableInfo.getEntityName();
            String name = tableInfo.getName();
            String otherPath = this.getPathInfo(OutputFile.other);
            customFile.forEach((key, value) -> {
                VueFileEnum vueFile = VueFileEnum.valueOf(key);
                String fileName = null;
                if (vueFile.getDir() == "view")
                    fileName = otherPath + File.separator + vueFile.getDir() + File.separator + name + File.separator + entityName + vueFile.getFileName();
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
