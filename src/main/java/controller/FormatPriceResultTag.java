package controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FormatPriceResultTag extends SimpleTagSupport {

    private String language;

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (language != null) {
            if (language.equals("en_US")){
                out.println(" in UAH");
            }
            if (language.equals("uk_UA")){
                out.println(" в гривнях");
            }
        }
    }

}
