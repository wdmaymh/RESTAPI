package ac.korea.isdevelop.restapi.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
@ActiveProfiles("test")
public class NaccMapperTest {

    @Autowired
    private NaccMapper naccMapper;

    @Test
    public void getNacc200ListTest() throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("accYear","2019");
        List<Map> nacc200List = naccMapper.getNacc200List(params);
        assertThat(nacc200List.size()).isEqualTo(3);

    }



}