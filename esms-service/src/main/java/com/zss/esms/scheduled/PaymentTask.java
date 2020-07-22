package com.zss.esms.scheduled;

import com.zss.esms.comment.Constant;
import com.zss.esms.model.entity.mongdb.Profile;
import com.zss.esms.model.entity.postgres.PayrollRecord;
import com.zss.esms.service.PayrollRecordService;
import com.zss.esms.service.ProfileService;
import com.zss.esms.service.SalesReceiptService;
import com.zss.esms.service.TimeCardService;
import com.zss.esms.util.IdUtil;
import com.zss.esms.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/20 16:48
 * @desc 支付任务
 */
@Slf4j
@Configuration
@EnableScheduling
@SuppressWarnings("unused")
public class PaymentTask {

    @Resource
    private ProfileService profileService;

    @Resource
    private TimeCardService timeCardService;

    @Resource
    private SalesReceiptService salesReceiptService;

    @Resource
    private PayrollRecordService payrollRecordService;

    /**
     * 定时任务 cron = "0 0 17 * * ?" 每天17：00执行
     * 0 0/2 * * * ? 每隔2分钟执行一次
     * 0/10 * * * * ? 每个10秒执行一次
     */
    @Scheduled(cron = "0 0 17 * * ?")
    private void paymentTask() {
        // 获取所有员工信息，如：empId, Salary
        List<Profile> profileList = profileService.findAllProfile();
        // 判断员工类型
        for (Profile profile : profileList) {
            BigDecimal payable = new BigDecimal("0");
            BigDecimal deduction = new BigDecimal("0");
            // 计算总工资
            switch (profile.getEmpType()) {
                // 钟点工
                case Constant.EmployeeType.HOURLY:
                    // 判断今天是不是周五
                    if (TimeUtil.dayOfWeek(Calendar.FRIDAY)) {
                        String hourlySalary = profile.getSalary().get("hourlySalary");
                        payable = calcuHourly(profile.getEmpId(), hourlySalary);
                    }
                    break;
                // 月薪工
                case Constant.EmployeeType.MONTHLY:
                    // 判断今天是不是周五
                    if (TimeUtil.dayOfWeek(Calendar.FRIDAY)) {
                        String monthlySalary = profile.getSalary().get("monthlySalary");
                        payable = calcuMonthly(monthlySalary);
                    }
                    break;
                // 受薪工
                case Constant.EmployeeType.SALARIED:
                    // 判断今天是不是月底
                    if (TimeUtil.isLastDayOfMonth()) {
                        String commission = profile.getSalary().get("commission");
                        payable = calcuSaleried(profile.getEmpId(), commission);
                    }
                    break;
                default:
                    break;
            }
            // 判断是否加入协会
            if (profile.getSociety()) {
                deduction = profile.getWeeklyDeduction();
            }
            // 应发工资 - 应扣工资 = 实收工资
            BigDecimal actualSalary = payable.subtract(deduction);
            // 发工资
            // todo 发工资接口
            // 记录薪酬发放记录
            saveRecord(profile.getEmpId(), payable, deduction, actualSalary);
        }
        // 结束
    }

    /**
     * 计算小时工工资
     *
     * @param empId  雇员Id
     * @param salary 工资
     * @return BigDecimal
     */
    private BigDecimal calcuHourly(String empId, String salary) {
        // 获取一周工作时数
        BigDecimal countHours = timeCardService.countWorkingHours(empId);
        System.out.println("Working Hours : " + countHours);
        try {
            BigDecimal multiply = countHours.multiply(new BigDecimal(salary));
            System.out.println("Multiply : " + multiply);
            return multiply;
        } catch (Exception e) {
            log.error("[ " + empId + " ]计算工资失败... [{}]", e.getMessage());
            return new BigDecimal("0");
        }
    }

    /**
     * 计算受新工工资
     *
     * @param empId  雇员Id
     * @param salary 工资
     * @return BigDecimal
     */
    private BigDecimal calcuSaleried(String empId, String salary) {
        // 获取一周销售数量
        Integer integer = salesReceiptService.countSalesQuantity(empId);
        BigDecimal quantityCount = new BigDecimal(integer);
        BigDecimal commission = new BigDecimal(salary);
        try {
            BigDecimal multiply = quantityCount.multiply(commission);
            System.out.println("Saleried Multiply : " + multiply);
            return multiply;
        } catch (Exception e) {
            log.error("[ " + empId + " ]计算工资失败... [{}]", e.getMessage());
            return new BigDecimal("0");
        }
    }

    /**
     * 计算月薪工资
     *
     * @param salary 工资
     * @return BigDecimal
     */
    private BigDecimal calcuMonthly(String salary) {
        // 直接结算
        return new BigDecimal(salary);
    }

    /**
     * 保存工资方法记录
     *
     * @param empId        员工Id
     * @param payable      应发工资
     * @param deduction    应扣费用
     * @param actualSalary 实收工资
     */
    private void saveRecord(String empId, BigDecimal payable, BigDecimal deduction, BigDecimal actualSalary) {
        PayrollRecord payrollRecord = PayrollRecord.builder()
                .payrollId(IdUtil.getId())
                .empId(empId)
                .payable(payable)
                .deduction(deduction)
                .actualSalary(actualSalary)
                .releaseDate(TimeUtil.getTimestamp())
                .issuer(Constant.ESMS)
                .confirmReceipt(Boolean.FALSE)
                .build();
        try {
            PayrollRecord saveResult = payrollRecordService.savePayrollRecord(payrollRecord);
            System.out.println("[ " + saveResult + " ]");
        } catch (Exception e) {
            log.error("保存 [{}] 的工资发放记录失败，请查看日志信息... [{}]", empId, e.getCause());
        }
    }
}
