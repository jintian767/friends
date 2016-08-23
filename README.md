# friends

该项目将做开源发布
前期计划如下：
1，使用分布式开发，采用web发布模式，后台服务调用采用rpc
2，mvc采用springmvc来做控制，spring做容器管理，mybatis做orm层，集成spring-data
3，数据存储，对于事物要求比较高的数据接口，我们采用mysql来存储，并且采用分库分表方式实现大数据量的存储
   对于事物要求不高的数据，前期规划使用mongodb来存储
4，为做分布式的session管理和缓存数据处理，使用redis来做分布式的缓存处理
5，缓存的分层机制，web容器级的缓存使用ehcache来实现，缓存的层级是ehcache-》redis-》db
6，和主业务绑定关系不密切的功能，采用消息队列的方式进行解耦操作，前期使用activemq来实现
7，增加全文检索的功能，前期使用elasticsearch的集群来实现，客户端采用原生的java，不集成spring-data，减少版本之间的依赖


项目采用maven来做资源管理和项目之间的关系依赖。

规范：
1，springmvc采用restful的方式
2，分布式系统间用户的访问采用token的方式来实现session的共享
3，暂定模块：用户，博客，商品，聊天，支付，翻译，全文检索


项目核心模块core，所有项目以friends开头进行管理。包的根目录是com.wsx.friends
