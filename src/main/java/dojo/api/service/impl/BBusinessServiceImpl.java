package dojo.api.service.impl;

import dojo.api.service.IBusinessService;
import org.springframework.stereotype.Service;

/**
 * @author Fanmao.Li
 * @since 06/09/2019
 */
@Service
public class BBusinessServiceImpl implements IBusinessService {
    @Override
    public String process(String str) {
        System.out.println("Process in BService.");
        return str;
    }
}
