package com.wsx.friends.dangdang.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;


public class UserKeyTableSharding implements SingleKeyTableShardingAlgorithm<String> {

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		Collection<String> resutl = new LinkedHashSet<String>(tableNames.size());
		Range<String> range = shardingValue.getValueRange();
		if (null != range) {
			
		}
		return resutl;
	}

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		for (String tablename : tableNames) {
			if (tablename.endsWith(String.valueOf(Math.abs(shardingValue.getValue().hashCode()) % 10))) {
				return tablename;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(tableNames.size());
		for (String key : shardingValue.getValues()) {
			for (String talbename : tableNames) {
				if (talbename.endsWith(String.valueOf(Math.abs(key.hashCode()) % 10))) {
					result.add(talbename);
				}
			}
		}
		return result;
	}

}
