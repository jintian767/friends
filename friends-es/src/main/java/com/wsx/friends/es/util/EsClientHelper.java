package com.wsx.friends.es.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 池化得客户端，避免每次都创建新的客户端
 * 后期再扩展成队列
 * @author wangshuaixin
 *
 */
public class EsClientHelper {
	
	private Settings settings;
	
	private Map<String, Client> clientMap = new ConcurrentHashMap<String, Client>();
	
	private static String clusterName = "rscluster";
	
	private Map<String, Integer> ips = new HashMap<String, Integer>();
	
	private EsClientHelper() {
		init();
	}
	
	
	private void init()  {
		constructIps();
		settings = Settings.settingsBuilder()
				.put("client.transport.sniff",true)
                .put("cluster.name",clusterName).build();
		constructClients(settings, getList(ips));
	}


	/**
	 * IP转换成客户端可识别的对象
	 * @param ips
	 * @return
	 */
	private List<InetSocketTransportAddress> getList(Map<String, Integer> ips) {
		List<InetSocketTransportAddress> addressList = new ArrayList<InetSocketTransportAddress>();
		try {
			for (String ip : ips.keySet()) {
	            addressList.add(new InetSocketTransportAddress(InetAddress.getByName(ip), ips.get(ip)));
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        return addressList;
	}

	/**
	 * 构造客户端的参数
	 * @param setting
	 * @param transportAddress
	 */
	private void constructClients(Settings setting, List<InetSocketTransportAddress> transportAddress) {
		Client client = new TransportClient.Builder().settings(setting).build().
				addTransportAddresses(transportAddress.toArray(new InetSocketTransportAddress[transportAddress.size()]));
		clientMap.put(setting.get("cluster.name"), client);
		
	}


	/**
	 * 此方法后期可以重写，改成读取配置文件的形式
	 */
	private void constructIps() {
		ips.put("127.0.0.1", 9300);
		ips.put("127.0.0.1", 9301);
	}


	/**
	 * 安全的单例
	 * @return
	 */
	public static final EsClientHelper getInstance() {
		return EsClientHolder.instance;
	}
	
	private static class EsClientHolder {
		private static final EsClientHelper instance = new EsClientHelper();
	}
	
	public Client getClient() {
        return getClient(clusterName);
    }

    public Client getClient(String clusterName) {
        return clientMap.get(clusterName);
    }

}
