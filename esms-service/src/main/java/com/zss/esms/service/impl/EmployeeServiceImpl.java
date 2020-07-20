package com.zss.esms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zss.esms.comment.Constant;
import com.zss.esms.model.dto.LoginDTO;
import com.zss.esms.model.dto.ProfileDTO;
import com.zss.esms.model.entity.mongdb.PaymentMethod;
import com.zss.esms.model.entity.mongdb.Profile;
import com.zss.esms.model.entity.postgres.Employee;
import com.zss.esms.page.Pagenation;
import com.zss.esms.repository.EmployeeRepository;
import com.zss.esms.repository.PaymentMethodRepository;
import com.zss.esms.repository.ProfileRepository;
import com.zss.esms.service.EmployeeService;
import com.zss.esms.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 14:10
 * @desc 雇员 - 实现服务层接口方法
 */
@Slf4j
@SuppressWarnings("unused")
@Service(interfaceClass = EmployeeService.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ProfileRepository profileRepository;

    @Resource
    private PaymentMethodRepository paymentMethodRepository;

    /**
     * 对三个表进行操作，psql(Employee), mongodb(Profile, PaymentMethod)
     * 1. 生成Employee存入
     * 2. 生成Profile存入
     * 3. 生成PaymentMethod存入
     *
     * @param fileBytes        文件字节流
     * @param originalFilename 文件名
     * @param cells            需要的列数
     * @return Integer 成功添加的数量
     */
    @Override
    public Integer createManayEmp(byte[] fileBytes, String originalFilename, Integer cells) {
        List<Map<String, String>> cellList = ExcelUtil.analysisExcel(fileBytes, originalFilename, cells);
        if (cellList == null) {
            return 0;
        }
        Integer succuseCount = 0;
        for (Map<String, String> tempMap : cellList) {
            ProfileDTO profileDTO = organizeContent(tempMap);
            String empId = IdUtil.getId();
            // 构建Employee
            Employee employee = Employee.builder()
                    .empId(empId)
                    .empName(profileDTO.getEmpName())
                    .empPassword(EncryptionUtil.encrypt("123456"))
                    .build();
            // 构建PaymentMethod
            PaymentMethod paymentMethod = PaymentMethod.builder()
                    .methodId(IdUtil.getId())
                    .empId(empId)
                    .build();
            if (StringUtils.equals(profileDTO.getPaymentMethod(), Constant.PaymentMethod.CHECK_PAYMENT)) {
                Map<String, String> checkMap = new HashMap<>(4);
                checkMap.put("recipient", profileDTO.getRecipient());
                checkMap.put("recipientAddress", profileDTO.getRecipientAddress());
                checkMap.put("recipientPhone", profileDTO.getRecipientPhone());
                checkMap.put("remark", profileDTO.getRemark());
                paymentMethod.setChoice(checkMap);
            }
            if (StringUtils.equals(profileDTO.getPaymentMethod(), Constant.PaymentMethod.BANK_TRANSFER)) {
                Map<String, String> bankMap = new HashMap<>(2);
                bankMap.put("cardNumber", profileDTO.getBankCardNumber());
                bankMap.put("cardOwner", profileDTO.getBankCardOwner());
                paymentMethod.setChoice(bankMap);
            }
            // 构建Profile
            Profile profile = Profile.builder()
                    .profileId(IdUtil.getId())
                    .empId(empId)
                    .empName(profileDTO.getEmpName())
                    .empEmail(profileDTO.getEmpEmail())
                    .empType(profileDTO.getEmpType())
                    .entryTime(TimeUtil.getTimestamp())
                    .build();
            if (StringUtils.equals(profileDTO.getSociety(), "是")) {
                profile.setSociety(true);
                profile.setWeeklyDeduction(new BigDecimal("10.0"));
            } else {
                profile.setSociety(false);
                profile.setWeeklyDeduction(new BigDecimal("0"));
            }
            switch (profileDTO.getEmpType()) {
                case Constant.EmployeeType.HOURLY:
                    profile.setSalary(MapUtil.create("hourlySalary", "40.0"));
                    break;
                case Constant.EmployeeType.MONTHLY:
                    profile.setSalary(MapUtil.create("monthlySalary", "6400.0"));
                    break;
                case Constant.EmployeeType.SALARIED:
                    profile.setSalary(MapUtil.create("commission", "1600.0"));
                    break;
                default:
                    break;
            }
            Employee existInDb = employeeRepository.findByEmpName(profileDTO.getEmpName());
            if (existInDb == null) {
                // 保存数据
                try {
                    employeeRepository.save(employee);
                    paymentMethodRepository.save(paymentMethod);
                    profileRepository.save(profile);
                    succuseCount++;
                } catch (Exception e) {
                    log.error("创建员工[ " + employee.getEmpName() + " ]发生未知异常...[{}]", e.getMessage());
                }
            }
        }
        return succuseCount;
    }

    @Override
    public Pagenation showAllEmployees(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "entryTime"));
        return PageUtil.converter(profileRepository.findAll(pageRequest));
    }

    @Override
    public Employee employeeLogin(LoginDTO loginDTO) {
        return employeeRepository.findByEmpNameAndEmpPassword(loginDTO.getUsername(),
                EncryptionUtil.encrypt(loginDTO.getPassword()));
    }

    // =============================== 内部私有方法 =============================== //

    /**
     * 整理内容
     *
     * @param cell 列
     * @return ProfileDTO
     */
    private ProfileDTO organizeContent(Map<String, String> cell) {
        ProfileDTO profileDTO = ProfileDTO.builder()
                .empName(cell.get(Constant.Profile.EMP_NAME))
                .empEmail(cell.get(Constant.Profile.EMP_EMAIL))
                .empType(cell.get(Constant.Profile.EMP_TYPE))
                .society(cell.get(Constant.Profile.SOCIETY))
                .paymentMethod(cell.get(Constant.Profile.PAYMENT_METHOD))
                .build();
        // 如果支付类型是 - 银行转账
        if (StringUtils.equals(cell.get(Constant.Profile.PAYMENT_METHOD), Constant.PaymentMethod.BANK_TRANSFER)) {
            profileDTO.setBankCardNumber(cell.get(Constant.Profile.BANK_CARD_NUMBER));
            profileDTO.setBankCardOwner(cell.get(Constant.Profile.BANK_CARD_OWNER));
        }
        // 如果支付方式是 - 支票支付
        if (StringUtils.equals(cell.get(Constant.Profile.PAYMENT_METHOD), Constant.PaymentMethod.CHECK_PAYMENT)) {
            profileDTO.setRecipient(cell.get(Constant.Profile.RECIPIENT));
            profileDTO.setRecipientPhone(cell.get(Constant.Profile.RECIPIENT_PHONE));
            profileDTO.setRecipientAddress(cell.get(Constant.Profile.RECIPIENT_ADDRESS));
            profileDTO.setRemark(cell.get(Constant.Profile.REMARK));
        }
        return profileDTO;
    }
}
