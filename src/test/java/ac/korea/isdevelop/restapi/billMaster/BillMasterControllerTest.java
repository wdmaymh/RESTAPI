package ac.korea.isdevelop.restapi.billMaster;

import ac.korea.isdevelop.restapi.common.RestDocsConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test")
class BillMasterControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BillMasterRepository billMasterRepository;

    @Test
    @DisplayName("????????? ???????????? ?????? ????????????")
    public void getBillMaster() throws Exception {

        //Given
        long billNo=11895005;

        //When & Then
        this.mockMvc.perform(get("/api/billMasters/{id}", billNo))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("accUnitCd").exists())
                .andExpect(jsonPath("accYear").exists())
                .andExpect(jsonPath("campusCd").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
        ;

    }

    @Test
    @DisplayName("?????? bill??? ???????????? ??? 404 ????????????")
    public  void  getBillMaster404() throws  Exception{
        this.mockMvc.perform(get("/api/billMasters/0"))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    @DisplayName("????????? 10?????? ????????? ????????? ????????????")
    public void getBillMasters() throws Exception {

        //Given
        //IntStream.range(0, 30~).forEach(i -> this.generateBillMaster(i));

        //When
        this.mockMvc.perform(get("/api/billMasters")
                        .param("accYear", "2021")
                        .param("campusCd", "2")
                        .param("accUnitCd", "18")
                        .param("page", "2")
                        .param("size", "5")
                        .param("sort", "billTitle,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.billMasterList[0]._links.self").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.profile").exists())
                .andDo(document("query-billmasters"))
        ;
    }



    private BillMaster generateBillMaster(int index) {

        BillMaster billMaster = BillMaster.builder()
                .billTitle("Test" + index)
                .billStsCd("10")
                .accYear("2022")
                .campusCd("1")
                .accUnitCd("01")
                .build();

        BillMaster newBillMaster = this.billMasterRepository.save(billMaster);


        return newBillMaster;


    }

    @Test
    @DisplayName("???????????? ?????? ?????????(ex billNo) ????????? ????????? ???????????? ?????????")
    public void createBillMasterBadRequest() throws Exception {
        BillMaster billMaster = BillMaster.builder()
                .billTitle("Spring")
                .billStsCd("10")
                .accYear("2022")
                .campusCd("1")
                .accUnitCd("01")
                .billNo(100)
                .build();



        mockMvc.perform(post("/api/billMasters/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(billMaster)))
                .andDo(print())
                .andExpect(status().isBadRequest())

        ;

    }

    @Test
    @DisplayName("?????????(ex accUintCd)??? ????????? ?????? ????????? ????????? ???????????? ?????????")
    public void createBillMasterBadRequestEmptyInput() throws Exception {
        BillMasterDto billMasterDto = BillMasterDto.builder().build();

        mockMvc.perform(post("/api/billMasters/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(billMasterDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("???????????? ?????? ???(ex campusCd=3) ????????? ????????? ???????????? ?????????")
    public void createBillMasterBadRequestWrongInput() throws Exception {
        BillMasterDto billMasterDto = BillMasterDto.builder()
                .accUnitCd("01")
                .accYear("2022")
                .campusCd("3")
                .build();

        mockMvc.perform(post("/api/billMasters/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(billMasterDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors[0].objectName").exists())
                .andExpect(jsonPath("errors[0].defaultMessage").exists())
                .andExpect(jsonPath("errors[0].code").exists())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }

    @Test
    @DisplayName("???????????? ?????????")
    public void createBillMaster() throws Exception {
        BillMasterDto billMaster = BillMasterDto.builder()
                .billTitle("Spring")
                .billStsCd("10")
                .accYear("2022")
                .campusCd("1")
                .accUnitCd("01")
                .billDt("20220301")
                .billDivCd("10")
                .billTypeCd("010")
                .billKindCd("101")
                .build();

        mockMvc.perform(post("/api/billMasters/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(billMaster)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("billNo").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE + ";charset=UTF-8"))
                .andExpect(jsonPath("billNo").value(Matchers.not(100)))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-billMasters").exists())
                .andExpect(jsonPath("_links.update-billMaster").exists())
                .andDo(document("create-billMaster",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-billMasters").description("link to query bill masters"),
                                linkWithRel("update-billMaster").description("link to update query master"),
                                linkWithRel("profile").description("link to profile")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("billTitle").description("Title of bill master"),
                                fieldWithPath("billStsCd").description("status of bill master"),
                                fieldWithPath("accYear").description("Acc year of bill master"),
                                fieldWithPath("accUnitCd").description("Acc unit  of bill master"),
                                fieldWithPath("campusCd").description("Campus  of bill master"),
                                fieldWithPath("billDt").description("Title of bill master"),
                                fieldWithPath("billDeptCd").description("status of bill master"),
                                fieldWithPath("billDivCd").description("Title of bill master"),
                                fieldWithPath("billTypeCd").description("status of bill master"),
                                fieldWithPath("billKindCd").description("Acc year of bill master"),
                                fieldWithPath("wrtEmpObjNo").description("Acc unit  of bill master"),
                                fieldWithPath("wrtEmpObjNo").description("Campus  of bill master"),
                                fieldWithPath("digest").description("Acc year of bill master"),
                                fieldWithPath("interfaceKey").description("Acc unit  of bill master"),
                                fieldWithPath("interfaceDivCd").description("Campus  of bill master")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description(""),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("")
                        ),
                        responseFields(
                                fieldWithPath("billNo").description("Title of bill master"),
                                fieldWithPath("accYear").description("Acc year of bill master"),
                                fieldWithPath("campusCd").description("Campus  of bill master"),
                                fieldWithPath("accUnitCd").description("Acc unit  of bill master"),
                                fieldWithPath("billDt").description("Title of bill master"),
                                fieldWithPath("billDocNo").description("Campus  of bill master"),
                                fieldWithPath("billDeptCd").description("status of bill master"),
                                fieldWithPath("billDivCd").description("Title of bill master"),
                                fieldWithPath("billTypeCd").description("status of bill master"),
                                fieldWithPath("billKindCd").description("Acc year of bill master"),
                                fieldWithPath("wrtEmpObjNo").description("Acc unit  of bill master"),
                                fieldWithPath("billTitle").description("Title of bill master"),
                                fieldWithPath("digest").description("Acc year of bill master"),
                                fieldWithPath("billStsCd").description("status of bill master"),
                                fieldWithPath("billDeptAprvDt").description("Acc year of bill master"),
                                fieldWithPath("deptAprvEmpObjNo").description("Acc unit  of bill master"),
                                fieldWithPath("accDt").description("Campus  of bill master"),
                                fieldWithPath("execAprvDt").description("Acc year of bill master"),
                                fieldWithPath("billGrpNo").description("Acc unit  of bill master"),
                                fieldWithPath("interfaceKey").description("Acc unit  of bill master"),
                                fieldWithPath("interfaceDivCd").description("Campus  of bill master"),
                                fieldWithPath("crtDttm").description("Campus  of bill master"),
                                fieldWithPath("crtId").description("Acc year of bill master"),
                                fieldWithPath("crtPgmId").description("Acc unit  of bill master"),
                                fieldWithPath("crtIp").description("Campus  of bill master"),
                                fieldWithPath("_links.self.href").description("Acc year of bill master"),
                                fieldWithPath("_links.update-billMaster.href").description("Acc unit  of bill master"),
                                fieldWithPath("_links.query-billMasters.href").description("Campus  of bill master"),
                                fieldWithPath("_links.profile.href").description("Campus  of bill master"),
                                fieldWithPath("table_PREFIX").description("Title of bill master"),
                                fieldWithPath("cardProofset").description("Title of bill master")
                        )
                ))
        ;

    }


}