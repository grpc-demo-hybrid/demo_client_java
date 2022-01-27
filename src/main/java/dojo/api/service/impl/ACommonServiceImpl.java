package dojo.api.service.impl;

import dojo.api.service.ICommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Fanmao.Li
 * @since 09/09/2019
 */
@Service
public class ACommonServiceImpl implements ICommonService {
    @Override
    public String process(String str) {
        return StringUtils.upperCase(str);
    }
}
