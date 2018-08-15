package com.master.nst.elasticsearch.service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.elasticsearch.index.EmployeeIndexer;
import com.master.nst.elasticsearch.mapper.EmployeeElasticMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeElasticService {

    private final EmployeeIndexer employeeIndexer;
    private final EmployeeElasticMapper employeeElasticMapper;
    private final Client elasticSearchClient;
    private final String employeeIndex;
    private final String employeeType;

    @Autowired
    public EmployeeElasticService(
        EmployeeIndexer employeeIndexer,
        EmployeeElasticMapper employeeElasticMapper,
        Client elasticSearchClient,
        @Value("${elasticsearch.employeeindex}") String employeeIndex,
        @Value("${elasticsearch.employeetype}") String employeeType)
    {
        this.employeeIndexer = employeeIndexer;
        this.employeeElasticMapper = employeeElasticMapper;
        this.elasticSearchClient = elasticSearchClient;
        this.employeeIndex = employeeIndex;
        this.employeeType = employeeType;
        this.employeeIndexer.createEmployeeIndexIfNotExists();
    }

    public List<EmployeeEntity> searchEmployees(String query, int limit, int page) {
        int offset = (page - 1) * limit;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (query != null && !query.isEmpty()) {
            boolQuery
                .should(QueryBuilders.queryStringQuery(query + "*").field("name"))
                .should(QueryBuilders.queryStringQuery(query + "*").field("surname"))
                .should(QueryBuilders.queryStringQuery(query + "*").field("personalIdentificationNumber"));
        }

        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(employeeIndex)
            .setTypes(employeeType)
            .setQuery(boolQuery)
            .setFrom(offset)
            .setSize(limit)
            .execute()
            .actionGet();

        return employeeElasticMapper.mapToEmployeeEntityList(searchResponse);
    }

    public List<EmployeeEntity> findAll() {
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(employeeIndex)
            .setTypes(employeeType)
            .execute()
            .actionGet();

        return employeeElasticMapper.mapToEmployeeEntityList(searchResponse);
    }

    public EmployeeEntity findById(Long employeeId) {
        BoolQueryBuilder boolQuery = boolQuery();
        boolQuery.must(termQuery("id", employeeId));
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(employeeIndex)
            .setTypes(employeeType)
            .setQuery(boolQuery)
            .execute()
            .actionGet();
        List<EmployeeEntity> employeeEntities = employeeElasticMapper.mapToEmployeeEntityList(searchResponse);
        if (employeeEntities.isEmpty()) {
            return null;
        }
        return employeeEntities.get(0);
    }

    public EmployeeEntity findByPersonalIdentificationNumber(String personalIdentificationNumber) {
        BoolQueryBuilder boolQuery = boolQuery();
        boolQuery.must(termQuery("personalIdentificationNumber", personalIdentificationNumber));
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(employeeIndex)
            .setTypes(employeeType)
            .setQuery(boolQuery)
            .execute()
            .actionGet();
        List<EmployeeEntity> employeeEntities = employeeElasticMapper.mapToEmployeeEntityList(searchResponse);
        if (employeeEntities.isEmpty()) {
            return null;
        }
        return employeeEntities.get(0);
    }

    public void save(EmployeeEntity employeeEntity) throws Exception {
        employeeIndexer.createEmployeeIndexIfNotExists();
        employeeEntity.setId(getMaxId() + 1);
        employeeIndexer.indexEmployee(employeeEntity);
    }

    public EmployeeEntity update(EmployeeEntity employeeEntity) throws Exception {
        employeeIndexer.updateEmployee(employeeEntity);
        return findById(employeeEntity.getId());
    }

    public void delete(Long employeeId) {
        employeeIndexer.deleteEmployee(employeeId);
    }

    public Long getMaxId() {
        MaxAggregationBuilder aggregation = AggregationBuilders.max("id").field("id");
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(employeeIndex)
            .setTypes(employeeType)
            .addAggregation(aggregation)
            .execute()
            .actionGet();
        Max id = searchResponse.getAggregations().get("id");
        return !Double.isInfinite(id.getValue()) ? (long) id.getValue() : 0;
    }

}
