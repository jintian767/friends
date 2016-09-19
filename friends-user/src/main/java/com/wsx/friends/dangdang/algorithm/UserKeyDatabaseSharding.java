package com.wsx.friends.dangdang.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

public class UserKeyDatabaseSharding implements SingleKeyDatabaseShardingAlgorithm<String> {

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		Range<String> range = shardingValue.getValueRange();
		if (null != range) {
			
		}
		return result;
	}

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		for (String key : availableTargetNames) {
			if (key.endsWith(String.valueOf(Math.abs(shardingValue.getValue().hashCode()) % 2))) {
				return key;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (String key : shardingValue.getValues()) {
			for (String table : availableTargetNames) {
				if (table.endsWith(String.valueOf(Math.abs(key.hashCode()) % 2))) {
					result.add(table);
				}
			}
		}
		return result;
	}

}
