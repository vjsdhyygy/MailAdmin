# MailAdmin - 汉化中文版

尚未启动，请期待

请根据 `https://thomas-leister.de/en/mailserver-debian-stretch/` 的邮件服务器安装手册安装邮件数据库。

原版 MailAdmin 位于 `https://github.com/Halbritter/MailAdmin`。

This program requires a MySQL user with r/w access to your database. Additionally, you have to make sure that you are allowed to connect to your DB from your IP and that your DB accepts external connections
If you are using MySQL then you might want to check your  MySQL server configuration. You can most likely find it at /etc/mysql/my.cf
MariaDB stores this file in a slightly different place. Usually  /etc/mysql/mariadb.conf.d/50-server.cnf

Since version 1.5 this tool will automatically save your connections (without the password) in an SQLite DB on your local hard disk. You can find this file at ~/.mailadmin.db.

You will also need Java 7 or higer to run this programm

本程序需要一个拥有 读写 权限的MYSQL/MariaDB账号 来管理您的数据库，此外，您必须确保允许您从IP连接到数据库，并且您的数据库接受外部连接，此外您的数据库需要支持SSL链接。 （汉化过程中有编写不需要SSL链接的版本）

从版本1.5开始，此工具将自动将您的连接（无密码）保存在本地硬盘上的SQLite DB中。你可以在 ~/.mailadmin.db 找到这个文件。

您还需要Java 7或更高版本才能运行此程序。
