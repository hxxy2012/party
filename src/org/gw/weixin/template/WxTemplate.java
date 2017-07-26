package org.gw.weixin.template;

import java.util.Map;

/**
 * 微信模板消息
 * @author fuyun
 *	2016-04-19
 */
public class WxTemplate {
	
    private String template_id;//模板消息id
    private String touser;//用户openId
    private String url;//URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
    private String topcolor;//标题颜色
    private Map<String,TemplateData> data;//详细内容
      
    public String getTemplate_id() {  
        return template_id;  
    }  
    public void setTemplate_id(String template_id) {  
        this.template_id = template_id;  
    }  
    public String getTouser() {  
        return touser;  
    }  
    public void setTouser(String touser) {  
        this.touser = touser;  
    }  
    public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
    public String getTopcolor() {  
        return topcolor;  
    }  
    public void setTopcolor(String topcolor) {  
        this.topcolor = topcolor;  
    }  
    public Map<String,TemplateData> getData() {  
        return data;  
    }  
    public void setData(Map<String,TemplateData> data) {  
        this.data = data;  
    }  
}
