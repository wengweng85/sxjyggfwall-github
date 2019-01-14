package com.insigma.cloud.auth.dao.common.codetype;

import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SelectCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxx
 */
@Mapper
public interface ApiCodeTypeMapper {

	CodeType  getCodeTypeInfo(String codeType);
	
    List<CodeType> getInitcodetypeList();

    List<CodeValue> getInitCodeValueList(CodeType code_type);
    
    List<SelectCode> getSelectCodeValueList(String code_type);

    CodeValue getByParentName(String code_name);

    List<CodeValue> getChildrenByParentId(@Param("code_type") String code_type, @Param("code_value") String code_value);
    
    List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue);
    
    List<CodeValue> getCodeValueTree(CodeValue codevalue);
}
