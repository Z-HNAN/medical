package cn.edu.hdu.innovate.medical.web.test;

import cn.edu.hdu.innovate.medical.domain.Bill;
import cn.edu.hdu.innovate.medical.domain.SystemDictionaryItem;
import cn.edu.hdu.innovate.medical.mapper.BillMapper;
import cn.edu.hdu.innovate.medical.mapper.SystemDictionaryItemMapper;
import cn.edu.hdu.innovate.medical.query.BillAuditQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MgrsiteAppTest {
	
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryMapper ;
	
	@Autowired
	private BillMapper billMapper;
	
	@Test
	public void test1() throws Exception {
		SystemDictionaryItem item = this.systemDictionaryMapper.selectByPrimaryKey(2L);
		System.out.println(item.getTitle());
		
		Bill bill = this.billMapper.selectByPrimaryKey(1L);
		System.out.println(bill.getSeriousType().getTitle());
	}
	
	@Test
	public void test2() throws Exception {
		BillAuditQueryObject qo = new BillAuditQueryObject();
		ArrayList<Integer> depts = new ArrayList<>();
		depts.add(2);
		qo.setDepts(depts);
		List<Bill> list = this.billMapper.queryInComIdAuditList(qo);
		System.out.println(list.toString());
		
	}

	@Test
	public void test3() throws Exception {
		BigDecimal b1 = new BigDecimal("0.00");
		BigDecimal b2 = BigDecimal.ZERO;
		System.out.println(b1.compareTo(b2) == 0);
		
	}
	
}
