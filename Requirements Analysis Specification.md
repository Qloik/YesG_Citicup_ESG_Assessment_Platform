# 需求分析

## 目录

[TOC]



## 1. 引言

### 1.1. 背景

在推进可持续发展的漫长征程中，社会、企业和银行不断面临新的问题和挑战。推动经济绿色转型，助力“双碳”目标，管控气候环境风险，探索社会创新和公益创新，是每一位市场参与者的共同目标。花旗作为一家领先的国际银行，需要快速应对这其中产生的一系列重大挑战，深刻理解金融市场ESG进程，精准把控战略方向，践行初心使命。

本项目为花旗提供了一个智能化的解决方案，开发了一个基于自然语言处理的esg评估支持平台，实现读取并分析任一指定企业的ESG相关数据（包括并不限于企业发布的ESG报告，第三方评级机构评级变动，ESG相关新闻舆论，ESG相关项目流程信息等），自动生成ESG summary report。其次，基于提取出的关键要素，通过比对国际ESG通行标准和最佳实践，搭建出适用于该企业的ESG框架，为花旗的销售团队制作针对该企业的ESG融资方案提供强有力的数据支持，成为花旗整个ESG产品链路的重要环节。

开发者团队为同济大学软件学院的6名本科生胡锦晖、杨思恒、李怿欣、夏佳幸、傅薏帆和戴秋璐以及电信学院研究生李远航一起完成。



### 1.2. 假定和约束

本项目开发主要受人员数量、学业以及疫情的约束，因此本项目的假定和约束如下所示：

1. 项目开发期限为2个月，时间为2023年1月底至2023年3月底；
2. 项目开发无经费，设备条件为Windows操作系统电脑和Mac操作系统电脑以及云服务器、图床等；
4. 团队交流方面通过Gitee等进行代码协作以及团队任务管理。



### 1.3. 技术亮点

1. 金融文本情绪可以调动管理者、信息中介和投资者的观点和意见，因此分析金融文本情感(情绪)是有价值的。我们采用了FinBERT-Sentiment模型进行文本感情分析，它根据标准昔尔500家公司的分析师报告中的10000个手动注释的句子进行Fine-tune（微调）。
2. 我们整合了十余个第三方评级平台以及多个新闻奥论平台进行实时爬取并进行数据清洗和整理，数据库更加全面和完善，能够给出更加综合的ESG评估和分析。



### 1.4. 平台受众与特点

① 投资主体：需要进行投资，关注ESG并认同ESG理念的投资者，包括投资公司（PE，VC及其他基金公司等），私人投资者，本项目能为关注可持续发展的投资者提供了资产配置的依据，能发挥引导绿色投资的作用。

② 研究主体：国内进行ESG理论研究的学者专家，各类行研、战略研究者，有助于ESG主题在中国的发展。

③ 各类企业主体：时刻关注ESG主题，特定行业遵守ESG标杆性企业发展，努力遵循ESG相关标准。

④ 市场监管主体：国家以及监管局等，能够指导产业风向标，引导全社会产业的健康绿色发展，本项目能为监管者判断相关行业的可持续发展变化走势，以及制定相应的行业政策导向提供参考。国家政府关注企业的污染物排放、 环保信息披露程度、环保违规处罚及重大安全事故等指标在兼顾环境、公司治理和社会责任三个维度同时，结合中国所处发展阶段及环保问题突出实际情况，制定国家发展战略。

⑤ 信用借贷主体：银行等信用主体可以借此估计企业的可持续发展能力并给予帮助和扶持，评价结果对银行识别客户和引导可持续投资均具有较好的应用价值。



## 2. 功能需求

### 2.1. 需求描述

① 平台能够快速且全面的地了解目标公司，获取对应公司的相关ESG新闻与报告，给出ESG评估。

② 在众多种类的上市公司中制定一个相对平衡的标准，同时能对同行业企业相关报告进行合理的对比。

③ 能够自动获取行业领先公司的相关ESG发展报告，并使用当前公司与其进行对比评价。

④ 本项目主要落脚于港股公司，获取的报告内容以繁体中文的形式呈现，为了方便阅读与使用，需要进行繁体字转换处理。

⑤ 增加公司估值部分，使公司ESG战略与其公司发展战略深度结合。



### 2.2. 系统流程

