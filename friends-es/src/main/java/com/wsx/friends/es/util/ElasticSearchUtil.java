package com.wsx.friends.es.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class ElasticSearchUtil {
	
	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param data
	 * @return
	 */
	public static String addDocument(String index, String type, String id, String data) {
		Client client = EsClientHelper.getInstance().getClient();
		IndexResponse response = client.prepareIndex(index, type, id).setSource(data).get();
		return response.getId();
	}
	
	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public static Map<String, Object> getDocumentByID(String index, String type, String id) {
		Client client = EsClientHelper.getInstance().getClient();
		GetResponse response = client.prepareGet(index, type, id).get();
		return response.getSource();
	}
	
	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param another
	 * @return
	 */
	public static Map<String, Object> getThreadDocumentByID(String index, String type, String id, boolean another) {
		Client client = EsClientHelper.getInstance().getClient();
		GetResponse response = client.prepareGet(index, type, id).setOperationThreaded(another).get();
		return response.getSource();
	}
	
	public static List<Map<String, Object>> getDocumentByContent(String index, String type, String content) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Client client = EsClientHelper.getInstance().getClient();
		QueryBuilder builder = new MatchQueryBuilder("name", content);
		SearchRequestBuilder requestBuilder = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(builder);
		
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			list.add(hit.getSource());
		}
		return list;
	}
	
	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public static String delDocumentByID(String index, String type, String id) {
		Client client = EsClientHelper.getInstance().getClient();
		DeleteResponse response = client.prepareDelete(index, type, id).get();
		return response.getId();
	}
	

	/**
	 * 
	 * @param index
	 * @param type
	 * @param id
	 * @param data
	 * @return
	 */
	public static String updateDocumentByID(String index, String type, String id, String data) {
		Client client = EsClientHelper.getInstance().getClient();
		UpdateResponse response = client.prepareUpdate(index, type, id).setDoc(data).get();
		return response.getId();
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String updateDocument(UpdateRequest request) {
		Client client = EsClientHelper.getInstance().getClient();
		try {
			UpdateResponse response = client.update(request).get();
			return response.getId();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
