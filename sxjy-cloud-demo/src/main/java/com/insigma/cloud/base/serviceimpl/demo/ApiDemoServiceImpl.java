package com.insigma.cloud.base.serviceimpl.demo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.demo.ApiDemoMapper;
import com.insigma.cloud.base.service.demo.ApiDemoService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.DemoAc01;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * demo ac01 service impl
 * @author admin
 *
 */

@Service
@Transactional
public class ApiDemoServiceImpl  implements ApiDemoService {

	@Resource
	private ApiDemoMapper apidemoMapper;
	
	/**
	 * 分页查询
	 */
	
	@Override
	public PageInfo<DemoAc01> getDemoAc01List(DemoAc01 ac01) {
		PageHelper.offsetPage(ac01.getOffset(), ac01.getLimit());
		if(StringUtils.isNotEmpty(ac01.getAac011())){
			ac01.setA_aac011(ac01.getAac011().split(","));
		}
		List<DemoAc01> list=apidemoMapper.getDemoAc01List(ac01);
		PageInfo<DemoAc01> pageinfo = new PageInfo<DemoAc01>(list);
		return pageinfo;
	}

	/**
	 * 通过id删除demo数据
	 */
	@Override
	public int deleteDemoById(String aac001) {
		int deletenum=apidemoMapper.deleteByPrimaryKey(aac001);
		return deletenum;
	}

	
	/**
	 * 批量删除
	 */
	@Override
	public int batDeleteDemoData(DemoAc01 ac01) {
		String [] ids=ac01.getSelectnodes().split(",");
		int batdeletenum=apidemoMapper.batDeleteData(ids);
		return batdeletenum;
	}

	/**
	 * 通过个人编号获取信息
	 */
	@Override
	public DemoAc01 getDemoById(String aac001) {
		return apidemoMapper.selectByPrimaryKey(aac001);
	}

	/**
	 * 保存
	 */
	@Override
	public void saveDemoData(DemoAc01 ac01) throws AppException {
		ac01.setAae010(SUserUtil.getName());
		//判断身份证号码是否重复
		int aac002num=apidemoMapper.selectByAac002(ac01);
		if(aac002num>0){
			throw new AppException("此身份证号码"+ac01.getAac002()+"已经存在，不能重复,请输入正确的号码");
		}
		//判断是否是更新
		if(StringUtils.isEmpty(ac01.getAac001())){
			 apidemoMapper.insertSelective (ac01);
		}else{
			 apidemoMapper.updateByPrimaryKey(ac01);
		}
	}

	/**
	 * 
	 */
	@Override
	public DemoAc01 getDemoNameById(String aac001) {
		return apidemoMapper.selectNameByPrimaryKey(aac001);
	}

	
	/**
	 * 通过业务id和文件id更新
	 */
	@Override
	public int updateDemoAc01DemBusUuid(DemoAc01 ac01) {
		int updatenum= apidemoMapper.updateDemoAc01DemBusUuid(ac01);
		return updatenum;
	}

}
