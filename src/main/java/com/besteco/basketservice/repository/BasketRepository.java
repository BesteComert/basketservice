package com.besteco.basketservice.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;
import com.besteco.basketservice.entity.Basket;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends CouchbaseRepository<Basket, String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND userId = $1")
    public List<Basket> findByUserId(Long userId);
}
