#!/usr/bin/env bash
#服务太拉了，没法云端构建，算了还是把dist放入git管理，然后本地构建后再提交吧
#echo 'build frontend'
#cd sbvadmin-vben
#yarn  安装依赖，只需执行一次
#yarn build

#这种方式需要把dist加入前端的版本控制，同时要加入sbvadmin-vben子模块
#echo 'update dist'
#cd adminserver/admin-web/src/main/resources/static
#rm -fr *
#cd ../../../../../../sbvadmin-vben
#cp -a dist/* ../adminserver/admin-web/src/main/resources/static/

# 暂时就在本地的提交脚本里写吧 这个方案也有问题，sbvadmin里面的static文件要加入git

#重新采用云端构建，这次把前端sbvadmin-vben加入到jenkins中
echo 'build frontend'
pwd
cd ../sbvadmin-vben
pwd
#yarn  安装依赖，只需执行一次
yarn build
echo 'update dist'
rm -fr ../sbvadmin/adminserver/admin-web/src/main/resources/static/*
cp -a dist/* ../sbvadmin/adminserver/admin-web/src/main/resources/static/





