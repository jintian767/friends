package com.wsx.friends.core.redis.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wangshuaixin
 *
 * @param <M>
 */
public class ListTranscoder<M extends Serializable> extends SerializeTranscoder {

	@SuppressWarnings("unchecked")
	@Override
	public byte[] serialize(Object value) {
		if (null == value) {
			throw new NullPointerException("");
		}
		
		List<M> list = (List<M>) value;
		
		byte[] results = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			for (M m : list) {
				oos.writeObject(m);
			}
			
			oos.close();
			bos.close();
			results = bos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(oos);
			close(bos);
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> deserialize(byte[] bytes) {
		List<M> list = new ArrayList<M>();
		if (null == bytes) {
			return list;
		}
		
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			while (true) {
				M m = (M) ois.readObject();
				if (null == m) {
					break;
				}
				list.add(m);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(ois);
			close(bis);
		}
		
		return list;
	}

}
