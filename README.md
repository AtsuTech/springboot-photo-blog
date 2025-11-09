プロジェクトの起動
```
./gradlew bootRun
```


# Security パスワード
```
username: user
password: 103445b5-88c5-474d-8d8e-92952020e8ea
```

username:dev
password:test0124


## Mysql コマンド
Last login: Sat Aug 30 21:38:10 on ttys037
```
docker exec -it 89c63cb5ab021dded6fdeb27398d45e87a39cb7a0f4183a2a2c8751c0913bb71 bash
```
```
mysql -u root -p mysql
```
Enter password: 
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
bash-5.1# mysql -u root -p mysql
Enter password: 
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 20
Server version: 8.0.42 MySQL Community Server - GPL

Copyright (c) 2000, 2025, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+----------------------+
| Database             |
+----------------------+
| information_schema   |
| mysql                |
| performance_schema   |
| spring_photo_blog_db |
| sys                  |
+----------------------+
5 rows in set (0.01 sec)

mysql> show tables from spring_photo_blog_db;
+--------------------------------+
| Tables_in_spring_photo_blog_db |
+--------------------------------+
| post                           |
| post_seq                       |
| user                           |
| user_seq                       |
+--------------------------------+
4 rows in set (0.01 sec)


mysql> show columns from post from spring_photo_blog_db;
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| id      | int          | NO   | PRI | NULL    |       |
| body    | varchar(255) | YES  |     | NULL    |       |
| image   | varchar(255) | YES  |     | NULL    |       |
| title   | varchar(255) | YES  |     | NULL    |       |
| user    | int          | YES  | UNI | NULL    |       |
| user_id | int          | YES  | MUL | NULL    |       |
+---------+--------------+------+-----+---------+-------+
6 rows in set (0.02 sec)


mysql> Use spring_photo_blog_db;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> select * from post;
+----+--------+-------+-------+------+---------+
| id | body   | image | title | user | user_id |
+----+--------+-------+-------+------+---------+
|  1 | wwwwww | NULL  | wwwww | NULL |    NULL |
|  2 | wwwwww | NULL  | ????? | NULL |    NULL |
+----+--------+-------+-------+------+---------+
2 rows in set (0.00 sec)





# AuthenticationPrincipalで不具合
UserDetailServiceImplのuserbuilderでこれを設定追加？
https://spring.pleiades.io/spring-security/reference/api/java/org/springframework/security/core/userdetails/User.UserBuilder.html

## UserDeatlを拡張してUserを作る感じにする？？
https://www.docswell.com/s/MasatoshiTada/KGVY9K-spring-security-intro#p38




#　共通レイアウト
https://qiita.com/TeruhisaShibuya/items/aa4de95710fa88985237