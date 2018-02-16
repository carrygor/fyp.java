package cn.com.youplus.apps.rpc.service.task;

import com.alicloud.openservices.tablestore.ClientConfiguration;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.AlwaysRetryStrategy;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 严汉羽 on 2017/6/27.
 */

@Component
public class AppsRpcTask {
    private static Logger log = Logger.getLogger(AppsRpcTask.class);

    @Scheduled(cron="0/10 * *  * * ? ")
    public void alive() {
        log.info("优加+ APPS RPC Server服务器---每10秒一次的log，证明服务器还活着。。。");
    }

    public static void 多选题(String [] args) {

        String [][] aaa = {
                {"0aa75367-ba37-11e7-a02d-00163e042194","平时习惯在油站购物"},
                {"0aa99224-ba37-11e7-a02d-00163e042194","因为有需要购物"},
                {"0aabe8de-ba37-11e7-a02d-00163e042194","便利店有促销优惠"},
                {"0aae331c-ba37-11e7-a02d-00163e042194","加油站员工的推介"},
                {"5d639544-ba35-11e7-a02d-00163e042194","油站员工介绍"},
                {"5d65f07a-ba35-11e7-a02d-00163e042194","油站内广告宣传"},
                {"5d683af8-ba35-11e7-a02d-00163e042194","短信"},
                {"5d6a8044-ba35-11e7-a02d-00163e042194","朋友推荐"},
                {"5d6cc10c-ba35-11e7-a02d-00163e042194","微信公众号"},
                {"958572a7-ba35-11e7-a02d-00163e042194","广播电台"},
                {"98b3deac-ba35-11e7-a02d-00163e042194","油站以外其他地方的广告"},
                {"79b0d13c-a426-11e6-a02d-00163e042194","小区门禁卡"},
                {"79b484a2-a426-11e6-a02d-00163e042194","充当各类会员卡享受折扣"},
                {"79b88144-a426-11e6-a02d-00163e042194","独立专属卡面设计"},
                {"79bcc058-a426-11e6-a02d-00163e042194","ETC"},
                {"b35e55c3-ba36-11e7-a02d-00163e042194","早餐"},
                {"b360a079-ba36-11e7-a02d-00163e042194","热快餐"},
                {"b362cdce-ba36-11e7-a02d-00163e042194","生鲜和冷冻食品"},
                {"b36506a9-ba36-11e7-a02d-00163e042194","地方特产"},
                {"b3673c63-ba36-11e7-a02d-00163e042194","进口食品"},
                {"d18d0aed-ba36-11e7-a02d-00163e042194","有机食品"},
                {"d4ce6740-ba36-11e7-a02d-00163e042194","书籍和音像制品"},
                {"d86af33c-ba36-11e7-a02d-00163e042194","快递代收取"},
                {"dbfc0c90-ba36-11e7-a02d-00163e042194","彩票业务"},
                {"df50ba05-ba36-11e7-a02d-00163e042194","票务服务"},
                {"e22938ce-ba36-11e7-a02d-00163e042194","线上订购门店提取服务"},
                {"e5c02175-ba36-11e7-a02d-00163e042194","汽车养护服务"},
                {"ebc183e4-ba36-11e7-a02d-00163e042194","应急药品与个人护理用品"}
        };


        StringBuilder builder = new StringBuilder();

        for (String[] a : aaa) {

            builder.append(String.format("SELECT \n" +
                    "'%s' as 选项名称,\n" +
                    "count(tb_qm_answerresult.id) as 选项总数\n" +
                    "from tb_qm_answerresult\n" +
                    "INNER JOIN tb_qm_answerresult q1 ON  q1.SubmitResultID = tb_qm_answerresult.SubmitResultID AND q1.QuestionID='7d760218-ba32-11e7-a02d-00163e042194'\n" +
                    "where \n" +
                    "tb_qm_answerresult.QuestionID in ( \n" +
                    "'0aa3f6dc-ba37-11e7-a02d-00163e042194',  -- <p>C4.请问您通常在什么情况下会想在油站便利店购物？[可多选]</p>\n" +
                    "'5d606083-ba35-11e7-a02d-00163e042194',  -- <p>B5.1您一般是通过什么渠道了解到油站的促销信息的？(多选)</p>\n" +
                    "'79ab3385-a426-11e6-a02d-00163e042194',  -- <p>B12.您希望加油卡除加油功能外还需具备哪些属性<span style=\"color: rgb(255, 0, 0);\">(可多选)</span></p>\n" +
                    "'b35ae13b-ba36-11e7-a02d-00163e042194'  -- <p>C2.增加哪些服务内容会吸引您到加油站便利店消费（多选）</p>\n" +
                    ") \n" +
                    "AND \n" +
                    "(\n" +
                    "tb_qm_answerresult.Result = '%s'\n" +
                    "OR\n" +
                    "tb_qm_answerresult.Result like '%s|%%'\n" +
                    "OR\n" +
                    "tb_qm_answerresult.Result like '%%|%s|%%'\n" +
                    "OR\n" +
                    "tb_qm_answerresult.Result like '%%|%s'\n" +
                    ")\n" +
                    "GROUP BY q1.Result \n UNION ALL \n", a[1], a[0],a[0],a[0],a[0] ));
        }

        System.out.println(builder.toString());
    }

