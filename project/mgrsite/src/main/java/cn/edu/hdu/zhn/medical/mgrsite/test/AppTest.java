package cn.edu.hdu.zhn.medical.mgrsite.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.edu.hdu.zhn.medical.base.domain.Bill;
import cn.edu.hdu.zhn.medical.base.domain.SystemDictionaryItem;
import cn.edu.hdu.zhn.medical.base.mapper.BillMapper;
import cn.edu.hdu.zhn.medical.base.mapper.SystemDictionaryItemMapper;
import cn.edu.hdu.zhn.medical.base.query.BillAuditQueryObject;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
	
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
