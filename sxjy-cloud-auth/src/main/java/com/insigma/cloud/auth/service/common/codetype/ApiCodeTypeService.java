package com.insigma.cloud.auth.service.common.codetype;

import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;

import java.util.List;


/**
 * Ö÷Ò³service
 *
 * @author admin
 */
public interface ApiCodeTypeService {

    List<CodeType> getInitcodetypeList();

    List<CodeValue> getInitCodeValueList(CodeType code_type);

    List<CodeValue> getChildrenByParentId(String code_type, String code_value);

    List<CodeValue> getMulticodeValuebyType(String code_type);

    List<CodeValue> queryCodeValueByCodeTypeAndParent(CodeValue codevalue);
    
    List<CodeValue> getCodeValueTree(CodeValue codevalue);
}
