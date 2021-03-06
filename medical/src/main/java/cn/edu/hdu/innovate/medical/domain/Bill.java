package cn.edu.hdu.innovate.medical.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * 主要的申请报销 账单模型
 *
 * @author Z_HNAN
 */
@Getter
@Setter
public class Bill extends BaseDomain {

    public final static int BILL_STATE_PASS = 0; // 已报销
    public final static int BILL_STATE_REJECT = 1; // 报销拒绝
    public final static int BILL_STATE_PENDING = 2; // 审批中
    public final static int BILL_STATE_WAIT_PASS = 3;    // 等待提交（通过审核，未放款）
    public final static int BILL_STATE_WAIT_MONEY = 4;    // 等待放款（待公司放款）

    public final static int ILLNESS_TYPE_OUTPATIENT = 0; // 门诊
    public final static int ILLNESS_TYPE_NORMAL = 1; // 普通疾病
    public final static int ILLNESS_TYPE_SERIOUS = 2; // 重大疾病

    // 报销相关
    private int billState; // 报销状态
    private Logininfo applier; // 申请人
    private Date applyDate; // 申请时间
    private int illnessType; // 疾病类型
    private SystemDictionaryItem seriousType; // 大病类型
    private String hospital; // 所在医院
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date inHospitalDate; // 住院时间
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date outHospitalDate; // 出院时间
    private BigDecimal applyMoney; // 申请报销金额
    private String description; // 简要描述
    private String billImg; // 报销图片
    private Long comDeptId; // 所属部门Id

    // 审核相关
    private Logininfo auditor; // 审核人
    private Date auditDate; // 审核时间
    private String note; // 审核批注
    private BigDecimal auditMoney; // 审批金额

    /**
     * 返回疾病类型的展示界面
     *
     * @return
     */
    public String getIllnessTypeDisplay() {
        switch (illnessType) {
            case ILLNESS_TYPE_OUTPATIENT:
                return "普通门诊";
            case ILLNESS_TYPE_NORMAL:
                return "普通疾病";
            case ILLNESS_TYPE_SERIOUS:
                return "重大疾病";
            default:
                return "";
        }
    }

    /**
     * 返回报销状态的展示
     *
     * @return
     */
    public String getBillStateDisplay() {
        switch (billState) {
            case BILL_STATE_PASS:
                return "已报销";
            case BILL_STATE_REJECT:
                return "报销拒绝";
            case BILL_STATE_PENDING:
                return "审批中";
            case BILL_STATE_WAIT_PASS:
                return "审核成功待放款";
            case BILL_STATE_WAIT_MONEY:
                return "放款中";
            default:
                return "";
        }
    }

    /**
     * 返回json串
     *
     * @return
     */
    public String getJsonString() {
        HashMap<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("illnessType", getIllnessTypeDisplay());
        if (seriousType != null) {
            // 重大疾病才会产生seriousType
            json.put("seriousType", seriousType.getTitle());
        }
        json.put("hospital", hospital);
        json.put("applyMoney", applyMoney);
        json.put("description", description);
        json.put("applyMoney", applyMoney);
        json.put("auditMoney", auditMoney);
        json.put("note", note);
        json.put("billState", getBillStateDisplay());
        return JSONObject.toJSONString(json);
    }
}
