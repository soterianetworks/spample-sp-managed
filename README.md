# 概述

本项目演示了如何基于 [SPASE](https://github.com/soterianetworks/spase) 项目开发并集成一个应用到 CIP(Cloud Integration Platform). 

# 摘要

本文主要包括以下内容

* 模块和目录结构
* 包命名
* 类命名
* 业务模型和DTO
* 业务校验
* 异常处理
* 国际化
* REST URL 风格
* REST Response 格式

# 正文

## 模块和目录结构

首先,我们采用Gradle作为构建工具,因此目录结构上需要遵守Gradle的目录机构规范. 

* settings.gradle 中定义整个结构
* core 为业务domain和业务接口定义,所有的业务接口代码都在该模块下,不能独立运行.
* impl 基于core定义的接口实现业务逻辑.
* rest 暴露Restful api，并提供测试代码，view和domain bean之间的转换 
* server 基于service打包的可运行可部署的子项目,最终交付的服务.
* apidoc 

## 包命名

依据：

* 以技术进行分类
    * 领域模型 domain
    * 持久层 repository
    * 业务层 service
    * WEB层 rest
* 接口和实现分离

### 顶层包 root package

> com.soterianetworks.sp.sample

### 模块化

> `com.soterianetworks.sp.sample.module`

- 示例：`com.soterianetworks.sp.sample.module.product`

### 子包 sub package

### Core & Service(core)
> `com.soterianetworks.sp.sample`

package | sub-package | 描述
---|---|---
domain |  | 领域模型
 | | model | 业务实体+对应的持久层声明,对于本项目为JPA
 | | request | 业务请求抽象, 包括查询和保存两种类型, 全部为接口
module | | 业务服务层接口定义, 其输入为domain.request下定义的接口,其输出为domain.model下定义的业务实体 
| |${moduleName}.exception | 业务异常 | 
| |${moduleName}.service | 业务接口定义 | 


### Service Impl（impl）

> `com.soterianetworks.sp.sample.module.${moduleName}`

package | sub-package | 描述
---|---|---
repository | | 持久层,对于本项目为Spring Data声明,全部为接口
 | | custom | 持久层扩展,遵循Spring Data建议的命名模式,全部为接口
 | | custom.impl | 持久层扩展的实现, 实现的是custom包下定义的接口
service | impl | 业务服务层实现, 依赖于domain和repository
validator |  | 具体业务的校验器实现

### Rest api (rest)

> `com.soterianetworks.sp.sample.rest`

package | sub-package | 描述
---|---|---
 | controller | | 基于Spring MVC的REST Controller的定义
 | support | | 在REST API和业务Service之间的适配层
 | support | impl | support的实现,主要负责来自WEB端请求和Service层要求的参数之间的格式转化
 | support | mock | support的mock,主要是实现和Service层的解耦,这样在启动API Doc服务的时候不需要依赖于业务服务实现,也就不需要依赖于更多的底层资源
 | support | param | 实现了domain.request接口定义,实现了WEB请求和业务请求之间的格式转化
 | support | view | 将业务服务返回的domain.model转化为view视图,将多层嵌套关系转化为平板结构,同时针对WEB进行数据精简,减少最终Response的大小.

## 类命名

依据：

* 以主模型为命名前缀
* 按技术进行分类，体现在后缀名上
    * 例如 Service 后缀表示服务类
    * 例如 Repository 后缀表示为持久类
    * 例如 Controller 后缀表示为Rest API
    * 例如 Validator 后缀表示为校验器类
    * 例如 Exception 后缀表示为异常类
* 后缀为Impl表示为实现类
* 后缀为Test表示为测试类

### 示例 - 模具

基于以下示例进行说明

业务实体

* 模具 Mold
* 模具生产 Mold Production
* 模具维修 Mold Maintenance
* 模具架 Rack

关系

* 一个模具可以有多条生产记录 (1:N)
* 一个模具可以有多条维修记录 (1:N)
* 一个模具只能放在一个模具架上 (1:1)

操作

* 基本的CRUD
* 模具的生产记录
* 模具的维修记录
* 将模具放到模具架
* 将模具从模具架取下  

类别 | package | Class | 备注
---|---|---|---
业务模型类 | com.soterianetworks.sp.sample.domain.model | Mold
|  | com.soterianetworks.sp.sample.domain.request | MoldSearchRequest
|  | | MoldSaveRequest
持久层 | com.soterianetworks.sp.sample.module.mold.repository | MoldRepository
| |  com.soterianetworks.sp.sample.module.mold.repository.custom| MoldCustomRepository
| |  com.soterianetworks.sp.sample.module.mold.repository.custom.impl| MoldRepositoryImpl
业务服务 | com.soterianetworks.sp.sample.module.mold.service | MoldService
 | | com.soterianetworks.sp.sample.module.mold.service.impl| MoldServiceImpl
MVC | com.soterianetworks.sp.sample.rest.controller | MoldRestController
 | | com.soterianetworks.sp.sample.rest.support | MoldRestSupport
 | | com.soterianetworks.sp.sample.rest.support.view | MoldSimpleView
 | | com.soterianetworks.sp.sample.rest.support.param | MoldSaveParam
 | | com.soterianetworks.sp.sample.rest.support.impl | MoldRestSupportImpl
 | | com.soterianetworks.sp.sample.rest.support.mock | MoldRestSupportImpl
校验器 | com.soterianetworks.sp.sample.module.mold.validator | MoldValidator
异常 | com.soterianetworks.sp.sample.module.mold.exception | MoldException
 | | com.soterianetworks.sp.sample.module.mold.exception | MoldExceptionCode
 | | com.soterianetworks.sp.sample.module.mold.exception | MoldNotFoundException
 | | com.soterianetworks.sp.sample.module.mold.exception | MoldValidationException
测试 |  com.soterianetworks.sp.sample.test.controller | MoldControllerTest
 

## 业务模型和应用模型

依据:

* 业务模型和应用模型分离
    * 核心服务使用业务模型
    * 应用服务使用应用模型
* 核心模型和用户请求分离
    * 核心模型 - Domain Model
    * 用户请求 - Request & Response
* 单向依赖 - 核心服务和应用服务解耦
* 应用层模型数据平板化
* 应用层模型数据量按需设计 - 明细和摘要数据分开

示例:

> 以模具Mold为例

一级分类 | 二级分类 | 定义 | 备注
---|---|---|---
业务层 | 领域模型 |  Mold | 
| | 业务保存请求 | MoldSaveRequest | 定义为接口，MoldService不关心实现类
| | 业务查询请求 | MoldSearchRequest | 定义为接口，MoldRepository不关心实现类
应用层  |  业务保存参数 | MoldSaveParam | 实现了MoldSaveRequest
| | 业务查询参数 | MoldSearchParam | 实现了MoldSearchRequest
| | 业务摘要视图 | MoldSummaryView | 用于列表
| | 业务详情视图 | MoldDetailView | 用于详情页

### 业务层和应用层格式转化

目标：

* 在MoldSummaryView和MoldDetailView中定义静态from方法，接受参数为领域模型Mold
* 静态from方法支持回调的方式进行扩展，满足定制化要求

定义示例：

> 以模具Mold为例

```java 
public class MoldDetailView {
    
    public static MoldDetailView from(Mold model) {
        ...
    }

    public static MoldDetailView from(Mold model, Consumer<MoldView> consumer) {
        ...
    }

} 
```

调用示例：

> 直接转换

```java
    return MoldDetailView.from(moldInstance);
```

> 列表中转换

```java
    return moldList.stream().map(MoldDetailView::from).collect(Collectors.toList());
```

> 定制化转换

```java
    return moldList.stream()
                            .map(mold -> MoldDetailView.from(mold, view -> {
                                //do customize
                            })
                            .collect(Collectors.toList());
```

## 国际化

基于Spring i18n支持

> * server/src/main/resources/locale/messages.properties
> * server/src/main/resources/locale/messages_zh_CN.properties

关于文件中常量名的命名规则见下一章

## 业务校验

* 业务校验定义在`com.soterianetworks.sp.sample.module.{moduleName}.validator`包中

* 以产品Product为例：

    `com.soterianetworks.sp.sample.module.product.validator.ProductValidator.java`

* 校验方法命名规则：
 
    * Product existsAndReturn(String id, other parameters...) 
    * void notExistsAndThrow(String id, other parameters...)
    * Boolean duplicate{FiledName}AndThrow(String code, other parameters)

* 以产品（Product）为例：

```java
public Product existsAndReturn(@NotNull String id, String auditAction) {
        Product bean = productRepository.findOne(id);
        if (bean == null) {
            if (StringUtils.isEmpty(auditAction)) {
                auditAction = AuditAction.VIEWING;
            }
            AuditLogger.logFail(auditAction, AuditEntity.PRODUCT, id);
            throw new ProductNotFoundException(ProductExceptionCode.PRODUCT_NOT_FOUND, id);
        }
        return bean;
    }

public void notExistsAndThrow(@NotNull String id, String auditAction) {
    boolean exists = productRepository.exists(id);
    if (!exists) {
        if (StringUtils.isEmpty(auditAction)) {
            auditAction = AuditAction.VIEWING;
        }
        AuditLogger.logFail(auditAction, AuditEntity.PRODUCT, id);
        throw new ProductNotFoundException(ProductExceptionCode.PRODUCT_NOT_FOUND, id);
    }

public void duplicateCodeAndThrow(@NotNull String code) {
    boolean exists = productRepository.existsByCode(code);
    if (exists) {
        AuditLogger.logFail(AuditAction.CREATING, AuditEntity.PRODUCT, code);
        throw new ProductValidationException(ProductExceptionCode.PRODUCT_CODE_CONFLICT, code);
    }
}

public void duplicateCodeAndThrow(@NotNull String code, @NotNull String id) {
    boolean exists = productRepository.existsByCodeAndIdNotLike(code, id);
    if (exists) {
        AuditLogger.logFail(AuditAction.UPDATING, AuditEntity.PRODUCT, code);
        throw new ProductValidationException(ProductExceptionCode.PRODUCT_CODE_CONFLICT, code, id);
    }
}
```


## 异常处理

分为两类

* 业务异常，通过抛出异常触发
* 校验异常，基于javax validation框架

下面分别对这两类异常的定义以及命名进行描述

### 业务异常

#### 异常类定义

##### 异常类命名规则：

以模块名开头 + 异常类型 + Exception

现有固定的异常类型分为以下几种：

 1. not found
 2. validation
 3. general

以Product模块为例：

异常类型 | 异常类定义
---|---
general | ProductException
not found | ProductNotFoundException
validation | ProductValidationException


##### 异常类状态码定义规则：

异常类异常状态码采用标准的
```
org.springframework.http.HttpStatus
```
采用注解的方式定义在异常类上，例如：
```
@ResponseStatus(HttpStatus.NOT_FOUND)
```

业务异常与状态码对应关系，以Product模块为例：

异常类型 | 状态码
---|---
ProductException           | HttpStatus.INTERNAL_SERVER_ERROR
ProductNotFoundException   | HttpStatus.NOT_FOUND
ProductValidationException | HttpStatus.BAD_REQUEST

异常定义示例：
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ApplicationException {

    public ProductNotFoundException(ProductExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ProductNotFoundException(ProductExceptionCode exceptionCode, Object... arguments) {
        super(exceptionCode, arguments);
    }

    public ProductNotFoundException(ProductExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    public ProductNotFoundException(ProductExceptionCode exceptionCode, Throwable cause, Object... arguments) {
        super(exceptionCode, cause, arguments);
    }
}
```

#### 异常常量定义

所有业务异常的常量定义在
```
com.soterianetworks.sp.sample.module.{moduleName}.exception.{moduleName}ExceptionCode
```
要求实现接口：
```
com.soterianetworks.spase.exception.ExceptionCode
```

以Product模块为例：

* 异常常量定义：com.soterianetworks.sp.sample.module.product.exception.ProductExceptionCode

```java
public enum ProductExceptionCode implements ExceptionCode {

    PRODUCT_NOT_FOUND,
    PRODUCT_ALREADY_EXISTED
    
}

```

异常常量命名规则：

* 所有字符大写
* 下划线`_`作为单词之间的分隔符
* 以各模块大写作为前缀
* 常用的格式参考
    * XXXX_NOT_FOUND
    * XXXX_ALREADY_EXISTED
    * XXXX_DOWHAT_NOT_ALLOWED
* 其他的根据实际业务需要进行定义

示例：

```
    PRODUCT_NOT_FOUND,
    PRODUCT_DELETE_NOT_ALLOWED,
    PRODUCT_CHANGE_STEP_NOT_ALLOWED
```

> 所有的常量在i18n对应的message文件中应该定义对应的值（根据不同语言，有不同的格式，`TODO 待补充`）

### 校验异常

这里的校验异常指`Controller`层对输入参数的校验，因为基于javax validation框架，因此只需要结合业务要求遵守javax validation框架的要求进行定义即可。这里只针对校验失败后的异常常量进行规范。

命名规则：

* 所有字符小写
* 点`.`作为单词之间的分隔符
* 多个单词组成的类名转化为全小写的时候，单词之间用下划线`_`进行分割
* 校验由三个区域组成
    * 类
    * 字段
    * 校验异常类型
* 校验异常类型应该和javax validation采用的注解一致，只需要转化为小写，如果有多个单词，单词之间用下划线`_`进行分割

示例：

> `MachineClass` 的 `model` 字段不能为空

对应的代码为

```java
public class MachineClassSaveParam implements MachineClassSaveRequest {

    @NotNull
    private String model;
    
}    
```

根据规则，对应的异常常量应该为 `machine_class.model.not_null`

```java
public class MachineClassSaveParam implements MachineClassSaveRequest {

    @NotNull(message="machine_class.model.not_null")
    private String model;
    
}    
```

> 所有的常量在i18n对应的message文件中应该定义对应的值（根据不同语言，有不同的格式，`TODO 待补充`）



## REST URL 格式

本章节基于以下示例进行说明

模型

* 工厂 Factory
* 仓库 Warehouse
* 储物架 Rack

关系

* 一个工厂有多个仓库 （1:N）
* 一个仓库有多个储物架 （1:N）

操作

* 工厂 CRUD
* 仓库 CRUD
* 储物架 CRUD

### 命名要求

* 所有的名词要求复数形式
* 多名词组合
    * 用`-`进行分割，不能用驼峰式
    * 最后一个单词用复数

名词 | 格式 | 是否正确
---|---|---
工厂 Factory | /factory | No
 | | /factories | Yes
模具生产 Mold Production | /moldProduction | No
 | | /mold-productions | Yes


### 示例 - 工厂

HTTP 请求 | 路径参数 | 传入参数 | 响应HTTP STATUS  | 备注
--- | --- | --- | --- | --- 
GET /factories | | FactorySearchParam |  | 查询列表
GET /factories/{id} | id | | | 查询详情
POST /factories | | FactorySaveParam | | 新增
PUT /factories/{id} | id | FactorySaveParam | | 修改
DELETE /factories/{id} | id |
GET /factories/{id}/warehouses | id | | 该请求可获取指定工厂内的仓库
POST /factories/{id}/warehouses | id | WarehouseSaveParam | | 该请求可创建指定工厂内的仓库 ， 为`POST /warehouses`的快捷方式

### 示例 - 仓库

HTTP 请求 | 路径参数 | 传入参数 | 响应HTTP STATUS  | 备注
---|---|---|---|---
GET /warehouses | | WarehouseSearchParam |  |
GET /warehouses/{id} | id | |  |
POST /warehouses | | WarehouseSaveParam | |
PUT /warehouses/{id} | id | WarehouseSaveParam |
DELETE /warehouses/{id} | id |
GET /warehouses/{id}/racks |id | | |  该请求可获取指定仓库内的储物架
POST /warehouses/{id}/racks |id | RackSaveParam | |  该请求可创建指定仓库内的储物架，为`POST /racks`的快捷方式

### 示例 - 储物架

HTTP 请求 | 路径参数 | 传入参数 | 响应HTTP STATUS | 备注
---|---|---|---|---
GET /racks | | RackSearchParam | 
GET /racks/{id} | id | | 
POST /racks | | RackSaveParam |
PUT /racks/{id} | id | RackSaveParam |
DELETE /racks/{id} | id |

## REST Response 格式


## Jpa soft delete config

### Model config 示例 - ShiftOutputLog

> 软删除标识为 deleted 字段，需要在实体上配置 @Where条件，过滤掉 deleted = 1 的数据

```java
@Entity
@Table(name = "MES_SHIFT_OUTPUT_LOG")
@Where(clause = "deleted = 0")
public class ShiftOutputLog extends AbstractBenityMultiTenantAuditable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIFT_OUTPUT_ID", nullable = false)
    private ShiftOutput shiftOutput;

    @Column(name = "OLD_GOOD_QUANTITY", nullable = false)
    private BigDecimal oldGoodQuantity;

    @Column(name = "NEW_GOOD_QUANTITY", nullable = false)
    private BigDecimal newGoodQuantity;

    @Column(name = "OLD_DEFECT_QUANTITY", nullable = false)
    private BigDecimal oldDefectQuantity;

    @Column(name = "NEW_DEFECT_QUANTITY", nullable = false)
    private BigDecimal newDefectQuantity;
}
```

### Jpa Repository config

> 重写 JpaRepository delete 方法

```java
@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

    @Override
    @Query("update #{#entityName} o set o.deleted = true where o.id = ?1")
    @Transactional
    @Modifying
    void delete(String s);

    @Override
    @Query("update #{#entityName} o set o.deleted = true where o = ?1")
    @Transactional
    @Modifying
    void delete(T entity);

    @Override
    @Query("update #{#entityName} o set o.deleted = true where o in (?1)")
    @Transactional
    @Modifying
    void delete(Iterable<? extends T> entities);

    @Override
    @Query("update #{#entityName} o set o.deleted = true")
    @Transactional
    @Modifying
    void deleteAll();

}

```




