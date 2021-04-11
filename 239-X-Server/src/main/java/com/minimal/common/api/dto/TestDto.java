package com.minimal.common.api.dto;

import com.minimal.annotation.LongToString;
import lombok.Data;

/**
 * @author linzhiqiang
 * @date 2019/3/27
 */

@Data
public class TestDto {
    /**
     * ID标识
     */
//    @LongToString
    private Long id;

    /**
     *  名字
     */
    private String name;
}
