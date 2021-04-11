package com.minimal.common.sdk;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页数据，对分页请求的响应
 * @author WuBin
 */
public class Page<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Page<?> EMPTY = new EmptyPage<>();
	
	/**
	 * 空页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Page<T> empty() {
		return (Page<T>) EMPTY;
	}

	private List<T> content;
	private Long total;
	
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	private static class EmptyPage<T> extends Page<T> {
		private static final long serialVersionUID = 1L;

		@Override
		public List<T> getContent() {
			return Collections.emptyList();
		}

		@Override
		public void setContent(List<T> content) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long getTotal() {
			return 0;
		}

		@Override
		public void setTotal(Long total) {
			throw new UnsupportedOperationException();
		}
		
		private Object readResolve() {
            return EMPTY;
        }
	}
}
