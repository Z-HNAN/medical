package cn.edu.hdu.innovate.medical.web.test;

import cn.edu.hdu.innovate.medical.mapper.BillMapper;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * 临时测试类
 * @author Z_HNAN
 *
 */
@SpringBootTest
public class WebsiteAppTest {
	
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
