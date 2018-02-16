package cn.com.youplus.notification.common.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SendmailUtil {

    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.youplus.com.cn";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String SEND_USER = "hewh@youplus.com.cn";
    private String SEND_UNAME = "hewh@youplus.com.cn";
    private String SEND_PWD = "1357abc";
    // 建立会话
    private MimeMessage message;
    private Session s;

    /*
     * 初始化方法
     */
    public SendmailUtil() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        //props.put("mail.smtp.auth", "true");
        s =  Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
            }});
        s.setDebug(true);
        message = new MimeMessage(s);
    }

    /**
     * 发送邮件
     *
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public void doSendHtmlEmail(String headName, String sendHtml,
                                String receiveUser) throws MessagingException {
            // 发件人
        InternetAddress from = new InternetAddress(SEND_USER);
        message.setFrom(from);
        // 收件人
        InternetAddress to = new InternetAddress(receiveUser);
        message.setRecipient(Message.RecipientType.TO, to);
        // 邮件标题
        message.setSubject(headName);
        String content = sendHtml.toString();
        // 邮件内容,也可以使纯文本"text/plain"
        message.setContent(content, "text/html;charset=GBK");
        message.saveChanges();
        Transport transport = s.getTransport("smtp");
        // smtp验证，就是你用来发邮件的邮箱用户名密码
        transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
        // 发送
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("send success!");
    }

    public void doSendHtmlEmail(String headName, String sendHtml,
                                List<String> receiveList) throws MessagingException {
        // 发件人
        InternetAddress from = new InternetAddress(SEND_USER);
        message.setFrom(from);
        // 收件人
        InternetAddress[] to = new InternetAddress[receiveList.size()];
        for(int i = 0; i < receiveList.size(); i++){
            to[i] = new InternetAddress(receiveList.get(i));
        }
        message.setRecipients(Message.RecipientType.TO, to);
        // 邮件标题
        message.setSubject(headName);
        String content = sendHtml.toString();
        // 邮件内容,也可以使纯文本"text/plain"
        message.setContent(content, "text/html;charset=GBK");
        message.saveChanges();
        Transport transport = s.getTransport("smtp");
        // smtp验证，就是你用来发邮件的邮箱用户名密码
        transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
        // 发送
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("send success!");
    }

    public static void main(String[] args) throws MessagingException {
        SendmailUtil se = new SendmailUtil();
        List<String> receiveList = new ArrayList<>();
        receiveList.add("724291943@qq.com");
        receiveList.add("yanhy@youplus.com.cn");
        se.doSendHtmlEmail("邮件头文件名", "<p>hello world</p>", receiveList);
    }
}//源代码片段来自云代码http://yuncode.net