系统共有注册用户、游客两种用户身份，游客只能进行注册和登录操作，注册用户可以登录并使用全部的系统功能。每个系统的使用流程会在后面的各个子系统中详细说明。

游客身份为初次使用系统的学生，只能进行注册，注册成功后成为注册用户。

注册用户身份的用户才能使用平台的全部功能，比如公司详细情况查询、公司ESG评估查询、分行业查询与对比等。



### 2.4. 需求分析

#### 2.4.1. 用户登录注册子系统

##### 2.4.1.1. 整体数据建模

在本模块，需要建模的数据都来自于用户，而用户的操作涉及到注册、登录与登出等。在注册时，用户需要通过填写手机号码作为用户ID，而登陆时则通过用户ID与密码一起比对进行用户身份判断。

##### 2.4.1.2. 用户注册用例

###### a. 功能建模

  游客进入注册界面进行注册，填写用户名、手机号、密码，系统判断该手机号未注册后即可获得系统账号。若手机号已被注册，将提示用户前往登录。

![PlantUML diagram](http://cdn-0.plantuml.com/plantuml/png/LO_1IaD134NtynMNh5JQNz1kL0HNRiN5-5xUwy1CKf8Cqhztj5hQLGZtv92xyyWMlTLqStUBxVOUz2Z11kErU5YEee93NfdIR_fKQaKHT6UJt7Y3mrw58RrzicsGPHwu-vVQJAaie1U_GCvcDfuV2B5dIS1Gluj-W9ujIy2F0sZ8qwHTWau0wEto9-_JnEvBh__vQ92uEl_vZkm_GaKeggv5pfxNiPrANPtAN9Qq6wEt-Wq0)

###### b. 行为建模

用户注册、登录的状态转换如下图所示：

<img src="http://cdn-0.plantuml.com/plantuml/png/ZLFBRjim4BphAnOw5iqF-52aJOesG8eQiPjBqGCjRTH5ALBWeq5GzD-xMPerBLE6SuY5tPdHS3Ywza6v44VT10eQuPj71myua0zE1R86Da45XArJnfCCRaaDJerDy_tD3xYwUWUV8lhGDF8ZWyH7HsQ05Tnewdv1cuSjx688rZGbgcHXpwHDt8qKuBVIr8Ch_P1vicxavug4M3YmoLp8nrxeztOWm_Iri-CKe7sl7Qh-HVW9zXOcZSeZQ3kmKZjNu4WE9xwn9f2923jddprx_7_qnakeoUwV1lWlX_b8-CpGyWhMQa1OySPJE3xBn1jXBx4klus_sLG9JzRIlFreUBgOPGlAWq1A4tSCA3KUN--3dyxBP5HLgVgE5DqPskBoj7ft1pT0FbNmh4HHlJaxL_0qNkoFqxqbFsdrSXoCZrs7tiF8_zI04iuUk4chBsbJAktR6-o-f60TTFCgYbH2Rgtq_glxyBeJgUfBf4tBdFgc_Avoz-x5LcxUClVYBwgihoZKezMoccP12c7DmzEk2w5USc4jjhjEpEigzm5xSjZ9lVsq8ufFYDEX1KzH9s4Ho_Yj_Spex7z-ACcmudWs4bJTtdmgsChROINHbsxvKgxHz77K_m00" alt="PlantUML diagram" style="zoom: 67%;" />

##### 2.4.1.3. 用户登录用例

###### a. 功能建模

已经过注册的用户通过手机号与密码进入系统。

![PlantUML diagram](assets/LOynYiD034NxESLdAdl1LzXriI4dI1LIA1xP7l2CZIHZVFi8aYQL1FyzYT-haTXUbj3yjEYwNyHBDHRegSO5HxOPsxnMHjtBqmEg2Hkf7gka6FA8vgOio0hXARiadFvQsCmLpSdQ1i3ltarsVY1UXQg1Xe5Lupk2lmwyA7_8aNntz3_hjj095ba5nGMQ-CkfAO-Xz-45NW00.png)

###### b. 行为建模

用户登陆的状态转换图见2.4.1.2.b.



##### 2.4.1.4. 用户登出用例

###### a. 功能建模

已登录的用户点击登出，后台记录使用时长。

###### b. 行为建模

用户登出的状态转换图如下：

![PlantUML diagram](http://cdn-0.plantuml.com/plantuml/png/ZLB1IiGm4BtdAuOUXVq13xAoYx0W26hn81vcqxG4qqII2RB8_hlfP8rPkXPlxRptfk-zpZeGUegZ4QJ9ABXtmw2wrSv26vI79q9IyEpH1atQMTXg73oEGhnUlS5gTNCIxAmGFqzbhEtm40ckOMEqV8ScpS15Qc0VYPmL5UTtKrQavyG4wMol_QWwKK2cFYhfV9UqRS11PNw2OTA4AAKAeO_67CGvaRN9U1BaTyzmMYy21oqHlWG0G1usMnq-31xI5eXJATgIyZrArG06EA7jhfQyeAQ9tpkVDIYd-bWmONVEt_A4HMK56vpZzUJ_zHvBgaB-CzUfQEGFZ2fCxTJfDXM-cBDULAUTBRYmk4e_vr-OpwwchHm4C7n3LGtCN6h1vEDFgaiT57tjV_4kowz9hadRg63ltMT8-FmddKlFBHOC8eCT4hAp3B22-ULzwTQFOgri5qVp3G00)

#### 2.4.3. 信息查询子系统

##### 2.4.3.1 整体数据建模

注册用户可以查询所有的公司基本信息、ESG评级信息、参考新闻等数据。

##### 2.4.3.2 查询所有公司用例

###### a.功能建模

注册用户进入该系统后可点击ESG SUMMARY进入ESG报告界面，查询所有公司的股票代码、名称、ESG评估结果等信息。



##### 2.4.3.3 查询公司详细信息用例

###### a.功能建模

​	注册用户进入某公司详细界面可以查询该公司的基本面信息、详细ESG评估分数以及广泛性和质量性分数。



##### 2.4.3.4 查询新闻用例

###### a.功能建模

​	注册用户可以点击News进入新闻界面，查询所有参与计算质量性分数的新闻信息。



##### 2.4.3.5 查询行业领头公司用例

###### a.功能建模

​	注册用户可以选择行业查询对应行业领头公司信息。



##### 2.4.3.6 导出查询结果用例

###### a.功能建模

​	注册用户可以选择将查询结果导出为csv文件。



#### 2.4.4. 对比子系统

##### 2.4.4.1. 公司详细信息对比用例

###### a. 功能建模

注册用户可以输入公司代码查询获取两个公司的评级详细数据，并得到简要对比结果显示分数较低公司的优劣势情况。

![PlantUML diagram](http://cdn-0.plantuml.com/plantuml/png/bO_FReCm3CRlUGeVwIkKIUlOTjyVRNUB6B1a4cGRJRpzWAugAfVrW4Zo-_BpbvCvge-z16SNWWzgsPoK8dmPARoFf1EyQQx9B8Gr7OwloMTMvtx0DCt_I6NW1efl58xh_l40tb62OY8x183ZMNIvyWIApgc5Y8xbdbSx_enIZu9E5s9rLe8cwrNwts2r19U-dolApQRN0zZjONNxc7dA2ziW-5TOsN829Hl5uOUzs_WwRZkPFwUuJ2KnWYBb-pJLJfCYDs4Zkwf8DIlqP8OjbU6SDCz3ECthiPTV)

###### b. 行为建模

公司详细信息对比的状态转换图如下：

![PlantUML diagram](http://cdn-0.plantuml.com/plantuml/png/bLJ1Rjim3BthAuWS1_G7TXYwDIrGO8SkQR73i0CdCvumMSeeUK0mzDzdqPvA1p7WNEhgyJs6Zo9rcp9oxZjliikUO4UjIvcO6dX9nF2b9px1FcCcU6OCoMKN0smTjeoTCT_UVOURcm_mEROjDO_1cF__2Ino5rfu3_SX4uEDtH73QVWsP6gqKlUzjPJIG9SZfF5ywFq5yWCwJqtb7kHeT1xXtg6tlSSybh75ZC1aoVqfP9L7snY8TzxPNx1v7Z9ZYoviu4UVSmn6vvFynHAx58jgRoDJ0Zjng34gF6VlAFK-3nh5EBZWqa-bghJBrNt2uM-EyBlug1MQ9FTLwNyD0C08RhOk7JrA9_jomwxSom4jRG0JJD6NHotvYaxS7YAF6hHb2aHGOW-HFmeYefgYr7mXhf4rK_8w-QcqHJ_I1JXoR9bIqdwU9cp9rQXaQRMsDJPVL6UPj3d5cY6hvdfHjJ3WkXygywmdJyZfhI4DPXHJ1QQo0CAjfwKjEHP2eshr9_NZgi2b-o3coD0DOcndutLV0bTEs8uodqIZawXhK0mDhDxhQastOcPD1tZy9DdtKcULqLum4PvXAzwBXNOhqRmamSyh4N1MXqPMlqChgXd5YnDWAr4lrqGOjLUEGNdnz1IedJbdgFDLp-UhkQNGz9t_1m00)



## 3. 非功能需求

### 3.1. 安全性需求

主要考虑**保密性和完整性**两个子特性。

**对于保密性**
我们会逐一说明我们对个人信息收集、使用、保护等处理的情况，以便您能够了解个人信息的概况。

当您注册帐号和使用我们的服务时，我们会根据您的同意和提供服务的需要，收集您的姓名、性别、年龄、个人资料照片或视频、电话号码、电子邮件、社交帐号、身份验证信息、位置信息和日志信息等个人信息。

目前，除法律法规、法律程序、诉讼或政府主管部门强制性要求外，我们不会主动公开披露您的个人信息，如果存在其他需要公开披露个人信息的情形，我们会征得您的明示同意。同时，我们保证披露采取符合法律和业界标准的安全防护措施。

您可以通过本隐私政策所列的途径访问、更正或删除您的个人信息，也可以进行隐私设置或与我们取得联系。 您使用或继续使用我们的服务，即意味着同意我们按照本《隐私政策》收集、使用、储存、共享、转让和公开披露您的相关信息。

**对于完整性**
本平台在完成业务需求和系统正常运行要求的功能之外，还应完成用户管理功能，用户可以自行管理自己账户的状态，如注册，登录和注销等等。

对于其中保证用户核心安全性的身份认证机制而言，拟采用如下的技术进行实现：

前端每次在登录的过程中将会由后端生成一段token信息，并存储到localStorage中。在发送所有API请求的过程中，将会自动将当前token信息放在request请求的头部（headers）中的Authorization字段，如果当前没有token则通过路由守卫跳转到登录界面重新获取token。

后端在接受到请求后，会先检验该Token信息是否过期。如果过期或者该用户无权限进行该操作，JWT会直接返回errorCode码。反之，返回该请求的结果。而前端接收到返回值后，如果发现请求失败，token过期失效，将会跳转到首页提醒登录过期，并需要用户重新登录获取新的token才能完成其他请求。反之，正常进行数据处理和页面展示。



### 3.2. 易用性需求

从平台的业务逻辑来看，本系统应满足以下最基本的易用性需求：

1. 能够根据不同行业选取不同指标与特点进行ESG的评估。
2. 能够快速获得公司之间ESG发展情况对比。
3. 能够快速获得公司估值、评分。



### 3.3. 可维护性与可扩展性需求

本项目平台的服务用户主要为投资主体、研究主体、各类企业主体以及市场监管主体，上线后将一直保持运行状态，因此进行实时的维护和更新是十分重要的。从决定可维护性的因素出发：

1. 系统大小：平台计划面向的用户规模应达到万人级。
2. 系统的年龄：平台上线后计划持续上线。
3. 结构合理性：平台拟采用微服务架构进行开发部署，不同的业务在不同的微服务内进行维护和部署，结构合理。
4. 可测试性：系统应在保证当前业务正常运作的情况下实现各模块的独立测试，保证测试的独立性和方便性。
5. 可修改性：系统的各微服务的前后端代码应保证开放-封闭原则，在进行业务的扩充和业务的修改时尽量保证现有模块无需进行大幅度修改。



## 4. 运行环境规定

### 4.1. 设备

YesG以网页端形式呈现，因此平台的运行必须接入互联网，设备要求如下：

1. 任意可联网设备



### 4.2. 控制

本平台的使用方式为：

- 输入域名进入网页端
- 注册账号登录或以游客进行访问
- 通过选择不同的功能板块进行交互，以实现需求

本平台无需控制信号，在未登录状态下可以进行部分操作，如需使用全部功能，需要进行手机注册并登录。

