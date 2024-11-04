### ElasticSearch 学习



ElasticSearch和关系型数据库的区别大致：

`Relational DB ‐> Databases ‐> Tables ‐> Rows ‐> Columns`

`Elasticsearch ‐> Indices ‐> Types ‐> Documents ‐> Fields`



#### **创建索引库index并添加映射mapping------PUT**

- URL

  `localhost:9200/hello`

- 请求方式

​		`PUT`

- 入参实例：

```json
{
	"mappings": {
		"properties": {
			"id": {
				"type": "long",
				"store": true,
				"index": "true"
			},
			"title": {
				"type": "text",
				"store": true,
				"index": "true",
				"analyzer": "standard"
			},
			"content": {
				"type": "text",
				"store": true,
				"index": "true",
				"analyzer": "standard"
			}
		}
	}
}
```

参数返回：

```json
{
    "acknowledged": true,
    "shards_acknowledged": true,
    "index": "hello"
}
```



