package com.insigma.cloud.base.serviceimpl.common.excel;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.common.excel.ApiExcelImpTestMapper;
import com.insigma.cloud.base.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.cloud.base.service.common.excel.ApiExcelImpTestService;
import com.insigma.cloud.base.service.common.excel.ExcelVS;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.DemoAc01ExcelTemp;
import com.insigma.mvc.model.SysExcelBatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 文件excel上传功能
 * 
 * @author admin
 *
 */

@Service(value="excelimptestservice")
public class ApiExcelImpTestServiceImpl  implements ApiExcelImpTestService,ExcelVS {

	private static Log log=LogFactory.getLog(ApiFuPingExcelImportServiceImpl.class);
	
	@Resource
	private ApiFileUploadMapper apiFileUploadMapper;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	 
	@Resource
	private ApiExcelImpTestMapper apiExcelImpTestMapper;

	// excel标准列名及对应指标名,用于格式检验及文件导出格式生成
	private static String[]HEADERS = new String[] {"编号,excel_rownum", "姓名,aac003","身份证号码,aac002","性别,aac004","出生日期,aac006","描述,aae009","错误原因,excel_errormsg"};

	/**
	 * 数据处理 增加到临时表
	 */
	@Override
	@Transactional
	public void executeListToTemp(List<String[]> list, SysExcelBatch sexcelbatch) throws AppException {
		/**2 将数据导入到临时表中*/
		if(list.size()>1){
			// 根据第一列是否与标准格式匹配，判断导入的excel格式是否正确
			String[] cells = list.get(0);
			// 是否匹配
			boolean eq = true;
			
			//列长度是否小于要求解析的数据长度
			if(cells.length<Integer.parseInt(sexcelbatch.getMincolumns())){
				eq = false;
			}else{
				// excel列循环
				for (int j = 0; j < Integer.parseInt(sexcelbatch.getMincolumns()); j++) {
					if (!cells[j].equals(HEADERS[j].split(",")[0])) {
						eq = false;
						break;
					}
				}
			}
		
			// 如果不匹配
			if (!eq) {
				sexcelbatch.setExcel_batch_status("4");//发生异常
				sexcelbatch.setExcel_batch_rt_msg("所用的excel格式不匹配,请确认");
				apiFileUploadMapper.updateExelBatch(sexcelbatch);
				throw new AppException("所用的excel格式不匹配,请确认");
			}
		}else{
			sexcelbatch.setExcel_batch_status("4");//发生异常
			sexcelbatch.setExcel_batch_rt_msg("所用的excel没有业务数据,请确认格式是否正确");
			apiFileUploadMapper.updateExelBatch(sexcelbatch);
			throw new AppException("所用的excel没有业务数据,请确认格式是否正确");
		}
		// 从第二行开始取数据

		// 新获取一个模式为BATCH，自动提交为false的session
		// 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// 通过新的session获取mapper
		ApiExcelImpTestMapper mapper = session.getMapper(ApiExcelImpTestMapper.class);
		try {
			// excel行循环
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				DemoAc01ExcelTemp temp = new DemoAc01ExcelTemp();
				temp.setExcel_batch_number(sexcelbatch.getExcel_batch_number());//导入临时表之导入批次号
				temp.setExcel_rownum(new Long(i));
				temp.setExcel_temp_id(UUID.randomUUID().toString());
				//基本信息 14
				temp.setAac003(tempstr[1]);//tempstr 从0开始计数
				temp.setAac002(tempstr[2]);//身份证号码
				temp.setAac004(tempstr[3]);//性别
				temp.setAac006(tempstr[4]);//出生日期
				temp.setAae009(tempstr[5]);//备注
				
				mapper.insertExcelTempData(temp);
				if (i % 5000 == 0 || i == list.size() - 1) {
					// 手动每1000个一提交，提交后无法回滚
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					//更新当前导入数据状态 10%+20%的百份比 导入临时表给30%的分段比例
					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
					sexcelbatch.setExcel_batch_data_status(statusnumber);
					apiFileUploadMapper.updateExelBatch(sexcelbatch);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 没有提交的数据可以回滚
			session.rollback();
			//throw new AppException(e);
		} finally {
			session.close();
		}
		//行总数
		sexcelbatch.setExcel_batch_total_count(new Long(list.size()));
		sexcelbatch.setExcel_batch_status("2");//解析临时表
		//更新文件记录
		apiFileUploadMapper.updateExelBatch(sexcelbatch);
		
	}
	/**
	 * 执行数据处理过程
	 * @param executeProc
	 */
	@Override
	@Async
	public void executeProc(final SysExcelBatch sexcelbatch) throws  AppException{
		/**3 调用过程处理数据*/
		//获取批次号
		final SysExcelBatch newsexcelbatch=apiFileUploadMapper.getExcelBatchById(sexcelbatch.getExcel_batch_id());
		try {
			   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
			   // 通过新的session获取mapper
			   ApiExcelImpTestMapper mapper = session.getMapper(ApiExcelImpTestMapper.class);
				Date start=new Date();
				log.info("调用过程开始时间"+new Date().toLocaleString());
				//调用过程处理数据
				mapper.executeProc(newsexcelbatch);
				Date end=new Date();
				Long cost=end.getTime()-start.getTime();
				log.info("调用过程结束时间"+new Date().toLocaleString()+"花费"+cost/1000+"s");
				//执行是否成功
				if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){

				}else{
					log.info("导入数据失败,失败原因"+newsexcelbatch.getExcel_batch_rt_msg());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询导入结果 分页
	 */
	@Override
	public PageInfo queryExcelInfoList(DemoAc01ExcelTemp demoAc01ExcelTemp) {
		PageHelper.offsetPage(demoAc01ExcelTemp.getOffset(), demoAc01ExcelTemp.getLimit());
		List<DemoAc01ExcelTemp> list = apiExcelImpTestMapper.queryExcelInfoList(demoAc01ExcelTemp);
		PageInfo<DemoAc01ExcelTemp> pageinfo = new PageInfo<DemoAc01ExcelTemp>(list);
		return pageinfo;
	}

	@Override
	public String queryAllExcelInfoList(DemoAc01ExcelTemp demoAc01ExcelTemp) {
		return null;
	}

}