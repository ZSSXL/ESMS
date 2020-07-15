package com.zss.esms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/13 16:36
 * @desc manager - DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO implements Serializable {

    @NotEmpty
    private String managerName;

    @NotEmpty
    private String managerPassword;
}
