package com.master.nst.elasticsearch.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.nst.domain.EmployeeEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeElasticMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public List<EmployeeEntity> mapToEmployeeEntityList(SearchResponse searchResponse)  {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        for (final SearchHit searchHit : searchResponse.getHits()) {
            try {
                employeeEntities.add(objectMapper.readValue(searchHit.getSourceAsString(), EmployeeEntity.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return employeeEntities;
    }
}
