 <h1 class="curproject-name"> 考试管理系统 </h1> 

# 反馈信息操作接口

## 分类筛选反馈信息

<a id=分类筛选反馈信息> </a>
### 基本信息

**Path：** /feed/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称    | 是否必须 | 示例 | 备注     |
|---------|------|----|--------|
| id      | 否    |    | 用户反馈ID |
| message | 是    |    | 用户反馈消息 |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> message</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈消息</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除反馈信息

<a id=批量删除反馈信息> </a>
### 基本信息

**Path：** /feed

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-36><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条反馈信息

<a id=新增一条反馈信息> </a>
### 基本信息

**Path：** /feed

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈消息</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有反馈信息

<a id=查询所有反馈信息> </a>
### 基本信息

**Path：** /feed

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> message</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户反馈消息</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 权限操作接口

## 修改一条权限信息

<a id=修改一条权限信息> </a>
### 基本信息

**Path：** /per

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选权限信息

<a id=分类筛选权限信息> </a>
### 基本信息

**Path：** /per/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称      | 是否必须 | 示例 | 备注    |
|-----------|------|----|-------|
| id        | 否    |    | 权限ID  |
| isDefault | 是    |    | 是否为默认 |
| name      | 否    |    | 权限名   |
| sign      | 是    |    | 权限标识  |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除权限信息

<a id=批量删除权限信息> </a>
### 基本信息

**Path：** /per

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-37><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条权限信息

<a id=新增一条权限信息> </a>
### 基本信息

**Path：** /per

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有权限信息

<a id=查询所有权限信息> </a>
### 基本信息

**Path：** /per

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 用户操作访问接口

## 上传用户头像

<a id=上传用户头像> </a>
### 基本信息

**Path：** /user/upload

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Query**

| 参数名称   | 是否必须 | 示例 | 备注      |
|--------|------|----|---------|
| userId | 是    |    | 操作的用户ID |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=4><span style="font-weight: '700'">format: </span><span>binary</span></p></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 下载用户头像

<a id=下载用户头像> </a>
### 基本信息

**Path：** /user/avatar

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称   | 是否必须 | 示例 | 备注   |
|--------|------|----|------|
| userId | 是    |    | 用户ID |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> description</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> file</span></td><td key=1><span>file</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> filename</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> inputStream</span></td><td key=1><span>object</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> open</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> readable</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> uri</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=4><span style="font-weight: '700'">format: </span><span>uri</span></p></td></tr><tr key=0-7><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> url</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=4><span style="font-weight: '700'">format: </span><span>url</span></p></td></tr>
               </tbody>
              </table>

## 修改一条用户信息

<a id=修改一条用户信息> </a>
### 基本信息

**Path：** /user

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> avatar</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">头像</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> email</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电子邮箱</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户ID</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户名</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> nickName</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">昵称</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> password</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">密码</span></td><td key=5></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> phone</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电话</span></td><td key=5></td></tr><tr key=0-7><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> roleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色</span></td><td key=5></td></tr><tr key=0-8><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> sex</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">性别(0女,1男)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-9><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">状态(0正常,1失效)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类查询用户

<a id=分类查询用户> </a>
### 基本信息

**Path：** /user/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称     | 是否必须 | 示例 | 备注          |
|----------|------|----|-------------|
| avatar   | 否    |    | 头像          |
| email    | 否    |    | 电子邮箱        |
| id       | 否    |    | 用户ID        |
| name     | 否    |    | 用户名         |
| nickName | 否    |    | 昵称          |
| password | 否    |    | 密码          |
| phone    | 否    |    | 电话          |
| roleId   | 否    |    | 角色          |
| sex      | 否    |    | 性别(0女,1男)   |
| status   | 否    |    | 状态(0正常,1失效) |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> avatar</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">头像</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> email</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电子邮箱</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户ID</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户名</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> nickName</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">昵称</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> password</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">密码</span></td><td key=5></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> phone</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电话</span></td><td key=5></td></tr><tr key=0-0-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> roleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色</span></td><td key=5></td></tr><tr key=0-0-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sex</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">性别(0女,1男)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-9><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">状态(0正常,1失效)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除用户信息

<a id=批量删除用户信息> </a>
### 基本信息

**Path：** /user

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-38><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查看所有用户

<a id=查看所有用户> </a>
### 基本信息

**Path：** /user

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> avatar</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">头像</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> email</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电子邮箱</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户ID</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户名</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> nickName</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">昵称</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> password</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">密码</span></td><td key=5></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> phone</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电话</span></td><td key=5></td></tr><tr key=0-0-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> roleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色</span></td><td key=5></td></tr><tr key=0-0-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sex</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">性别(0女,1男)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-9><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">状态(0正常,1失效)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 用户注册

