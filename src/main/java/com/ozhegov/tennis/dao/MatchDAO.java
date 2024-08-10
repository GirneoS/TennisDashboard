package com.ozhegov.tennis.dao;

import com.ozhegov.tennis.dto.MatchDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MatchDAO {
    public List<MatchDTO> getAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MatchDTO> cq = cb.createQuery(MatchDTO.class);

            Root<MatchDTO> root = cq.from(MatchDTO.class);
            cq.select(root);


            Query<MatchDTO> query = session.createQuery(cq);

            List<MatchDTO> list = query.getResultList();
            return list;
        }
    }
}
