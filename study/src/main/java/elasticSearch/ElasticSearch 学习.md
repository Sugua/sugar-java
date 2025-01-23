### ElasticSearch 学习



ElasticSearch和关系型数据库的区别大致：

`Relational DB ‐> Databases ‐> Tables ‐> Rows ‐> Columns`

`Elasticsearch ‐> Indices ‐> Types ‐> Documents ‐> Fields`



#### **创建索引库index并添加映射mapping------PUT**

- URL

  `localhost:9200/{index_name}`

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











#### **删除索引库index------DELETE**

- URL

  `localhost:9200/{index_name}`

- 请求方式

​		`DELETE`

- 入参实例：

```json
first_index
```

参数返回：

```json
{
    "acknowledged": true
}
```









#### **创建文档document（向索引库中添加内容）---POST**

- URL

  `localhost:9200/{index_name}/{docment_name}}/{_id}`

  请求方式

​		`POST`

- 入参实例：

```json
{
    "id": 1,
    "title": "ElasticSearch是一个基于Lucene的搜索服务器",
    "content": "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。"
}
```

参数返回：

```json
{
    "_index": "hello",
    "_id": "1",
    "_version": 1,
    "result": "created",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 0,
    "_primary_term": 1
}
```







#### **修改文档document（向索引库中添加内容）---POST**

- URL

  `localhost:9200/{index_name}/{docment_name}}/{_id}`

- 请求方式

​		`POST`

- 入参实例：

```json
{
    "id": 1,
    "title": "修改ElasticSearch是一个基于Lucene的搜索服务器",
    "content": "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。"
}
```

参数返回：

```json
{
    "_index": "hello",
    "_id": "1",
    "_version": 2,
    "result": "updated",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 2,
    "_primary_term": 1
}
```











#### **根据id查询文档document---GET**

- URL

  `localhost:9200/{index_name}/{docment_name}}/{_id}`

- 请求方式

​		`GET`

- 入参实例：

```json

```

参数返回：

```json
{
    "_index": "hello",
    "_id": "1",
    "_version": 2,
    "_seq_no": 2,
    "_primary_term": 1,
    "found": true,
    "_source": {
        "id": 1,
        "title": "修改---ElasticSearch是一个基于Lucene的搜索服务器",
        "content": "修改---它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。"
    }
}
```









#### **删除文档document---DELETE**

- URL

  `localhost:9200/{index_name}/{docment_name}}/{_id}`

- 请求方式

​		`DELETE`

- 入参实例：

```json

```

参数返回：

```json
{
    "_index": "hello",
    "_id": "1",
    "_version": 3,
    "result": "deleted",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 3,
    "_primary_term": 1
}
```




_update 更新命令


###关于文档的基本操作
-  基本的操作


	- 创建文档 PUT
	
		- ==PUT== /{index}/{typename}/{id}
	-  获取 ==GET==

		- ==GET== /{index}/{typename}/_search
	-  更新 POST _update 推荐使用_update，_update可以精确去修改莫哥字段，post修改整个文档，没有赋值的字段值空。
-  复杂的操作

	query{match}
	sort（排序）
	from/size(分页)
	bool/must 多条件查询and
	bool/should or
	must_not !=
	filter 进行数据的过滤
	
- 精确查询

term岔村是直接通过倒排索引指定的词条进行精确查询

**关于分词：**

	- term，直接查询精确的
	- match，会使用分词器解析（先分析文档，然后通过分析的文档进行查询）


**两个类型**

- keyword字段不会分词器分析
- text字段会被分词器分析

#### 高亮查询

highlight: pre_tags post_tag 


#### 具体的API


- 创建索引
- 获取索引
- 删除索引 
 



