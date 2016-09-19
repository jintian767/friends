package com.wsx.friends.dangdang.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

public class UserSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % 3 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
	}

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
		for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % 3 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
		 Collection<String> result = new LinkedHashSet<String>(tableNames.size());
	        for (Integer value : shardingValue.getValues()) {
	            for (String tableName : tableNames) {
	                if (tableName.endsWith(value % 3 + "")) {
	                    result.add(tableName);
	                }
	            }
	        }
	        return result;
	}

}
