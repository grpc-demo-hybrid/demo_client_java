package dojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("demo")
public class DemoConfig {
    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
