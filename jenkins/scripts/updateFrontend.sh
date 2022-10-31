#!/usr/bin/env bash
#服务太拉了，没法云端构建，算了还是把dist放入git管理，然后本地构建后再提交吧
#echo 'build frontend'
#cd sbvadmin-vben
#yarn  安装依赖，只需执行一次
#yarn build
echo 'update dist'
cd adminserver/admin-web/src/main/resources/static
rm -fr *
cp ../../../../../
pwd

