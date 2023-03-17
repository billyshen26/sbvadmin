#!/bin/bash
echo '*************************开始删除后端文件*************************'
rm src/main/java/com/${1%.*}/controller/${3%.*}Controller.java
rm ../admin-model/src/main/java/com/${1%.*}/model/${3%.*}.java
rm ../admin-mapper/src/main/java/com/${1%.*}/mapper/${3%.*}Mapper.java
rm ../admin-mapper/src/main/resources/mapper/${1%.*}/${3%.*}Mapper.xml
rm ../admin-service/src/main/java/com/${1%.*}/service/I${3%.*}Service.java
rm ../admin-service/src/main/java/com/${1%.*}/service/impl/${3%.*}ServiceImpl.java
rm src/main/resources/db/migration/*__${3%.*}Menu.sql
echo '*************************开始删除前端文件*************************'
rm ../../../${2%.*}/src/api/${1%.*}/${3%.*}.ts
rm ../../../${2%.*}/src/api/${1%.*}/model/${3%.*}Model.ts
rm -rf ../../../${2%.*}/src/views/${1%.*}/${4%.*}
rm ../../../${2%.*}/src/locales/lang/en/routes/${3%.*}.ts
rm ../../../${2%.*}/src/locales/lang/zh-CN/routes/${3%.*}.ts
echo '*************************删除完成************************'
