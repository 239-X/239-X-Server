package com.minimal.common.api.dto;

import com.minimal.common.sdk.TablePage;
import lombok.Data;

/**
 *
 * @author 配置文件dto
 * @date 2021-05-03
 */
@Data
public class ConfigurationDto extends TablePage{
    /**
     * 配置key
     */
    private String configKey;

    /**
     * 配置value
     */
    private String configValue;
}