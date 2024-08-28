package com.sugar.study.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class IOBean implements Serializable {


    private static final long serialVersionUID = -5185680395068676299L;
    private String name;
    private int age;
    private boolean gay;
    private transient String sayHello;

    public String toString(){
        String s = "name:" + this.name + ",age:" + this.age + ",is gay:" + this.gay;
        if(StringUtils.isNotBlank(this.sayHello)){
            s+="，say hello :"+this.sayHello;
        }
        return s;
    }

}