    public static void main(String [] args) {
        // 心动里
        //String questionId = "2d434587-af58-11e7-a02d-00163e042194";
        //int qum = 18;

        //中油 - String questionId = "3e0e32e5-d3ef-11e7-a02d-00163e042194";
        //广西石化
        String questionId = "d55b9de7-a422-11e6-a02d-00163e042194";
        int qum = 36;
        问卷(questionId, qum);
    }

    public static void 问卷1(String questionId, int qum) {

        String [] ids = {
                "",
                "95699eb4-d415-11e7-a02d-00163e042194",
                "3e20f0ea-d3ef-11e7-a02d-00163e042194",
                "3e239646-d3ef-11e7-a02d-00163e042194",
                "3e262b3c-d3ef-11e7-a02d-00163e042194",
                "3e28d712-d3ef-11e7-a02d-00163e042194",
                "3e2b7707-d3ef-11e7-a02d-00163e042194",
                "3e2e19ba-d3ef-11e7-a02d-00163e042194",
                "3e48347e-d3ef-11e7-a02d-00163e042194",
                "7642a6dd-d3f2-11e7-a02d-00163e042194",
                "c976157b-d3f2-11e7-a02d-00163e042194",
                "3e77fe38-d3ef-11e7-a02d-00163e042194"
        };
        qum = qum + 1;
        StringBuilder builder = new StringBuilder("\n" +
                "\n" +
                "select r.id as sid, \n" +
                "r.CustomerCode AS ccode,\n" +
                "r.CreationDate AS sdate,\n");
        for (int i = 1; i < qum; i++) {
            String buf =
                    String.format(
                            " q%d.id as q%did, q%d.Result as q%danswer\n",
                            i,i,i,i,i);

            if (i < qum - 1) {
                buf = buf + ",";
            }

            builder.append(buf);
        }

        builder.append("from  tb_qm_submitresult r ");

        for (int i = 1; i < qum; i++) {
            String buf =
                    String.format(
                            "INNER JOIN tb_qm_answerresult AS q%d ON q%d.SubmitResultId = r.id AND q%d.QuestionId = '%s'\n",
                            i,i,i,ids[i]);
            builder.append(buf);
        }
        builder.append("WHERE \n" +
                "r.QuestionnaireID='" + questionId + "'");

        System.out.println(builder.toString());
    }

    public static void 问卷(String questionId, int qum) {
        qum = qum + 1;
        StringBuilder builder = new StringBuilder("\n" +
                "\n" +
                "select \n" +
                "(@rowNO := @rowNo+1) AS 结果编号,\n" +
                "r.ProjectName as 项目名称,\n" +
                "r.ProjectCycleName as 周期名称,\n" +
                "r.QuestionnaireTitle as 问卷名称,\n" +
                "r.CustomerName AS 客户名称,\n" +
                "r.CustomerCode AS 客户编号,\n" +
                "r.Score AS 问卷得分,\n" +
                "r.SubmitDate AS 提交日期,\n");
        for (int i = 1; i < qum; i++) {
            String buf =
                   String.format(
                           " IF(q%d.ResultText IS NULL, q%d.Result, q%d.ResultText) as 题目%d答案\n",
                           i,i,i,i);

            if (i < qum - 1) {
                buf = buf + ",";
            }

            builder.append(buf);
        }

        builder.append("from (select @rowNO :=0) b, tb_qm_effectiveresult r ");

        for (int i = 1; i < qum; i++) {
            String buf =
                    String.format(
                            "LEFT JOIN tb_qm_effectiveresultdetail AS q%d ON q%d.EffectiveResultId = r.id AND q%d.QuestionNo = 'Q%d'\n",
                            i,i,i,i);
            builder.append(buf);
        }
        builder.append("WHERE \n" +
                "r.QuestionnaireID='" + questionId + "'");

        System.out.println(builder.toString());
    }
}

