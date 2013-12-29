package org.xhome.db.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project xhome-db
 * @author 	jhat
 * @email 	cpf624@126.com
 * @date 	Dec 29, 20139:35:04 PM
 * @describe 
 */
@SuppressWarnings("rawtypes")
public class QueryBase implements Serializable {
	
	private static final long	serialVersionUID	= -5879637756379888847L;
	
	protected long					start		= 0;  // 查询开始行
	protected long					limit		= 20; // 分页限制
	protected long					total		= 0L; // 总行数
	protected long					page		= 1;  // 当前页
	protected long					totalPage	= 0;  // 总页数
	
	protected Map<String, Object>	parameters	= new HashMap<String, Object>(); // 查询参数
	protected List					results; // 查询结果
	
	public void setStart(long start) {
		if (start >= 0) {
			this.start = start;
			this.page = 1 + (long) (start / this.limit);
		}
	}
	
	public long getStart() {
		return start;
	}
	
	public void setLimit(long limit) {
		if (limit > 0) {
			this.limit = limit;
			this.setPage(this.page);
		}
	}
	
	public long getLimit() {
		return limit;
	}
	
	public void setTotal(long total) {
		if (total > 0 && total != this.total) {
			this.total = total;
			this.totalPage = total / this.limit;
			if (total % this.limit != 0) this.totalPage++;
		}
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setPage(long page) {
		if (page > 0) {
			this.page = page;
			this.start = (page - 1) * this.limit;
		}
	}
	
	public long getPage() {
		return page;
	}
	
	public boolean isFirstPage() {
		return this.page == 1;
	}
	
	public boolean isLastPage() {
		return this.page == this.totalPage;
	}
	
	public long getNextPage() {
		if (this.page < this.totalPage) {
			this.setPage(this.page + 1);
		}
		return this.page;
	}
	
	public long getPreviousPage() {
		if (this.page > 1) {
			this.setPage(this.page - 1);
		}
		return this.page;
	}
	
	public long getTotalPage() {
		return totalPage;
	}
	
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	public void removeParameter(String key) {
		parameters.remove(key);
	}
	
	public Object getParameter(String key) {
		return parameters.get(key);
	}
	
	public void setResults(List results) {
		this.results = results;
	}
	
	public List getResults() {
		return results;
	}
	
}
