package app.controllerFront.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class VirguleTag extends TagSupport {

    private String amount;
    @Override
    public int doStartTag() throws JspException {
        JspWriter out= pageContext.getOut();
        try {
            amount = amount.replace('.', ',');
            out.print(amount);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount;}
}
