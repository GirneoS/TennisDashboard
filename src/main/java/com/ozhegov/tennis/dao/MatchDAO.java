package com.ozhegov.tennis.dao;

import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.model.MatchDTO;
import com.ozhegov.tennis.model.Player;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MatchDAO {
    public List<MatchDTO> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<MatchDTO> cq = cb.createQuery(MatchDTO.class);

        Root<MatchDTO> root = cq.from(MatchDTO.class);
        cq.select(root);


        Query<MatchDTO> query = session.createQuery(cq);
        List<MatchDTO> list = query.getResultList();

        for (MatchDTO match: list){
            System.out.println(match.getWinner().getName());
        }

        session.close();

        return list;

    }
    public void create(MatchDTO dto){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(dto);

        session.getTransaction().commit();
        session.close();
    }
    public List<MatchDTO> getByPlayerName(String name){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {


            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MatchDTO> cq = cb.createQuery(MatchDTO.class);

            Root<MatchDTO> matchTable = cq.from(MatchDTO.class);
            Join<MatchDTO, Player> playerJoin1 = matchTable.join("player1", JoinType.INNER);
            Join<MatchDTO, Player> playerJoin2 = matchTable.join("player2", JoinType.INNER);

            Predicate player1Predicate = cb.equal(playerJoin1.get("name"), name);
            Predicate player2Predicate = cb.equal(playerJoin2.get("name"), name);

            cq.select(matchTable).where(cb.or(player1Predicate, player2Predicate));

            TypedQuery<MatchDTO> query = session.createQuery(cq);

            return query.getResultList();
        }
    }
}
