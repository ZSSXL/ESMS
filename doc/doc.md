## 雇员薪酬管理系统

#### Author：ZSS

#### 修改时间：2020-07-10

#### 描述：家庭作业

----

##### 项目需求：

```TXT
下面是在和客户交谈关于第一次迭代中的素材时做的一些记录
	有些雇员是钟点工。会按照他们雇员记录中每小时报酬字段的值对他们进行支付，他们每天会提交工作时间卡，其中记录了日期以及工作小时数，如果他们每天工作超过8小时，那么超过的部分会按照正常报酬的1.5倍进行支付。每周五对他们进行支付。
	有些雇员完全以月薪进行支付。每个月的最后一个工作日对他们进行支付。在他们的雇员记录中有一个月薪字段。
	同时，对于一些受薪（salaried）雇员，会根据他们的销售情况，支付给他们一定数量的酬金（commission）。他们会提交销售凭条，其中记录了销售的日期和数量。在他们的雇员记录中有一个酬金字段，每隔一周的周五对他们进行支付。
	雇员可以选择支付方式。可以选择把支付支票邮寄到他们指定的邮政地址；也可以把支票保存在出纳人员那里随时支取；或者要求直接存入他们指定的银行账户；
	一些雇员会加入协会。在他们的雇员记录中有一个每周应付款项字段。这些应付款必须从他们的薪水中扣除。协会有时会针对单个协会成员征收服务费用。协会每周会提交这些服务费用，服务费用必须要从相应雇员的下个月的薪水总额中扣除。
	薪水支付程序每个工作日运行一次，并在当天为相应的雇员进行支付。系统会被告知雇员的支付日期，这样系统会计算从雇员上次支付日期到规定的本次支付日期间应支付的数额。
```

#### 数据库初步构想：

```TXT
1. 雇员（Employee）: 员工Id, 员工名称，员工密码
2. 雇员资料（Employee_PROFILE）: 资料Id，员工Id, 员工名称，员工编号，员工类型（钟点工（钟点工：周五发工资/月底发工资）/受薪工） 邮箱，电话，是否加入协会，入职时间, 每小时报酬/周薪/月薪，每周应付款
4. 时间卡（Time_Card）: 时间卡Id, 员工Id, 日期，数量	
5. 销售凭条（Sales_Receipt）: 凭条Id, 时间，数量
6. 支付方式（Payment_Method）: 支付方式Id, 员工Id，方式选择（支票/银行卡转账）注：选择邮寄支票，需要填写收货地址等信息；选择银行卡转账，需要填写银行卡卡号等信息
7. 工资发放记录（Payroll_Record）：发放Id, 员工Id，发放日期，应发金额，应扣费用，实发金额，发放人，是否确认收款。
8. 管理者（Manager）: 管理员Id, 管理员名称，管理员密码，创建时间
```

----

#### 数据库实现：雇员薪酬管理系统（Employee Salary Manager System 缩写：ESMS）

1. 雇员（ESMS_EMPLOYEE）

| 字段名 | 数据类型 | 字段长度 | 主键 | 注释 |
| :------: | :--------: | :--------: | :---: | :---: |
| EMP_ID | VARCHAR | 255 | √ | 员工Id |
| EMP_NAME | VARCHAR | 20 |  | 员工名称 |
| EMP_PASSWORD | VARCHAR | 255 |  | 员工密码 |

2. 时间卡（ESMS_TIME_CARD）

| 字段名 | 数据类型 | 字段长度 | 主键 | 注释 |
| :----: | :--------: | :--------: | :----: | :--: |
| TIME_CARD_ID | VARCHAR | 255 | √ | 时间卡Id |
| EMP_ID | VARCHAR | 255 |  | 员工Id |
| WORKING_DATE | VARCHAR | 15 |  | 工作日期 |
| WORKING_HOURS | DECIMAL | (3, 1) |  | 工作时数 |

3. 销售凭条  （ESMS_SALES_RECEIPT）

| 字段名 | 数据类型 | 字段长度 | 主键 | 注释 |
| :----: | :--------: | :--------: | :----: | :--: |
| RECEIPT_ID | VARCHAR | 255 | √ | 凭条Id |
| EMP_ID | VARCHAR | 255 |  |  |
| RECEIPT_DATE | VARCHAR | 15 |  | 日期 |
| 3QUANTITY | INT | 4 |  | 销售数量 |

4. 工资发放记录 （ESMS_PAYROLL_RECORD）

| 字段名 | 数据类型 | 字段长度 | 主键 | 注释 |
| :----: | :--------: | :--------: | :----: | :--: |
| PAYROLL_ID | VARCHAR | 255 | √ | 工资单Id |
| EMP_ID | VARCHAR | 255 |  | 员工Id |
| PAYABLE | DECIMAL | (12, 2)|  | 应发工资 |
| DEDUCTION | DECIMAL | (12, 2) |  | 应扣费用 |
| ACTUAL_SALARY | DECIMAL | (12, 2) |  | 实收工资 |
| RELEASE_DATE | VARCHAR | 15 |  | 工资发放日期 |
| ISSUER | VARCHAR | 20 |  | 发放人 |
| CONFIRM_RECEIPT | BOOLEAN |  |  | 确认收款 |

5. 管理员（ESMS_MANAGER）

| 字段名 | 数据类型 | 字段长度 | 主键 | 注释 |
| :----: | :--------: | :--------: | :----: | :--: |
| MANAGER_ID | VARCHAR | 255 | √ | 管理员ID |
| MANAGER_NAME | VARCHAR | 20 |  | 管理员名称 |
| MANAGER_PASSWORD | VARCHAR | 255 |  | 管理员密码 |
| CREATE_TIME | VARCHAR | 15 |  | 创建时间 |


5. 支付方式（ESMS_PAYMENT_METHOD）

```json
{
   "methodId" : "支付方式ID",
   "empId" : "员工id",
   "bank" : {
   	  "cardNumber" : "银行卡号",
      "cardOwner" : "银行卡持有人"
   },
   "check" :{
   	  "recipientAddress" : "收件地址",
      "recipient" : "收件人",
      "recipientPhone" : "收件人电话",
      "remark" : "备注"
   }
        
}
```

6. 雇员资料 （ESMS_PROFILE）

```JSON
{
    "profileId" : "资料ID",
    "empId" : "员工ID",
    "empName": "员工名称",
    "empCode" : "员工工号",
    "empType" : "员工类型：钟点工/月薪工/受薪工 HOURLY/MONTHLY/SALARIED",
    "empEmail" : "员工邮箱",
    "society" : "是否加入协会 TRUE\FALSE",
    "entryTime" : "入职时间",
    "weeklyDeduction" : "每周应扣费用",
    "salary" : {
        "hourlySalary" : "时薪 40.0",
    	"monthlySalary" : "月薪 6400.00",
    	"commission" : "酬金 1600.00"
    } 
}
```

