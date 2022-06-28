package ac.korea.isdevelop.restapi.billMaster;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of=" billNo")
public class BillMaster {

    public long billNo;
    public String accYear;
    public String campusCd;
    public String accUnitCd;
    public String billDt;
    public String billDocNo;
    public String billDeptCd;
    public String billDivCd;
    public String billTypeCd;
    public String billKindCd;
    public String wrtEmpObjNo;
    public String billTitle;
    public String digest;
    public String billStsCd;
    public String billDeptAprvDt;
    public String deptAprvEmpObjNo;
    public String accDt;
    public String execAprvDt;
    public long billGrpNo;
    public String interfaceKey;
    public String interfaceDivCd;
    public LocalDateTime crtDttm;
    public String crtId;
    public String crtPgmId;
    public String crtIp;
    public String mgtItemData_1;
}