
<div align="center"> <a href="https://github.com/billyshen26/sbvadmin"> <img alt="Sbvadmin Logo" width="200" height="200" src="https://github.com/billyshen26/sbvadmin-vben/blob/main/public/resource/img/logo.png"> </a> <br> <br>

[![license](https://img.shields.io/github/license/anncwb/vue-vben-admin.svg)](LICENSE)

<h1>Sbvadmin</h1>
</div>

**中文** | [English](./README.en-US.md)

## 简介
一个基于spring boot的后端管理系统，使用了`springboot`和`Vben(vue3)`进行搭建，开箱即用。
- 演示地址：[http://118.31.68.110:8081/index.html](http://118.31.68.110:8081/index.html)
- 账号：root
- 密码：123

## 特性
- **最新技术栈** 使用springboot2.7 和 vue3
- **配套学习教程** 配备了学习教程，和学习视频，并会持续更新
- **低代码开发** 配备了一个简单的低代码开发功能，可以完成快速开发增删改查

## 学习教程
本系统配备了一套手把手的[学习教程](https://blog.csdn.net/F_angT/article/details/125717125)，需要学习的可以参考下载对应的分支进行学习。

## 低代码开发
本系统为了提高开发效率，写了一个简单低代码开发脚本，具体使用步骤可以参考[这篇文档](https://blog.csdn.net/F_angT/article/details/127740239)，或者[这个视频](https://www.bilibili.com/video/BV1F3411Z7BK)。

## 前端代码仓库
[sbvadmin-vben](https://github.com/billyshen26/sbvadmin-vben)

## 更新日志
[CHANGELOG](./CHANGELOG.zh_CN.md)

## 知识点
### 获取springboot应用的版本号
在application.properties中加入
`application.version = @project.version@`
这里虽然我们用的是.properties,但不能用${}，[参考这里](https://blog.csdn.net/qq_34730511/article/details/119380836)
