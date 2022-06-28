package ac.korea.isdevelop.restapi.billMaster;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BillMasterControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
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
                ;
    }

    @Test
    public void createBillMaster() throws Exception {
        BillMasterDto billMaster = BillMasterDto.builder()
                .billTitle("Spring")
                .billStsCd("10")
                .accYear("2022")
                .campusCd("1")
                .accUnitCd("01")
                .build();

        mockMvc.perform(post("/api/billMasters/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(billMaster)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("billNo").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("billNo").value(Matchers.not(100)))

        ;

    }


}