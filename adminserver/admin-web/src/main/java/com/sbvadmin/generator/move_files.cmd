echo '*************************开始移动后端文件*************************'
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\controller\* src\main\java\com\sbvadmin\controller
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\model\*.java ..\admin-model\src\main\java\com\sbvadmin\model
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\mapper\*.java ..\admin-mapper\src\main\java\com\sbvadmin\mapper
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\service\impl\*.java ..\admin-service\src\main\java\com\sbvadmin\service\impl
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\service\*.java ..\admin-service\src\main\java\com\sbvadmin\service
echo n|xcopy /-y src\main\java\com\sbvadmin\generator\tempFiles\*.xml ..\admin-mapper\src\main\resources\mapper
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\sql\* src\main\resources\db\migration
echo '*************************开始移动前端文件*************************'
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\api\*.ts ..\..\..\sbvadmin-vben\src\api\sbvadmin
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\model\*.ts ..\..\..\sbvadmin-vben\src\api\sbvadmin\model
echo n|xcopy /-y /e src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\view\* ..\..\..\sbvadmin-vben\src\views\sbvadmin
echo n|xcopy /-y src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\i18nEN\*.ts ..\..\..\sbvadmin-vben\src\locales\lang\en\routes
echo n|xcopy /-y src\main\java\com\sbvadmin\generator\tempFiles\com\sbvadmin\vue\i18nZH\*.ts ..\..\..\sbvadmin-vben\src\locales\lang\zh-CN\routes
echo '*************************删除自动生成的文件************************'
rd /s /q .\src\main\java\com\sbvadmin\generator\tempFiles