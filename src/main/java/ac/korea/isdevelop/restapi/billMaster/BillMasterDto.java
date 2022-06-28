package ac.korea.isdevelop.restapi.billMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillMasterDto {

    public String accYear;
    public String campusCd;
    public String accUnitCd;
    public String billDt;
    public String billDeptCd;
    public String billDivCd;
    public String billTypeCd;
    public String billKindCd;
    public String wrtEmpObjNo;
    public String billTitle;
    public String digest;
    public String billStsCd;
    public String interfaceKey;
    public String interfaceDivCd;

}
