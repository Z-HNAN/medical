package cn.edu.hdu.zhn.medical.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionaryQueryObject extends QueryObject{
	
	private int isdel;	// 此记录是否被删
}
