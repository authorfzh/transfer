package cn.transfer.beetl;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader(); 
		Configuration cfg = Configuration.defaultConfiguration(); 
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg); 
		Template t = gt.getTemplate("<% var tname={};tname.a='ddd'; %>hello,${name},${tname.a}"); 
		t.binding("name", "beetl"); 
		String str = t.render(); 
		System.out.println(str);
	}

}
