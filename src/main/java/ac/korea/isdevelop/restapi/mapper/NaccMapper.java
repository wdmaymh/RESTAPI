package ac.korea.isdevelop.restapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NaccMapper {

    public List<Map> getNacc200List(Map<String, String> params) throws Exception;
}
