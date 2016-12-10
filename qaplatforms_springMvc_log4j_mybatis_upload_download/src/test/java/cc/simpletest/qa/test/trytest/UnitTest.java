package cc.simpletest.qa.test.trytest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cc.simpletest.qa.test.dbutils.DbUtils;
import cc.simpletest.qa.test.mapper.MitcversionsMapper;
import cc.simpletest.qa.test.model.Mitcversions;

public class UnitTest {
	
	@Test
	public void testConnectSuccess(){
		
		SqlSession session=DbUtils.getSession();
		try {   
			MitcversionsMapper mitcversionsMapper = session.getMapper(MitcversionsMapper.class);   
			Mitcversions  mitcversions  = mitcversionsMapper.selectByPrimaryKey(2);   
			System.out.println(mitcversions.getId());
			System.out.println(mitcversions.getAuthorId());
			System.out.println(mitcversions.getStatus());
			System.out.println(mitcversions.getTcExternalId());
			System.out.println(mitcversions.getVersion());
		}finally{   
		    session.close();   
		}   
	}
		
		
}
