package ac.korea.isdevelop.restapi.billMaster;

import jdk.jfr.Name;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = " billNo")
@Entity
@Table(name = "NACC200TL")
@SequenceGenerator(name="billNoSeq", sequenceName="SEQ_NACC200_SEQ", allocationSize=1)
public class BillMaster {

    @Transient
    private final String TABLE_PREFIX = "NACC200_";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billNoSeq")
    @Column(name = TABLE_PREFIX + "billNo")
    public long billNo;
    @Column(name = TABLE_PREFIX + "accYear", columnDefinition = "char(4)")
    public String accYear;
    @Column(name = TABLE_PREFIX + "campusCd", columnDefinition = "char(1)")
    public String campusCd;
    @Column(name = TABLE_PREFIX + "accUnitCd", columnDefinition = "char(2)")
    public String accUnitCd;
    @Column(name = TABLE_PREFIX + "billDt", columnDefinition = "varchar2(8)")
    public String billDt;
    @Column(name = TABLE_PREFIX + "billDocNo", columnDefinition = "varchar2(14)")
    public String billDocNo;
    @Column(name = TABLE_PREFIX + "billDeptCd", columnDefinition = "char(4)")
    public String billDeptCd;
    @Column(name = TABLE_PREFIX + "billDivCd", columnDefinition = "char(2)")
    public String billDivCd;
    @Column(name = TABLE_PREFIX + "billTypeCd", columnDefinition = "char(3)")
    public String billTypeCd;
    @Column(name = TABLE_PREFIX + "billKindCd", columnDefinition = "char(3)")
    public String billKindCd;
    @Column(name = TABLE_PREFIX + "wrtEmpObjNo", columnDefinition = "varchar2(6)")
    public String wrtEmpObjNo;
    @Column(name = TABLE_PREFIX + "billTitle", columnDefinition = "varchar2(1000)")
    public String billTitle;
    @Column(name = TABLE_PREFIX + "digest", columnDefinition = "varchar2(4000)")
    public String digest;
    @Column(name = TABLE_PREFIX + "billStsCd", columnDefinition = "char(2)")
    public String billStsCd;
    @Column(name = TABLE_PREFIX + "billDeptAprvDt", columnDefinition = "varchar2(8)")
    public String billDeptAprvDt;
    @Column(name = TABLE_PREFIX + "deptAprvEmpObjNo", columnDefinition = "varchar2(10)")
    public String deptAprvEmpObjNo;
    @Column(name = TABLE_PREFIX + "accDt", columnDefinition = "varchar2(8)")
    public String accDt;
    @Column(name = TABLE_PREFIX + "execAprvDt", columnDefinition = "varchar2(8)")
    public String execAprvDt;
    @Column(name = TABLE_PREFIX + "billGrpNo")
    public Long billGrpNo;
    @Column(name = TABLE_PREFIX + "interfaceKey", columnDefinition = "varchar2(100)")
    public String interfaceKey;
    @Column(name = TABLE_PREFIX + "interfaceDivCd", columnDefinition = "varchar2(7)")
    public String interfaceDivCd;
    public LocalDateTime crtDttm;
    @Column(columnDefinition = "varchar2(20)")
    public String crtId;
    @Column(columnDefinition = "varchar2(50)")
    public String crtPgmId;
    @Column(columnDefinition = "varchar2(20)")
    public String crtIp;
}