<a id=用户注册> </a>
### 基本信息

**Path：** /user/register

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> avatar</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">头像</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> email</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电子邮箱</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户ID</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用户名</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> nickName</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">昵称</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> password</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">密码</span></td><td key=5></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> phone</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">电话</span></td><td key=5></td></tr><tr key=0-7><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> roleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色</span></td><td key=5></td></tr><tr key=0-8><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> sex</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">性别(0女,1男)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-9><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">状态(0正常,1失效)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 用户登录接口

<a id=用户登录接口> </a>
### 基本信息

**Path：** /user/login

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Query**

| 参数名称     | 是否必须 | 示例 | 备注  |
|----------|------|----|-----|
| password | 是    |    | 密码  |
| username | 是    |    | 用户名 |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 用户退出接口

<a id=用户退出接口> </a>
### 基本信息

**Path：** /user/logout

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称  | 是否必须 | 示例 | 备注       |
|-------|------|----|----------|
| token | 是    |    | 登录的token |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 科目操作访问接口

## 修改一条科目信息

<a id=修改一条科目信息> </a>
### 基本信息

**Path：** /subject

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目名</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选科目信息

<a id=分类筛选科目信息> </a>
### 基本信息

**Path：** /subject/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称 | 是否必须 | 示例 | 备注   |
|------|------|----|------|
| id   | 否    |    | 科目ID |
| name | 否    |    | 科目名  |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目名</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除科目信息

<a id=批量删除科目信息> </a>
### 基本信息

**Path：** /subject

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-39><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条科目信息

<a id=新增一条科目信息> </a>
### 基本信息

**Path：** /subject

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目名</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有科目信息

<a id=查询所有科目信息> </a>
### 基本信息

**Path：** /subject

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目名</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 角色操作访问接口

## lookRoleById

<a id=lookRoleById> </a>
### 基本信息

**Path：** /role/{roleId}

**Method：** GET

**接口描述：**

### 请求参数

**路径参数**

| 参数名称   | 示例 | 备注     |
|--------|----|--------|
| roleId |    | roleId |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用于存储角色信息</span></td><td key=5></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-2-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-2-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-2-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=0><span style="font-weight: '700'">最大值: </span><span>511</span></p><p key=1><span style="font-weight: '700'">最小值: </span><span>100</span></p><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 修改一条角色信息

<a id=修改一条角色信息> </a>
### 基本信息

**Path：** /role

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-3-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-3-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-3-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选角色信息

<a id=分类筛选角色信息> </a>
### 基本信息

**Path：** /role/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称                        | 是否必须 | 示例 | 备注              |
|-----------------------------|------|----|-----------------|
| id                          | 否    |    | 角色ID            |
| isDefault                   | 否    |    | 是否为默认(0默认,1自定义) |
| name                        | 否    |    | 角色名             |
| permissionList[0].id        | 否    |    | 权限ID            |
| permissionList[0].isDefault | 是    |    | 是否为默认           |
| permissionList[0].name      | 否    |    | 权限名             |
| permissionList[0].sign      | 是    |    | 权限标识            |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除角色信息

<a id=批量删除角色信息> </a>
### 基本信息

**Path：** /role

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-40><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条角色信息

<a id=新增一条角色信息> </a>
### 基本信息

**Path：** /role

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-3-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-3-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-3-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询单个角色信息

<a id=查询单个角色信息> </a>
### 基本信息

**Path：** /role/one

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称   | 是否必须 | 示例 | 备注   |
|--------|------|----|------|
| roleId | 是    |    | 角色ID |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用于存储角色信息</span></td><td key=5></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有角色信息

<a id=查询所有角色信息> </a>
### 基本信息

**Path：** /role

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">角色名</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 试卷操作接口

## 修改一条角色信息

<a id=修改一条角色信息> </a>
### 基本信息

**Path：** /exam

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷名称</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> pass</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">合格分数</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> problemList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题库列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-3-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-3-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-3-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-3-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-3-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-3-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-3-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-3-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-3-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">总分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> time</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">考试时间</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选试卷信息

<a id=分类筛选试卷信息> </a>
### 基本信息

