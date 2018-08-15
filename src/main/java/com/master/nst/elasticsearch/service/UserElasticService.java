package com.master.nst.elasticsearch.service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import com.master.nst.domain.UserEntity;
import com.master.nst.elasticsearch.index.UserIndexer;
import com.master.nst.elasticsearch.mapper.UserElasticMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserElasticService {

    private final Client elasticSearchClient;
    private final UserElasticMapper userElasticMapper;
    private final UserIndexer userIndexer;
    private final String userIndex;
    private final String userType;

    public UserElasticService(
        Client elasticSearchClient,
        UserElasticMapper userElasticMapper,
        UserIndexer userIndexer,
        @Value("${elasticsearch.userindex}") String userIndex,
        @Value("${elasticsearch.usertype}") String userType)
    {
        this.elasticSearchClient = elasticSearchClient;
        this.userElasticMapper = userElasticMapper;
        this.userIndexer = userIndexer;
        this.userIndex = userIndex;
        this.userType = userType;
        this.userIndexer.createUserIndexIfNotExists();
    }

    public List<UserEntity> searchUser(String query, int limit, int page) {
        int offset = (page - 1) * limit;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (query != null && !query.isEmpty()) {
            boolQuery
                .should(QueryBuilders.queryStringQuery(query + "*").field("username"))
                .should(QueryBuilders.queryStringQuery(query + "*").field("email"))
                .should(QueryBuilders.queryStringQuery(query + "*").field("role"));
        }

        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(userIndex)
            .setTypes(userType)
            .setQuery(boolQuery)
            .setFrom(offset)
            .setSize(limit)
            .execute()
            .actionGet();

        return userElasticMapper.mapToUserEntityList(searchResponse);
    }

    public List<UserEntity> findAll() {
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(userIndex)
            .setTypes(userType)
            .execute()
            .actionGet();

        return userElasticMapper.mapToUserEntityList(searchResponse);
    }

    public UserEntity findById(Long userId) {
        BoolQueryBuilder boolQuery = boolQuery();
        boolQuery.must(termQuery("id", userId));
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(userIndex)
            .setTypes(userType)
            .setQuery(boolQuery)
            .execute()
            .actionGet();
        List<UserEntity> userEntities = userElasticMapper.mapToUserEntityList(searchResponse);
        if (userEntities.isEmpty()) {
            return null;
        }
        return userEntities.get(0);
    }

    public UserEntity findByUsername(String personalIdentificationNumber) {
        BoolQueryBuilder boolQuery = boolQuery();
        boolQuery.must(termQuery("username", personalIdentificationNumber));
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(userIndex)
            .setTypes(userType)
            .setQuery(boolQuery)
            .execute()
            .actionGet();
        List<UserEntity> userEntities = userElasticMapper.mapToUserEntityList(searchResponse);
        if (userEntities.isEmpty()) {
            return null;
        }
        return userEntities.get(0);
    }

    public UserEntity save(UserEntity userEntity) throws Exception {
        userIndexer.createUserIndexIfNotExists();
        userEntity.setId(getMaxId() + 1);
        userIndexer.indexUser(userEntity);
        return findById(userEntity.getId());
    }

    public UserEntity update(UserEntity userEntity) throws Exception {
        userIndexer.updateUser(userEntity);
        return findById(userEntity.getId());
    }

    public void delete(Long userId) {
        userIndexer.deleteUser(userId);
    }

    public Long getMaxId() {
        MaxAggregationBuilder aggregation = AggregationBuilders.max("id").field("id");
        SearchResponse searchResponse = elasticSearchClient
            .prepareSearch(userIndex)
            .setTypes(userType)
            .addAggregation(aggregation)
            .execute()
            .actionGet();
        Max id = searchResponse.getAggregations().get("id");
        return !Double.isInfinite(id.getValue()) ? (long) id.getValue() : 0;
    }

}
