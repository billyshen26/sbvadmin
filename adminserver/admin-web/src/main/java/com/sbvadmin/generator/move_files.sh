#!/bin/bash
echo '*************************开始移动后端文件*************************'
# 移动自动生成的文件到对应的文件夹，如果已经存在则不覆盖，防止辛苦写的代码被干掉了
# 这里低代码生产的代码放到了admin-model中的src/main/java/com/sbvadmin/generator/tempFiles
#mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/controller/*.java ../admin-web/src/main/java/com/sbvadmin/controller  废弃，用下面这条自定义的
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/controller/* ../admin-web/src/main/java/com/sbvadmin/controller
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/model/*.java ../admin-model/src/main/java/com/sbvadmin/model
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/mapper/*.java ../admin-mapper/src/main/java/com/sbvadmin/mapper
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/service/impl/*.java ../admin-service/src/main/java/com/sbvadmin/service/impl
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/service/*.java ../admin-service/src/main/java/com/sbvadmin/service
mv -n src/main/java/com/sbvadmin/generator/tempFiles/*.xml ../admin-mapper/src/main/resources/mapper
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/sql/* ../admin-web/src/main/resources/db/migration
echo '*************************开始移动前端文件*************************'
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/api/*.ts ../../../sbvadmin-vben/src/api/sbvadmin
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/model/*.ts ../../../sbvadmin-vben/src/api/sbvadmin/model
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/view/* ../../../sbvadmin-vben/src/views/sbvadmin
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/i18nEN/* ../../../sbvadmin-vben/src/locales/lang/en/routes
mv -n src/main/java/com/sbvadmin/generator/tempFiles/com/sbvadmin/vue/i18nZH/* ../../../sbvadmin-vben/src/locales/lang/zh-CN/routes

echo '*************************删除自动生成的文件************************'
rm -fr src/main/java/com/sbvadmin/generator/tempFiles
echo '*************************移动完成*********************************'