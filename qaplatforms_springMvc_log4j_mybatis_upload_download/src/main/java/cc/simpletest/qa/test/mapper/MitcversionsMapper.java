package cc.simpletest.qa.test.mapper;

import cc.simpletest.qa.test.model.Mitcversions;
import cc.simpletest.qa.test.model.MitcversionsWithBLOBs;

public interface MitcversionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MitcversionsWithBLOBs record);

    int insertSelective(MitcversionsWithBLOBs record);

    MitcversionsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MitcversionsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MitcversionsWithBLOBs record);

    int updateByPrimaryKey(Mitcversions record);
}