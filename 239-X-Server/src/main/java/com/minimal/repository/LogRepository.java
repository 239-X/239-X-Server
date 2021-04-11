package com.minimal.repository;

import com.minimal.common.sdk.log.OperateLogPO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author linzhiqinag
 * @date 2019/4/26
 */
public interface LogRepository extends MongoRepository<OperateLogPO, String> {

}
