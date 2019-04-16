package cn.easyliao.transfer.common.constant.tips;

/**
 * 返回给前台的成功提示
 *
 * @author 
 */
public class SuccessTip extends Tip{
	
	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}
}
