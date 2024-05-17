package unicore.api.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreeMaker {

    public static FreeMaker instance = null;
    Configuration cfg;

    public FreeMaker() {
        if (instance == null)
        {
            instance = this;

            cfg = new Configuration(Configuration.VERSION_2_3_27);
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/pages");
        }
    }

    public String makePage(String filename, String content) {
        Map<String, Object> root = new HashMap<>();
        root.put("content", content);
        Template temp = null;

        try {
            temp = cfg.getTemplate(filename + ".ftl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringWriter out = new StringWriter();

        try {
            temp.process(root, out);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }

}
