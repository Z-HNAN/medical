package cn.edu.hdu.zhn.medical.website.test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSONObject;

import cn.edu.hdu.zhn.medical.base.domain.Bill;
import cn.edu.hdu.zhn.medical.base.mapper.BillMapper;

/**
 * 临时测试类
 * @author Z_HNAN
 *
 */
@SpringBootTest
public class AppTest {
	
	@Autowired
	private BillMapper billMapper;

	@Test
	public void testListJson() throws Exception {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(1);
		System.out.println(list);
		System.out.println(JSONObject.toJSONString(list));
		
	}
}
