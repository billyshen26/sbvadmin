package com.shenfangtao.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/7 15:53
 */
public class SbvGenerator {
    @Test
    public void testSimple() {
//        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
//                .globalConfig(builder -> builder.outputDir("/Users/billyshen/Documents/idea_workspace/gened"))
////                .strategyConfig(builder -> builder.addInclude("permission"))
//                .execute();

        FastAutoGenerator.create("jdbc:mysql:///sbvadmin", "sbvadminuser", "Sbvadmin")
            .globalConfig(builder -> {
                builder.author("billy") // 设置作者
//                    .enableSwagger() // 开启 swagger 模式
                    .outputDir("/Users/billyshen/Documents/idea_workspace/gened"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.shenfangtao") // 设置父包名
                        .entity("model") //设置entity包名
//                    .moduleName("system") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/billyshen/Documents/idea_workspace/gened")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.entityBuilder().enableLombok()
                        .enableFileOverride();
            })
            .execute();
    }
}
