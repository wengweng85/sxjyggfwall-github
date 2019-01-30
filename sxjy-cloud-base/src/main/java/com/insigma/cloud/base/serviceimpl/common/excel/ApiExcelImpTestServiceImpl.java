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
 * �ļ�excel�ϴ�����
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

	// excel��׼��������Ӧָ����,���ڸ�ʽ���鼰�ļ�������ʽ����
	private static String[]HEADERS = new String[] {"���,excel_rownum", "����,aac003","���֤����,aac002","�Ա�,aac004","��������,aac006","����,aae009","����ԭ��,excel_errormsg"};

	/**
	 * ���ݴ��� ���ӵ���ʱ��
	 */
	@Override
	@Transactional
	public void executeListToTemp(List<String[]> list, SysExcelBatch sexcelbatch) throws AppException {
		/**2 �����ݵ��뵽��ʱ����*/
		if(list.size()>1){
			// ���ݵ�һ���Ƿ����׼��ʽƥ�䣬�жϵ����excel��ʽ�Ƿ���ȷ
			String[] cells = list.get(0);
			// �Ƿ�ƥ��
			boolean eq = true;
			
			//�г����Ƿ�С��Ҫ����������ݳ���
			if(cells.length<Integer.parseInt(sexcelbatch.getMincolumns())){
				eq = false;
			}else{
				// excel��ѭ��
				for (int j = 0; j < Integer.parseInt(sexcelbatch.getMincolumns()); j++) {
					if (!cells[j].equals(HEADERS[j].split(",")[0])) {
						eq = false;
						break;
					}
				}
			}
		
			// �����ƥ��
			if (!eq) {
				sexcelbatch.setExcel_batch_status("4");//�����쳣
				sexcelbatch.setExcel_batch_rt_msg("���õ�excel��ʽ��ƥ��,��ȷ��");
				apiFileUploadMapper.updateExelBatch(sexcelbatch);
				throw new AppException("���õ�excel��ʽ��ƥ��,��ȷ��");
			}
		}else{
			sexcelbatch.setExcel_batch_status("4");//�����쳣
			sexcelbatch.setExcel_batch_rt_msg("���õ�excelû��ҵ������,��ȷ�ϸ�ʽ�Ƿ���ȷ");
			apiFileUploadMapper.updateExelBatch(sexcelbatch);
			throw new AppException("���õ�excelû��ҵ������,��ȷ�ϸ�ʽ�Ƿ���ȷ");
		}
		// �ӵڶ��п�ʼȡ����

		// �»�ȡһ��ģʽΪBATCH���Զ��ύΪfalse��session
		// ����Զ��ύ����Ϊtrue,���޷������ύ����������Ϊ���ͳһ�ύ�����ܵ����ڴ����
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// ͨ���µ�session��ȡmapper
		ApiExcelImpTestMapper mapper = session.getMapper(ApiExcelImpTestMapper.class);
		try {
			// excel��ѭ��
			for (int i = 1; i < list.size(); i++) {
				String[] tempstr = list.get(i);
				DemoAc01ExcelTemp temp = new DemoAc01ExcelTemp();
				temp.setExcel_batch_number(sexcelbatch.getExcel_batch_number());//������ʱ��֮�������κ�
				temp.setExcel_rownum(new Long(i));
				temp.setExcel_temp_id(UUID.randomUUID().toString());
				//������Ϣ 14
				temp.setAac003(tempstr[1]);//tempstr ��0��ʼ����
				temp.setAac002(tempstr[2]);//���֤����
				temp.setAac004(tempstr[3]);//�Ա�
				temp.setAac006(tempstr[4]);//��������
				temp.setAae009(tempstr[5]);//��ע
				
				mapper.insertExcelTempData(temp);
				if (i % 5000 == 0 || i == list.size() - 1) {
					// �ֶ�ÿ1000��һ�ύ���ύ���޷��ع�
					session.commit();
					// �����棬��ֹ���
					session.clearCache();
					//���µ�ǰ��������״̬ 10%+20%�İٷݱ� ������ʱ���30%�ķֶα���
					int statusnumber=new Float((i+0f)/(list.size()+0f)*20).intValue()+10;
					sexcelbatch.setExcel_batch_data_status(statusnumber);
					apiFileUploadMapper.updateExelBatch(sexcelbatch);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// û���ύ�����ݿ��Իع�
			session.rollback();
			//throw new AppException(e);
		} finally {
			session.close();
		}
		//������
		sexcelbatch.setExcel_batch_total_count(new Long(list.size()));
		sexcelbatch.setExcel_batch_status("2");//������ʱ��
		//�����ļ���¼
		apiFileUploadMapper.updateExelBatch(sexcelbatch);
		
	}
	/**
	 * ִ�����ݴ������
	 * @param executeProc
	 */
	@Override
	@Async
	public void executeProc(final SysExcelBatch sexcelbatch) throws  AppException{
		/**3 ���ù��̴�������*/
		//��ȡ���κ�
		final SysExcelBatch newsexcelbatch=apiFileUploadMapper.getExcelBatchById(sexcelbatch.getExcel_batch_id());
		try {
			   SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
			   // ͨ���µ�session��ȡmapper
			   ApiExcelImpTestMapper mapper = session.getMapper(ApiExcelImpTestMapper.class);
				Date start=new Date();
				log.info("���ù��̿�ʼʱ��"+new Date().toLocaleString());
				//���ù��̴�������
				mapper.executeProc(newsexcelbatch);
				Date end=new Date();
				Long cost=end.getTime()-start.getTime();
				log.info("���ù��̽���ʱ��"+new Date().toLocaleString()+"����"+cost/1000+"s");
				//ִ���Ƿ�ɹ�
				if(newsexcelbatch.getExcel_batch_rt_code() .equals("1")){

				}else{
					log.info("��������ʧ��,ʧ��ԭ��"+newsexcelbatch.getExcel_batch_rt_msg());
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ������ ��ҳ
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