**Path：** /exam/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称                     | 是否必须 | 示例 | 备注   |
|--------------------------|------|----|------|
| id                       | 否    |    | 试卷ID |
| name                     | 否    |    | 试卷名称 |
| pass                     | 否    |    | 合格分数 |
| problemList[0].answer    | 否    |    | 答案   |
| problemList[0].gradleId  | 否    |    | 年级ID |
| problemList[0].id        | 否    |    | 题目ID |
| problemList[0].main      | 否    |    | 题干   |
| problemList[0].options   | 否    |    | 选项   |
| problemList[0].parse     | 否    |    | 解析   |
| problemList[0].score     | 否    |    | 得分   |
| problemList[0].subjectId | 否    |    | 科目   |
| problemList[0].type      | 否    |    | 类型   |
| score                    | 否    |    | 总分   |
| time                     | 否    |    | 考试时间 |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷名称</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> pass</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">合格分数</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> problemList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题库列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-0-3-4><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-0-3-5><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-0-3-6><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3-7><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-0-3-8><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">总分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> time</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">考试时间</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除试卷信息

<a id=批量删除试卷信息> </a>
### 基本信息

**Path：** /exam

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-41><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条试卷信息

<a id=新增一条试卷信息> </a>
### 基本信息

**Path：** /exam

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷名称</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> pass</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">合格分数</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> problemList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题库列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-3-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-3-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-3-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-3-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-3-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-3-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-3-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-3-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-3-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">总分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> time</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">考试时间</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询一条试卷信息

<a id=查询一条试卷信息> </a>
### 基本信息

**Path：** /exam/one

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称   | 是否必须 | 示例 | 备注   |
|--------|------|----|------|
| examId | 是    |    | 试卷ID |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用于存储试卷数据的实体</span></td><td key=5></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷名称</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> pass</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">合格分数</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> problemList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题库列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-0-3-4><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-0-3-5><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-0-3-6><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3-7><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-0-3-8><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">总分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> time</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">考试时间</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有试卷信息

<a id=查询所有试卷信息> </a>
### 基本信息

**Path：** /exam

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">试卷名称</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> pass</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">合格分数</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> problemList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题库列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-3-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-0-3-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-3-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-0-3-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-0-3-4><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-0-3-5><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-0-3-6><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-3-7><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-0-3-8><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">总分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> time</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">考试时间</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 题库操作接口

## 修改一条题库信息

<a id=修改一条题库信息> </a>
### 基本信息

**Path：** /pro

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-7><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-8><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选题库信息

<a id=分类筛选题库信息> </a>
### 基本信息

**Path：** /pro/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称      | 是否必须 | 示例 | 备注   |
|-----------|------|----|------|
| answer    | 否    |    | 答案   |
| gradleId  | 否    |    | 年级ID |
| id        | 否    |    | 题目ID |
| main      | 否    |    | 题干   |
| options   | 否    |    | 选项   |
| parse     | 否    |    | 解析   |
| score     | 否    |    | 得分   |
| subjectId | 否    |    | 科目   |
| type      | 否    |    | 类型   |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-0-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除题库信息

<a id=批量删除题库信息> </a>
### 基本信息

**Path：** /pro

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-42><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条题库信息

<a id=新增一条题库信息> </a>
### 基本信息

**Path：** /pro

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-7><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-8><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有题库信息

<a id=查询所有题库信息> </a>
### 基本信息

**Path：** /pro

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> answer</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">答案</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> gradleId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题目ID</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> main</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">题干</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> options</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">选项</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> parse</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">解析</span></td><td key=5></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> score</span></td><td key=1><span>number</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">得分</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>float</span></p></td></tr><tr key=0-0-7><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> subjectId</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">科目</span></td><td key=5></td></tr><tr key=0-0-8><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">类型</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 路由操作访问接口

## 修改一条路由信息

<a id=修改一条路由信息> </a>
### 基本信息

**Path：** /router

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> method</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">请求方式</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由名</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-4-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-4-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-4-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-4-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由类型(0后端,1前端)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> value</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由值</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选路由信息

<a id=分类筛选路由信息> </a>
### 基本信息

**Path：** /router/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称                        | 是否必须 | 示例 | 备注             |
|-----------------------------|------|----|----------------|
| id                          | 否    |    | 路由ID           |
| isDefault                   | 否    |    | 是否默认(0默认,1自定义) |
| method                      | 否    |    | 请求方式           |
| name                        | 否    |    | 路由名            |
| permissionList[0].id        | 否    |    | 权限ID           |
| permissionList[0].isDefault | 是    |    | 是否为默认          |
| permissionList[0].name      | 否    |    | 权限名            |
| permissionList[0].sign      | 是    |    | 权限标识           |
| type                        | 否    |    | 路由类型(0后端,1前端)  |
| value                       | 否    |    | 路由值            |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> method</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">请求方式</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由名</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-4-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-4-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-4-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-4-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由类型(0后端,1前端)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> value</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由值</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除路由信息

<a id=批量删除路由信息> </a>
### 基本信息

**Path：** /router

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-43><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条路由信息

