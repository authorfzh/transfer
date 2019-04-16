package cn.easyliao.transfer.domain;

import cn.easyliao.transfer.common.constant.tips.Tip;


public class ReturnSuccessTip extends Tip {

	  protected int  id = 0;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReturnSuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}
}
