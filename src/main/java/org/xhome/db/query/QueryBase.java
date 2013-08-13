package org.xhome.db.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project xhome-db
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201311:19:16 PM
 * @description 
 */
@SuppressWarnings("rawtypes")
public class QueryBase implements Serializable {
	
	private static final long	serialVersionUID	= -5879637756379888847L;
	
	private final static long	defaultFirstRow		= 0L;
	private final static long	defaultTotalRow		= 0L;
	private final static long	firstPage			= 1L;
	private final static long	defaultPageSize		= 20L;
	private final static long	defaultTotalPage	= 0L;
	
	private long				firstRow			= defaultFirstRow;
	private long				maxRow				= defaultPageSize;
	private long				totalRow			= defaultTotalRow;
	
	private long				currentPage			= firstPage;
	private long				pageSize			= defaultPageSize;
	private long				totalPage			= defaultTotalPage;
	
	private Map<String, Object>	parameters			= new HashMap<String, Object>();
	private List				results;
	
	public boolean isFirstPage() {
		return this.currentPage == firstPage;
	}
	
	public boolean isLastPage() {
		return this.currentPage == this.totalPage;
	}
	
	public boolean nextPage() {
		if (this.totalRow == defaultTotalRow
				|| this.currentPage == this.totalPage) return false;
		this.setCurrentPage(this.getNextPage());
		return true;
	}
	
	public boolean previousPage() {
		if (this.totalRow == defaultTotalRow || this.currentPage == firstPage) return false;
		this.setCurrentPage(this.getPreviousPage());
		return true;
	}
	
	public long getNextPage() {
		long next = this.currentPage + 1;
		return next > this.totalPage ? this.totalPage : next;
	}
	
	public long getPreviousPage() {
		long previous = this.currentPage - 1;
		return previous < firstPage ? firstPage : previous;
	}
	
	public void setTotalRow(long totalRow) {
		if (totalRow > 0 && totalRow != this.totalRow) {
			this.totalRow = totalRow;
			this.totalPage = totalRow / this.pageSize;
			if (totalRow % this.pageSize != 0) this.totalPage++;
		}
	}
	
	public void setTotalRow() {
		this.setTotalRow(defaultTotalRow);
	}
	
	public long getTotalRow() {
		return totalRow;
	}
	
	public void setCurrentPage(long currentPage) {
		if (currentPage > 0) {
			this.currentPage = currentPage;
			this.firstRow = (currentPage - 1) * this.pageSize;
		}
	}
	
	public void setCurrentPage() {
		this.currentPage = firstPage;
		this.firstRow = (this.currentPage - 1) * this.pageSize;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}
	
	public void setPageSize(long pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
			this.maxRow = pageSize;
			this.setCurrentPage(this.currentPage);
		}
	}
	
	public void setPageSize() {
		this.pageSize = defaultPageSize;
		this.maxRow = defaultPageSize;
	}
	
	public long getPageSize() {
		return pageSize;
	}
	
	public long getDefaultPageSize() {
		return defaultPageSize;
	}
	
	public long getTotalPage() {
		return totalPage;
	}
	
	public void setFirstRow(long firstRow) {
		if (firstRow >= 0) {
			this.firstRow = firstRow;
			this.currentPage = 1 + (long) (firstRow / this.pageSize);
		}
	}
	
	public long getFirstRow() {
		return firstRow;
	}
	
	public void setMaxRow(long maxRow) {
		if (maxRow > 0) {
			this.pageSize = maxRow;
			this.maxRow = maxRow;
		}
	}
	
	public long getMaxRow() {
		return maxRow;
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
