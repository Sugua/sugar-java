###Mysql

####Mysql 锁

- 全局锁

使用全局锁：
```sql
flush tables WITH READ LOCK 
```
释放全局锁
```sql
UNLOCK TABLES 
```
全局锁的使用场景全库备份，但是有缺点，业务会停。

备份数据库用mysqldump,在使用mysqlldump时加上``-singele-transaction``,就会用MVCC（多版本控制）创建Read View了，可重复度


- 表级锁

- 表锁
    
    - 元数据锁
    
    - 意向锁
    
    - AUTO-INC 锁
    
    当innodb_aotonic_mode=2时，并且binlog_format = row ,既能提升并发性，又不会出现数据一致性的问题了
    
- 行级锁

    -
    
    

####用什么命令分析加了什么锁？

```sql
select * from performance_schema.data_lock\G
```

如果lock_mode 为X，说明是next-key锁
如果lock_mode 为X，REC_NOT_GAP,说明是记录锁
如果lock_mode 为x，GAP，说明是间隙锁

A
    