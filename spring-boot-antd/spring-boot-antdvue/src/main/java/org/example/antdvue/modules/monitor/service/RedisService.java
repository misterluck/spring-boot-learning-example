package org.example.antdvue.modules.monitor.service;

import org.example.antdvue.modules.monitor.domain.RedisInfo;
import org.example.antdvue.modules.monitor.exception.RedisConnectException;

import java.util.List;
import java.util.Map;

public interface RedisService {

	/**
	 * 获取 redis 的详细信息
	 *
	 * @return List
	 */
	List<RedisInfo> getRedisInfo() throws RedisConnectException;

	/**
	 * 获取 redis key 数量
	 *
	 * @return Map
	 */
	Map<String, Object> getKeysSize() throws RedisConnectException;

	/**
	 * 获取 redis 内存信息
	 *
	 * @return Map
	 */
	Map<String, Object> getMemoryInfo() throws RedisConnectException;

}
