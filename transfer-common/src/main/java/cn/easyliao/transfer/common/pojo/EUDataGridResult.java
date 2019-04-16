package cn.easyliao.transfer.common.pojo;

import java.util.List;

public class EUDataGridResult {

	private long total;
	private List<?> rows;
	private List<?> footer;
	public List<?> getFooter() {
		return footer;
	}
	public void setFooter(List<?> footer) {
		this.footer = footer;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
