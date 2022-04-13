# 结合kotlin与jetpack进行项目实现

![Image](https://user-images.githubusercontent.com/72546851/163128852-fd253bc1-bad7-457b-9af4-cab7940c4840.png)

### Paging3
- 加载数据的流程

![Image](https://user-images.githubusercontent.com/72546851/163130148-cad4c6f6-9bc0-4e28-bd12-82a114832237.png)

### 获取LoadState
- 加载的状态信息被存放与一个Flow当中

![Image](https://user-images.githubusercontent.com/72546851/163130527-9c25db34-4847-4f63-9cc2-298b068586e5.png)

### PageConfig
- pageSize, 每页显示的数据的大小
- prefetchDistance, 预刷新的距离, 距离最后一个item多远时加载数据, 默认为pageSize
- initialLoadSize, 初始化加载数量, 默认为pageSize*3

### Hilt
- 负责托管对象与对象之间的注入关系

![Image](https://user-images.githubusercontent.com/72546851/163130722-20638157-975e-40c1-8488-b855939659a0.png)

#### 常用注解
- @HiltAndroidApp: 触发Hilt的代码生成
- @AndroidEntryPoint: 创建一个依赖容器, 该容器遵循Android类的生命周期
- @Module: 告诉Hlit如何提供不同类型的实例
- @InstallIn: Install用来告诉Hilt这个模块会被安装到哪个组件上
- @Provides: 告诉Hilt如何获得具体实例
- @Singleton: 单例
- @ViewModelInject: 通过构造函数, 给ViewModel注入实例

### Paging3架构
![Image](https://user-images.githubusercontent.com/72546851/163131179-805bafa6-4539-446a-a459-e28b78989d8c.png)

### Room支持
- 如果使用的似乎room, 从2.3.0-alpha 开始, 它将默认为你实现PagingSource. 在定义Dao接口的Query语句时, 返回类型要使用PagingSource类型, 同时不需要在Query里指定页数和每页展示数量, 页数由PagingSource来控制, 每页数量在PagingConfig中定义.

### RemoteMediator
- 第一步: 判断LoadType
- 第二步: 请求网络分页数据
- 第三步: 插入数据库

### Data Mapper
- CarBrandEntity与CarBrandItemModel互相转换

![Image](https://user-images.githubusercontent.com/72546851/163131448-dc6c1260-3bd5-44ca-a19a-6a450ad86f89.png)

- 使用Data Mapper 分离数据源的Model和页面显示的Model, 不要因为数据源的增加, 修改或者删除, 导致上层页面也要跟着一起修改

![Image](https://user-images.githubusercontent.com/72546851/163131520-d2f5d075-7647-4f28-a94d-6e835796aedb.png)

### Coil
- 性能优秀
- 体积较小: 其包体积与Picasso相当, 显著低于Glide和Fresco, 仅仅只有1500个方法, 但是在功能上却不输于其他同类库
- 简单易用: 配合kotlin扩展方法等语法优势, api简单易用
- 技术先进: 基于coroutine, okHttp, Okio, AndroidX等先端技术开发, 确保了技术上的先进性
- 丰富功能: 缓存管理(MemCache, DishCache) , 动态采样 (Dynamic image sampling), 加载中暂停/终止等功能有助于提高图片加载效率

### LoadType
- LoadType 是一个枚举类, 里面定义了三个值, 如下所示:

![Image](https://user-images.githubusercontent.com/72546851/163131734-14e106d3-ce5b-47f8-ad5d-7baaae6fdea2.png)

### PagingState
- pages: List<Page<Key, Value>>返回的上一页的数据, 主要用来获取上一页最后一条数据作为下一页的开始位置
- config: PagingCofig返回的初始化设置的PagingConfig包含了pageSize, prefetchDistance, initLoadSize等等

### MediatorResult
- 请求出现错误, 返回MediatorResult.Error(e)
- 请求成功且有数据, 返回MediatorResult.Success(endOfPaginationReached = true)
- 请求成功但是没有数据, 返回MediatorResult.Success(endOfPaginationReached = false)

### App Startup
- App Startup 是Android Jetpack最新成员, 提供了在App启动时初始化组件简单, 高效的方法, 无论是library开发还是App开发人员都可以使用App Startup显示的设置初始化顺序

![Image](https://user-images.githubusercontent.com/72546851/163132008-5dd4d729-9d8c-4344-a96f-cd4b6c1b2dee.png)


