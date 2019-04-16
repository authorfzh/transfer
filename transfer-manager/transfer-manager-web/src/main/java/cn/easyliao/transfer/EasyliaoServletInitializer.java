package cn.easyliao.transfer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *  Web程序启动类
 *
 * @date 2017-05-21 9:43
 */
public class EasyliaoServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(jxzcApplication.class);
    }

}
