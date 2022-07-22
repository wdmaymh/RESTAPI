package ac.korea.isdevelop.restapi.getDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class BillMasterCardProofDto {
    private long billNo;
    private String billDocNo;
    public String billDeptCd;
    public String billTitle;
    private String accDt;
    private String cardNo;
    private String cardAprvNo;
    private String cardAprvDt;
    private String trdDt;
    private String cardSettDt;
    private Long useAmt;
    private Long chargeAmt;

    @QueryProjection
    public BillMasterCardProofDto(long billNo, String billDocNo, String billDeptCd, String billTitle, String accDt, String cardNo, String cardAprvNo, String cardAprvDt, String trdDt, String cardSettDt, Long useAmt, Long chargeAmt) {
        this.billNo = billNo;
        this.billDocNo = billDocNo;
        this.billDeptCd = billDeptCd;
        this.billTitle = billTitle;
        this.accDt = accDt;
        this.cardNo = cardNo;
        this.cardAprvNo = cardAprvNo;
        this.cardAprvDt = cardAprvDt;
        this.trdDt = trdDt;
        this.cardSettDt = cardSettDt;
        this.useAmt = useAmt;
        this.chargeAmt = chargeAmt;
    }
}

