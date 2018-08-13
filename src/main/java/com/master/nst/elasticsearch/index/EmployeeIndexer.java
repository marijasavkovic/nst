package com.master.nst.elasticsearch.index;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.elasticsearch.ElasticSearchClient;
import com.master.nst.elasticsearch.util.ElasticSearchUtil;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmployeeIndexer {

    @Autowired
    private ElasticSearchClient elasticClient;

    public void indexEmployee(EmployeeEntity employeeEntity) throws Exception {
        IndexResponse indexResponse = elasticClient
            .getClient()
            .prepareIndex(ElasticSearchUtil.EMPLOYEE_INDEX, ElasticSearchUtil.EMPLOYEE_TYPE,
                          String.valueOf(employeeEntity.getId()))
            .setSource(buildEmployee(employeeEntity))
            .get();
    }

    public void updateEmployee(EmployeeEntity employeeEntity) throws Exception {
        elasticClient.getClient().prepareUpdate(
            ElasticSearchUtil.EMPLOYEE_INDEX, ElasticSearchUtil.EMPLOYEE_TYPE, String.valueOf(employeeEntity.getId()))
            .setDoc(buildEmployee(employeeEntity)).get();
    }

    public void createEmployeeIndexIfNotExists() {
        boolean exists = elasticClient.getClient().admin().indices()
            .prepareExists(ElasticSearchUtil.EMPLOYEE_INDEX)
            .execute().actionGet().isExists();
        if (!exists) {
            CreateIndexResponse createIndexResponse = elasticClient.getClient().admin().indices().
                create(Requests.createIndexRequest(ElasticSearchUtil.EMPLOYEE_INDEX)).actionGet();
        }
    }

    public void deleteEmployeeIndexes() {
        boolean exists = elasticClient.getClient().admin().indices()
            .prepareExists(ElasticSearchUtil.EMPLOYEE_INDEX)
            .execute().actionGet().isExists();
        if (exists) {
            elasticClient.getClient().
                admin().indices().delete(new DeleteIndexRequest(ElasticSearchUtil.EMPLOYEE_INDEX)).actionGet();
        }
    }

    public void deleteEmployee(Long employeeId) {
        elasticClient.getClient().prepareDelete(ElasticSearchUtil.EMPLOYEE_INDEX,
                                                ElasticSearchUtil.EMPLOYEE_TYPE, String.valueOf(employeeId)).get();
    }

    public XContentBuilder buildEmployee(EmployeeEntity employeeEntity) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        if(employeeEntity.getId()!=null){
            builder.field("id", employeeEntity.getId());
        }
        builder.field("name", employeeEntity.getName());
        builder.field("surname", employeeEntity.getSurname());
        builder.field("personalIdentificationNumber", employeeEntity.getPersonalIdentificationNumber());
        builder.field("dateOfBirth", new Date(employeeEntity.getDateOfBirth().getTime()));
        builder.field("dateOfEmployment", new Date(employeeEntity.getDateOfEmployment().getTime()));
        builder.field("address", employeeEntity.getAddress());
        builder.field("title", employeeEntity.getTitle());
        builder.field("vocation", employeeEntity.getVocation());
        builder.endObject();
        return builder;
    }
}
