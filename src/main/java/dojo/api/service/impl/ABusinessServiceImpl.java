package dojo.api.service.impl;

import dojo.ActionEnum;
import dojo.api.service.ICommonService;
import dojo.api.service.IBusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Fanmao.Li
 * @since 06/09/2019
 */
@Service
public class ABusinessServiceImpl implements IBusinessService {
    @Autowired
    private ICommonService iCommonService;

    /**
     * HANDLER_MAP is a sample to refactor if/switch code smell.
     * 对于if/switch这类code smell，可以使用表驱动的方式进行重构，HANDLER_MAP在这里只是起到一个例子的作用，可能跟本类的业务无关
     */
    static Map<ActionEnum, Function<String, String>> HANDLER_MAP = new HashMap<>();

    static {
        HANDLER_MAP.put(ActionEnum.UPPER,  StringUtils::upperCase);
        HANDLER_MAP.put(ActionEnum.LOWER,  StringUtils::lowerCase);
    }

    @Override
    public String process(String str) {
        System.out.println("Process in AService.");
        return HANDLER_MAP.get(ActionEnum.LOWER).apply(str);
    }

    private String toUpper(String str) {
        if (str == null) {
            throw new IllegalArgumentException("argument can't be null.");
        }

        return iCommonService.process(str);
    }
}
