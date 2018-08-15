package com.master.nst.elasticsearch.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserElasticMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public List<UserEntity> mapToUserEntityList(SearchResponse searchResponse)  {
        List<UserEntity> userEntities = new ArrayList<>();
        for (final SearchHit searchHit : searchResponse.getHits()) {
            try {
                userEntities.add(objectMapper.readValue(searchHit.getSourceAsString(), UserEntity.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userEntities;
    }
}
