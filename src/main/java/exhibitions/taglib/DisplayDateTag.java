package exhibitions.taglib;


import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class DisplayDateTag extends TagSupport {
    private Logger logger = Logger.getLogger(DisplayDateTag.class);
    private Date inputDate;

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();//returns the instance of JspWriter
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        logger.info("JspWriter out ="+out);
        String formattedDate = format.format(out);
        logger.info("formattedDate ="+formattedDate);
        try {
            //pageContext.getRequest().setAttribute();
            out.print(formattedDate);//printing date and time using JspWriter
        } catch (Exception e) {
            logger.error(e);
        }
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}

