package cn.edu.hdu.innovate.medical.mapper;


import cn.edu.hdu.innovate.medical.domain.MedicalRoom;

import java.util.List;

public interface MedicalRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MedicalRoom record);

    MedicalRoom selectByPrimaryKey(Long id);

    List<MedicalRoom> selectAll();

    int updateByPrimaryKey(MedicalRoom record);

    /**
     * 通过负责人id找到对应的medicalRoom
     * @param id
     * @return
     */
	MedicalRoom selectByPrincipalId(Long id);
	
	/**
	 * 更新账户信息
	 * @param medicalRoom
	 */
	void updateAmount(MedicalRoom medicalRoom);
	
	
}