<a id=新增一条路由信息> </a>
### 基本信息

**Path：** /router

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> method</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">请求方式</span></td><td key=5></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由名</span></td><td key=5></td></tr><tr key=0-4><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-4-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-4-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-4-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-4-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-5><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由类型(0后端,1前端)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-6><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> value</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由值</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询一条路由信息

<a id=查询一条路由信息> </a>
### 基本信息

**Path：** /router/one

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称     | 是否必须 | 示例 | 备注       |
|----------|------|----|----------|
| routerId | 是    |    | routerId |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">用于存储请求路由</span></td><td key=5></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> method</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">请求方式</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由名</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-4-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-4-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-4-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-4-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由类型(0后端,1前端)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> value</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由值</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有路由信息

<a id=查询所有路由信息> </a>
### 基本信息

**Path：** /router

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否默认(0默认,1自定义)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-2><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> method</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">请求方式</span></td><td key=5></td></tr><tr key=0-0-3><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由名</span></td><td key=5></td></tr><tr key=0-0-4><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> permissionList</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限列表</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-4-0><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限ID</span></td><td key=5></td></tr><tr key=0-0-4-1><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> isDefault</span></td><td key=1><span>integer</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否为默认</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-0-4-2><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限名</span></td><td key=5></td></tr><tr key=0-0-4-3><td key=0><span style="padding-left: 40px"><span style="color: #8c8a8a">├─</span> sign</span></td><td key=1><span>string</span></td><td key=2>必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">权限标识</span></td><td key=5></td></tr><tr key=0-0-5><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> type</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由类型(0后端,1前端)</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p><p key=3><span style="font-weight: '700'">枚举: </span><span>0,1</span></p></td></tr><tr key=0-0-6><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> value</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">路由值</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

# 年级操作访问接口

## 修改一条年级信息

<a id=修改一条年级信息> </a>
### 基本信息

**Path：** /gradle

**Method：** PUT

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级名</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 分类筛选年级信息

<a id=分类筛选年级信息> </a>
### 基本信息

**Path：** /gradle/select

**Method：** GET

**接口描述：**

### 请求参数

**Query**

| 参数名称 | 是否必须 | 示例 | 备注   |
|------|------|----|------|
| id   | 否    |    | 年级ID |
| name | 否    |    | 年级名  |

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级名</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 批量删除年级信息

<a id=批量删除年级信息> </a>
### 基本信息

**Path：** /gradle

**Method：** DELETE

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> </span></td><td key=1><span>string []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>string</span></p></td></tr><tr key=array-44><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> </span></td><td key=1><span></span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap"></span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 新增一条年级信息

<a id=新增一条年级信息> </a>
### 基本信息

**Path：** /gradle

**Method：** POST

**接口描述：**

### 请求参数

**Headers**

| 参数名称         | 参数值              | 是否必须 | 示例 | 备注 |
|--------------|------------------|------|----|----|
| Content-Type | application/json | 是    |    |    |

**Body**

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级名</span></td><td key=5></td></tr>
               </tbody>
              </table>

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>

## 查询所有年级信息

<a id=查询所有年级信息> </a>
### 基本信息

**Path：** /gradle

**Method：** GET

**接口描述：**

### 请求参数

### 返回数据

<table>
  <thead class="ant-table-thead">
    <tr>
      <th key=name>名称</th><th key=type>类型</th><th key=required>是否必须</th><th key=default>默认值</th><th key=desc>备注</th><th key=sub>其他信息</th>
    </tr>
  </thead><tbody className="ant-table-tbody"><tr key=0-0><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> data</span></td><td key=1><span>object []</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">数据</span></td><td key=5><p key=3><span style="font-weight: '700'">item 类型: </span><span>object</span></p></td></tr><tr key=0-0-0><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> id</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级ID</span></td><td key=5></td></tr><tr key=0-0-1><td key=0><span style="padding-left: 20px"><span style="color: #8c8a8a">├─</span> name</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">年级名</span></td><td key=5></td></tr><tr key=0-1><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> message</span></td><td key=1><span>string</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">消息</span></td><td key=5></td></tr><tr key=0-2><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> status</span></td><td key=1><span>integer</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">错误代码</span></td><td key=5><p key=2><span style="font-weight: '700'">format: </span><span>int32</span></p></td></tr><tr key=0-3><td key=0><span style="padding-left: 0px"><span style="color: #8c8a8a"></span> success</span></td><td key=1><span>boolean</span></td><td key=2>非必须</td><td key=3></td><td key=4><span style="white-space: pre-wrap">是否正常</span></td><td key=5></td></tr>
               </tbody>
              </table>
            