package com.master.nst.elasticsearch.index;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserEntity;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserIndexer {

    @Autowired
    private Client elasticClient;
    @Value("${elasticsearch.userindex}")
    private String userIndex;
    @Value("${elasticsearch.usertype}")
    private String userType;

    public void indexUser(UserEntity userEntity) throws Exception {
        elasticClient
            .prepareIndex(userIndex, userType, String.valueOf(userEntity.getId()))
            .setSource(buildUser(userEntity))
            .get();
    }

    public void updateUser(UserEntity userEntity) throws Exception {
        elasticClient
            .prepareUpdate(userIndex, userType, String.valueOf(userEntity.getId()))
            .setDoc(buildUser(userEntity))
            .get();
    }

    public void createUserIndexIfNotExists() {
        boolean exists = elasticClient.admin().indices().prepareExists(userIndex).execute().actionGet().isExists();
        if (!exists) {
            elasticClient.admin().indices().
                create(Requests.createIndexRequest(userIndex)).actionGet();
        }
    }

    public void deleteUserIndexes() {
        boolean exists = elasticClient.admin().indices().prepareExists(userIndex).execute().actionGet().isExists();
        if (exists) {
            elasticClient.
                admin().indices().delete(new DeleteIndexRequest(userIndex)).actionGet();
        }
    }

    public void deleteUser(long userId) {
        elasticClient.prepareDelete(userIndex, userType, String.valueOf(userId)).get();
    }

    public XContentBuilder buildUser(UserEntity userEntity) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("id", userEntity.getId());
        builder.field("username", userEntity.getUsername());
        builder.field("password", userEntity.getPassword());
        builder.field("email", userEntity.getEmail());
        builder.field("role", userEntity.getRole());
        EmployeeEntity employeeEntity = userEntity.getEmployee();
        builder.startObject("employee");
        builder.field("id", employeeEntity.getId());
        builder.field("name", employeeEntity.getName());
        builder.field("surname", employeeEntity.getSurname());
        builder.field("personalIdentificationNumber", employeeEntity.getPersonalIdentificationNumber());
        builder.field("dateOfBirth", new Date(employeeEntity.getDateOfBirth().getTime()));
        builder.field("dateOfEmployment", new Date(employeeEntity.getDateOfEmployment().getTime()));
        builder.field("address", employeeEntity.getAddress());
        builder.field("title", employeeEntity.getTitle());
        builder.field("vocation", employeeEntity.getVocation());
        builder.endObject();
        builder.endObject();
        return builder;
    }
}
