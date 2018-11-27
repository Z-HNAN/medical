package cn.edu.hdu.zhn.medical.base.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSONResult {
	
	private boolean success = true;
	private String msg;

	public JSONResult() {
		super();
	}

	public JSONResult(String msg) {
		super();
		this.msg = msg;
	}
	
	public JSONResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	
}
