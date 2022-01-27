package dojo.api.service;

import dojo.api.model.Person;

import java.util.UUID;

/**
 * @author Fanmao.Li
 * @since 06/09/2019
 */
public interface IBusinessService {
    ThreadLocal<Person> traceId = new ThreadLocal<Person>() {
        @Override
        protected Person initialValue() {
            return new Person("Andy");
        }
    };
    String process(String name);
}
