package com.insigma.cloud.base.serviceimpl.common.codetype;

import com.insigma.cloud.base.dao.common.codetype.ApiCodeTypeMapper;
import com.insigma.cloud.base.service.common.codetype.ApiCodeTypeService;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author admin
 */
@Service
public class ApiCodeTypeServiceImpl implements ApiCodeTypeService {

    @Autowired
    private ApiCodeTypeMapper apiCodeTypeMapper;

    private static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    @Override
    public List<CodeType> getInitcodetypeList() {
        List<CodeType> list= apiCodeTypeMapper.getInitcodetypeList();
        System.out.println(list.size());
        return list;
    }

    @Override
    public List<CodeValue> getInitCodeValueList(CodeType code_type) {
        List<CodeValue> list= apiCodeTypeMapper.getInitCodeValueList(code_type);
        System.out.println(list.size());
        return list;
    }

    @Override
    public List<CodeValue> getChildrenByParentId(String code_type, String code_value) {
        if ("AAB301".equals(code_type) && isContainChinese(code_value)) {
            code_value = apiCodeTypeMapper.getByParentName(code_value).getCode_value();
        }
        return apiCodeTypeMapper.getChildrenByParentId(code_type, code_value);
    }

    @Override
    public List<CodeValue> getMulticodeValuebyType(String code_type) {
        CodeType codetype = apiCodeTypeMapper.getCodeTypeInfo(code_type);
        if (codetype.getCode_root_value() == null) {
            return null;
        }
        return getChildredCodeValueList(code_type, codetype.getCode_root_value());
    }


    /**
     *
     * @param code_type
     * @param code_value
     * @return
     */
    private List<CodeValue> getChildredCodeValueList(String code_type, String code_value) {
        List<CodeValue> codevaluelist = apiCodeTypeMapper.getChildrenByParentId(code_type, code_value);
        if (codevaluelist != null) {
            for (int i = 0; i < codevaluelist.size(); i++) {
                CodeValue codevalue = codevaluelist.get(i);
                codevalue.setChildren(getChildredCodeValueList(code_type, codevalue.getCode_value()));
                codevaluelist.set(i, codevalue);
            }
        }
        return codevaluelist;
    }

    private static boolean isContainChinese(String str) {
        Matcher matcher = p.matcher(str);
        return matcher.find();
    }

    @Override
    public List<CodeValue>queryCodeValueByCodeTypeAndParent( CodeValue codevalue) {
        return apiCodeTypeMapper.queryCodeValueByCodeTypeAndParent(codevalue);
    }

    @Override
    public List<CodeValue> getCodeValueTree(CodeValue codevalue) {
        return apiCodeTypeMapper.getCodeValueTree(codevalue);
    }
}